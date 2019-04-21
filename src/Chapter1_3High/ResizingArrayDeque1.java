package Chapter1_3High;

import java.util.Iterator;

//Exercise 1.3.33
public class ResizingArrayDeque1<Item> implements Iterable<Item> {
    private Item[] a;
    private int N;

    public boolean isEmpty(){
        return N==0;
    }
    //构造函数
    public ResizingArrayDeque1(){
        a=(Item[])(new Object[1]);
    }
    //大小
    public int size(){
        return N;
    }
    /*
    * 这个实现的问题在于虽然pushRight的效率很高，只需要在数组后面追加元素就行了，但PushLeft的时候每次都要resize，
    * 因此耗时较多。所以不是很好的实现
    * */
    public void pushLeft(Item item){
        Item[] temp=(Item[])(new Object[N+1]);
        temp[0]=item;
        for(int i=0;i<N;i++){
            temp[i+1]=a[i]; //创建一个新的泛型对象数组temp，第一个元素是添加的item，后面是原来数组a中的全部元素
        }
        a=temp; //然后将temp的引用覆盖掉原来a的引用,a指向添加了新项的新数组
        N++;
    }

    private void resize(int max){
        Item[] temp=(Item[])(new Object[max]);
        for(int i=0;i<N;i++){
            temp[i]=a[i];
        }
        a=temp;
    }

    public void pushRight(Item item){
        if(N==a.length){
            resize(N*2);
        }
        a[N++]=item;
    }

    public Item popLeft(){
        Item[] temp=(Item[])(new Object[N+1]);
        Item poppedItem=a[0];
        for(int i=0;i<N;i++){
            temp[i]=a[i+1]; //将数组a第二项开始的所有项复制到新数组temp中
        }
        a=temp;  //a指向去掉了第一项的新数组
        N--;
        return poppedItem;
    }

    public Item popRight(){
        Item poppedItem=a[--N];
        a[N]=null;
        if(a.length==4*N){
            resize(a.length/2);
        }
        return poppedItem;
    }

    public Iterator<Item> iterator(){
        return new DequeIterator();
    }
    private class DequeIterator implements Iterator<Item>{
        int index=0;
        public boolean hasNext(){
            return index!=N;
        }
        public Item next(){
            return a[index++];
        }
        public void remove(){ }
    }

    public static void main(String[] args){
        ResizingArrayDeque1<String> deque=new ResizingArrayDeque1<String>();
        deque.pushRight("我");
        deque.pushRight("的");
        deque.pushRight("名字");
        deque.pushRight("叫yyc");
        deque.pushRight("hacker");

        for(String string:deque){
            System.out.println(string);
        }
        System.out.println("======");
        ResizingArrayDeque1<String> deque1=new ResizingArrayDeque1<String>();
        deque1.pushLeft("我");
        deque1.pushLeft("的");
        deque1.pushLeft("名字");
        deque1.pushLeft("叫yyc");
        deque1.pushLeft("hacker");
        for(String string:deque){
            System.out.println(string);
        }
        System.out.println("======");
        deque.popLeft();
        for(String string:deque){
            System.out.println(string);
        }
        System.out.println("======");
        deque1.popRight();
        for(String string:deque1){
            System.out.println(string);
        }
    }
}
