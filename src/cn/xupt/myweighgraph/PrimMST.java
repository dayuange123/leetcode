package cn.xupt.myweighgraph;

import java.util.ArrayList;
import java.util.List;

/**
 * 优化的Prim算法
 */
public class PrimMST {
    private Edge[] edgeTo;  //记录到该结点的边 整个数组也就是最小生成树的所有边
    private double[] distTo; //distTo[w]=edge[w].weight()
    private boolean[] marked;
    private IndexMinPQ<Double> pq;  //有效的横切边

    public PrimMST(EdgeWeightedGraph G) {
        edgeTo = new Edge[G.V()];
        distTo = new double[G.V()];
        marked = new boolean[G.V()];
        for (int i = 0; i < G.V(); i++) {
            distTo[i] = Double.POSITIVE_INFINITY;
        }
        pq = new IndexMinPQ<>(G.V());
        distTo[0] = 0.0;
        pq.insert(0, 0.0);//用顶点0和权重0初始化pq
        while (!pq.isEmpty()) {
            //每次获取最小权值 对应的顶点 进行访问，
            visit(G, pq.delMin());
        }
    }

    private void visit(EdgeWeightedGraph g, int v) {
        //标记v
        marked[v] = true;
        //遍历v的所有邻边
        for (Edge edge : g.adj(v)) {
            //获取到邻边的结点索引。
            int w = edge.other(v);
            //如果再树中 跳过。
            if (marked[w]) continue;
            //如果w索引对应的权值小于v到w的权值，那么进行更新
            if (edge.weight() < distTo[w]) {
                edgeTo[w] = edge;
                distTo[w] = edge.weight();
                //如果w已经包含再队列中 更新w 和其对应的最小权值。
                if (pq.contains(w)) pq.changeKey(w, distTo[w]);
                //如果不在 加入到队列中，
                else pq.insert(w, distTo[w]);
            }
        }
    }

    public Iterable<Edge> edges() {
        List<Edge> mst = new ArrayList<>();
        for (int i = 0; i < edgeTo.length; i++) {
            mst.add(edgeTo[i]);

        }
        return mst;
    }
}