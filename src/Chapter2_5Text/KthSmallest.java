package Chapter2_5Text;

import edu.princeton.cs.algs4.StdRandom;

public class KthSmallest {
    //找到一组数中的第k小元素
    public static Comparable select(Comparable[] a, int k) {
        StdRandom.shuffle(a);
        int lo = 0, hi = a.length - 1;
        while (hi > lo) {
            int j = partition(a, lo, hi);
            if (j == k) return a[k]; //切分之后，a[j]左边的数小于等于a[j]，右边的数大于等于a[j]，如果j碰巧等于k，则该a[j]就是第k小的数
            else if (j > k) hi = j - 1;
            else if (j < k) lo = j + 1;
        }
        return a[k];
    }

    private static int partition(Comparable[] a, int lo, int hi) {  //快速排序中的切分操作
        //将数组切分为a[lo..i-1]，a[i]，a[i+1..hi]
        int i = lo, j = hi + 1; //向右向左的扫描指针，j为hi+1，这样从右到左扫描会先从hi开始扫描
        Comparable v = a[lo]; //切分元素初始化为第一个元素，且该v以后为定值，就是最初的a[lo]，以后a[lo]的值如何改变都与v无关
        while (true) {
            //扫描左右，检查扫描是否结束并交换元素
            while (less(a[++i], v)) if (i == hi) break;
            while (less(v, a[--j])) if (j == lo) break;
            if (i >= j) break;
            exch(a, i, j);
        }
        exch(a, lo, j); //将v=a[j]放入正确的位置，当扫描指针i和j相遇时，将a[j]与a[lo]交换
        return j;  //a[lo..j-1]<=a[j]<=a[j+1..hi]达成
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
}
