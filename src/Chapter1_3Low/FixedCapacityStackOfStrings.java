package Chapter1_3Low;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class FixedCapacityStackOfStrings {
    private int N;
    private String[] a;
    public FixedCapacityStackOfStrings(int cap){
        String[] temp=new String[cap];
        a=temp;
    }
    public void push(String item){
        a[N++]=item;
    }
    public int size(){
        return N;
    }
    public String pop(){
        return a[--N];
    }
    public boolean isEmpty(){
        return N==0;
    }
    //添加的isFull方法，用于判断数组是否已满
    public boolean isFull(){
        return N==a.length;
    }
    public static void main(String[] args){
        FixedCapacityStackOfStrings strs=new FixedCapacityStackOfStrings(100);
        strs.push("My");
        strs.push("name");
        strs.push("is");
        strs.push("yyc");
    }
}
