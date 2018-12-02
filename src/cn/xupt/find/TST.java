package cn.xupt.find;

import javax.lang.model.element.NestingKind;
import java.util.ArrayList;
import java.util.List;

public class TST<Value> {

    private Node root;

    private class Node {
        char c;
        Node left, mid, right;
        Value val;
        int N;
    }

    public Value get(String key) {
        if (key == null || key.equals("")) throw new NullPointerException("key不能为null或者空");
        Node x = get(root, key, 0);
        return x == null ? null : (Value) x.val;
    }

    private Node get(Node x, String key, int d) {
        if (x == null) return null;
        char c = key.charAt(d);
        if (c < x.c) return get(x.left, key, d);
        else if (c > x.c) return get(x.right, key, d);
        else if (d < key.length() - 1)
            return get(x.mid, key, d + 1);
        else
            return x;
    }

    public void put(String key, Value val) {
        root = put(root, key, val, 0);

    }

    private Node put(Node x, String key, Value val, int d) {
        if (key == null || key.equals(""))
            throw new NullPointerException("key不能为null或者空");
        char c = key.charAt(d);
        if (x == null) {
            x = new Node();
            x.c = c;
        }
        if (c < x.c)
            x.left = put(x.left, key, val, d);
        else if (c > x.c)
            x.right = put(x.right, key, val, d);
        else if (d < key.length() - 1)
            x.mid = put(x.mid, key, val, d + 1);
        else x.val = val;
        x.N = (x.left == null ? 0 : x.left.N) + (x.right == null ? 0 : x.right.N)
                + (x.mid == null ? 0 : x.mid.N) + (x.val == null ? 0 : 1);
        return x;
    }

    public int size() {
        return root == null ? 0 : root.N;
    }

    public void delete(String key) {
        root = delete(root, key, 0);
    }

    private Node delete(Node x, String key, int d) {
        if (x == null) return null;
        char c = key.charAt(d);
        if (c < x.c) x.left = delete(x.right, key, d);
        else if (c > x.c) x.right = delete(x.right, key, d);
        else if (d < key.length() - 1) x.mid = delete(x.mid, key, d);
            //删除操作
        else {
            if (x.mid != null) {
                x.val = null;
            } else if (x.left.right == null) {
                Node temp = x.right;
                x = x.left;
                x.right = temp;
            } else {
                Node right = x.left.right;
                while (true) {
                    if (right.left == null && right.right == null) {
                        break;
                    }
                    right = right.right;
                }
                Node xLeft = x.left;
                Node xRight = x.right;
                x = right;
                x.left = xLeft;
                x.right = xRight;
            }
        }
        return x;
    }

    public static void main(String[] args) {
//        TST<String> tst = new TST<>();
//        tst.put("123", "123");
//        tst.put("123", "123");
//        tst.put("1234", "123");
//        tst.put("14545234", "123");
//        tst.delete("1234");
//        System.out.println(tst.size());


    }
}