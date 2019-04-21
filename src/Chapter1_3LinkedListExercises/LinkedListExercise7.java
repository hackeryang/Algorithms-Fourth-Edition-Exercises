package Chapter1_3LinkedListExercises;

//Exercise 1.3.27
public class LinkedListExercise7 {
    private static class Node{
        Node next;
        Integer item;

        @Override
        public String toString() {
            return "item: "+item;
        }
    }
    public Integer max(Node first){
        if(null==first){
            return 0;
        }
        if(first.next==null){
            return first.item;
        }
        Node current=first;
        Integer max=current.item;
        while(current!=null){
            if(current.item>max){
                max=current.item;
            }
            current=current.next;
        }
        return max;
    }
    /*
    * @param args
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
        first.item=1;
        first.next=second;
        second.item=3;
        second.next=third;
        third.item=999;
        third.next=forth;
        forth.item=33;
        forth.next=fifth;
        fifth.item=21;
        fifth.next=null;

        System.out.println("原链表：\n------");
        Node current1=first;
        while(current1.next!=null){
            System.out.println(current1.item);
            current1=current1.next;
        }
        System.out.println(current1.item); //因为前面while循环只会遍历到倒数第二个节点，这里需要再输出一次来获得最后一个节点的item
        System.out.println("------");

        System.out.println("正在求最大值。。。");
        LinkedListExercise7 linkedListExercise7=new LinkedListExercise7();
        Integer result=linkedListExercise7.max(first);
        System.out.println("result: "+result);
    }
}
