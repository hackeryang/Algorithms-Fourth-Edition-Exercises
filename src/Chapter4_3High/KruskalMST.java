package Chapter4_3High;

import Chapter4_3Text.Edge;
import Chapter4_3Text.EdgeWeightedGraph;
import edu.princeton.cs.algs4.*;

public class KruskalMST {  //产生最小生成树的Kruskal算法
    private Queue<Edge> mst;  //保存MST中的所有边
    private double weight;  //边的权重

    public KruskalMST(Chapter4_3Text.EdgeWeightedGraph G) {
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

    //Exercise 4.3.33
    private boolean check(EdgeWeightedGraph G) {  //检查是否是最小生成树
        //检查所有边的权重和是否等于MST中所有边应有的权重
        double total = 0;
        for (Edge edge : mst) {
            total += edge.weight();
        }
        if (Math.abs(total - weight) > 1E-12) {  //差值大于一个极小值，相当于大于0
            System.err.println("total weight not equal");
            return false;
        }
        //检查MST中是否没有环（有点问题）
        UF uf = new UF(G.V());
        for (Edge edge : mst) {
            int v = edge.either(), w = edge.other(v);  //获得这条边的两端结点
            if (uf.connected(v, w)) {  //这里觉得有点问题，MST中的边本来就将两个结点连通在一个MST中
                System.err.println("It is not acyclic.");
                return false;
            } else {
                uf.union(v, w);
            }
        }
        //检查是否是生成树
        for (Edge edge : G.edges()) {
            int v = edge.either(), w = edge.other(v);
            if (!uf.connected(v, w)) {
                System.err.println("It is not a spanning tree.");
                return false;
            }
        }
        //检查是否是最小生成树
        for (Edge edge : mst) {
            uf = new UF(G.V());
            for (Edge e : mst) {
                int v = e.either(), w = e.other(v);
                if (e != edge) {  //先将生成树中所有结点周围的非树中边全部连接起来
                    uf.union(v, w);
                }
            }
            for (Edge e : G.edges()) {  //遍历图中所有边
                int v = e.either(), w = e.other(v);
                if (!uf.connected(v, w) && e.weight() < edge.weight()) {  //如果图中有一条连接结点v和w的边的权重比树中连接这两个结点的横切边的权重还小，说明不是MST
                    System.err.println("It is not a minimal spanning tree.");
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        Chapter4_3Text.EdgeWeightedGraph G;
        G = new EdgeWeightedGraph(in);  //从输入流中构造加权无向图
        LazyPrimMST mst = new LazyPrimMST(G);  //通过构造函数产生最小生成树
        for (Edge e : mst.edges())  //遍历输出最小生成树的所有边以及整个树的权重
            StdOut.println(e);
        StdOut.println(mst.weight());
    }
}
