package Chapter1_3Low;

import edu.princeton.cs.algs4.StdIn;

import java.util.Iterator;

//Exercise 1.3.8
public class DoublingStackOfStrings<Item> implements Iterable<Item> {
    private int N;
    private Item[] items = (Item[]) (new Object[1]);

    public void push(Item item) {
        if (items.length == N) resize(N * 2);
        items[N++] = item;
    }

    public Item pop() {
        Item item = items[--N];
        items[N] = null;
        if (N > 0 && N == items.length / 4) resize(N / 2); //当实际有值的项只有总容量的1/4时，就将容量减半
        return item;
    }

    private void resize(int max) {
        Item[] items2 = (Item[]) (new Object[max]);
        for (int i = 0; i < N; i++) {
            items2[i] = items[i];
        }
        items = items2;
    }

    public int arraySize() {
        return items.length;
    }

    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<Item> {
        private int i = N;

        public boolean hasNext() {
            return i > 0;
        }

        public Item next() {
            return items[--i];
        }
    }

    public static void main(String[] args) {
        DoublingStackOfStrings<String> stack = new DoublingStackOfStrings<String>();
        String[] inputs = StdIn.readAllStrings();
        for (int i = 0; i < inputs.length; i++) {
            if (inputs[i].equals("-")) {
                stack.pop();
            } else {
                stack.push(inputs[i]);
            }
        }
        for (String s : stack) {
            System.out.print(s + " ");
        }
        System.out.println();
        System.out.println("ArraySize: " + stack.arraySize());
        System.out.println();
    }
}
