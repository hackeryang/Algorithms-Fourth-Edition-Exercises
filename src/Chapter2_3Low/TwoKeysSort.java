package Chapter2_3Low;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class TwoKeysSort {
    //Exercise 2.3.5，将已知只有两种主键值的数组排序
    public static void sort(Comparable[] a) {
        for (int i = 1, lo = 0, hi = a.length - 1; i <= hi; ) {
            int cmp = a[i].compareTo(a[lo]);
            if (cmp < 0) {
                exch(a, lo++, i++);
            } else if (cmp > 0) {
                exch(a, i, hi--);
            } else {
                i++;
            }
        }
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            StdOut.print(a[i] + " ");
        }
        StdOut.println();
    }

    public static void main(String[] args) {
        String[] a = new In().readAllStrings();
        sort(a);
        show(a);
    }
}
