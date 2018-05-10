package Chapter2_3Text;

import edu.princeton.cs.algs4.Insertion;
import edu.princeton.cs.algs4.StdRandom;

public class Quick { //快速排序算法（重要）
    public static void sort(Comparable[] a){
        StdRandom.shuffle(a);  //把数组打乱，消除对输入的依赖
        sort(a,0,a.length-1);
    }
    private static void sort(Comparable[] a,int lo,int hi){
        int M=10; //转换参数M的最佳值与系统有关，但5到15的任意值在大多数情况下都行
        if(hi<=lo+M){
            Insertion.sort(a,lo,hi);  //对于小数组，快速排序比插入排序慢
            return;
        }
        int j=partition(a,lo,hi); //切分成两个子数组
        sort(a,lo,j-1); //将左半部分a[lo.j-1]排序
        sort(a,j+1,hi); //将右半部分a[j+1..hi]排序
    }
    private static int partition(Comparable[] a,int lo,int hi){
        //将数组切分为a[lo..i-1]，a[i]，a[i+1..hi]
        int i=lo,j=hi+1; //向右向左的扫描指针，j为hi+1，这样从右到左扫描会先从hi开始扫描
        Comparable v=a[lo]; //切分元素初始化为第一个元素，且该v以后为定值，就是最初的a[lo]，以后a[lo]的值如何改变都与v无关
        while(true){
            //扫描左右两侧，检查扫描是否结束并交换元素
            while(less(a[++i],v)) if(i==hi) break;
            while(less(v,a[--j])) if(j==lo) break;
            if(i>=j) break;
            exch(a,i,j);
        }
        exch(a,lo,j);  //将v=a[j]放入正确的位置，当扫描指针i和j相遇时，将a[j]与a[lo]交换
        return j;  //a[lo..j-1]<=a[j]<=a[j+1..hi]达成
    }
    private static boolean less(Comparable v,Comparable w){return v.compareTo(w)<0;}
    private static void exch(Comparable[] a,int i,int j){
        Comparable t=a[i];
        a[i]=a[j];
        a[j]=t;
    }
}
