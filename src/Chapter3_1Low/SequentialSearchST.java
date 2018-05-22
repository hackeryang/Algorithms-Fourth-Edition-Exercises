package Chapter3_1Low;

import edu.princeton.cs.algs4.Queue;

public class SequentialSearchST<Key,Value> {
    //顺序查找（基于无序链表)
    private Node first;  //链表首节点
    private int N=0;
    private class Node{
        //链表节点的定义
        Key key;
        Value val;
        Node next;
        public Node(Key key,Value val,Node next){
            this.key=key;
            this.val=val;
            this.next=next;
        }
    }
    public Value get(Key key){
        //查找给定的键，返回相关联的值
        for(Node x=first;x!=null;x=x.next)
            if(key.equals(x.key))
                return x.val;  //命中
        return null;  //未命中
    }
    public void put(Key key,Value val){
        //查找给定的键，找到则更新其值，否则在表中新建节点
        for(Node x=first;x!=null;x=x.next){
            if(key.equals(x.key)){
                x.val=val;  //命中，更新
                return;
            }
        }
        first=new Node(key,val,first);  //未命中，在链表开头新建节点
        N++;
    }
    //Exercise 3.1.5
    public int size(){
        return N;
    }
    public void delete(Key key){
        first=delete(first,key);
    }
    private Node delete(Node x,Key key){
        if(x==null){
            return null;
        }
        if(x.key.equals(key)){  //如果链表第一项就是要删除的key的项，则删除第一项并将原来的第二个链表项变成现在链表的第一项
            N--;
            return x.next;
        }
        x.next=delete(x.next,key);  //链表第一项不是要删除的项，则从第二项开始递归查找并删除对应的key
        return x;
    }
    public Iterable<Key> keys(){
        Queue<Key> queue=new Queue<>();
        for(Node x=first;x!=null;x=x.next){
            queue.enqueue(x.key);
        }
        return queue;
    }
}
