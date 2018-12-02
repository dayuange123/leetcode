package cn.xupt.mydigraph;

import java.util.Stack;

public class DirectedCycle {
    private boolean[] marked;
    private int[] edgeTo;
    private Stack<Integer> cycle;//有向环中所有顶点
    private boolean[] onStack;//递归调用的栈上的所有顶点

    public DirectedCycle(Digraph G) {
        onStack = new boolean[G.V()];
        edgeTo = new int[G.V()];
        marked = new boolean[G.V()];
        for (int i = 0; i < G.V(); i++) {
            if (!marked[i])
                dfs(G, i);
        }
    }

    private void dfs(Digraph g, int v) {
        onStack[v] = true;//标记
        marked[v] = true;
        //遍历和v连接的点
        g.adj(v).forEach(w -> {
            //如果找到了一个环 直接结束，
            if (this.hasCycle()) {
                return;
            }
            //w没有递归过的话，把w的前驱点记录到edgeTo数组中，然后dfs  w
            else if (!marked[w]) {
                edgeTo[w] = v;
                dfs(g, w);
            }
            //如果w遍历过，经过递归又一次走到了w 那么初始化Stack 并且将环的路径记录到栈中，
            else if (onStack[w]) {
                cycle = new Stack<>();
                //这里先将x指向到达w的结点v 然后依次将v的前驱入栈，直到碰到第一次入栈的w结束循环(此时w没在栈中)
                // 最后将w,v入栈，此时整个环的所有结点都存入栈中，v两次入栈形成环，
                for (int x = v; x != w; x = edgeTo[x])
                    cycle.push(x);
                cycle.push(w);
                cycle.push(v);
            }
        });
        onStack[v] = false;
    }

    public boolean hasCycle() {
        return cycle != null;
    }

    public Iterable<Integer> cycleIterable() {
        return cycle;
    }

    public static void main(String[] args) {
        Digraph d = new Digraph(5);
        d.addEdge(0, 2);
        d.addEdge(0, 1);
        d.addEdge(0, 3);
        d.addEdge(2, 4);
        d.addEdge(4, 0);
        DirectedCycle directedCycle = new DirectedCycle(d);
        while (!directedCycle.cycle.isEmpty()) {
            System.out.println(directedCycle.cycle.pop());
        }
    }
}