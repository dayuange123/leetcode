package cn.xupt.find;

import java.util.Random;

public class BST {


    public static void main(String[] args) {
        MyBST<Integer, String> mb = new MyBST<>();
        for (int i = 0; i < 20; i++) {
            mb.insert(new Random(i).nextInt(20), "长江" + i + "号");
        }
        mb.inOrder(mb.node);
        MyBST<Integer, String>.Node<Integer, String> node = mb.node;
        System.out.println(node.key + ":" + node.value);
        mb.node = null;
        System.out.println(node.key + ":" + node.value);
        System.out.println(mb.node.key + ":" + mb.node.value);

//        mb.insert(19, "长江" + 12313 + "号");
//        //  mb.insert(null, "长江" + 12313 + "号");
//        MyBST.Node p = mb.node;
//
//        System.out.println(mb.size());
//        System.out.println(mb.contain(19));
//        System.out.println(mb.search(16));
    }

}
