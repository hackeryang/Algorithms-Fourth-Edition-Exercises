package Chapter4_4Text;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;

public class EdgeWeightedDigraph {  //加权有向图
    private final int V;  //顶点总数
    private int E;  //边的总数
    private Bag<DirectedEdge>[] adj;  //结点v指出去的所有边的集合的数组，即如果一条边从v指向w，那么它只会出现在v的邻接链表adj[v]中

    public EdgeWeightedDigraph(int V) {  //创建一副含有V个结点的空图，还没有边
        this.V = V;
        this.E = 0;
        adj = (Bag<DirectedEdge>[]) new Bag[V];
        for (int v = 0; v < V; v++)
            adj[v] = new Bag<DirectedEdge>();  //结点v可能连接多条有向边，所以用Bag数组表示
    }

    public EdgeWeightedDigraph(In in) {  //从输入流中读取图文件并构造加权有向图
        this(in.readInt());  //读取V并将图初始化
        int E = in.readInt();  //读取E
        if (E < 0) {
            throw new IllegalArgumentException("The weight of edges must be nonnegative.");
        }
        for (int i = 0; i < E; i++) {
            int v = in.readInt();  //获取一条边的两端结点
            int w = in.readInt();
            double weight = in.readDouble();  //获取边的权重
            addEdge(new DirectedEdge(v, w, weight));  //获取边的结点和权重信息后，将这条边加入到图中
        }
    }

    public void addEdge(DirectedEdge e) {  //将有向边e加入到图中
        adj[e.from()].add(e);  //与边起点v相连的边中添加边e
        E++;  //图中边的数量加1
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public Iterable<DirectedEdge> adj(int v) {  //返回所有从结点v指出去的边
        return adj[v];
    }

    public Iterable<DirectedEdge> edges() {
        Bag<DirectedEdge> bag = new Bag<DirectedEdge>();
        for (int v = 0; v < V; v++)
            for (DirectedEdge e : adj[v])
                bag.add(e);  //Bag对象中装入所有结点指出去的所有边
        return bag;
    }

    //Exercise 4.4.2
    @Override
    public String toString() {
        String s = V + " vertices, " + E + " edges\n";
        for (int v = 0; v < V; v++) {  //遍历所有结点，每一行打印出每个结点连接的边
            s += v + ": ";
            for (DirectedEdge e : this.adj(v)) {
                s += e + " ";
            }
            s += "\n";
        }
        return s;
    }
}
