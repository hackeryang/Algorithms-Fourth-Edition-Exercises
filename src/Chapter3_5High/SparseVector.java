package Chapter3_5High;

import edu.princeton.cs.algs4.ST;

public class SparseVector {
    //Exercise 3.5.23，为系数二维矩阵设计一组API实现矩阵的加法和乘法操作
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
        if (!st.contains(i)) {
            return 0.0;
        } else {
            return st.get(i);
        }
    }

    public double dot(double[] that) {  //向量点乘
        double sum = 0.0;
        for (int i : st.keys()) {
            sum += that[i] * this.get(i);
        }
        return sum;
    }

    //Exercise 3.5.23，两个长度不同的向量点乘
    public double dot(SparseVector that) {
        double sum = 0.0;
        if (this.st.size() <= that.st.size()) {
            for (int i : this.st.keys()) {
                if (that.st.contains(i)) {
                    sum += this.get(i) * that.get(i);
                }
            }
        } else {
            for (int i : that.st.keys()) {
                if (this.st.contains(i)) {
                    sum += this.get(i) * that.get(i);
                }
            }
        }
        return sum;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i : st.keys()) {
            s.append("(" + i + "，" + st.get(i) + ")");
        }
        return s.toString();
    }
}
