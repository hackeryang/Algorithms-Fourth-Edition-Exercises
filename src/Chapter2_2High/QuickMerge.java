package Chapter2_2High;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class QuickMerge {  //Exercise 2.2.10
    private static Comparable[] aux;

    public static void merge(Comparable[] a,int lo,int mid,int hi){
        int i=lo,j=hi;  //i从左到右扫描，j从右到左扫描
        for(int k=lo;k<=mid;k++){
            aux[k]=a[k];
        }
        for(int k=mid+1;k<=hi;k++){
            aux[k]=a[hi+mid+1-k];  //按降序将a[]后半部分复制到aux[]，这样将i从左到右扫描，j从右到左扫描，就相当于右半边降序排列前，左右两边各从左到右扫描比较
        }
        for(int k=lo;k<=hi;k++){
            if(less(aux[j],aux[i])){
                a[k]=aux[j--];  //右半边的当前元素小于左半边的当前元素，取右半边的元素
            }else{
                a[k]=aux[i++];  //右半边的当前元素大于左半边的当前元素，取左半边的元素
            }
        }
    }
    private static boolean less(Comparable v,Comparable w){return v.compareTo(w)<0;}
    public static void sort(Comparable[] a){
        aux=new Comparable[a.length];
        sort(a,0,a.length-1);
    }
    private static void sort(Comparable[] a,int lo,int hi){
        if(hi<=lo)
            return;
        int mid=lo+(hi-lo)/2;
        sort(a,lo,mid);  //对数组左半边进行左右两部分递归分解
        sort(a,mid+1,hi);  //对数组右半边进行左右两部分递归分解
        merge(a,lo,mid,hi);  //由于上两行的从顶部向底部递归分解，这里从底向上递归对左右两半部分进行归并排序
    }
    private static void exch(Comparable[] a,int i,int j){
        Comparable t=a[i];
        a[i]=a[j];
        a[j]=t;
    }
    private static void show(Comparable[] a){
        for(int i=0;i<a.length;i++){
            StdOut.print(a[i]+" ");
        }
        StdOut.println();
    }
    public static boolean isSorted(Comparable[] a){
        for(int i=0;i<a.length;i++){
            if(less(a[i],a[i-1])){
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args){
        String[] a=new In().readAllStrings();
        sort(a);
        assert isSorted(a);
        show(a);
    }
}
