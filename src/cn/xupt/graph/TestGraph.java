package cn.xupt.graph;

public class TestGraph {
    public static void main(String[] args) throws Exception {
        String filename = "cn/xupt/graph/TestFile";
        ReadGraph readGraph = new ReadGraph(SparseGraph.class, filename, false);
        //  ((SparseGraph) readGraph.graph).print();
        // System.out.println("连通分量个数" + readGraph.DeepTraGraph());

        readGraph.getPathBydfs(0, 1);

        readGraph.getPathBybfs(0, 1);
        System.out.println(readGraph.getShortRoot(0,1));
    }
}