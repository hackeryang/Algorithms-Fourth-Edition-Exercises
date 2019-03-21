package Chapter1_3LinkedListExercises;

//exercise 1.3.24
public class LinkedListExercise4<Item> {
    private static class Node<Item>{
        Node next;
        Item item;

        @Override
        public String toString() {
            return "item: "+item;
        }
    }

    public Node<Item> removeAfter(Node node,Node first){
        if(first==null){
            return null;
        }
        Node current=first;
        while(current!=null){
            if(current.item.equals(node.item)){ //当当前节点的值与想删除的开头链表项的值相同时，就开始删除操作
                if(current.next!=null){
                    current.next=current.next.next;
                    return first; //返回整个链表的开头元素引用，但是找到first相当于找到整个链表
                }else{
                    return first;
                }
            }
            current=current.next;
        }
        return null;
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

        LinkedListExercise4<String> linkedListExercise4=new LinkedListExercise4<String>();
        Node targetNode=first;
        System.out.println("即将移除节点："+targetNode+"之后的节点");
        Node resultNode=linkedListExercise4.removeAfter(targetNode,first);

        System.out.println("新链表：\n------");
        Node current2=resultNode;
        while(current2.next!=null){
            System.out.println(current2.item);
            current2=current2.next;
        }
        System.out.println(current2.item);
        System.out.println("------");
    }
}
