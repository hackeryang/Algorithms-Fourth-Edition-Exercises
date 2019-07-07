package Chapter1_2Low;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

//Exercise 1.2.10
public class VisualCounter {
    private long N;
    private double total;

    public VisualCounter(long trails, double max) {
        StdDraw.setXscale(0, trails);
        StdDraw.setYscale(-max, max);
        StdDraw.setPenRadius(.005f);
    }

    public void increment() {
        N++;
        total++;
        StdDraw.setPenColor(StdDraw.DARK_GRAY);
        StdDraw.point(N, total);
    }

    public void decrement() {
        N++;
        total--;
        StdDraw.setPenColor(StdDraw.DARK_GRAY);
        StdDraw.point(N, total);
    }

    public static void main(String[] args) {
        VisualCounter counter = new VisualCounter(300, 150.0);
        for (int i = 0; i < 10000; i++)
            if (StdRandom.random() > 0.5) {
                counter.increment();
            } else {
                counter.decrement();
            }
        StdOut.println(counter);
    }
}
