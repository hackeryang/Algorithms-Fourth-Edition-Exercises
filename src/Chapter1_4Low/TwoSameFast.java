package Chapter1_4Low;

import edu.princeton.cs.algs4.In;

import java.util.Arrays;

public class TwoSameFast {
    public static int twoSameFast(long[] a){
        int cnt=0;
        for(int i=0;i<a.length-1;i++){
            if(a[i]==a[i+1]){
                cnt++;
            }
        }
        return cnt;
    }
    public static void main(String[] args){
        String filePathString=System.getProperty("user.dir");
        String intFileString=filePathString+"/src/1Kints.txt";
        In in=new In(intFileString);
        long[] a=in.readAllLongs();
        Arrays.sort(a);
        System.out.println("相同的整数的数量为："+twoSameFast(a));
    }
}
