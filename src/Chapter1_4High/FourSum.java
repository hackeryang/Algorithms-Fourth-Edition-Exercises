package Chapter1_4High;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

//Exercise 1.4.14
public class FourSum {
    public int count(long[] a) {  //三个for循环复杂度为N的三次方，故总的复杂度（包括rank)为N的三次方乘以lgN
        int cnt = 0;
        for (int i = 0; i < a.length - 1; i++)
            for (int j = i + 1; j < a.length - 1; j++)
                for (int k = j + 1; k < a.length - 1; k++)
                    if (rank(-a[i] - a[j] - a[k], a) != -1)
                        cnt++;
        return cnt;
    }

    public static int rank(long key, long[] a) { //复杂度为lgN
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
            else
                return mid;
        }
        return -1;
    }

    //复杂度更低的方法
    public int fourSum(long[] a) {
        int len = a.length;
        int cnt = 0;
        /*
         *  一开始的时候，l,i,j在开头三个位置：0，1，2。k在最后一个元素的位置，如果四个数的和大于0，则把k往前移一位，如果找到小于或等于0的和，
         *  j往后移一位，且k向前移一位，l和i暂时不动，如此循环直到j和k相遇或者k比j小，则证明和j有关的第三个for循环一轮结束，将i往后移一位，
         *  j重新移到i现在位置的后一位，即3，k重新移到最后一位，继续第二轮的第三个for循环，直到i移动到倒数第二个的时候，l开始往后移一位，即到1，
         *  直到最后三个数都到达规定的底部则遍历完全完成，比纯粹的暴力遍历要好一点
         * */
        for (int l = 0; l < len - 3; l++) {
            for (int i = l + 1; i < len - 2; i++) {
                for (int j = i + 1, k = len - 1; j < k; ) {
                    if (a[l] + a[i] + a[j] + a[k] > 0) {
                        k--;
                    } else {
                        cnt++;
                        j++;
                        k--;
                    }
                }
            }
        }
        return cnt;
    }

    public static void main(String[] args) {
        String filePathString = System.getProperty("user.dir");
        String intFileString = filePathString + "/src/1Kints.txt";
        In in = new In(intFileString);
        long[] a = in.readAllLongs();
        Arrays.sort(a);
        FourSum sum = new FourSum();
        StdOut.println(sum.fourSum(a) + "对");
    }
}
