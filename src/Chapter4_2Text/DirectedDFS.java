package Chapter4_2Text;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class DirectedDFS {  //使用DFS在有向图中判断给定的一个或者一组结点能到达哪些其他结点
    private boolean[] marked;  //用于标记结点是否由起点s可达，如果从起点s可以到达结点v，经过结点v就会使marked[v]置为true，没经过的结点w会使marked[w]为false

    public DirectedDFS(Digraph G, int s) {  //从图G中找到从结点s可达的所有结点
        marked = new boolean[G.V()];
        dfs(G, s);
    }

    public DirectedDFS(Digraph G, Iterable<Integer> sources) {  //从图G中找到从结点集合sources中的所有结点，它们可到达的所有结点
        marked = new boolean[G.V()];
        for (int s : sources)
            if (!marked[s]) dfs(G, s);
    }

    private void dfs(Digraph G, int v) {  //DFS核心方法
        marked[v] = true;
        for (int w : G.adj(v))  //递归遍历没有到达过的邻接结点
            if (!marked[w]) dfs(G, w);
    }

    public boolean marked(int v) {  //从起点s是否可以到达结点v
        return marked[v];
    }

    public static void main(String[] args) {
        Digraph G = new Digraph(new In(args[0]));  //从命令行中第1个参数指定图输入文件
        Bag<Integer> sources = new Bag<Integer>();
        for (int i = 1; i < args.length; i++)  //从命令行的第2个参数开始指定多个查找可达性的起点
            sources.add(Integer.parseInt(args[i]));
        DirectedDFS reachable = new DirectedDFS(G, sources);
        for (int v = 0; v < G.V(); v++)
            if (reachable.marked(v)) StdOut.println(v + " ");  //打印起点集合中的多个起点所能到达的所有结点
        StdOut.println();
    }
}
