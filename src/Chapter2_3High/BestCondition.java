package Chapter2_3High;

import edu.princeton.cs.algs4.StdIn;

public class BestCondition {
    //Exercise 2.3.16
    public static int[] best(int n) {
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = i;
        }
        best(a, 0, n - 1);
        return a;
    }

    private static int[] best(int[] a, int lo, int hi) {
        if (hi <= lo) {
            return a;
        }
        int mid = lo + (hi - lo) / 2;
        a = best(a, lo, mid - 1);  //递归切分左半数组
        a = best(a, mid + 1, hi);  //递归切分右半数组
        exch(a, lo, mid);
        return a;
    }

    private static void exch(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static void main(String[] args) {
        int n = StdIn.readInt();
        int[] a = best(n);
        for (int i = 0; i < n; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }
}
