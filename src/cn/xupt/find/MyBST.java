package cn.xupt.find;

import java.util.ArrayDeque;
import java.util.Queue;

public class MyBST<K extends Comparable<K>, V> {
    Node<K, V> node;

    class Node<K, V> {
        public K key;
        public V value;
        public Node<K, V> left;
        public Node<K, V> right;
        public int N; //以该结点为根的子树中的结点总数

        public Node(K key, V value, int N) {
            this.key = key;
            this.value = value;
            this.left = this.right = null;
            this.N = N;
        }
    }

    public boolean isEmpty() {
        return size(node) == 0;
    }

    public int size() {
        return size(node);
    }

    private int size(Node<K, V> node) {
        if (node == null) return 0;
        else return node.N;
    }

    public boolean contain(K key) {
        return contain(node, key);
    }

    private boolean contain(Node<K, V> node, K key) {
        if (node == null)
            return false;
        if (key.compareTo(node.key) == 0)
            return true;
        else if (key.compareTo(node.key) < 0)
            return contain(node.left, key);
        else return contain(node.right, key);
    }

    //查找key所在的value
    public V search(K key) {
        return search(node, key);
    }

    private V search(Node<K, V> node, K key) {
        if (node == null) return null;
        if (key.compareTo(node.key) == 0)
            return node.value;
        else if (key.compareTo(node.key) < 0)
            return search(node.left, key);
        else
            return search(node.right, key);
    }

    public void insert(K key, V value) {
        if (key == null || value == null)
            throw new RuntimeException("插入空");
        node = insert(node, key, value);
    }

    //插入，如果本节点为空，则初始化并返回，如果等于本节点的key，就进行覆盖
    //如果小于 则往left进行递归，大于 往右边递归，
    private Node<K, V> insert(Node<K, V> node, K key, V value) {
        if (node == null)
            return new Node(key, value, 1);
        if (key.compareTo(node.key) == 0) {
            node.value = value;
        } else if (key.compareTo(node.key) > 0) {
            node.right = insert(node.right, key, value);
        } else
            node.left = insert(node.left, key, value);
        //确保插入之后 对影响到的每个结点的N进行更新操作
        node.N = size(node.left) + size(node.right) + 1;
        return node;
    }

