package cn.xupt.unionfind;

/**
 * 基于UnionFind2的优化 ，rank优化
 * 根据层数
 * rank[i] 表示以i为根的结合树的层数
 * 加上路径压缩的优化
 */
public class UnionFind3 {

    private int[] parent;
    private int count;
    private int[] rank;

    public UnionFind3(int count) {
        this.count = count;
        parent = new int[count];
        for (int i = 0; i < count; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }

    //返回根 通过这个判断两个结点是否链接，即就是是否拥有相同的根
    public int find(int p) {
        assert (p >= 0 && p < count);
        while (p != parent[p]) {
            //这么一句就是对路径的压缩
            parent[p] = parent[parent[p]];
            p = parent[p];
        }
        return p;
        //递归实现，最后都指向了父节点，也就是整个路径的元素直接指向root，
//        if (p != parent[p])
//            parent[p] = find(parent[p]);
//        return parent[p];
    }

    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    public void unionEle(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot)
            return;
        //proot的链比较短，将pRoot连到qRoot 如果连上其层数并不会改变 ，除非两个根节点相等，那么层数会加1
        if (rank[pRoot] < rank[qRoot]) {
            parent[pRoot] = qRoot;
        } else if (rank[qRoot] < rank[pRoot]) {
            parent[qRoot] = pRoot;
        } else {
            parent[pRoot] = qRoot;
            rank[qRoot] += 1;
        }
    }
}