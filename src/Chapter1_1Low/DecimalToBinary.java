package Chapter1_1Low;

import edu.princeton.cs.algs4.StdOut;

public class DecimalToBinary {  //将一个数字转换为二进制
    public static String decimalToBinary(int n){
        String resultString="";
        for(int i=31;i>=0;i--)
            resultString=resultString+(n>>>i&1);  //将整数每一个高位右移至相应的最低位再“与”1，结果转换为字符串从左到右排列输出，依然是原来的二进制
        return resultString;
    }
    public static void main(String[] args){

        StdOut.println(decimalToBinary(97));
    }
}
