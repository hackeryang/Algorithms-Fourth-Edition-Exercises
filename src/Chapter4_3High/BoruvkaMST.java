package Chapter4_3High;

import Chapter4_3Low.EdgeWeightedGraph;
import Chapter4_3Text.Edge;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.UF;

//Exercise 4.3.43
public class BoruvkaMST {  //与Kruskal算法类似，只是分阶段地向一组森林中逐渐添加边来构造MST
    private Queue<Edge> mst;  //保存MST中所有边
    private double weight;  //MST中所有边的权重和

    public BoruvkaMST(EdgeWeightedGraph G) {
        mst = new Queue<Edge>();
        UF uf = new UF(G.V());  //使用1.5节中的union-find数据结构识别会形成环的边

        for (int t = 0; t < G.V() && mst.size() < G.V() - 1; t += t) {
            Edge[] closest = new Edge[G.V()];  //存储各结点权重最小的边
            for (Edge e : G.edges()) {
                int v = e.either(), w = e.other(v);  //获得这条边的两端结点
                int i = uf.find(v), j = uf.find(w);
                if (i == j) {  //如果结点v和w在同一片森林里，则跳过这轮循环（和if(connected(v,w)功能相同）
                    continue;
                }
                if (closest[i] == null || less(e, closest[i])) {
                    closest[i] = e;
                }
                if (closest[j] == null || less(e, closest[j])) {
                    closest[j] = e;
                }
            }
            for (int i = 0; i < G.V(); i++) {
                Edge e = closest[i];
                if (e != null) {
                    int v = e.either(), w = e.other(v);
                    if (!uf.connected(v, w)) {
                        mst.enqueue(e);  //MST中加入这条边
                        weight += e.weight();  //累加MST的权重和
                        uf.union(v, w);  //将原本属于两片森林的结点归并到一片森林中
                    }
                }
            }
        }
        assert check(G);
    }

    private boolean less(Edge e, Edge f) {  //边e的权重是否小于边f的权重
        return e.weight() < f.weight();
    }

    public Iterable<Edge> edges() {
        return mst;
    }

    public double weight() {
        return weight;
    }

    private boolean check(EdgeWeightedGraph G) {  //检查是否是最小生成树
        //检查所有边的权重和是否等于MST中所有边应有的权重
        double total = 0;
        for (Edge edge : mst) {
            total += edge.weight();
        }
        if (Math.abs(total - weight) > 1E-12) {  //差值大于一个极小值，相当于大于0
            System.err.println("Total weight not equal.");
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
        //检查是否为生成树
        for (Edge edge : G.edges()) {
            int v = edge.either(), w = edge.other(v);
            if (!uf.connected(v, w)) {
                System.err.println("It is not a spanning tree.");
                return false;
            }
        }
        //检查是否为最小生成树
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
        EdgeWeightedGraph G = new EdgeWeightedGraph(in);
        BoruvkaMST mst = new BoruvkaMST(G);
        for (Edge e : mst.edges()) {
            StdOut.println(e);
        }
        StdOut.println(mst.weight());
    }
}
