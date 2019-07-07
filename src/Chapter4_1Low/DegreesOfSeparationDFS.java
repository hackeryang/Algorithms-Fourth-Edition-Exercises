package Chapter4_1Low;

import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.*;

//Exercise 4.1.25
public class DegreesOfSeparationDFS {  //使用深度优先搜索查找两个演员之间的路径
    public static void main(String[] args) {
        SymbolGraph sg = new SymbolGraph(args[0], "/");  //用指定文件和文字分隔符构造图
        Graph G = sg.G();  //获取图的数据结构

        StdOut.print("Source: ");
        String source = StdIn.readLine();  //从命令行中读取起点
        if (!sg.contains(source)) {
            StdOut.println(source + " Not in database.");
            return;
        }

        int s = sg.index(source);
        DepthFirstPaths dfp = new DepthFirstPaths(G, s);  //使用给定的图和起点索引构造DFS
        StdOut.print("Query: ");
        String query = StdIn.readLine();  //从命令行获取目标结点
        if (sg.contains(query)) {
            int t = sg.index(query);
            if (dfp.hasPathTo(t)) {  //当起点能够到达目标结点时，打印经过的路径
                for (int v : dfp.pathTo(t)) {
                    StdOut.println(" " + sg.name(v));
                }
            } else {
                StdOut.println("Not connected");
            }
        } else {
            StdOut.println("Not in database.");
        }
    }
}
