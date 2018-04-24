package Chapter1_3LinkedListExercises;

import java.util.Iterator;

public class CircularLinkedListQueue<Item> implements Iterable<Item> {
    private class Node<Item>{
        Item item;
        Node<Item> next;
    }
    private Node<Item> last; //最后一个节点
    private int N=0; //链表项的数目

    public void enqueue(Item item){
        Node<Item> node=new Node<Item>();
        node.item=item;
        if(this.isEmpty()){
            node.next=node;
            last=node;
            N++;
        }else{
            node.next=last.next; //原本last节点下一项指向first，现在将first的指向赋给node，使node指向first
            last.next=node; //使node成为最后一个节点，向前链接到原来的last，last的next原本指向first现在指向node
            last=node; //使最后一个节点依然叫last，原本叫node
            N++; //链表项个数加1
        }
    }
    public Item dequeue(){
        if(this.isEmpty()){
            return null;
        }
        Node<Item> first=last.next;
        last.next=last.next.next; //解除对first的链接，直接跳过first链接到下下个节点如second
        N--; //链表项个数减1
        return first.item; //返回被排出队列的项
    }
    public boolean isEmpty(){
        return last==null;
    }
    public Iterator<Item> iterator(){
        return new QueueIterator();
    }
    private class QueueIterator implements Iterator<Item>{
        Node<Item> current=last;
        int index=0;

        public boolean hasNext(){
            if(last==null){
                return false;
            }
            if(index<N){
                return true;
            }
            return false;
        }

        public Item next(){
            current=current.next;
            index++;
            return current.item;
        }
        public void remove(){

        }
    }

    public static void main(String[] args){
        CircularLinkedListQueue<String> queue=new CircularLinkedListQueue<String>();
        queue.enqueue("我的");
        queue.enqueue("名字");
        queue.enqueue("叫");
        queue.enqueue("yyc");
        queue.enqueue("hacker");
        queue.dequeue();
        queue.dequeue();

        for(String string:queue){
            System.out.println(string);
        }
        System.out.println(queue);
    }
}
