package Chapter1_3LinkedListExercises;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class DoublyLinkedList<Item> implements Iterable<Item> {
    private int N;
    private DoubleNode first;
    private DoubleNode last;

    private class DoubleNode{
        private DoublyLinkedList<Item> parent=list();
        private Item item;
        private DoubleNode prev;
        private DoubleNode next;
    }

    private DoublyLinkedList<Item> list(){
        return this;
    }
    public DoublyLinkedList(){
        first=null;
        last=null;
    }
    public DoublyLinkedList(Item[] a){
        for(Item t:a)
            append(t);
    }
    public DoublyLinkedList(Iterable<Item> coll){
        for(Item t:coll)
            append(t);
    }
    public boolean isEmpty(){
        return first==null;
    }
    public int size(){
        return N;
    }
    public Item first(){
        if(isEmpty()) throw new RuntimeException("List is empty");
        return first.item;
    }
    public Item last(){
        if(isEmpty()) throw new RuntimeException("List is empty");
        return last.item;
    }
    public DoubleNode node(int k){
        DoubleNode curr=null;
        int i=1;
        if(k>0) //正向遍历
        {
            curr=first;
            while(i<k && curr != null){
                curr=curr.next;
                i++;
            }
        }else if(k<0){  //反向遍历
            k=-k;
            curr=last;
            while(i<k && curr!=null){
                curr=curr.prev;
                i++;
            }
        }
        return curr;
    }
    public void prepend(Item item){  //在表头插入节点
        DoubleNode x=new DoubleNode();
        x.item=item;
        if(isEmpty()){
            first=x;
            last=x;
        }else{
            x.next=first;
            first.prev=x;
            first=x;
        }
        N++;
    }
    public void append(Item item){ //在表尾插入节点
        DoubleNode x=new DoubleNode();
        x.item=item;
        if(isEmpty()){
            first=x;
            last=x;
        }
        else{
            x.prev=last;
            last.next=x;
            last=x;
        }
        N++;
    }
    public void insertBefore(DoubleNode node,Item item){  //在指定节点前插入新节点
        if(node.parent!=this) throw new RuntimeException("Node does not belong to list.");
        if(node==first)
            prepend(item);
        else{
            DoubleNode prev=node.prev;
            DoubleNode x=new DoubleNode();
            x.item=item;
            x.prev=prev;
            x.next=node;
            prev.next=x;
            node.prev=x;
            N++;
        }
    }

    public void insertAfter(DoubleNode node,Item item){  //在指定节点后插入新节点
        if(node.parent!=this) throw new RuntimeException("Node does not belong to list.");
        if(node==last)
            append(item);
        else{
            DoubleNode next=node.next;
            DoubleNode x=new DoubleNode();
            x.item=item;
            x.prev=node;
            x.next=next;
            next.prev=x;
            node.next=x;
            N++;
        }
    }

    public Item removeFirst(){  //从表头删除节点
        if(isEmpty()) throw new RuntimeException("List is empty");
        Item item=first.item;
        first.parent=null;
        if(first.next!=null) first.next.prev=null;
        first=first.next;
        N--;
        if(last==null) first=null; //to avoid loitering
        return item;
    }

    public Item removeLast(){ //从表尾删除节点
        if(isEmpty()) throw new RuntimeException("List is empty");
        Item item=last.item;
        last.parent=null;
        if(last.prev!=null) last.prev.next=null;
        last=last.prev;
        N--;
        if(last==null) first=null;
        return item;
    }

    public Item remove(DoubleNode node){ //删除指定节点
        if(node.parent!=this) throw new RuntimeException("Node does not belong to list.");
        if(node==first)
            return removeFirst();
        else if(node==last)
            return removeLast();
        else{
            node.parent=null;
            DoubleNode prev=node.prev,next=node.next;
            prev.next=node.next;
            next.prev=node.prev;
            N--;
            return node.item;
        }
    }
    public String toString(){
        StringBuilder s=new StringBuilder();
        for(Item item:this)
            s.append(item+" ");
        return s.toString();
    }

    public Iterator<Item> iterator(){
        return new ListIterator();
    }
    private class ListIterator implements Iterator<Item>{
        private DoubleNode current=first;
        public boolean hasNext(){return current!=null;}
        public void remove(){throw new UnsupportedOperationException();}
        public Item next(){
            if(!hasNext()) throw new NoSuchElementException();
            Item item=current.item;
            current=current.next;
            return item;
        }
    }

}
