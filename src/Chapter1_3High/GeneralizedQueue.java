package Chapter1_3High;

import java.util.Iterator;

//Exercise 1.3.38
public class GeneralizedQueue<Item> implements Iterable<Item> {
    private Item[] a;
    private int N;

    public GeneralizedQueue() {
        N = 0;
        a = (Item[]) new Object[1];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public void insert(Item x) {
        if (N == a.length) {
            resize(N * 2);
        }
        a[N++] = x;
    }

    public Item delete(int k) {
        if (k > N || k < 0) {
            return null;
        }
        if (N == a.length / 4) {
            resize(a.length / 2);
        }
        Item target = a[k];
        Item[] temp = (Item[]) new Object[N];
        for (int i = 0; i < N; i++) {
            if (i < k) {
                temp[i] = a[i];
            } else {
                temp[i] = a[i + 1];
            }
        }
        a = temp;
        a[--N] = null;
        return target;
    }

    private void resize(int max) {
        Item[] temp = (Item[]) new Object[max];
        for (int i = 0; i < N; i++) {
            temp[i] = a[i];
        }
        a = temp;
    }

    public Iterator<Item> iterator() {
        return new GeneralizedQueueIterator();
    }

    public class GeneralizedQueueIterator implements Iterator<Item> {
        private Item[] temp;
        private int index;

        public GeneralizedQueueIterator() {
            temp = (Item[]) new Object[N];
            for (int i = 0; i < N; i++) {
                temp[i] = a[i];
            }
            index = 0;
        }

        public boolean hasNext() {
            return index < N;
        }

        public Item next() {
            return temp[index++];
        }

        public void remove() {
        }
    }

    public static void main(String[] args) {
        GeneralizedQueue<String> queue = new GeneralizedQueue<String>();
        queue.insert("我");
        queue.insert("的");
        queue.insert("名字");
        queue.insert("叫yyc");
        queue.insert("hacker");

        queue.delete(0);
        queue.delete(2);
        for (String string : queue) {
            System.out.println(string);
        }
    }
}
