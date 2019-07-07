package Chapter2_2Text;

public class Merge { //自顶向下的归并排序，先将所有元素复制到aux[]中，然后再归并回a[]中
    private static Comparable[] aux; //归并所需的辅助数组

    public static void sort(Comparable[] a) {
        aux = new Comparable[a.length];  //一次性分配空间，这里必须要初始化容量为a.length，否则下面for循环k<=hi情况下递增到最后会报错NullPointerException
        sort(a, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        //将数组a[lo..hi]排序
        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;  //也可以int mid=(lo+hi)>>1，二进制中按位向右移一位等同于除以2
        sort(a, lo, mid); //递归将数组左半边排序
        sort(a, mid + 1, hi); //递归将数组右半边排序
        merge(a, lo, mid, hi); //归并左右半边的结果
    }

    public static void merge(Comparable[] a, int lo, int mid, int hi) {
        //将a[lo..mid]和a[mid+1..hi]归并
        int i = lo, j = mid + 1;  //设置两个分别从数组头部和中间位置开始的两个游标，将数组分为左右两半进行归并比较
        for (int k = lo; k <= hi; k++)
            aux[k] = a[k];  //将辅助数组填满原数组原来顺序的元素，因为最后原数组会被填入排序过的元素
        for (int k = lo; k <= hi; k++) {
            //归并回到a[lo..hi]
            if (i > mid) a[k] = aux[j++];  //左半边用尽，取右半边的元素
            else if (j > hi) a[k] = aux[i++];  //右半边用尽，取左半边的元素
            else if (less(aux[j], aux[i])) a[k] = aux[j++];  //出现逆序的数字对，右半边的当前元素小于左半边的当前元素，取右半边的元素
            else a[k] = aux[i++];  //右半边的当前元素大于左半边的当前元素，取左半边的元素
        }
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }
}
