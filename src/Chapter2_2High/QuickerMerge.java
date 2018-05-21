package Chapter2_2High;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class QuickerMerge {
    private static int CUTOFF=15;
    public static void merge(Comparable[] src,Comparable[] dest,int lo,int mid,int hi){
        int i=lo,j=mid+1;
        for(int k=lo;k<=hi;k++){
            if(i>mid){
                dest[k]=src[j++];  //左半边用尽，取右半边的元素
            }else if(j>hi){
                dest[k]=src[i++];  //右半边用尽，取左半边的元素
            }else if(less(src[j],src[i])){
                dest[k]=src[j++];  //右半边的当前元素小于左半边的当前元素，取右半边的元素
            }else{
                dest[k]=src[i++];   //右半边的当前元素大于左半边的当前元素，取左半边的元素
            }
        }
    }
    private static boolean less(Comparable v,Comparable w){return v.compareTo(w)<0;}
    public static void sort(Comparable[] a){
        Comparable[] aux=a.clone();  //将比较器数组a[]克隆到aux[]中
        sort(aux,a,0,a.length-1);
    }
    private static void sort(Comparable[] src,Comparable[] dest,int lo,int hi){
        if(hi-lo<CUTOFF){  //小数组情况下使用插入排序，加快小数组的排序速度
            insertionSort(dest,lo,hi);
            return;
        }
        int mid=lo+(hi-lo)/2;
        sort(dest,src,lo,mid);  //在递归中交换参数，避免数组复制，并对数组左半边进行左右两部分递归分解
        sort(dest,src,mid+1,hi);   //在递归中交换参数，避免数组复制，并对数组右半边进行左右两部分递归分解
        if(less(src[mid+1],src[mid])){
            merge(src,dest,lo,mid,hi);  //由于上两行的从顶部向底部递归分解，这里从底向上递归对左右两半部分进行归并排序
        }
    }
    public static void insertionSort(Comparable[] a,int lo,int hi){  //插入排序
        for(int i=lo;i<=hi;i++){
            for(int j=i;j>lo && less(a[j],a[j-1]);j--){   //将a[i]插入到a[i-1]、a[i-2]、a[i-3]...之中
                exch(a,j,j-1);
            }
        }
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
