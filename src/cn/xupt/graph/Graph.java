package cn.xupt.graph;

public abstract class Graph {
    private int V; //结点数
    private int E;  //边数
    private boolean directed; //是否为有向图

    abstract public void addEdge(int v, int w);

    abstract public boolean hasEdge(int v, int w);

    abstract public Iterable<Integer> iterable(int v);

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public void setV(int v) {
        V = v;
    }

    public void setE(int e) {
        E = e;
    }

    public boolean isDirected() {
        return directed;
    }

    public void setDirected(boolean directed) {
        this.directed = directed;
    }
}