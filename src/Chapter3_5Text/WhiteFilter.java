package Chapter3_5Text;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.HashSet;

public class WhiteFilter {  //只将所有在白名单上的键传递到输出并忽略所有不在白名单上的键
    public static void main(String[] args) {
        HashSet<String> set;
        set = new HashSet<String>();
        In in = new In(args[0]);
        while (!in.isEmpty())
            set.add(in.readString());
        while (!StdIn.isEmpty()) {
            String word = StdIn.readString();
            if (set.contains(word))
                StdOut.print(word + " ");
        }
    }
}
