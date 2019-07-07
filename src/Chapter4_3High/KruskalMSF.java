package Chapter4_3High;

import Chapter4_3Low.EdgeWeightedGraph;
import Chapter4_3Text.Edge;
import edu.princeton.cs.algs4.*;

//Exercise 4.3.22
public class KruskalMSF {  //Kruskal算法计算一幅加权图的最小生成森林(MSF)，整个图不一定是连通的
    private Queue<Edge> msf;  //保存MSF中的所有边

    public KruskalMSF(EdgeWeightedGraph G) {
        msf = new Queue<Edge>();
        MinPQ<Edge> pq = new MinPQ<Edge>();  //使用最小优先队列将所有边按照权重排序
        for (Edge e : G.edges()) {  //将图中的所有边插入到最小优先队列中
            pq.insert(e);
        }
        UF uf = new UF(G.V());  //使用1.5节中的union-find数据结构识别会形成环的边
        Graph graph = new Graph(G.V());  //生成没有边的图
        for (Edge edge : G.edges()) {
            int v = edge.either(), w = edge.other(v);  //获得这条边的两端结点
            graph.addEdge(v, w);  //在图中添加所有边
        }
        CC cc = new CC(graph);  //使用深度优先搜索（DFS）找出图中的所有连通分量

        while (!pq.isEmpty() && msf.size() < G.V() - cc.count()) {  //当优先队列非空且MSF中的边数小于图节点数量与连通分量个数的差（这个差值就是图中所有边的数量）时
            Edge e = pq.delMin();  //从pq中得到权重最小的边
            int v = e.either(), w = e.other(v);
            if (uf.connected(v, w)) {  //如果这条边连接的这两个结点已经连通在同一片森林里，则为失效横切边，跳过这一轮循环
                continue;
            }
            uf.union(v, w);  //将原本属于两片森林的结点归并到一片森林中
            msf.enqueue(e);  //MSF中加入这条边
        }
    }

    public Iterable<Edge> edges() {
        return msf;
    }
}
