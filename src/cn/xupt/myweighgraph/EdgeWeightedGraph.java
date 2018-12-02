package cn.xupt.myweighgraph;

import java.util.ArrayList;
import java.util.List;

/**
 * 加权无向图的数据类型。
 */
public class EdgeWeightedGraph {
    private final int V; //定点总数
    private int E; //边总数
    private List<Edge>[] adj;//邻接表

    public EdgeWeightedGraph(int v) {
        V = v;
        this.E = 0;
        adj = (List<Edge>[]) new List[V];
        for (int i = 0; i < adj.length; i++) {
            adj[i] = new ArrayList<Edge>();
        }
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public void addEdge(Edge e) {
        int v = e.either(), w = e.other(v);
        adj[v].add(e);
        adj[w].add(e);
        E++;
    }

    public Iterable<Edge> adj(int v) {
        return adj[v];
    }

    public Iterable<Edge> edges() {
        List<Edge> list = new ArrayList<>();
        for (int i = 0; i < adj.length; i++) {
            List<Edge> edges = adj[i];
            edges.forEach(edge -> {
                list.add(edge);
            });
        }
        return list;
    }
}