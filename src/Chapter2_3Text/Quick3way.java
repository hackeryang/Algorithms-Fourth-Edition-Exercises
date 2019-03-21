package Chapter2_3Text;

import edu.princeton.cs.algs4.StdRandom;

//三向切分的快速排序，是从左到右遍历数组一次，维持一个指针lt使a[lo..lt-1]中的元素都小于a[lo]，一个指针gt使a[gt+1..hi]中的元素都大于a[lo]，一个指针i使a[lt..i-1]中的元素都等于a[lo]，而a[i..gt]中的元素还未确定。
public class Quick3way {
    public static void sort(Comparable[] a){
        StdRandom.shuffle(a);  //将数组内元素打乱顺序
        sort(a,0,a.length-1);
    }
    private static void sort(Comparable[] a,int lo,int hi){
        if(hi<=lo) return;
        int lt=lo,i=lo+1,gt=hi;
        Comparable v=a[lo]; //这边v为定值，不再随循环中的a[lo]变化
        while(i<=gt){
            int cmp=a[i].compareTo(v);
            if(cmp<0) exch(a,lt++,i++);
            else if(cmp>0) exch(a,i,gt--);
            else i++;
        }  //现在a[lo..lt-1]<v=a[lt..gt]<a[gt+1..hi]成立
        sort(a,lo,lt-1);
        sort(a,gt+1,hi);
    }
    private static void exch(Comparable[] a,int i,int j){
        Comparable t=a[i];
        a[i]=a[j];
        a[j]=t;
    }
}
