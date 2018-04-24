package Chapter1_4High;

import java.util.Arrays;

public class FastestPair {  //算法复杂度为常数，因为调用sort排序之后，直接取最大最小值即可，比较时间与数组长度无关
    public static void fastestPair(double[] x){
        System.out.println("最遥远的两个数为："+x[0]);
        System.out.println("和："+x[x.length-1]);
    }
    public static void main(String[] args){
        double[] a={1,2,3,4,5,888,76,45};
        Arrays.sort(a);
        fastestPair(a);
    }
}
