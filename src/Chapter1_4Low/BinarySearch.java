package Chapter1_4Low;

import java.util.Arrays;

//Exercise 1.4.10
public class BinarySearch {
    public int rank(int key, int[] a) {
        //数组必须是有序的
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            //被查找的键要么不存在，要么必然存在于a[lo..hi]之中
            int mid = lo + (hi - lo) / 2;
            if (key < a[mid])
                hi = mid - 1;
            else if (key > a[mid])
                lo = mid + 1;
            else {
                //先向左边查找，但要注意避免越界
                int minIndex = mid;
                while (a[minIndex] == key) {
                    --minIndex;
                    if (minIndex < 0) {
                        break;
                    }
                }
                return minIndex + 1;
            }
        }
        return -1;
    }

    //一种更好的方案
    public int rank2(int[] a, int key) {
        int hi = a.length;
        int lo = 0;
        int mid = 0;
        while (lo < hi) {
            mid = (hi + lo) / 2;
            if (a[mid] < key) {
                lo = mid + 1;
            } else if (a[mid] > key) {
                hi = mid;
            } else if (mid > 0 && a[mid - 1] == key) {
                hi = mid;
            } else {
                return mid;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] b = {1, 2, 3, 5, 4, 5, 6, 77, 7, 8, 8, 9, 1, 11, 22, 234, 90};
        Arrays.sort(b);
        BinarySearch search = new BinarySearch();
        int targetKey = 1;
        int index = search.rank(targetKey, b);
        System.out.println(targetKey + "在第" + index + "个");
    }
}
