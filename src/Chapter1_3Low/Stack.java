package Chapter1_3Low;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Stack<Item> {
    private int N;
    private Node first;
    private class Node{
        Item item;
        Node next;
    }
    public Stack(){
        first=new Node();
    }
    public void push(Item item){
        Node oldFirst=first;
        first=new Node();
        first.item=item;
        first.next=oldFirst;
        N++;
    }
    public Item pop(){
        Item item=first.item;
        first=first.next;
        N--;
        return item;
    }
    public boolean isEmpty(){
        //或first=null
        return N==0;
    }

    public Item peek(){return first.item;}
    public static void main(String[] args){
        Stack<String> s=new Stack<String>();
        while(!StdIn.isEmpty()){
            String item=StdIn.readString();
            if(!item.equals("-")){
                s.push(item);
            }else if(!s.isEmpty()){
                StdOut.print(s.pop()+" ");
            }
        }
    }

}
