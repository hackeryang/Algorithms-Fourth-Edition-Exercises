package Chapter2_3High;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Quick3WayPartition {
    //Exercise 2.3.22，快速三向切分
    //使用插入排序的阈值，必须大于等于1
    private static final int INSERTION_SORT_CUTOFF=8;
    //使用三个数的中位数切分的阈值
    private static final int MEDIAN_OF_3_CUTOFF=40;
    private Quick3WayPartition(){

    }
    private static boolean less(Comparable v,Comparable w){return v.compareTo(w)<0;}
    private static void exch(Object[] a,int i,int j){
        Object swap=a[i];
        a[i]=a[j];
        a[j]=swap;
    }
    private static void insertionSort(Comparable[] a,int lo,int hi){  //插入排序
        for(int i=lo;i<=hi;i++)
            for(int j=i;j>lo && less(a[j],a[j-1]);j--)
                exch(a,j,j-1);
    }
    //在a[i]，a[j]，a[k]中找到中位数，并且返回该中位数的索引
    private static int median3(Comparable[] a,int i,int j,int k){
        /*
        * （1）如果a[i]<a[j]，并且a[j]<a[k]，则中位数索引返回j，在a[i]<a[j]情况下，a[j]>a[k]时，若a[i]<a[k]则中位数索引
        * 返回k，如果a[i]>a[k]则中位数索引返回i；（2）如果a[i]>a[j]，且a[k]<a[j]，中位数索引返回j，在a[i]>a[j]的情况下，
        * a[k]>a[j]时，若a[k]<a[i]时中位数索引返回k，若a[k]>a[i]，则中位数索引返回i
        * */
        return (less(a[i],a[j])?(less(a[j],a[k])?j:less(a[i],a[k])?k:i):(less(a[k],a[j])?j:less(a[k],a[i])?k:i));
    }
    private static boolean eq(Comparable v,Comparable w){return v.compareTo(w)==0;}  //判断两个元素是否相等
    //将数组以升序排序
    public static void sort(Comparable[] a){
        sort(a,0,a.length-1);
    }
    private static void sort(Comparable[] a,int lo,int hi){
        int n=hi-lo+1;
        //如果数组足够小，就使用插入排序，速度最快
        if(n<=INSERTION_SORT_CUTOFF){
            insertionSort(a,lo,hi);
            return;
        }else if(n<=MEDIAN_OF_3_CUTOFF){  //使用3个元素中的中位数作为切分元素
            int m=median3(a,lo,lo+n/2,hi);
            exch(a,m,lo);
        }else{  //利用Tukey ninther方法来计算中位数，即每隔3个元素计算一次中位数，然后再求几个中位数的中位数，节省计算次数
            int eps=n/8;
            int mid=lo+n/2;
            int m1=median3(a,lo,lo+eps,lo+eps+eps);
            int m2=median3(a,mid-eps,mid,mid+eps);
            int m3=median3(a,hi-eps-eps,hi-eps,hi);
            int ninther=median3(a,m1,m2,m3);
            exch(a,ninther,lo);
        }
        //Bentley-McIlroy三向切分
        int i=lo,j=hi+1;
        int p=lo,q=hi+1;
        Comparable v=a[lo];  //此处的v为定值，不会再因a[lo]的改变而改变
        while(true){
            while(less(a[++i],v))  //将i从左向右扫描，到达最右端停止
                if(i==hi)
                    break;
            while(less(v,a[--j]))  //将j从右向左扫描，到达最左端停止
                if(j==lo)
                    break;
            //向左向右的两个指针交叉时，如果a[i]与v相等，则将a[i]与a[p]交换并将指针p向后移一位，保证p左边的元素都等于v
            if(i==j && eq(a[i],v))
                exch(a,++p,i);
            if(i>=j)
                break;
            exch(a,i,j);  //在a[i]>v和a[j]<v的时候将两者交换，保证i前面的元素小于v，j后面的元素大于v
            if(eq(a[i],v))  //如果a[i]与v相等，则将指针p向后移一位并将a[i]与a[p]交换，保证p左边的元素都等于v
                exch(a,++p,i);
            if(eq(a[j],v))  //如果a[j]与v相等，则将指针q向前移一位并将a[j]与a[q]交换，保证q右边的元素都等于v
                exch(a,--q,j);
        }
        i=j+1;  //在保证p到i之间元素小于v，j到q之间元素大于v，i与j相遇后，继续将两个指针向左向右扫描，并相互错开
        for(int k=lo;k<=p;k++)
            exch(a,k,j--);  //将原本p到i（现在左侧指针是j）之间小于v的元素交换到开头，将j后面的元素交换成等于v的元素
        for(int k=hi;k>=q;k--)
            exch(a,k,i++);  //将原本j（现在右侧指针为i）到q之间大于v的元素交换到末尾，将i前面的元素交换成等于v的元素，这样j到i之间的元素都等于v
        sort(a,lo,j);  //将lo到j之间小于v的元素排序
        sort(a,i,hi);  //将i到hi之间大于v的元素排序
    }
    private static boolean isSorted(Comparable[] a){
        for(int i=1;i<a.length;i++)
            if(less(a[i],a[i-1]))
                return false;
        return true;
    }
    private static void show(Comparable[] a){
        for(int i=0;i<a.length;i++){
            StdOut.println(a[i]);
        }
    }
    public static void main(String[] args){
        String[] a= StdIn.readAllStrings();
        Quick3WayPartition.sort(a);
        assert isSorted(a);
        show(a);
    }
}
