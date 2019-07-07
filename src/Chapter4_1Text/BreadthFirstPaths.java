package Chapter4_1Text;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class BreadthFirstPaths {  //使用广度优先搜索(BFS)查找图中的最短路径
    private boolean[] marked;  //用于标记结点是否与起点s连通，如果从起点s可以到达结点v，经过结点v就会使marked[v]置为true，没经过的结点w会使marked[w]为false
    private int[] edgeTo;  //从起点到一个结点的已知路径上的最后一个结点，相当于走迷宫例子中绳子记录路径的作用，通过该数组可以找到从每个与s连通的结点回到s的最短路径，是一棵用父节点链接表示的以s为根且含有所有与s连通的结点的树
    private final int s;  //起点

    public BreadthFirstPaths(Graph G, int s) {
        marked = new boolean[G.V()];  //创建一个布尔值数组，元素数量为图中的结点数量，初始化数组中每个位置都为false
        edgeTo = new int[G.V()];
        this.s = s;
        bfs(G, s);
    }

    private void bfs(Graph G, int s) {
        Queue<Integer> queue = new Queue<Integer>();
        marked[s] = true;  //经过的结点设置为已访问过
        queue.enqueue(s);  //将途径的结点加入队列
        while (!queue.isEmpty()) {
            int v = queue.dequeue();  //从队列中出列下一个顶点
            for (int w : G.adj(v))  //遍历所有邻接结点
                if (!marked[w]) {
                    //如果有邻接结点没有访问过，则记录到该邻接结点为止的路径，方式是例如边“v-w”第一次访问到w时，将edgeTo[w]设为v，即“v-w”是从s到w路径上最后一条已知的边，edgeTo[]是一棵由父结点链接表示的树，代表s到每个s连通的结点的最短路径
                    edgeTo[w] = v;
                    marked[w] = true;  //标记它，因为最短路径已知
                    queue.enqueue(w);  //将它添加到队列中
                }
        }
    }

    public boolean hasPathTo(int v) {  //是否存在从s到v的路径
        return marked[v];
    }

    public Iterable<Integer> pathTo(int v) {  //用可遍历的迭代器输出s到v的路径，如果不存在则返回null
        if (!hasPathTo(v)) return null;
        Stack<Integer> path = new Stack<Integer>();
        for (int x = v; x != s; x = edgeTo[x])  //从树的底部结点v开始从底向上遍历整棵树，因为edgeTo[]中放置的元素是各索引表示的结点的父结点
            path.push(x);  //在到达树根s之前，遇到的所有结点都压入栈中
        path.push(s);  //最后只剩下起点s，压入栈中后形成一棵以起点s为根节点的路径树
        return path;
    }

    public static void main(String[] args) {
        Graph G = new Graph(new In(args[0]));  //从输入流中读取命令行第一个参数指定的图结构文件
        int s = Integer.parseInt(args[1]);  //读取命令行第二个参数指定的起点结点
        BreadthFirstPaths search = new BreadthFirstPaths(G, s);  //该对象用于找到所有和起点s连通的最短路径
        for (int v = 0; v < G.V(); v++) {
            StdOut.print(s + " to " + v + ": ");  //打印经过的最短路径
            if (search.hasPathTo(v))
                for (int x : search.pathTo(v))
                    if (x == s) StdOut.print(x);
                    else StdOut.print("-" + x);
            StdOut.println();
        }
    }
}
