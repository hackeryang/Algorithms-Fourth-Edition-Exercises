package Chapter1_3High;

import java.util.Iterator;

//Exercise 1.3.32
public class Steque<Item> implements Iterable<Item> {
    private class Node {
        Node next;
        Item item;
    }

    private Node first;
    private Node last;

    public void push(Item x) {
        Node node = new Node();
        node.item = x;
        if (this.isEmpty()) {
            first = node;
            last = node;
        } else {
            node.next = first;
            first = node;
        }
    }

    public Item pop() {
        if (this.isEmpty()) {
            return null;
        }
        Item item = first.item;
        first = first.next;
        return item;
    }

    public void enqueue(Item x) {
        Node node = new Node();
        node.item = x;
        if (this.isEmpty()) {
            first = node;
            last = node;
        } else {
            last.next = node;
            last = node;
        }
    }

    public boolean isEmpty() {
        return first == null;
    }

    public Iterator<Item> iterator() {
        return new StequeIterator();
    }

    private class StequeIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            Item x = current.item;
            current = current.next;
            return x;
        }
    }

    public static void main(String[] args) {
        Steque<String> stringSteque1 = new Steque<String>();
        stringSteque1.enqueue("我");
        stringSteque1.enqueue("的");
        stringSteque1.enqueue("名字");
        stringSteque1.enqueue("叫yyc");
        stringSteque1.enqueue("hacker");
        for (String string : stringSteque1) {
            System.out.println(string);
        }
        Steque<String> stringSteque2 = new Steque<String>();
        stringSteque2.push("我");
        stringSteque2.push("的");
        stringSteque2.push("名字");
        stringSteque2.push("叫yyc");
        stringSteque2.push("hacker");
        for (String string : stringSteque2) {
            System.out.println(string);
        }
    }
}
