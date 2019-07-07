package Chapter3_5Text;

import edu.princeton.cs.algs4.ST;

public class SparseVector {  //完成两条向量点乘的稀疏向量
    private ST<Integer, Double> st;

    public SparseVector() {
        st = new ST<Integer, Double>();
    }

    public int size() {
        return st.size();
    }

    public void put(int i, double x) {
        st.put(i, x);
    }

    public double get(int i) {
        if (!st.contains(i)) return 0.0;
        else return st.get(i);
    }

    public double dot(double[] that) {
        double sum = 0.0;
        for (int i : st.keys())
            sum += that[i] * this.get(i);
        return sum;
    }
}
