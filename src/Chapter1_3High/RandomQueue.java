package Chapter1_3High;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

//Exercise 1.3.35
public class RandomQueue<Item> implements Iterable<Item> {
    private Item[] a;
    private int N;

    public RandomQueue(){
        a=(Item[])(new Object[1]);
        N=0;
    }
    public boolean isEmpty(){
        return N==0;
    }
    public int size(){
        return N;
    }
    public void enqueue(Item x){
        if(N==a.length){
            this.resize(a.length*2);
        }
        a[N++]=x;
    }
    public Item dequeue(){
        if(this.isEmpty()){
            return null;
        }
        if(N==a.length/4){
            resize(a.length/2);
        }
        int index= StdRandom.uniform(N);
        Item x=a[index];
        a[index]=a[--N];
        a[N]=null;
        return x;
    }
    public void resize(int max){
        Item[] temp=(Item[])new Object[max];
        for(int i=0;i<N;i++){
            temp[i]=a[i];
        }
        a=temp;
    }
    public Item sample(){
        if(this.isEmpty()){
            return null;
        }
        int index=StdRandom.uniform(N);
        return a[index];
    }
    public Iterator<Item> iterator(){
        return new RandomQueueIterator();
    }
    public class RandomQueueIterator implements Iterator<Item>{
        private Item[] temp;
        private int index;

        public RandomQueueIterator(){
            temp=(Item[])new Object[N];
            for(int i=0;i<N;i++)
                temp[i]=a[i];
            StdRandom.shuffle(temp);
            index=0;
        }
        public boolean hasNext(){
            return index<N;
        }
        public Item next(){
            return temp[index++];
        }
        public void remove(){

        }
    }
    public static void main(String[] args){
        RandomQueue<Integer> queue=new RandomQueue<Integer>();
        for(int i=1;i<=52;i++)
            queue.enqueue(i);
        for(Object object:queue){
            System.out.println(object);
        }
    }
}
