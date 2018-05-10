package Chapter2_1Text;

public class Selection {
    public static void sort(Comparable[] a){
        //将a[]按升序排列
        int N=a.length; //数组长度
        for(int i=0;i<N;i++){
            //将a[i]和a[i+1..N]中最小的元素交换
            int min=i; //最小元素的索引
            for(int j=i+1;j<N;j++)
                if(less(a[j],a[min])) min=j;
            exch(a,i,min);
        }
    }
    private static boolean less(Comparable v,Comparable w){return v.compareTo(w)<0;}
    private static void exch(Comparable[] a,int i,int j){
        Comparable t=a[i];
        a[i]=a[j];
        a[j]=t;
    }
}
