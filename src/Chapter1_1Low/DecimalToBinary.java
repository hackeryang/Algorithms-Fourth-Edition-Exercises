package Chapter1_1Low;

import edu.princeton.cs.algs4.StdOut;

public class DecimalToBinary {
    public static String decimalToBinary(int n){
        String resultString="";
        for(int i=31;i>=0;i--)
            resultString=resultString+(n>>>i&1);
        return resultString;
    }
    public static void main(String[] args){

        StdOut.println(decimalToBinary(97));
    }
}
