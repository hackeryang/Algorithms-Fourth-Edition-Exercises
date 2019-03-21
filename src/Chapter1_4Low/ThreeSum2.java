package Chapter1_4Low;

import edu.princeton.cs.algs4.BinarySearch;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

//exercise 1.4.2
public class ThreeSum2 {
    public static int count(int[] a){
        //计算和为0的三元组的数目
        Arrays.sort(a);
        int N=a.length;
        int cnt=0;
        for(int i=0;i<N;i++)
            for(int j=i+1;j<N;j++)
                if(BinarySearch.rank(-a[i]-a[j],a)>j)
                    cnt++;
        return cnt;
    }
    public static void main(String[] args){
        String filePathString=System.getProperty("user.dir");
        String intFileString=filePathString+"/src/"+"1Kints.txt";
        In in=new In(intFileString);
        int[] a=in.readAllInts();
        StdOut.println(count(a));
    }
}
