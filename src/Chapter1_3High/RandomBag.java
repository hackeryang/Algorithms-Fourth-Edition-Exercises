package Chapter1_3High;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

public class RandomBag<Item> implements Iterable<Item> {
    private Item[] a=(Item[])new Object[1];
    private int N=0;
    public boolean isEmpty(){
        return N==0;
    }
    public int size(){
        return N;
    }
    public void add(Item x){
        if(N==a.length)
            resize(2*a.length);
        a[N++]=x;
    }
    private void resize(int max){
        Item[] temp=(Item[]) new Object[max];
        for(int i=0;i<N;i++){
            temp[i]=a[i];
        }
        a=temp;
    }
    public Iterator<Item> iterator(){
        return new RandomBagIterator();
    }
    private class RandomBagIterator implements Iterator<Item>{
        private int[] seq=new int[N];
        private int index=0;

        public RandomBagIterator(){
            for(int i=0;i<seq.length;i++)
                seq[i]=i;
            StdRandom.shuffle(seq);
        }
        public boolean hasNext(){
            return index<N;
        }
        public Item next(){
            return a[seq[index++]]; //因为前面seq[i]=i并被随机排序，所以这里的意思是随机选出一个a[k]来，随着index++这个k就是随机的
        }
        public void remove(){

        }
    }

    public static void main(String[] args){
        RandomBag<String> randomBag=new RandomBag<String>();
        randomBag.add("我");
        randomBag.add("的");
        randomBag.add("名字");
        randomBag.add("叫");
        randomBag.add("yyc");
        randomBag.add("hacker");
        for(String string:randomBag){  //类继承了Iterable接口，所以for或者foreach加冒号迭代的时候会自动调用迭代器的方法
            System.out.println(string);
        }
    }
}
