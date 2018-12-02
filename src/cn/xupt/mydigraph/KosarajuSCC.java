package cn.xupt.mydigraph;

import java.util.Stack;

/**
 * 计算强连通分量的Kosaraju算法
 */
public class KosarajuSCC {
    private boolean[] marked;
    private int[] id;
    private int count;

    public KosarajuSCC(Digraph G) {
        marked = new boolean[G.V()];
        id = new int[G.V()];
        //求逆后续排列
        DepthFirstOrder order = new DepthFirstOrder(G.reserve());
        Stack<Integer> stack = (Stack<Integer>) order.reversePost();
        while (!stack.isEmpty()) {
            int v = stack.pop();
            if (!marked[v]) {
                dfs(G, v);
                count++;
            }
        }

    }

    private void dfs(Digraph g, Integer v) {
        marked[v] = true;
        id[v] = count;
        g.adj(v).forEach(w -> {
            if (!marked[w])
                dfs(g, w);
        });
    }

    public boolean strongConnected(int v, int w) {
        return id[v] == id[w];
    }

    public int id(int v) {
        return id[v];
    }

    public int count() {
        return count;
    }

    public static void main(String[] args) {
        Digraph d = new Digraph(4);
        d.addEdge(0, 2);
        d.addEdge(2, 1);
        d.addEdge(1, 0);
        d.addEdge(3, 1);
        DepthFirstOrder order = new DepthFirstOrder(d);

        KosarajuSCC kosarajuSCC = new KosarajuSCC(d);
        System.out.println(kosarajuSCC.strongConnected(1, 3));
//        while (!((Stack) order.reversePost()).empty()) {
//            System.out.println(((Stack) order.reversePost()).pop());
//        }
//        ((Stack) order.reversePost()).forEach(s -> {
//            System.out.println(s);
//        });
    }
}