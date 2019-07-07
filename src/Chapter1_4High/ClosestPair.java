package Chapter1_4High;

import java.util.Arrays;

public class ClosestPair {
    public static void closestPair(double[] a) {
        double smallestValues = Double.MAX_VALUE;
        int smallestIndexI = 0;
        int smallestIndexJ = 0;
        for (int i = 0; i < a.length; i++) {  //存在两个for循环，算法复杂度为N的平方
            for (int j = i + 1; j < a.length; j++) {
                double value = a[j] - a[i];
                if (value < smallestValues) {
                    smallestValues = value;
                    smallestIndexI = i;
                    smallestIndexJ = j;
                }
            }
        }
        System.out.println("最接近的两个数为: " + a[smallestIndexI]);
        System.out.println("和" + a[smallestIndexJ]);
    }

    public static void main(String[] args) {
        double[] b = {1.0, 26, 36, 74, 599, 60, 7, 8};
        Arrays.sort(b);
        closestPair(b);
    }
}
