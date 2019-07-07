package Chapter1_4High;

public class TwoSumFaster {
    public int twoSumFaster(int[] a) {
        int cnt = 0;
        int len = a.length;
        for (int j = 0, k = len - 1; j < k; ) {
            if (a[j] + a[k] < 0) {
                j++;
            } else if (a[j] + a[k] > 0) {
                k--;
            } else {
                j++;
                k--;
                ++cnt;
            }
        }
        return cnt;
    }
}
