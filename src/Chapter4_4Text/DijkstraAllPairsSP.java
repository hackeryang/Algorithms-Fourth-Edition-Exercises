package Chapter4_4Text;

public class DijkstraAllPairsSP {  //任意结点对之间的最短路径，给定起点s与终点t，找到从s到t的最短路径
    private DijkstraSP[] all;  //构造DijkstraSP对象数组，每个元素都把索引对应的结点作为起点

    DijkstraAllPairsSP(EdgeWeightedDigraph G) {
        all = new DijkstraSP[G.V()];
        for (int v = 0; v < G.V(); v++)  //利用DijkstraSP的构造函数生成以图中每个结点为起点的加权有向图
            all[v] = new DijkstraSP(G, v);
    }

    Iterable<DirectedEdge> path(int s, int t) {  //指定起点与终点，找到最短路径
        return all[s].pathTo(t);
    }

    double dist(int s, int t) {  //指定起点与终点，计算最短距离权重
        return all[s].distTo(t);
    }
}
