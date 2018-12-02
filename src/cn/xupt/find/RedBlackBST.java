package cn.xupt.find;



public class RedBlackBST<Key extends Comparable<Key>, Value> {

    private static final boolean RED = true;
    private static final boolean Black = false;
    private Node root;

    private class Node {
        Key key;
        Value value;
        Node left, right;
        int N;//这颗子树的结点总数
        boolean color;// 代表指向该节点的连接颜色 true为红色 false 为黑色

        public Node(Key key, Value value, int n, boolean color) {
            this.key = key;
            this.value = value;
            N = n;
            this.color = color;
        }
    }

    //判断红黑连接
    private boolean isRed(Node x) {
        if (x == null) return false;
        return x.color == RED;
    }

    Node rotateLeft(Node h) {
        //提取父节点的右当 目前的根节点
        Node x = h.right;
        //父节点右指向x左
        h.right = x.left;
        x.left = h;
        //更新color
        x.color = h.color;
        h.color = RED;
        x.N = h.N;
        h.N = 1 + size(h.left) + size(h.right);
        return x;
    }

    Node rotateRight(Node h) {
        //提取父节点的右当 目前的根节点
        Node x = h.left;
        //父节点右指向x左
        h.left = x.right;
        x.right = h;
        //更新color
        x.color = h.color;
        h.color = RED;
        x.N = h.N;
        h.N = 1 + size(h.left) + size(h.right);
        return x;
    }

    public int size() {
        return size(root);
    }

    public boolean isEmpty() {
        return root == null;
    }

    private int size(Node x) {
        if (x == null)
            throw new NullPointerException("a null Object");
        return x.N;
    }

    private void changeColors(Node h) {
        h.color = RED;
        h.left.color = Black;
        h.right.color = Black;
    }

    private void flipColors(Node h) {
        h.color = !h.color;
        h.left.color = !h.left.color;
        h.right.color = !h.right.color;
    }

    //红黑树的插入操作
    public void put(Key key, Value value) {
        root = put(root, key, value);
        root.color = Black;
    }

    private Node put(Node h, Key key, Value value) {
        if (h == null)
            //插入 和父节点用红连接相连
            return new Node(key, value, 1, RED);
        int cmp = key.compareTo(h.key);
        //比h的key小
        if (cmp < 0) h.left = put(h.left, key, value);
        else if (cmp > 0) h.right = put(h.right, key, value);
        else h.value = value;
        //维护红黑树的颜色规则。
        if (isRed(h.right) && !isRed(h.left))
            h = rotateLeft(h);
        if (isRed(h.left.left) && isRed(h.left))
            h = rotateRight(h);
        if (isRed(h.left) && isRed(h.right))
            changeColors(h);
        h.N = size(h.left) + size(h.right) + 1;
        return h;

    }


    public void deleteMin() {

        root = deleteMin(root);
        if (!isEmpty())
            root.color = Black;
    }

    private Node deleteMin(Node h) {
        if (h.left == null)
            return null;
        //如果当前结点的左子节点为2-结点, 并且当前结点也是2-结点   就要借兄弟节点，或者和父节点最小键 兄弟结点一个键合3为一。
        if (!isRed(h.left) && !isRed(h.left.left))
            h = moveRedLeft(h);
        //这里能代表的是其不为2-  或者其左子不为2-  那么就可以直接删除
        h.left = deleteMin(h.left);
        return balance(h);

    }

    private Node moveRedLeft(Node h) {
        //先合3为1
        flipColors(h);
        //h的右子结点不是2-结点  即兄弟节点不是2-结点 从兄弟中拿来一个
        if (isRed(h.right.left)) {
            //右旋将 h.right.left 变成h.right=red
            h.right = rotateRight(h.right);
            //然后左旋 将右边的  h.left变为红色  这样就将它的兄弟节点的最小键拿过来当作h。
            h = rotateLeft(h);
            //然后复位回去 此时h的left就是一个3-结点了 即h.left.left=red
            flipColors(h);
        }
        return h;
    }

    public void deleteMax() {
        root = deleteMax(root);
        if (!isEmpty())
            root.color = Black;
    }

    private Node deleteMax(Node h) {
        if (isRed(h.left))
            h = rotateRight(h);
        if (h.right == null)
            return null;
        if (!isRed(h.right) && !isRed(h.right.left))
            h = moveRedRight(h);
        h.left = deleteMax(h.left);
        return balance(h);
    }

    private Node moveRedRight(Node h) {
        flipColors(h);
        if (isRed(h.left.left)) {
            h = rotateRight(h);
            flipColors(h);
        }
        return h;
    }

    private Node balance(Node h) {
        if (isRed(h.right))
            h = rotateLeft(h);
        if (isRed(h.left) && isRed(h.left.left))
            h = rotateRight(h);
        if (isRed(h.left) && isRed(h.right))
            flipColors(h);
        return h;
    }


    private Key minnum(Node node) {
        if (node.left == null)
            return node.key;
        return minnum(node.left);
    }


    public static void main(String[] args) {

    }
}