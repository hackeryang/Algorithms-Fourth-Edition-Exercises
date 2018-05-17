package Chapter3_5Text;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.HashSet;

public class DeDup {  //去掉输入流中的重复项
    public static void main(String[] args){
        HashSet<String> set;
        set=new HashSet<String>();
        while(!StdIn.isEmpty()){
            String key=StdIn.readString();
            if(!set.contains(key)){
                set.add(key);
                StdOut.print(key+" ");
            }
        }
    }
}
