package Chapter1_4High;

import java.util.Arrays;

//Exercise 1.4.16
public class ClosestPairFaster {
    public static void closestPairFaster(double[] x) {
        double minNum = Double.MAX_VALUE;
        int minI = 0;
        for (int i = 0; i < x.length - 1; i++) {
            if (x[i + 1] - x[i] < minNum) {
                minI = i;
                minNum = x[i + 1] - x[i];
            }
        }
        System.out.println("最接近的两个数为：" + x[minI]);
        System.out.println("和: " + x[minI + 1]);
    }

    public static void main(String[] args) {
        double[] a = {1, 2, 3, 4, 5, 888, 76, 45};
        Arrays.sort(a);
        closestPairFaster(a);
    }
}
