package cn.xupt.find;

import java.util.ArrayDeque;
import java.util.Queue;

public class TriedST<Value> {


    private static int R = 256;
    private Node root;//根节点


    private static class Node {
        private Object val;
        private Node[] next = new Node[R];
        private int N;
    }

    //插入
    public void put(String key, Value value) {
        assert (key != null && value != null);
        root = put(root, key, value, 0);
    }

    private Node put(Node x, String key, Value value, int d) {
        if (x == null) x = new Node();
        if (d == key.length()) {
            if (x.val == null)
                x.N++;
            x.val = value;
            return x;
        }
        //获取key的d所对应的字符
        char c = key.charAt(d);
        //
        int in = 0;
        if (x.next[c] != null)
            in = x.next[c].N;
        x.next[c] = put(x.next[c], key, value, d + 1);
        x.N = x.N - in + x.next[c].N;
        return x;
    }

    public Value get(String key) {
        if (key == null)
            throw new NullPointerException();
        Node x = get(root, key, 0);
        return x == null ? null : (Value) x.val;
    }

    private Node get(Node x, String key, int d) {

        if (x == null) return null;
        if (d == key.length()) return x;
        char c = key.charAt(d);
        return get(x.next[c], key, d + 1);
    }

    public Iterable<String> keys() {
        return keysWithPrefix("");
    }

    private Iterable<String> keysWithPrefix(String pre) {
        Queue<String> queue = new ArrayDeque<>();
        collcet(get(root, pre, 0), pre, queue);
        return queue;
    }

    private void collcet(Node x, String pre, Queue<String> queue) {
        if (x == null) return;
        if (x.val != null) queue.add(pre);
        for (char c = 0; c < R; c++)
            collcet(x.next[c], pre + c, queue);
    }


    //通过通配符(.)匹配
    public Iterable<String> keysThatMatch(String pat) {
        Queue<String> queue = new ArrayDeque<>();
        collcet(root, "", pat, queue);
        return queue;
    }

    private void collcet(Node x, String pre, String pat, Queue<String> queue) {
        if (x == null) return;
        int d = pre.length();
        if (d == pat.length() && x.val != null) {
            queue.add(pre);
        }
        //长度超过了模式串，直接返回
        if (d == pat.length()) return;
        char next = pat.charAt(d);
        for (char c = 0; c < R; c++)
            if (next == '.' || next == c)
                collcet(x.next[c], pre + c, pat, queue);
    }

    public void delete(String key) {
        root = delete(root, key, 0);
    }

    private Node delete(Node x, String key, int d) {
        if (x == null) return null;
        if (d == key.length()) {
            if (x.val != null) {
                //代表删除了
                x.val = null;
                //维护该节点以及所有子节点对应的key
                x.N--;
            }
        } else {
            char c = key.charAt(d);
            int in = 0;
            if (x.next[c] != null)
                in = x.next[c].N;
            x.next[c] = delete(x.next[c], key, d + 1);
            //这里主要就是因为假如不存在要删除的键，那x.N就不需要改变，但是如果删除了就需要--
            //所以我们通过in来完成
            if (in != 0 && x.next[c] != null)
                x.N = x.N + x.next[c].N - in;
            //删除之后返回的可能为空
            if (in != 0 && x.next[c] == null)
                x.N--;
        }

        if (x.val != null) return x;
        for (char c = 0; c < R; c++)
            if (x.next[c] != null) return x;
        return null;
    }

    public boolean contains(String key) {

        return get(key) == null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public int size() {
        return root == null ? 0 : root.N;
    }

    public static void main(String[] args) {
        TriedST<String> triedST = new TriedST();


    }
}