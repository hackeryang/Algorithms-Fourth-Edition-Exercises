package Chapter2_4Text;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class HeapSort {
    //堆排序，一开始将原始数组重新组织安排进一个二叉堆中，然后进行下沉排序，从二叉堆中按递减顺序不断重复取出并删除最大元素
    public static void sort(Comparable[] a){
        int N=a.length;
        for(int k=N/2;k>=1;k--)  //从底部倒数第二层开始下沉交换
            sink(a,k,N);
        while(N>1){
            exch(a,1,N--);  //将当前N减小，当前的N后面的最大元素都已有序并被排除出二叉堆，因此只排序N前面的元素
            sink(a,1,N);
        }
    }
    private static boolean less(Comparable[] a,int i,int j){return a[i-1].compareTo(a[j-1])<0;}
    private static void exch(Comparable[] a,int i,int j){
        Comparable t=a[i-1];  //因为二叉堆的索引是1到N，而一般数组是0到N-1，为了与其他排序算法实现一致而减一，即将a[0]至a[N-1]排序
        a[i-1]=a[j-1];
        a[j-1]=t;
    }
    private static void sink(Comparable[] a,int k,int N){
        while(2*k<=N){
            int j=2*k;
            if(j<N && less(a,j,j+1)) j++;
            if(!less(a,k,j)) break;
            exch(a,k,j);
            k=j;
        }
    }
    private static boolean isSorted(Comparable[] a){
        for(int i=1;i<a.length;i++)
            if(less(a,i,i-1)) return false;
        return true;
    }
    private static void show(Comparable[] a){
        for(int i=0;i<a.length;i++){
            StdOut.println(a[i]);
        }
    }
    public static void main(String[] args){
        String[] a= StdIn.readStrings();
        HeapSort.sort(a);
        show(a);
    }
}
