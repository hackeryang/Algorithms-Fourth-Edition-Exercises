package Chapter4_3Text;

import edu.princeton.cs.algs4.*;

public class KruskalMST {  //产生最小生成树的Kruskal算法
    private Queue<Edge> mst;  //保存MST中的所有边
    private double weight;  //边的权重

    public KruskalMST(EdgeWeightedGraph G) {
        mst = new Queue<Edge>();
        MinPQ<Edge> pq = new MinPQ<Edge>();  //使用最小优先队列将所有边按照权重排序
        for (Edge e : G.edges()) pq.insert(e);  //将图中的所有边插入到最小优先队列中
        UF uf = new UF(G.V());  //使用1.5节中的union-find数据结构识别会形成环的边
        while (!pq.isEmpty() && mst.size() < G.V() - 1) {  //当优先队列非空且MST中的边数没达到应有的V-1个时
            Edge e = pq.delMin();  //从pq中得到权重最小的边
            int v = e.either(), w = e.other(v);  //获得这条边的两端结点
            if (uf.connected(v, w)) continue;  //如果这条边连接的这两个结点已经连通在同一片森林里，则为失效横切边，跳过这一轮循环
            uf.union(v, w);  //将原本属于两片森林的结点归并到一片森林中
            mst.enqueue(e);  //MST中加入这条边
            weight += e.weight();  //累加MST的权重和
        }
    }

    public Iterable<Edge> edges() {  //返回最小生成树的所有边
        return mst;
    }

    //Exercise 4.3.31
    public double weight() {  //返回最小生成树的边权重和
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
