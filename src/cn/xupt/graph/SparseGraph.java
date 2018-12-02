package cn.xupt.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * 邻接表实现稀疏图
 * 存在平行边问题，比如两个路径之间有很多边,z这里使用list集合的contain避免了
 */
public class SparseGraph extends Graph {


    private List<Integer>[] adj;//邻接表

    public SparseGraph() {
    }

    public SparseGraph(int v, boolean directed) {

        this.setV(v);
        this.setDirected(directed);
        adj = (List<Integer>[]) new List[v];
        for (int i = 0; i < v; i++)
            adj[i] = new ArrayList<>();
    }

    public void addEdge(int v, int w) {
        assert (v >= 0 && v < this.V());
        assert (w >= 0 && w < this.V());

        if (!adj[v].contains(w))
            adj[v].add(w);
        if (v != w && !this.isDirected() && !adj[w].contains(v))
            adj[w].add(v);
        int E = this.E();
        this.setE(E++);
    }

    //判断v和w是否连接
    public boolean hasEdge(int v, int w) {
        assert (v >= 0 && v < this.V());
        assert (w >= 0 && w < this.V());
        for (int i = 0; i < adj[v].size(); i++) {
            if (adj[v].get(i) == w)
                return true;
        }
        return false;
    }

    public Iterable<Integer> iterable(int v) {
        return adj[v];
    }

    public void print() {
        for (int i = 0; i < this.V(); i++) {
            System.out.println(adj[i]);
        }
    }
}