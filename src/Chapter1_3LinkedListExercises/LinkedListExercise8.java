package Chapter1_3LinkedListExercises;

//Exercise 1.3.30
public class LinkedListExercise8 {
    private static class Node<Item> {
        Node next;
        Item item;

        public String toString() {
            return "item: " + item;
        }
    }

    public static Node reverse(Node x) {
        Node first = x;  //初始化首节点，从原链表的首节点开始反转操作
        Node reverse = null;  //用一个新的空链表存放反转后的链表
        while (first != null) {  //当原链表的节点没有被剥离完时不断循环
            Node second = first.next; //初始化原链表首节点的下一个节点
            first.next = reverse; //原链表首节点的下一个节点链接到新链表的首节点处
            reverse = first; //下一跳节点链接完成后，将原链表首节点放入到新链表中，成为新链表的首节点
            first = second; //从原链表中剥离掉原首节点，原链表首节点的下一个节点成为新的原链表首节点，用于下一次循环
        }
        return reverse; //返回插入到新链表的首节点，即原链表的最后一个节点
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
        fifth.item = "hacker";
        fifth.next = null;

        Node newFirst = reverse(first);
        Node current = newFirst;
        while (current != null) {
            System.out.println(current.item);
            current = current.next;
        }
    }
}
