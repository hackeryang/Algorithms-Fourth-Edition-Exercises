package Chapter1_4High;

import java.util.Arrays;

public class FractionBinarySearch {
    public static int rank(double key,double[] a){
        //数组必须是有序的
        int lo=0;
        int hi=a.length-1;
        double threshold=1.0/(a.length*a.length);
            while(lo<=hi){
                int mid=lo+(hi-lo)/2;
                //这里的判断条件从等于变为相差小于某个极小值
                if(Math.abs(a[mid]-key)<=threshold)
                    return mid;
                else if(key>a[mid])
                    lo=mid+1;
                else hi=mid-1;
            }
        return -1;
    }
    public static void main(String[] args){
        double[] fractions={1.0/2.0,2.0/3.0,3.0/4.0,4.0/5.0,5.0/6.0};
        Arrays.sort(fractions);
        int index=rank(4.0/5.0,fractions);
        System.out.println("4.0/5.0在第"+index+"个");
    }
}
