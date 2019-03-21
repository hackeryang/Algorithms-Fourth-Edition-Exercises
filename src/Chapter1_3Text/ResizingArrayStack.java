package Chapter1_3Text;

import java.util.Iterator;

public class ResizingArrayStack<Item> implements Iterable<Item> {  //下压栈的实现（后进先出）
    private Item[] a=(Item[]) new Object[1]; //栈元素
    private int N=0; //元素数量
    public boolean isEmpty(){return N==0;}
    public int size(){return N;}
    private void resize(int max){
        //将栈移动到一个大小为max的新数组
        Item[] temp=(Item[]) new Object[max];
        for(int i=0;i<N;i++)
            temp[i]=a[i];
        a=temp;
    }
    public void push(Item item){
        //将元素添加到栈顶
        if(N==a.length) resize(2*a.length);
        a[N++]=item;
    }
    public Item pop(){
        //从栈顶删除元素
        Item item=a[--N];  //N为元素数量，因此N-1才是数组中最后一个元素
        a[N]=null; //避免对象游离，此时的N已经是N减小前的N-1，即把最后一个元素置为空
        if(N>0 && N==a.length/4) resize(a.length/2);
        return item;
    }
    public Iterator<Item> iterator(){
        return new ReverseArrayIterator();
    }
    private class ReverseArrayIterator implements Iterator<Item>{
        //支持后进先出的迭代
        private int i=N;
        public boolean hasNext(){return i>0;}
        public Item next(){return a[--i];}
        public void remove(){ }
    }
}
