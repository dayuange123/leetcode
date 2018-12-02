package cn.xupt.mydigraph;

/**
 * 拓扑排序
 */
public class Topological {
    private Iterable<Integer> order;//顶点的拓扑顺序

    public Topological(Digraph G) {
        DirectedCycle cycle = new DirectedCycle(G);
        if (!cycle.hasCycle()) {
            DepthFirstOrder dfsOrder = new DepthFirstOrder(G);
            order = dfsOrder.reversePost();
        }
    }
    public Iterable<Integer> order(){
        return order;
    }
    public boolean isDAG(){
        return order!=null;
    }

}