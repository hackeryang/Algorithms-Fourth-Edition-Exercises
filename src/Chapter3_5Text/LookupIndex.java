package Chapter3_5Text;

import edu.princeton.cs.algs4.*;

public class LookupIndex {
    //索引以及反向索引的查找
    public static void main(String[] args) {
        In in = new In(args[0]);  //索引数据库
        String sp = args[1];  //分隔符
        ST<String, Queue<String>> st = new ST<String, Queue<String>>();  //存放键及其对应多个值的符号表
        ST<String, Queue<String>> ts = new ST<String, Queue<String>>();  //存放值及其对应的键出现的多个位置
        while (in.hasNextLine()) {
            String[] a = in.readLine().split(sp);
            String key = a[0];
            for (int i = 1; i < a.length; i++) {
                String val = a[i];  //i从1开始，所以是值，0为键
                if (!st.contains(key)) st.put(key, new Queue<String>());  //在符号表中插入键和对应这个键的多个值的队列容器
                if (!ts.contains(val)) ts.put(val, new Queue<String>());  //在符号表中插入该值和对应键出现的所有位置的队列容器
                st.get(key).enqueue(val);  //找到这个键对应的多个值所在的队列容器，插入对应的多个值
                ts.get(val).enqueue(key);  //找到这个值对应键的所有位置的队列容器，插入对应键的多个位置
            }
        }
        while (!StdIn.isEmpty()) {
            String query = StdIn.readLine();
            if (st.contains(query))
                for (String s : st.get(query))
                    StdOut.println(" " + s);  //根据查找的键输出多个值
            if (ts.contains(query))
                for (String s : ts.get(query))
                    StdOut.println(" " + s);  //根据查找的值找到对应键出现的所有位置
        }
    }
}
