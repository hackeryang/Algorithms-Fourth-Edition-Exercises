package Chapter1_1High;

//Exercise 1.1.27
public class BinomialSample {  //二项分布发生k次的概率
    /*
     * 使用一个二维数组来存储各项二项分布的概率
     * 行代表重复N次实验，列代表发生k次，所以在下面循环条件中需要j<=i
     * */
    public static double[][] binomial(int N, int k, double p) {
        double[][] array = new double[N + 1][k + 1];
        //给二维数组初始化第一列，避免下面执行时出现数组下标越界
        array[0][0] = 1.0;
        for (int i = 1; i < N + 1; i++)
            array[i][0] = array[i - 1][0] * (1 - p);
        for (int i = 1; i < N + 1; i++)
            for (int j = 1; j <= i && j < k + 1; j++)
                array[i][j] = (1 - p) * array[i - 1][j] + p * array[i - 1][j - 1]; //前i-1次实验中已经发生j-1次的概率加上前i-1次实验中没有已经发生j次的概率
        return array;
    }

    public static void main(String[] args) {
        double[][] array = binomial(100, 50, 0.25); //100次实验中发生50次的概率，每一次实验中发生的概率为0.25
        System.out.println(array[100][50]);
    }
}
