package cn.xupt.unionfind;

/**
 * 普通的实现。
 */
public class UnionFind1 {
    int[] id;
    int count;

    public UnionFind1(int n) {
        count = n;
        id = new int[n];
        for (int i = 0; i < n; i++) {
            id[i] = i;
        }
    }

    public int find(int p) {
        assert (p >= 0 && p < count);
        return id[p];
    }

    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    //合并
    public void unionEle(int p, int q) {
        int pid = find(p);
        int qid = find(q);
        if (pid == qid)
            return;
        for (int i = 0; i < count; i++)
            if (id[i] == pid)
                id[i] = qid;
    }
}