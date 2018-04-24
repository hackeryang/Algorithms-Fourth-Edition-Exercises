package Chapter1_2Low;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class ClassicString {
    public static boolean isPalindrome(String s){
        //判断字符串是否是一条回文（正读反读都一样的文字）
        int N=s.length();
        for(int i=0;i<N/2;i++)
            if(s.charAt(i)!=s.charAt(N-i-1))
                return false;
        return true;
    }
    public static void main(String[] args){
        //从命令行参数中提取文件名和扩展名
        String s=args[0];
        int dot=s.indexOf(".");
        String base=s.substring(0,dot);
        String extension=s.substring(dot+1,s.length());

        //打印出标准输入中所有含有通过命令行指定的字符串的行
        String query=args[0];
        while(!StdIn.isEmpty()){
            String s1=StdIn.readLine();
            if(s1.contains(query)) StdOut.println(s1);
        }

        //以空白字符为分隔符从StdIn中创建一个字符串数组
        String input=StdIn.readAll();
        String[] words=input.split("\\s+");


    }
}
