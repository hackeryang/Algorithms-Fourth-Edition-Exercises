package Chapter1_3Low;

import java.util.Iterator;

//Exercise 1.3.12
public class Stack2<Item> implements Iterable<Item> {  //将一个链表栈中的项按照原顺序复制到另一个链表栈中，需要两次迭代器遍历以及两次入栈操作来将顺序负负得正
    /*
    * 链表实现
    * */
    private int N;
    private Node first;
    private class Node{
        Item item;
        Node next;
    }
    public Stack2(){
        N=0;
        first=null;
    }
    public void push(Item item){
        Node oldFirst=first;
        first=new Node();
        first.item=item;
        first.next=oldFirst;
        N++;
    }
    public Item top(){
        return first.item;
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
    public int size(){
        return N;
    }
    public Iterator<Item> iterator(){
        return new ArrayIterator();
    }
    private class ArrayIterator implements Iterator<Item>{
        private Node current=first;
        public boolean hasNext(){
            return current!=null;
        }
        public Item next(){
            Item item=current.item;
            current=current.next;
            return item;
        }
        public void remove(){ }
    }
    public static Stack2<String> copy(Stack2<String> stack2){
        //链表栈是在栈的最上层添加链表项，最先添加的项会在栈底，用迭代器遍历是从栈顶顺序遍历，再push入栈中会被逆序，所以需要第二次迭代器遍历再push入栈来第二次逆序，逆逆得正
        Stack2<String> resultStack=new Stack2<String>();
        //逆序1
        Stack2<String> tempStack=new Stack2<String>();
        Iterator<String> iterator=stack2.iterator();
        while(iterator.hasNext()){
            tempStack.push(iterator.next());
        }
        //逆序2
        Iterator<String> tempIterator=tempStack.iterator();
        while(tempIterator.hasNext()){
            resultStack.push(tempIterator.next());
        }
        return resultStack;
    }
    public static void main(String[] args){
        Stack2<String> stack=new Stack2<String>();
        stack.push("我");
        stack.push("的");
        stack.push("名字");
        stack.push("叫");
        stack.push("yyc");
        stack.push("hacker");
        //打印
        System.out.println("原栈逆序输出：");
        for(String string:stack){
            System.out.print(string);
        }
        System.out.println("");
        System.out.println("开始拷贝。。。");
        //拷贝
        Stack2<String> stack2=Stack2.copy(stack);
        System.out.println("开始打印拷贝后的栈");
        //创建迭代器
        for(String string:stack2){
            System.out.print(string);
        }
    }
}

