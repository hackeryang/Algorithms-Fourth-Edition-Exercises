package Chapter1_1Low;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

//Exercise 1.1.23
public class BinarySearchWithParams {
    public static int rank(int key,int[] a){
        int lo=0;
        int hi=a.length-1;
        while(lo<=hi){
            int mid=lo+(hi-lo)/2;  //也可以为(lo + hi) >> 1，二进制中按位向右移一位等同于除以2
            if(key<a[mid])
                hi=mid-1;
            else if(key>a[mid])
                lo=mid+1;
            else return mid;
        }
        return -1;
    }
    public static void main(String[] args){
        //这里参数symbol本来是要传进来的，这里写死，是为了demo方便
        char symbol='+';
        int[] whitelist={1,2,3,4,5,6,7,11,222};
        Arrays.sort(whitelist);
        while(!StdIn.isEmpty()){
            int key=StdIn.readInt();
            int found=rank(key,whitelist);
            if('+'==symbol && found==-1)
                StdOut.println(key);
            if('-'==symbol && found!=-1)
                StdOut.println(key);
        }
    }
}
