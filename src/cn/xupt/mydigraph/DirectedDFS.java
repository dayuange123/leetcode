package cn.xupt.mydigraph;

/**
 * 有向图的可达性，。
 */
public class DirectedDFS {
    private boolean[] marked;

    public DirectedDFS(Digraph G, int s) {
        marked = new boolean[G.V()];
        dfs(G, s);
    }

    public DirectedDFS(Digraph G, Iterable<Integer> sources) {
        marked = new boolean[G.V()];
        sources.forEach(s -> {
            if (!marked[s])
                dfs(G, s);
        });
    }

    private void dfs(Digraph G, int v) {
        marked[v] = true;
        G.adj(v).forEach(w -> {
            if (!marked[w]) dfs(G, w);
        });
    }
    public boolean marked(int v){
        return marked[v];
    }

    public static void main(String[] args) {

    }
}