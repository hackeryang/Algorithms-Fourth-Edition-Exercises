package Chapter4_1Text;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class TestSearch {  //读取图并使用深度优先搜索(DFS)测试结点间的连通性
    public static void main(String[] args) {
        Graph G = new Graph(new In(args[0]));  //从输入流中读取命令行第一个参数指定的图结构文件
        int s = Integer.parseInt(args[1]);  //读取命令行第二个参数指定的起点结点
        DepthFirstSearch search = new DepthFirstSearch(G, s);  //该对象用于找到和起点s连通的所有顶点

        for (int v = 0; v < G.V(); v++)  //遍历图中所有结点
            if (search.marked(v))  //判断结点v和起点s是否连通，若连通则打印v
                StdOut.print(v + " ");
        StdOut.println();

        if (search.count() != G.V())  //如果图内与起点s连通的结点总数不为图中所有结点数，说明该图不是完全连通的，即不是连通图
            StdOut.print("NOT ");
        StdOut.println("connected");
    }
}
