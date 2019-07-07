package Chapter4_4Text;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Arbitrage {  //利用负权重环检测进行货币兑换的套汇
    public static void main(String[] args) {
        int V = StdIn.readInt();  //读取货币种类数
        String[] name = new String[V];  //存放每种货币名称
        EdgeWeightedDigraph G = new EdgeWeightedDigraph(V);  //创建一副含有V个结点的空图，还没有边
        for (int v = 0; v < V; v++) {
            name[v] = StdIn.readString();  //读取每一行的货币名称
            for (int w = 0; w < V; w++) {
                double rate = StdIn.readDouble();  //读取对每种货币的汇率
                DirectedEdge e = new DirectedEdge(v, w, -Math.log(rate));  //创造一个有向权重边，货币v指向货币w，权重为汇率求自然对数再取反，这种对汇率的权重转换可以创造出负权重边，才能形成最短路径环
                G.addEdge(e);  //在空图中添加有向边
            }
        }
        BellmanFordSP spt = new BellmanFordSP(G, 0);  //name[0]为USD美元，以美元为起点在Bellman-Ford算法中检测负权重环
        if (spt.hasNegativeCycle()) {
            double stake = 1000.0;  //本金赌注
            for (DirectedEdge e : spt.negativeCycle()) {
                StdOut.printf("%10.5f %s", stake, name[e.from()]);  //输出每一次兑换前的本金及其货币种类
                stake *= Math.exp(-e.weight());  //本金乘以e^(-weight)，因为货币的实际兑换汇率一开始被求自然对数再取反，所以这里再乘以e的指数抵消掉权重转换，回到实际的汇率
                StdOut.printf("= %10.5f %s\n", stake, name[e.to()]);  //输出兑换后的货币数量及其货币种类
            }
        } else StdOut.println("No arbitrage opportunity");
    }
}
