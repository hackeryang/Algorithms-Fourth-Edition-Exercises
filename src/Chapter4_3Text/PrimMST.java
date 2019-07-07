package Chapter4_3Text;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.IndexMinPQ;
import edu.princeton.cs.algs4.StdOut;

public class PrimMST {  //产生最小生成树的Prim算法的即时实现，即横切边失效后将被立刻删除，只保存每个非树中结点连接树中结点的权重最小横切边
    private Edge[] edgeTo;  //非树中结点v和树中结点连接的最小权重横切边，最终会作为MST中的边
    private boolean[] marked;  //如果结点v在树中则为true
    private double[] distTo;  //非树中结点v和树中结点连接的最小权重横切边的权重，distTo[w]=edgeTo[w].weight()，最终会作为MST中的边权重
    private IndexMinPQ<Double> pq;  //索引最小优先队列，保存非树中结点v连接树中结点的未失效最小权重横切边的权重，该结点v会是下一个将被添加到树中的结点，v对应的最小权重边会作为MST中的边

    public PrimMST(EdgeWeightedGraph G) {
        edgeTo = new Edge[G.V()];
        distTo = new double[G.V()];
        marked = new boolean[G.V()];
        for (int v = 0; v < G.V(); v++)
            distTo[v] = Double.POSITIVE_INFINITY;  //用最大值初始化权重数组
        pq = new IndexMinPQ<Double>(G.V());
        distTo[0] = 0.0;
        pq.insert(0, 0.0);  //用结点0和权重0初始化pq，树中第1个结点连接自己的权重当然是0
        while (!pq.isEmpty())  //将最小权重横切边另一端的非树中结点加入到最小生成树中
            visit(G, pq.delMin());
    }

    private void visit(EdgeWeightedGraph G, int v) {  //将结点v添加到树中，更新权重和横切边等数据
        marked[v] = true;
        for (Edge e : G.adj(v)) {
            int w = e.other(v); //w是刚加入树中的原非树中结点v连接的另一端结点
            if (marked[w]) continue;  //如果结点w和结点v一样都已经在树中，则横切边v-w失效跳过这一轮循环，失效的横切边不会被加入edgeTo[]数组
            if (e.weight() < distTo[w]) {  //如果结点w不在树中且边v-w（即e）的权重小于目前已知的最小权重边edgeTo[w]，则将最小权重边更新为e，且更新最小权重值
                edgeTo[w] = e;
                distTo[w] = e.weight();
                if (pq.contains(w)) pq.change(w, distTo[w]);  //如果pq中已经存在结点w的边权重记录，则更新最小权重值，不存在w的边权重值记录则添加该记录
                else pq.insert(w, distTo[w]);
            }
        }
    }

    //Exercise 4.3.21
    public Iterable<Edge> edges() {  //遍历最小生成树中的所有边
        Bag<Edge> mst = new Bag<Edge>();
        for (int v = 1; v < edgeTo.length; v++)  //v=0的值用于上面构造函数初始化（已经作为在树中的第1个结点），不需要被遍历，所以从1开始
            mst.add(edgeTo[v]);
        return mst;
    }

    //Exercise 4.3.31
    public double weight() {  //最小生成树中所有边的权重
        double weight = 0;
        for (int i = 0; i < distTo.length; i++) {
            weight += distTo[i];
        }
        return weight;
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        EdgeWeightedGraph G = new EdgeWeightedGraph(in);  //从输入流中构造加权无向图
        PrimMST mst = new PrimMST(G);  //通过构造函数产生最小生成树
        for (Edge e : mst.edges()) {  //遍历输出最小生成树的所有边以及整个树的权重
            StdOut.println(e);
        }
        StdOut.println(mst.weight());
    }
}
