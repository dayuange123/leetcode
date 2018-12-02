package cn.xupt.graph;


import java.util.ArrayList;
import java.util.List;

/**
 * 无向图采用邻接矩阵实现。
 */
public class DenseGraph extends Graph {

    boolean[][] g;

    public DenseGraph(int v, boolean directed) {
        this.setV(v);

        this.setDirected(directed);
        g = new boolean[v][v];
        for (int i = 0; i < v; i++)
            for (int j = 0; j < v; j++)
                g[i][j] = false;
    }

    //连接 v w
    public void addEdge(int v, int w) {
        assert (v >= 0 && v < this.V());
        assert (w >= 0 && w < this.V());
        g[v][w] = true;
        if (!this.isDirected())
            g[w][v] = true;
        int E = this.E();
        this.setE(E++);
    }

    public boolean hasEdge(int v, int w) {
        assert (v >= 0 && v < this.V());
        assert (w >= 0 && w < this.V());
        return g[v][w];
    }

    public Iterable<Integer> iterable(int v) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < this.V(); i++) {
            if (g[v][i] == true)
                list.add(i);
        }
        return list;
    }

    public void print() {
        for (int i = 0; i < this.V(); i++) {
            System.out.println(g[i]);
        }
    }
}