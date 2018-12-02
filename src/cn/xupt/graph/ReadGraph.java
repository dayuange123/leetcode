package cn.xupt.graph;


import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Constructor;
import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 封装了所有图的算法
 * 构造方法 用户传入指定的实现类的类型 来构造图
 */
public class ReadGraph {
    Graph graph;
    private boolean[] visited; //记录是否被遍历过
    private int[] from; //记录遍历的前驱索引
    private int[] ord;//记录到指定点的最短距离

    //此构造方法用来初始化图  第一个参数为图的实现类型，比如邻接矩阵胡或者连接表实现的，第二个参数为传入数据的文件路径。第三个为是否为有向图
    public ReadGraph(Class<? extends Graph> graph, final String filename, boolean directed) throws Exception {

        FileReader in = new FileReader(new File(filename));
        char[] char1 = new char[100];
        in.read(char1);
        String s = String.valueOf(char1);
        String[] contents = s.split(" ");
        Constructor<? extends Graph> constructor = graph.getConstructor(int.class, boolean.class);
        this.graph = constructor.newInstance(Integer.valueOf(contents[0]), directed);
        int k = 2;
        for (int i = 0; i < Integer.valueOf(contents[1]); i++) {
            int v = Integer.valueOf(contents[k]);
            int w = Integer.valueOf(contents[k + 1]);
            k += 2;
            this.graph.addEdge(v, w);
        }
        visited = new boolean[this.graph.V()];
        from = new int[this.graph.V()];
        ord = new int[this.graph.V()];
    }

    /**
     * 通过dfs遍历
     *
     * @return 连通分量个数
     */
    public int DeepTraGraph() {
        //初始化标记数组
        initSign();
        int count = 0;//连通分量个数
        for (int i = 0; i < graph.V(); i++)
            if (!visited[i]) {
                dfs(i);
                count++;
            }
        return count;
    }

    private void dfs(int i) {
        visited[i] = true;
        System.out.println("遍历了" + i);
        Iterable<Integer> iterable = graph.iterable(i);
        iterable.forEach(integer -> {
            //    System.out.println(integer);
            if (!visited[integer]) {
                //from[integer] =i 表示integer 是从i来遍历的
                from[integer] = i;
                dfs(integer);
            }

        });
    }

    //初始化相应的标记数组
    private void initSign() {
        for (int i = 0; i < this.graph.V(); i++) {
            visited[i] = false;
            from[i] = -1;
            ord[i] = -1;
        }
    }

    //采用dfs 通过s进行遍历。并更新from和visted数组
    private void path(int s) {
        initSign();
        dfs(s);
    }

    //判断s和w是否连通  采用dfs
    public boolean hasPath(int s, int w) {
        path(s);
        return visited[w];
    }

    //输出从s到w的路径  这个求出来的不是最短路径 它与构建图的时候路径相连顺序有关！
    public void getPathBydfs(int s, int w) {
        if (!hasPath(s, w))
            System.out.println("不存在此路径!");
        getPath(s, w);
    }


    public int getShortRoot(int s, int w) {
        bfs(s);
        return ord[w];
    }

    //最短路径
    public void getPathBybfs(int s, int w) {
        bfs(s);
        if (!visited[w]) {
            System.out.println("不存在此路径!");
        }
        getPath(s, w);
    }

    private void getPath(int s, int w) {
        int root[] = new int[graph.V()];
        int i = 0;
        while (from[w] != -1) {
            root[i++] = w;
            w = from[w];
        }
        //因为from[s]=-1;所以s没有记录到root数组中。只是将后面路径的点记录到数组中了。
        System.out.print(s);
        for (int j = i - 1; j >= 0; j--)
            System.out.print("->" + root[j]);
        System.out.println();
    }

    //求最短路径  bfs
    private void bfs(int s) {
        //重新初始化标记数组。
        initSign();
        assert (s >= 0 && s < graph.V());
        Queue<Integer> queue = new ArrayDeque<>();
        //无向图最短路径算法
        queue.add(s);
        visited[s] = true;
        ord[s] = 0;
        while (!queue.isEmpty()) {
            int v = queue.remove();
            Iterable<Integer> vit = graph.iterable(v);
            vit.forEach(integerValue -> {
                //没有访问过，放入队列，并更新标记数组
                if (!visited[integerValue]) {
                    queue.add(integerValue);
                    visited[integerValue] = true;
                    from[integerValue] = v;
                    ord[integerValue] = ord[v] + 1;
                }
            });
        }
    }
}