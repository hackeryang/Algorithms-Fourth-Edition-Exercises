package Chapter1_1Low;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

//Exercise 1.1.21
public class Tabulate {
    public static void main(String[] args) {
        int M = 3;
        int index = 0;
        String[] strs = new String[M];
        while (index < M)
            strs[index++] = StdIn.readLine();
        for (int i = 0; i < strs.length; ++i) {
            String[] arr = strs[i].split("\\s+");
            double temp = Double.parseDouble(arr[1]) / Double.parseDouble(arr[2]);
            StdOut.printf("%-10s %-10s %-13.3f\n", arr[0], arr[1], arr[2], temp);
        }
    }
}
