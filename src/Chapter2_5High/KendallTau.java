package Chapter2_5High;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;

import static Chapter2_2High.ReverseTimes.count;

public class KendallTau {
    //Exercise 2.5.19，在线性对数时间内计算两组排列之间的Kendall tau距离
    public static int distance(int[] a, int[] b) {
        int n = a.length;
        int[] aIndex = new int[n];
        for (int i = 0; i < n; i++) {
            aIndex[a[i]] = i;  //只要a[i]不是大小顺序的，就可以产生倒序数
        }
        Integer[] bIndexInA = new Integer[n];
        for (int i = 0; i < n; i++) {
            bIndexInA[i] = aIndex[b[i]];
        }
        return count(bIndexInA);
    }

    public static void main(String[] args) {
        int n = StdIn.readInt();
        int[] a = new int[n];
        int[] b = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = i;
            b[i] = i;
        }
        StdRandom.shuffle(a);
        for (int i = 0; i < n; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
        StdRandom.shuffle(b);
        for (int i = 0; i < n; i++) {
            System.out.print(b[i] + " ");
        }
        System.out.println();
        System.out.println("distance: " + distance(a, b));
    }
}
