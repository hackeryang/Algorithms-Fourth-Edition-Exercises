package Chapter2_5High;

import edu.princeton.cs.algs4.StdIn;

import java.util.Arrays;

public class SPT {
    //Exercise 2.5.12，使用Job.java的数据类型和方法
    public static void main(String[] args){
        int n= StdIn.readInt();
        Job[] jobs=new Job[n];
        for(int i=0;i<n;i++){
            jobs[i]=new Job(StdIn.readString(),StdIn.readDouble());
        }
        Arrays.sort(jobs);
        for(int i=0;i<n;i++){
            System.out.println(jobs[i]);
        }
    }
}
