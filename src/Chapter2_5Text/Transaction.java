package Chapter2_5Text;

import edu.princeton.cs.algs4.Date;

import java.util.Comparator;

public class Transaction {  //根据金融交易的不同属性自定义比较器
    private String who;
    private Date when;
    private double amount;

    public static void sort(Object[] a, Comparator c) {  //在参数c中传入多种Comparator可以实现针对对象的不同属性进行排序的方法，例如传入下方的new Transaction.WhenOrder()
        int N = a.length;
        for (int i = 1; i < N; i++)
            for (int j = i; j > 0 && less(c, a[j], a[j - 1]); j--)
                exch(a, j, j - 1);
    }

    private static boolean less(Comparator c, Object v, Object w) {
        return c.compare(v, w) < 0;
    }

    private static void exch(Object[] a, int i, int j) {
        Object t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static class WhenOrder implements Comparator<Transaction> {  //根据日期进行排序的比较器
        public int compare(Transaction v, Transaction w) {
            return v.when.compareTo(w.when);
        }
    }

    public static class HowMuchOrder implements Comparator<Transaction> {  //根据金额大小进行排序的比较器
        public int compare(Transaction v, Transaction w) {
            if (v.amount < w.amount) return -1;
            else if (v.amount > w.amount) return +1;
            else return 0;
        }
    }
}
