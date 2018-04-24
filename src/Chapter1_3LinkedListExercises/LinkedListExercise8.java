package Chapter1_3LinkedListExercises;

public class LinkedListExercise8 {
    private static class Node<Item>{
        Node next;
        Item item;
        public String toString(){
            return "item: "+item;
        }
    }
    public static Node reverse(Node x){
        Node first=x;
        Node reverse=null;
        while(first!=null){
            Node second=first.next; //初始化second为first的下一个节点
            first.next=reverse; //将null赋给first.next，因为在新链表中first是最后一个节点
            reverse=first; //插入first到新链表中，reverse指向结果链表中的首节点
            first=second; //因为first被插入到reverse，所以second成为原链表中的首节点
        }
        return reverse; //返回插入到新链表的链表项
    }
    public static void main(String[] args){
        /*
        * 创建链表
        * */
        Node<String> first=new Node<String>();
        Node<String> second=new Node<String>();
        Node<String> third=new Node<String>();
        Node<String> forth=new Node<String>();
        Node<String> fifth=new Node<String>();
        first.item="我的";
        first.next=second;
        second.item="名字";
        second.next=third;
        third.item="叫";
        third.next=forth;
        forth.item="yyc";
        forth.next=fifth;
        fifth.item="hacker";
        fifth.next=null;

        Node newFirst=reverse(first);
        Node current=newFirst;
        while(current!=null){
            System.out.println(current.item);
            current=current.next;
        }
    }
}
