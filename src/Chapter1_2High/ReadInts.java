package Chapter1_2High;

import edu.princeton.cs.algs4.In;

public class ReadInts {
    public static int[] readInts(String name){
        In in=new In(name);
        String input=in.readAll();
        String[] words=input.split("\\s+");
        int[] ints=new int[words.length];
        for(int i=0;i<words.length;i++){
            ints[i]=Integer.parseInt(words[i]);
        }
        return ints;
    }
    public static void main(String[] args){
        int[] ints=readInts(args[0]);
        for(int i=0;i<ints.length;i++){
            System.out.println(ints[i]);
        }
    }
}
