package Chapter1_3LinkedListExercises;

public class LinkedListExercise2<Item> {
    private static class Node<Item>{
        Node next;
        Item item;
    }
    /*
    * @param k 第k个元素
    * @param first 链表的首节点
    * @return 新的链表
    * @throws Exception
    * */
    public Node<Item> delete(int k,Node<Item> first) throws Exception{
        if(k<0 || first == null) return null;
        if(k==0){
            first=first.next;
            return first;
        }
        Node<Item> current=first;
        while(current!=null && --k!=0){
            current=current.next;
        }
        if(k!=0 || current.next==null || current==null){
            throw new Exception();
        }else{
            current.next=current.next.next; //将当前第k-1个链表元素的下一个指向链接（即next）直接跳过下一个（即k），指向下下个（即k+1）
        }
        return first;
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
        third.item="叫做";
        third.next=forth;
        forth.item="yyc";
        forth.next=fifth;
        fifth.item="hacker";
        fifth.next=null;
        //删除尾节点之前
        System.out.println("原链表：\n------");
        Node current1=first;
        while(current1.next!=null){
            System.out.println(current1.item);
            current1=current1.next;
        }
        System.out.println(current1.item);
        System.out.println("------");

        LinkedListExercise2<String> linkedListExercise2=new LinkedListExercise2<>();
        //删除第一个元素
        int k=4;
        System.out.println("正在删除第"+k+"个节点...");
        Node<String> resultNode=null;
        try{
            resultNode=linkedListExercise2.delete(k,first);
        }catch(Exception e){
            e.printStackTrace();
        }
        System.out.println("删除成功");
        System.out.println("新链表：\n------");
        Node current2=resultNode;

    }
}
