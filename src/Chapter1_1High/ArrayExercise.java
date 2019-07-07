package Chapter1_1High;

import java.util.Arrays;

//Exercise 1.1.30
public class ArrayExercise {
    /*
     * 求最大公约数
     * */
    public static int gcd(int m, int n) {
        if (n == 0)
            return m;
        while (m % n != 0) {
            int temp = m % n;
            m = n;
            n = temp;
        }
        return n;
    }

    /*
     * 两数是否互质
     * */
    public static boolean isCoprime(int m, int n) {
        if (1 == gcd(m, n)) {
            return true;
        }
        return false;
    }

    public static boolean[][] boolArray(int N) {
        boolean[][] resultAry = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                //如果是第0行或者第0列
                if (i == 0 || j == 0) {
                    resultAry[i][j] = false;
                } else {
                    resultAry[i][j] = isCoprime(i, j) ? true : false;
                }
            }
        }
        return resultAry;
    }

    public static void main(String[] args) {
        boolean[][] resultArray = boolArray(9);
        for (int i = 0; i < resultArray.length; i++) {
            System.out.println(Arrays.toString(resultArray[i]));
        }
    }
}
