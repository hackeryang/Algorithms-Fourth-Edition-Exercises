package Chapter1_3LinkedListExercises;

public class LinkedListExercise3 {
    private static class Node{
        Node next;
        String item;
    }
    public boolean find(Node first,String key){
        if(first==null)
            return false;
        Node current=first;
        while(current!=null){
            if(current.item.equals(key)){
                return true;
            }
            current=current.next;
        }
        return false;
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

        String targetString="hacker";
        LinkedListExercise3 linkedListExercise3=new LinkedListExercise3();
        boolean find=linkedListExercise3.find(first,targetString);
        System.out.println("查找字符串"+targetString+":"+find);
    }
}
