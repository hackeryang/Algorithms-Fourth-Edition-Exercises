package Chapter1_3Low;

import java.util.Iterator;

//Exercise 1.3.6
public class Queue<Item> implements Iterable<Item> {
    private int N;
    private class Node{
        Item item;
        Node next;
    }
    private Node first;
    private Node last;
    public Queue(){

    }
    public boolean isEmpty(){
        if(first==null)
            return true;
        return false;
    }
    public int size(){
        return N;
    }
    public void enqueue(Item item){
        Node oldLast=last;
        last=new Node();
        last.item=item;
        last.next=null;
        if(this.isEmpty()){
            first=last;
        }else{
            oldLast.next=last;
        }
        N++;
    }
    public Item dequeue(){
        Item item=first.item;
        first=first.next;
        if(this.isEmpty()){
            last=null;
        }
        N--;
        return item;
    }
    public Iterator<Item> iterator(){
        return new QueueIterator();
    }
    private class QueueIterator implements Iterator<Item>{
        public boolean hasNext(){
            return false;
        }
        public Item next(){
            return null;
        }
        public void remove(){

        }
    }
    public static void main(String[] args){
        Queue<String> stringQueue=new Queue<String>();
        stringQueue.enqueue("我");
        stringQueue.enqueue("的");
        stringQueue.enqueue("名字");
        stringQueue.enqueue("叫yyc");
        stringQueue.enqueue("hacker");
        // System.out.println(stringQueue.dequeue());
        // System.out.println(stringQueue.dequeue));
        // System.out.println(stringQueue.dequeue));
        // System.out.println(stringQueue.dequeue));
        // System.out.println(stringQueue.dequeue));
        // System.out.println(stringQueue.dequeue));
        Stack<String> stack=new Stack<String>();
        while(!stringQueue.isEmpty())
            stack.push(stringQueue.dequeue());
        while(!stack.isEmpty())
            stringQueue.enqueue(stack.pop());

         System.out.println(stringQueue.dequeue());
         System.out.println(stringQueue.dequeue());
         System.out.println(stringQueue.dequeue());
         System.out.println(stringQueue.dequeue());
         System.out.println(stringQueue.dequeue());
    }
}
