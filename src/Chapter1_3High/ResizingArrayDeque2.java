package Chapter1_3High;

import java.util.Iterator;

public class ResizingArrayDeque2<Item> implements Iterable<Item> {
    private Item[] a;
    private int head=1;
    private int tail=1;
    public boolean isEmpty(){
        return head==tail;
    }
   public ResizingArrayDeque2(){
        a=(Item[])(new Object[2]);
   }
    public int size(){
       return tail-head;
    }
    public void pushLeft(Item item){
        if(head==0){
            resize(3*size());
        }
        a[--head]=item;
    }
    private void resize(int max){
        if(max<3){
            max=3;
        }
        Item tmp[]=(Item[])new Object[max];
        int j=max/3;
        for(int i=head;i<tail;i++){
            tmp[j++]=a[i]; //将原数组的所有内容都放到加长过的数组中，从长数组的左边1/3处开始存放，这样相当于最左边和最右边都加长了原长度的两倍
        }
        a=tmp;
        head=max/3;
        tail=j;
    }
    public void pushRight(Item item){
        if(tail==a.length){
            resize(3*size());
        }
        a[tail++]=item;
    }
    public Item popLeft(){
        if(isEmpty()){
            return null;
        }
        if(size()*6<a.length){
            resize(size()*3);
        }
        return a[head++];
    }
    public Item popRight(){
        if(isEmpty()){
            return null;
        }
        if(size()*6<a.length){
            resize(size()*3);
        }
        return a[--tail];
    }
    public Iterator<Item> iterator(){
        return new DequeIterator();
    }
    private class DequeIterator implements Iterator<Item>{
        private int index=head;
        public boolean hasNext(){
            return index<tail;
        }
        public Item next(){
            Item x=a[index++];
            return x;
        }
        public void remove(){

        }
    }

    public static void main(String[] args){
        ResizingArrayDeque2<String> deque=new ResizingArrayDeque2<String>();
        deque.pushRight("我");
        deque.pushRight("的");
        deque.pushRight("名字");
        deque.pushRight("叫yyc");
        deque.pushRight("hacker");
        for(String string:deque){
            System.out.println(string);
        }
        System.out.println("======");
        ResizingArrayDeque2<String> deque1=new ResizingArrayDeque2<String>();
        deque1.pushLeft("我");
        deque1.pushLeft("的");
        deque1.pushLeft("名字");
        deque1.pushLeft("叫yyc");
        deque1.pushLeft("hacker");
        for(String string:deque1){
            System.out.println(string);
        }
        System.out.println("======");
        deque.popLeft();
        for(String string:deque){
            System.out.println(string);
        }
    }
}
