package Chapter2_2Low;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Merge {  //Exercise 2.2.9
    public static void merge(Comparable[] a,int lo,int mid,int hi,Comparable[] aux){
        //将a[lo..mid]和a[mid+1..hi]归并
        int i=lo,j=mid+1;
        for(int k=lo;k<=hi;k++){  //将a[lo..hi]复制到aux[lo..hi]
            aux[k]=a[k];
        }
        for(int k=lo;k<=hi;k++){
            //归并回到a[lo..hi]
            if(i>mid){
                a[k]=aux[j++];  //左半边用尽，取右半边的元素
            }else if(j>hi){
                a[k]=aux[i++];  //右半边用尽，取左半边的元素
            }else if(less(aux[j],aux[i])){
                a[k]=aux[j++];  //右半边的当前元素小于左半边的当前元素，取右半边的元素
            }else{
                a[k]=aux[i++];  //右半边的当前元素大于左半边的当前元素，取左半边的元素
            }
        }
    }
    private static boolean less(Comparable v,Comparable w){return v.compareTo(w)<0;}
    public static void sort(Comparable[] a){
        Comparable[] aux=new Comparable[a.length];
        sort(a,0,a.length-1,aux);
    }
    private static void sort(Comparable[] a,int lo,int hi,Comparable[] aux){
        if(hi<=lo){
            return;
        }
        int mid=lo+(hi-lo)/2;
        sort(a,lo,mid,aux);  //对数组左半边进行左右两部分递归分解
        sort(a,mid+1,hi,aux); //对数组右半边进行左右两部分递归分解
        merge(a,lo,mid,hi,aux);  //由于上两行的从顶部向底部递归分解，这里从底向上递归对左右两半部分进行归并排序
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