    //前序遍历二叉搜索树
    public void preOrder(Node<K, V> node) {
        if (node != null) {
            System.out.println(node.key + ":" + node.value);
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    //中序遍历
    public void inOrder(Node<K, V> node) {
        if (node != null) {
            inOrder(node.left);
            System.out.println(node.key + ":" + node.value);
            inOrder(node.right);
        }
    }

    //后序遍历
    public void arferOrder(Node<K, V> node) {
        if (node != null) {
            inOrder(node.left);
            inOrder(node.right);
            System.out.println(node.key + ":" + node.value);
        }
    }

    /**
     * 层序遍历二叉搜索树
     */
    public void levelOrder() {
        Queue<Node<K, V>> queue = new ArrayDeque<>();
        queue.add(this.node);
        while (!queue.isEmpty()) {
            System.out.println(node.key + ":" + node.value);
            Node<K, V> n = queue.remove();
            System.out.println(n.key + ":" + n.value);
            if (n.left != null)
                queue.add(n.left);
            if (n.right != null)
                queue.add(n.right);
        }
    }

    public K minnum() {
        return minnum(this.node).key;
    }

    public K maxnum() {
        return maxnum(this.node).key;
    }

    private Node<K, V> minnum(Node node) {
        if (node.left == null)
            return node;
        return minnum(node.left);
    }

    private Node<K, V> maxnum(Node<K, V> node) {
        if (node == null)
            return node;
        return maxnum(node.right);

    }

    public void removeMax() {
        node = removeMax(node);
    }

    private Node<K, V> removeMax(Node<K, V> node) {
        if (node.right == null) {
            return node.left;
        }
        node.right = removeMin(node.right);
        node.N = size(node.left) + size(node.right) + 1;
        return node;
    }

    public void removeMin() {
        node = removeMin(node);
    }

    private Node<K, V> removeMin(Node<K, V> node) {
        if (node.left == null) {
            //提取出node.right返回 这里不用判断是否为null
            return node.right;
        }
        //一直去寻找左结点
        node.left = removeMin(node.left);
        //更新结点数
        node.N = size(node.left) + size(node.right) + 1;
        return node;
    }

    /**
     * @param key 传入用户想要移出的结点的key
     *            删除左右都有孩子的节点 :选择将右子树的最小的 即其的后继节点，放在其位置上
     */
    public void remove(K key) {
        node = remove(node, key);
    }

    private Node<K, V> remove(Node<K, V> node, K key) {
        if (node == null) return null;
        if (key.compareTo(node.key) < 0) {
            node.left = remove(node.left, key);

        } else if (key.compareTo(node.key) > 0) {
            node.right = remove(node.right, key);

        } else {
            //key==node.key
            //要删除的节点的左子树为空
            if (node.left == null) return node.right;
            //要删除的节点的右子树为空
            if (node.right == null) return node.left;
            Node<K, V> t = node;
            //右子树最小的结点取出来
            node = minnum(t.right);
            node.left = t.left;

            node.right = removeMin(t.right);
            //返回让上一个父节点指向它。
        }
        node.N = size(node.left) + size(node.right) + 1;
        return node;
    }

    /**
     * @param key
     * @return比它小的所有节点中最大的一个
     */
    public K floor(K key) {
        Node<K, V> x = floor(node, key);
        if (x == null) return null;
        return x.key;
    }

    private Node floor(Node<K, V> node, K key) {
        //递归寻找小于或等于它的结点，如果为空，表示没有找到，直接返回null
        if (node == null) return null;
        //等于的话 返回该节点
        if (node.key.compareTo(key) == 0)
            return node;
        //小于该结点的key 从左子树寻找。
        if (node.key.compareTo(key) > 0) {
            return floor(node.left, key);
        }
        //大于该节点的key
        Node t = floor(node.right, key);
        //如果右子树没有找到小于等于的，返回该结点。因为该节点就是想要的结点。
        if (t != null) return t;
            //找到了的话 直接返回
        else return node;
    }

    /**
     * @param key 用户传入的key
     * @return比它大的所有结点中的最小的那个
     */
    public K ceiling(K key) {
        Node<K, V> x = ceiling(node, key);
        if (x == null) return null;
        return x.key;
    }

    private Node<K, V> ceiling(Node<K, V> node, K key) {
        if (node == null) return null;
        if (node.key.compareTo(key) == 0)
            return node;
        //大于该结点的key 肯定就不在左子树 ，可能在右子树或者没有
        if (node.key.compareTo(key) < 0)
            return ceiling(node.right, key);
        //小于node的key 去左子树 ，如果左子树没有找到大于等于它的 就返回该结点！
        Node<K, V> ceiling = ceiling(node.left, key);
        if (ceiling == null)
            return node;
        return ceiling;
    }

    /**
     * @param n
     * @return 排名为n的结点
     */
    public K select(int n) {
        return select(node, n).key;
    }

    private Node<K, V> select(Node<K, V> node, int n) {
        if (node == null) return null;
        int size = size(node.left);
        if (size > n) return select(node.left, n);
        else if (size < n) return select(node.right, n - size - 1);
        else return node;
    }

    public int rank(K key) {
        return rank(key, node);
    }

    private int rank(K key, Node<K, V> node) {
        if (node == null) return 0;
        int temp = key.compareTo(node.key);
        if (temp < 0) return rank(key, node.left);
            //这里的1代表该节点
        else if (temp > 0) return rank(key, node.left) + size(node.left) + 1;
            //相等返回比它小的所有 即左子树的所有节点数
        else return size(node.left);
    }

    //范围查找函数  返回所有的结点
    public Iterable<K> keys() {
        return keys(minnum(), maxnum());
    }

    private Iterable<K> keys(K lo, K hi) {
        Queue<K> queue = new ArrayDeque<>();
        keys(node, queue, lo, hi);
        return queue;
    }

    private void keys(Node<K, V> node, Queue<K> queue, K lo, K hi) {
        if (node == null) return;
        int tmplo = lo.compareTo(node.key);
        int tmphi = hi.compareTo(node.key);
        //加入判断 如果lo比该节点还小 就继续搜索其左子树。
        if (tmplo < 0)
            keys(node.left, queue, lo, hi);
        //把比左界限大的 右界限都加入到队列中
        if (tmplo <= 0 && tmphi >= 0) queue.add(node.key);
        //加入判断 如果hi比该节点还大 就继续搜索其右子树。
        if (tmphi > 0)
            keys(node.right, queue, lo, hi);
    }

    public static void main(String[] args) {

    }
}