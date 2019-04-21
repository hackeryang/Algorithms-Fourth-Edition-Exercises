package Chapter1_3LinkedListExercises;

//Exercise 1.3.26
public class LinkedListExercise6 {
    private static class Node{
        Node next;
        String item;
        @Override
        public String toString() {
            return "item: "+item;
        }
    }
    public Node remove(Node first,String key){  //删除链表中所有item值为key的链表项，在remove()方法中接受一条链表和字符串key作为参数
        Node newFirst=new Node();
        newFirst.next=first;

        Node current=newFirst;
        while(current.next!=null){
            if(current.next.item.equals(key)){
                current.next=current.next.next; //跳过要删除的节点，直接链接到下下个节点
            }else{
                current=current.next;
            }
        }
        return newFirst.next;
    }
    /*
    *  @param args
    * */
    public static void main(String[] args){
        /*
        * 创建链表
        * */
        Node first=new Node();
        Node second=new Node();
        Node third=new Node();
        Node forth=new Node();
        Node fifth=new Node();
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

        LinkedListExercise6 linkedListExercise6=new LinkedListExercise6();
        Node resultNode=linkedListExercise6.remove(first,"我的");

        System.out.println("新链表：\n------");
        Node current2=resultNode;
        while(current2.next!=null){
            System.out.println(current2.item);
            current2=current2.next;
        }
        System.out.println(current2.item);  //因为前面while循环只会循环到链表倒数第二项，最后一项的next为null所以不会被println出来，所以在这里要再println出链表最后一项
        System.out.println("------");
    }
}
