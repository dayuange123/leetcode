package cn.xupt.mydigraph;

import java.util.ArrayList;
import java.util.List;

/**
 * 采用临界表实现的有向图
 */
public class Digraph {
    private final int V;
    private int E;
    private List<Integer>[] adj;

    public Digraph(int v) {
        V = v;
        this.E = 0;
        adj = (List<Integer>[]) new List[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new ArrayList<>();
        }
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public void addEdge(int v, int w) {
        adj[v].add(w);
        E++;
    }

    public Iterable<Integer> adj(int v) {
        return adj[v];
    }
    //有向图的取反
    public Digraph reserve() {
        Digraph d = new Digraph(V);
        for (int v = 0; v < V; v++) {
            for (int w : adj[v])
                d.addEdge(w, v);
        }
        return d;
    }
}