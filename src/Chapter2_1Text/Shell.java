package Chapter2_1Text;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Shell {  //希尔排序，在插入排序中加入一个外循环来以h为间隔，按照递增序列递减得到
    public static void sort(Comparable[] a){
        //将a[]按升序排列
        int N=a.length;
        int h=1;
        while(h<N/3) h=3*h+1; //1,4,13,40,121,364,1093...
        while(h>=1){
            //将数组变为h有序
            for(int i=h;i<N;i++){
                //将a[i]插入到a[i-h]，a[i-3*h]，a[i-3h]...之中
                for(int j=i;i>=h && less(a[j],a[j-h]);j-=h)
                    exch(a,j,j-h);
            }
            h=h/3;
        }
    }
    private static boolean less(Comparable v,Comparable w){return v.compareTo(w)<0;}
    private static void exch(Comparable[] a,int i,int j){
        Comparable t=a[i];
        a[i]=a[j];
        a[j]=t;
    }
    public static boolean isSorted(Comparable[] a){
        //测试数组元素是否有序
        for(int i=1;i<a.length;i++)
            if(less(a[i],a[i-1])) return false;
        return true;
    }
    private static void show(Comparable[] a){
        //在单行中打印数组
        for(int i=0;i<a.length;i++)
            StdOut.print(a[i]+" ");
        StdOut.println();
    }
    public static void main(String[] args){
        //从标准输入读取字符串，将它们排序并输出
        String[] a= In.readStrings();
        sort(a);
        assert isSorted(a);
        show(a);
    }
}
