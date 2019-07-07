package Chapter2_5Low;

import edu.princeton.cs.algs4.StdIn;

import java.util.Arrays;

public class Frequency {
    //Exercise 2.5.8，使用Record.java的数据结构和方法
    public static void main(String[] args) {
        String[] a = StdIn.readAllStrings();
        Arrays.sort(a);
        Record[] records = new Record[a.length];
        records[0] = new Record(a[0]);
        int n = 0;
        for (int i = 1; i < a.length; i++) {
            if (records[n].getWord().equals(a[i])) {
                records[n].addFreq();  //如果有重复的字符串则次数加一
            } else {
                records[++n] = new Record(a[i]);  //如果不是重复的字符串则加入Record数组
            }
        }
        Arrays.sort(records, 0, n);
        for (int i = 0; i <= n; i++) {
            Record record = records[i];
            System.out.println(record.getWord() + ": " + record.getFreq());
        }
    }
}
