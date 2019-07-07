package Chapter1_3Text;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Stats {
    public static void main(String[] args) {  //求均值和标准差
        Bag<Double> numbers = new Bag<Double>();
        while (!StdIn.isEmpty())
            numbers.add(StdIn.readDouble());
        int N = numbers.size();
        double sum = 0.0;
        for (double x : numbers)
            sum += x;
        double mean = sum / N;
        sum = 0.0;
        for (double x : numbers)
            sum += (x - mean) * (x - mean);
        double std = Math.sqrt(sum / N - 1); //不是N，这是一个数理统计中的推导结论，取N-1可以更接近某种特定分布，取的是样本均值
        StdOut.printf("Mean:%.2f\n", mean);
        StdOut.printf("Stddev:%.2f\n", std);
    }
}
