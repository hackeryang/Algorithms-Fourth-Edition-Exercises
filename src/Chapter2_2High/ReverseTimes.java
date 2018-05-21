package Chapter2_2High;

import edu.princeton.cs.algs4.In;

public class ReverseTimes {
    //Exercise 2.2.19，统计给定数组中倒置数量，即插入排序所需的交换次数
    private static Comparable[] aux;
    private static int merge(Comparable[] a,int lo,int mid,int hi,int inversions){
        int i=lo,j=mid+1;
        for(int k=lo;k<=hi;k++){
            aux[k]=a[k];
        }
        for(int k=lo;k<=hi;k++){
            if(i>mid){
                a[k]=aux[j++];  //左半边用尽，取右半边的元素
            }else if(j>hi){
                a[k]=aux[i++];  //右半边用尽，取左半边的元素
            }else if(less(aux[j],aux[i])){
                a[k]=a[j++];  //右半边的当前元素小于左半边的当前元素，取右半边的元素，这种情况下会出现倒置
                inversions+=j-k-1;  //计算倒置数量
            }else{
                a[k]=aux[i++];  //右半边的当前元素大于左半边的当前元素，取左半边的元素
            }
        }
        return inversions;
    }
    private static boolean less(Comparable v,Comparable w){return v.compareTo(w)<0;}
    public static int count(Comparable[] a){
        aux=new Comparable[a.length];
        return count(a,0,a.length-1,0);
    }
    private static int count(Comparable[] a,int lo,int hi,int inversions){
        if(hi<=lo){
            return inversions;  //当递归到底部，间隔为0时一层层往上返回倒置数量
        }
        int mid=lo+(hi-lo)/2;
        inversions=count(a,lo,mid,inversions);  //递归累加左半部分的倒置数量
        inversions=count(a,mid+1,hi,inversions);  //递归累加右半部分的倒置数量
        return merge(a,lo,mid,hi,inversions);  //归并左右两部分的倒置数量
    }
    public static void main(String[] args){
        String[] a=new In().readAllStrings();
        System.out.println(count(a));
    }
}
