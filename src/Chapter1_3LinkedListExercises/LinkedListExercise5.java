package Chapter1_3LinkedListExercises;

//Exercise 1.3.25
public class LinkedListExercise5<Item> {
    private static class Node<Item> {
        Node next;
        Item item;

        @Override
        public String toString() {
            return "item: " + item;
        }
    }

    public Node<Item> insertAfter(Node<Item> targetNode, Node<Item> node, Node<Item> first) {  //在某个链表项后面插入一个新链表项
        if (targetNode == null || node == null) {
            return first;
        }
        Node<Item> current = first;
        while (current != null) {
            if (current.equals(targetNode)) {
                Node<Item> t = current.next; //在插入前，targetNode原本下一个节点为t
                current.next = node;         //将节点插入到targetNode的后面
                node.next = t;               //确保插入的节点既向前链接到targetNode，又要向后链接到原来targetNode下一个节点的的节点t
                return first;
            }
            current = current.next;   //向后迭代
        }
        return null;
    }

    public static void main(String[] args) {
        /*
         * 创建链表
         * */
        Node<String> first = new Node<String>();
        Node<String> second = new Node<String>();
        Node<String> third = new Node<String>();
        Node<String> forth = new Node<String>();
        Node<String> fifth = new Node<String>();
        first.item = "我的";
        first.next = second;
        second.item = "名字";
        second.next = third;
        third.item = "叫";
        third.next = forth;
        forth.item = "yyc";
        forth.next = fifth;
        forth.item = "hacker";
        forth.next = null;

        LinkedListExercise5<String> linkedListExercise5 = new LinkedListExercise5<String>();
        Node targetNode = second;
        System.out.println("即将移除节点：" + targetNode + "之后的节点");
        Node<String> insertedNode = new Node<String>();
        insertedNode.item = "天天开心哈哈哈";
        Node resultNode = linkedListExercise5.insertAfter(targetNode, insertedNode, first);
        System.out.println("新链表：\n------");
        Node current2 = resultNode;
        while (current2.next != null) {
            System.out.println(current2.item);
            current2 = current2.next;
        }
        System.out.println(current2.item);  //因为前面while循环只会循环到链表倒数第二项，最后一项的next为null所以不会被println出来，所以在这里要再println出链表最后一项
        System.out.println("------");
    }
}
