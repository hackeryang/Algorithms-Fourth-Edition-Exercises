package Chapter4_3High;

import Chapter4_3Low.EdgeWeightedGraph;
import Chapter4_3Text.Edge;
import edu.princeton.cs.algs4.IndexMinPQ;
import edu.princeton.cs.algs4.Queue;

//Exercise 4.3.22
public class PrimMSF {  //Prim算法计算一幅加权图的最小生成森林，整个图不一定是连通的
    private Edge[] edgeTo;  //非森林中结点v和森林中结点连接的最小权重横切边，最终会作为MST中的边
    private double[] distTo;  //非森林中结点v和森林中结点连接的最小权重横切边的权重，distTo[w]=edgeTo[w].weight()，最终会作为最小生成森林中的边权重
    private boolean[] marked;  //如果结点v在森林中则为true
    private IndexMinPQ<Double> pq;  //索引最小优先队列，保存非森林中结点v连接森林中结点的未失效最小权重横切边的权重，该结点v会是下一个将被添加到森林中的结点，v对应的最小权重边会作为MSF中的边

    public PrimMSF(EdgeWeightedGraph G) {
        edgeTo = new Edge[G.V()];
        distTo = new double[G.V()];
        marked = new boolean[G.V()];
        for (int v = 0; v < G.V(); v++) {
            distTo[v] = Double.POSITIVE_INFINITY;  //用最大值初始化权重数组
        }
        pq = new IndexMinPQ<Double>(G.V());
        for (int v = 0; v < G.V(); v++) {
            if (!marked[v]) {
                distTo[v] = 0.0;  //如果结点v不在最小生成森林中，将v连接自己的权重设置为0并插入到最小优先队列中
                pq.insert(v, 0.0);
                while (!pq.isEmpty()) {  //将最小权重横切边另一端的非森林中结点加入到最小生成森林中
                    visit(G, pq.delMin());
                }
            }
        }
    }

    private void visit(EdgeWeightedGraph G, int v) {  //将结点v添加到森林中，更新权重和横切边等数据
        marked[v] = true;
        for (Edge e : G.adj(v)) {
            int w = e.other(v);  //w是刚加入森林中的原非森林中结点v连接的另一端结点
            if (marked[w]) {  //如果结点w和结点v一样都已经在森林中，则横切边v-w失效跳过这一轮循环，失效的横切边不会被加入edgeTo[]数组
                continue;
            }
            if (e.weight() < distTo[w]) {  //如果结点w不在森林中且边v-w（即e）的权重小于目前已知的最小权重边edgeTo[w]，则将最小权重边更新为e，且更新最小权重值
                edgeTo[w] = e;
                distTo[w] = e.weight();
                if (pq.contains(w)) {
                    pq.changeKey(w, distTo[w]);  //如果pq中已经存在结点w的边权重记录，则更新最小权重值，不存在w的边权重值记录则添加该记录
                } else {
                    pq.insert(w, distTo[w]);
                }
            }
        }
    }

    public Iterable<Edge> edges() {  //遍历最小生成森林中的所有边
        Queue<Edge> msf = new Queue<>();
        for (Edge edge : edgeTo) {
            if (edge != null) {
                msf.enqueue(edge);
            }
        }
        return msf;
    }
}
