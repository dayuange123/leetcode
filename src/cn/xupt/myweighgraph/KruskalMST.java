package cn.xupt.myweighgraph;

import cn.xupt.unionfind.UnionFind3;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 最小生成树的算法
 */
public class KruskalMST {
    private Queue<Edge> mst;

    public KruskalMST(EdgeWeightedGraph G) {
        int i = 0;
        mst = new ArrayDeque<>();
        IndexMinPQ<Edge> pq = new IndexMinPQ<>(G.V());
        //将所有的边放入堆中
        for (Edge edge : G.edges()) {
            pq.insert(i++, edge);
        }
        UnionFind3 uf = new UnionFind3(G.V());
        while (!pq.isEmpty() && mst.size() < G.V() - 1) {
            Edge edge = pq.minKey();
            int v = edge.either();
            int w = edge.other(v);
            //判断是否连接
            if (uf.isConnected(v, w)) {
                continue;
            }
            //将其连接起来
            uf.unionEle(v, w);
            mst.add(edge);
        }
    }

    public Iterable<Edge> edges() {
        return mst;
    }
}