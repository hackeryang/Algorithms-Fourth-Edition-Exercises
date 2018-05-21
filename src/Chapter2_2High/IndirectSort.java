package Chapter2_2High;

import edu.princeton.cs.algs4.In;

public class IndirectSort {
    //Exercise 2.2.20,不改变数组的归并排序
    private static int[] aux;
    private static int[] merge(Comparable[] a,int lo,int mid,int hi,int[] perm){
        int i=lo,j=mid+1;
        for(int k=lo;k<=hi;k++){
            aux[k]=perm[k];
        }
        for(int k=lo;k<=hi;k++){
            if(i>mid){
                perm[k]=aux[j++];  //左半边用尽，取右半边的元素
            }else if(j>hi){
                perm[k]=aux[i++];  //右半边用尽，取左半边的元素
            }else if(less(a[aux[j]],a[aux[i]])){  //aux[i]=perm[i]=i，所以a[aux[j]]=a[j]
                perm[k]=aux[j++];  //a[]中右半边的当前元素小于左半边的当前元素，取aux[]右半边的元素
            }else{
                perm[k]=aux[i++];  //a[]中右半边的当前元素大于左半边的当前元素，取aux[]左半边的元素
            }
        }
        return perm;
    }
    private static boolean less(Comparable v,Comparable w){return v.compareTo(w)<0;}
    public static int[] sort(Comparable[] a){
        int[] perm=new int[a.length];
        aux=new int[a.length];
        for(int i=0;i<perm.length;i++){
            perm[i]=i;
        }
        return sort(a,0,a.length-1,perm);
    }
    private static int[] sort(Comparable[] a,int lo,int hi,int[] perm){
        if(hi<=lo){
            return perm;
        }
        int mid=lo+(hi-lo)/2;
        perm=sort(a,lo,mid,perm);  //对数组左半边进行左右两部分递归分解
        perm=sort(a,mid+1,hi,perm);  //对数组右半边进行左右两部分递归分解
        perm=merge(a,lo,mid,hi,perm);  //由于上两行的从顶部向底部递归分解，这里从底向上递归对左右两半部分对进行归并排序
        return perm;  //排序结果是原数组中第i小的元素的位置，而不是元素的值本身，数组perm的元素本来就是顺序自然数i，所以返回它，没有改变原来的数组a
    }
    public static void main(String[] args){
        String[] a=new In().readAllStrings();
        int[] perm=sort(a);
        for(int i=0;i<perm.length;i++){
            System.out.print(perm[i]+" ");
        }
        System.out.println();
    }
}
