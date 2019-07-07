package Chapter5_1Text;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Count {  //计算指定文件中每个字符出现过的次数
    public static void main(String[] args) {
        Alphabet alpha = new Alphabet(args[0]);  //读取命令行第一个参数指定的字母表
        int R = alpha.R();
        int[] count = new int[R];  //存储每个字符出现的次数
        String s = StdIn.readAll();  //读取输入文件中包含的所有字符串
        int N = s.length();
        for (int i = 0; i < N; i++)
            if (alpha.contains(s.charAt(i)))  //文件中的字符串里每出现某个字符一次，对应的次数加一
                count[alpha.toIndex(s.charAt(i))]++;
        for (int c = 0; c < R; c++)  //输出字母表中每个字符出现的次数
            StdOut.println(alpha.toChar(c) + " " + count[c]);
    }
}