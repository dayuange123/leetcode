package cn.xupt.unionfind;

/**
 * 初始优化  sz[i] 代表的是以i为根的结点
 */
public class UnionFind2 {
    private int[] parent;
    private int count;
    private int[] sz;

    public UnionFind2(int count) {
        this.count = count;
        parent = new int[count];
        for (int i = 0; i < count; i++) {
            parent[i] = i;
            sz[i] = 1;
        }
    }

    //返回根 通过这个判断两个结点是否链接，即就是是否拥有相同的根
    public int find(int p) {
        assert (p >= 0 && p < count);
        while (p != parent[p])
            p = parent[p];
        return p;
    }

    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    //
    public void unionEle(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot)
            return;
        //proot的元素少，将pRoot连到qRoot
        if (sz[pRoot] < sz[qRoot]) {
            parent[pRoot] = qRoot;
            sz[qRoot] += sz[pRoot];
        } else {
            parent[qRoot] = pRoot;
            sz[pRoot] += sz[qRoot];
        }
    }
}