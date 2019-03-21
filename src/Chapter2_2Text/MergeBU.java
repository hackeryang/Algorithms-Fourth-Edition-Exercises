package Chapter2_2Text;

public class MergeBU {  //自底向上的归并排序
    private static Comparable[] aux; //归并所需的辅助数组，多次遍历整个数组，根据子数组大小进行两两归并，四四归并，八八归并一直下去。每下一轮中子数组的大小会翻倍
    public static void merge(Comparable[] a,int lo,int mid,int hi){
        //将a[lo..mid]和a[mid+1..hi]归并
        int i=lo,j=mid+1;
        for(int k=lo;k<=hi;k++)  //将a[lo..hi]复制到aux[lo..hi]
            aux[k]=a[k];
        for(int k=lo;k<=hi;k++){
            //归并回到a[lo..hi]
            if(i>mid) a[k]=aux[j++]; //左半边用尽，取右半边的元素
            else if(j>hi) a[k]=aux[i++]; //右半边用尽，取左半边的元素
            else if(less(aux[j],aux[i])) a[k]=aux[j++]; //右半边的当前元素小于左半边的当前元素，取右半边的元素
            else a[k]=aux[i++]; //右半边的当前元素大于左半边的当前元素，取左半边的元素
        }

    }
    private static boolean less(Comparable v,Comparable w){return v.compareTo(w)<0;}
    public static void sort(Comparable[] a){
        //进行lgN次两两归并
        int N=a.length;
        aux=new Comparable[N];
        for(int sz=1;sz<N;sz=sz+sz)  //sz：子数组大小，每sz个元素为一个子数组
            for(int lo=0;lo<N-sz;lo+=sz+sz) //lo:子数组索引
                merge(a,lo,lo+sz-1,Math.min(lo+sz+sz-1,N-1));  //lo移动sz位到达中位，中位再移动sz位到达hi
    }
}
