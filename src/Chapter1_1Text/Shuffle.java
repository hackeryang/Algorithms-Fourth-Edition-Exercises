package Chapter1_1Text;

import edu.princeton.cs.algs4.StdRandom;

public class Shuffle {  //随机将double数组中的元素排序
    //随机将double数组中的元素排序
    public static void shuffle(double[] a){
        int N=a.length;
        for(int i=0;i<N;i++){
            //将a[i]和a[i+N-1]中任意一个元素交换
            int r=i+ StdRandom.uniform(N-i);
            double temp=a[i];
            a[i]=a[r];
            a[r]=temp;
        }
    }
}
