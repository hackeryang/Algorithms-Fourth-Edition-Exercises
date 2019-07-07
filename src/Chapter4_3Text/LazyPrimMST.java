package Chapter4_3Text;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

public class LazyPrimMST {  //产生最小生成树的Prim算法的延时实现，即横切边失效后不会被立刻删除
    private boolean[] marked;  //最小生成树中的结点，如果结点v在树中，marked[v]为true
    private Queue<Edge> mst;  //最小生成树中的边
    private MinPQ<Edge> pq;  //横切边的最小优先队列，包括已失效的横切边（即横切边另一端本不在树中的结点也已经加入到了树中）
    private double weight;  //最小生成树所有边的权重

    public LazyPrimMST(EdgeWeightedGraph G) {
        pq = new MinPQ<Edge>();
        marked = new boolean[G.V()];
        mst = new Queue<Edge>();
        visit(G, 0);  //为树添加一个结点，将它标记为已访问并将与它关联的所有未失效边加入优先队列，保证优先队列中含有所有连接树中结点与非树中结点的边，即横切边
        while (!pq.isEmpty()) {
            Edge e = pq.delMin();  //从最小优先队列中得到权重最小的边
            int v = e.either(), w = e.other(v);  //获得一条边的两端结点
            if (marked[v] && marked[w]) continue;  //跳过已失效的横切边，即这条边两端的结点都已经在最小生成树中
            mst.enqueue(e);  //将这条权重最小的边加入到最小生成树中
            weight += e.weight();  //累加当前边的权重
            if (!marked[v]) visit(G, v);  //将结点v或w添加到最小生成树中
            if (!marked[v]) visit(G, w);
        }
    }

    private void visit(EdgeWeightedGraph G, int v) {  //标记结点v（即放入最小生成树中）并将所有连接v和未标记结点的边（即横切边）加入最小优先队列pq
        marked[v] = true;
        for (Edge e : G.adj(v))
            if (!marked[e.other(v)]) pq.insert(e);
    }

    public Iterable<Edge> edges() {  //返回最小生成树的所有边
        return mst;
    }

    //Exercise 4.3.31
    public double weight() {  //返回最小生成树的权重
        return weight;
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        EdgeWeightedGraph G;
        G = new EdgeWeightedGraph(in);  //从输入流中构造加权无向图
        LazyPrimMST mst = new LazyPrimMST(G);  //通过构造函数产生最小生成树
        for (Edge e : mst.edges())  //遍历输出最小生成树的所有边以及整个树的权重
            StdOut.println(e);
        StdOut.println(mst.weight());
    }
}
