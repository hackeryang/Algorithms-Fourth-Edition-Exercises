package Chapter3_1Low;

import edu.princeton.cs.algs4.SequentialSearchST;
import edu.princeton.cs.algs4.StdIn;

public class ScoreST {
    //Exercise 3.1.1，创建一张符号表并建立字母成绩与数值分数的对应关系
    public static void main(String[] args) {
        SequentialSearchST<String, Double> st = new SequentialSearchST<>();
        st.put("A+", 4.33);
        st.put("A", 4.00);
        st.put("A-", 3.67);
        st.put("B+", 3.33);
        st.put("B", 3.00);
        st.put("B-", 2.67);
        st.put("C+", 2.33);
        st.put("C", 2.00);
        st.put("C-", 1.67);
        st.put("D", 1.00);
        st.put("F", 0.00);
        String[] a = StdIn.readAllStrings();
        double sum = 0;
        for (int i = 0; i < a.length; i++) {
            sum += st.get(a[i]);
        }
        System.out.println("GPA: " + sum / a.length);
    }
}
