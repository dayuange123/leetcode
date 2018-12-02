package cn.xupt.myweighgraph;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;
import java.util.Queue;

public class LazyPrimMST {
    private boolean[] marked;        //最小生成树的顶点
    private Queue<Edge> mst;        //最小生成树的边
    private PriorityQueue<Edge> pq;     //横切边(包括失效的边)的队列

    public LazyPrimMST(EdgeWeightedGraph G) {
        pq = new PriorityQueue<Edge>();
        marked = new boolean[G.V()];
        mst = new ArrayDeque<>();
        visit(G, 0);//
        while (!pq.isEmpty()) {
            Edge e = pq.poll();//取得权重最小的边。
            int v = e.either(), w = e.other(v);
            if (marked[v] && marked[w]) continue;//跳过失效的边
            mst.add(e);//将边加入到队列中
            if (!marked[v]) visit(G, v);
            if (!marked[w]) visit(G, w);

        }
    }

    private void visit(EdgeWeightedGraph g, int v) {
        marked[v] = true;
        g.adj(v).forEach(edge -> {
            if (!marked[edge.other(v)]) pq.add(edge);//如果没有遍历过,加入优先队列
        });
    }

    //返回最小生成树中所有的边
    public Iterable<Edge> edges() {
        return mst;
    }

    public double weight() {

        return 0;
    }
}