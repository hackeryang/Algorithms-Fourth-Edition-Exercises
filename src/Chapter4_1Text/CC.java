package Chapter4_1Text;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class CC {  //使用深度优先搜索（DFS）找出图中的所有连通分量（即图中有几片互相连通的结点区域，但这几个区域之间不连通）
    private boolean[] marked;  //用于标记结点是否与起点s连通，如果从起点s可以到达结点v，经过结点v就会使marked[v]置为true，没经过的结点w会使marked[w]为false
    private int[] id;  //以结点ID作为索引的数组，存储对应结点所属的连通分量ID，例如如果结点v属于第i个连通分量，id[v]的值为i
    private int count;  //连通区域的数量（即连通分量数）

    public CC(Graph G) {
        marked = new boolean[G.V()];
        id = new int[G.V()];
        for (int s = 0; s < G.V(); s++)  //遍历整张图，所有互相连通的结点形成1个子连通区域，即1个连通分量
            if (!marked[s]) {
                dfs(G, s);
                count++;  //遍历完一个连通分量后，连通分量数增加，也作为下一个连通分量的ID
            }
    }

    private void dfs(Graph G, int v) {
        marked[v] = true;  //经过的结点设置为已访问过
        id[v] = count;  //将当前结点所属的连通分量ID保存，遇到的第1个结点属于连通分量0
        for (int w : G.adj(v))
            if (!marked[w])  //递归搜索未访问的邻接结点
                dfs(G, w);
    }

    public boolean connected(int v, int w) {  //检测结点v与w是否连通
        return id[v] == id[w];
    }

    public int id(int v) {  //返回结点v所属的连通分量ID
        return id[v];
    }

    public int count() {  //返回连通分量数
        return count;
    }

    public static void main(String[] args) {
        Graph G = new Graph(new In(args[0]));
        CC cc = new CC(G);

        int M = cc.count();
        StdOut.println(M + " components");  //打印图中的连通分量数

        Bag<Integer>[] components;  //存储每个子连通区域（连通分量）的所有结点
        components = (Bag<Integer>[]) new Bag[M];
        for (int i = 0; i < M; i++)
            components[i] = new Bag<Integer>();
        for (int v = 0; v < G.V(); v++)
            components[cc.id(v)].add(v);  //遍历整张图，将当前结点加入到所属连通分量的Bag对象数组的Bag元素中
        for (int i = 0; i < M; i++) {
            for (int v : components[i])  //将每个连通分量中的所有结点打印出来
                StdOut.print(v + " ");
            StdOut.println();  //每打印完一个连通分量就另起一行
        }
    }
}
