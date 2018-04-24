package Chapter1_3High;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

public class RandomQueueIterator<Item> implements Iterator<Item> {
    public int N;
    public Item[] a;
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
    public void remove(){ }
}
