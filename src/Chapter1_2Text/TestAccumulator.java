package Chapter1_2Text;

import edu.princeton.cs.algs4.Accumulator;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class TestAccumulator {
    public static void main(String[] args){
        int T=Integer.parseInt(args[0]);
        Accumulator a=new Accumulator();
        for(int t=0;t<T;t++)
            a.addDataValue(StdRandom.random());
        StdOut.println(a);
    }
    private double total;
    private int N;
    public void addDataValue(double val){
        N++;
        total+=val;
    }
    public double mean(){
        return total/N;
    }
    public String toString(){
        return "Mean ("+N+" values): "+String.format("%7.5f",mean());
    }
}
