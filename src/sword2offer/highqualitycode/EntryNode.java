package sword2offer.highqualitycode;

public class EntryNode {
    private int getNodeNumbers(Node node) {
        if (node == null) throw new NullPointerException();
        //先查找是否有环
        Node node1 = node;
        Node node2 = node;

        while (node2 != null) {
            node1 = node1.next;
            node2 = node2.next;
            if (node2 == null) return -1;
            node2 = node2.next;
            if (node1 == node2)
                break;
        }
        int index = 0;
        node2 = node2.next;
        while (node1 != node2) {
            node2 = node2.next;
            index++;
        }
        return ++index;
    }

    public Node getStartNode(Node node) {
        int nodeNumbers = getNodeNumbers(node);
        if (nodeNumbers == -1) return null;
        Node node1 = node;
        Node node2 = node;
        while (nodeNumbers-- > 0) {
            node1 = node1.next;
        }
        while (node1 != node2) {
            node1 = node1.next;
            node2 = node2.next;
        }
        return node1;
    }

    public static void main(String[] args) {
        Node node = new Node(1);
        node.next = new Node(2);
        node.next.next = new Node(3);
        node.next.next.next = new Node(4);
        node.next.next.next.next = new Node(5);
   //     node.next.next.next.next.next = null;
        Node startNode = new EntryNode().getStartNode(node);
        System.out.println(startNode.value);
    }
}