package cn.xupt.myweighgraph;

/**
 * 带权重边的数据类型。
 */
public class Edge implements Comparable<Edge> {

    private final int v;
    private final int w;
    private final double weight;

    public Edge(int v, int w, double weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public double weight() {
        return weight;
    }

    public int either() {
        return v;
    }

    public int other(int vertex) {
        if (vertex == v) return w;
        if (vertex == w) return v;
        throw new RuntimeException("Error edge");
    }

    @Override
    public int compareTo(Edge that) {
        if (this.weight() < that.weight()) return -1;
        else if (this.weight() > that.weight()) return 1;
        else
            return 0;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "v=" + v +
                ", w=" + w +
                ", weight=" + weight +
                '}';
    }
}