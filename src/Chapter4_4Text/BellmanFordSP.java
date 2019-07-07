package Chapter4_4Text;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;

public class BellmanFordSP {  //基于队列的Bellman-Ford算法，解决不存在负权重环的加权有向图中的最短路径问题，可以存在负权重边
    private double[] distTo;  //从起点到某个结点的路径长度，如果该结点不可达则路径距离为+∞，如果该结点可达但在负权重环中，则路径距离为-∞
    private DirectedEdge[] edgeTo;  //从起点到某个结点的最后一条边
    private boolean[] onQ;  //对应索引的结点是否已经存在于队列中
    private Queue<Integer> queue;  //将要被relax()方法放松的结点，只有上一轮放松过程中distTo[]值改变了的结点，它指出的边才能改变其他结点的distTo[]值
    private int cost;  //relax()的调用次数
    private Iterable<DirectedEdge> cycle;  //edgeTo[]中是否有负权重环

    public BellmanFordSP(EdgeWeightedDigraph G, int s) {
        distTo = new double[G.V()];
        edgeTo = new DirectedEdge[G.V()];
        onQ = new boolean[G.V()];
        queue = new Queue<Integer>();
        for (int v = 0; v < G.V(); v++)
            distTo[v] = Double.POSITIVE_INFINITY;
        distTo[s] = 0.0;  //起点到达自己的权重当然是0
        queue.enqueue(s);
        onQ[s] = true;  //起点已被加入到队列中，将被relax()放松
        while (!queue.isEmpty() && !hasNegativeCycle()) {  //当队列非空且图中不存在负权重环时，从队列中取出结点进行边的放松，但当图中存在负权重环时，队列永远不会为空，所以有环就会停止循环
            int v = queue.dequeue();
            onQ[v] = false;  //结点已从队列中取出
            relax(G, v);
        }
    }

    //边的松弛，就像橡皮筋的两端如果靠近，就会变松，两端拉远就会紧绷，所以意思就是选择两个结点间的更短路径
    private void relax(EdgeWeightedDigraph G, int v) {
        for (DirectedEdge e : G.adj(v)) {  //放松从一个给定结点指出的所有边
            int w = e.to();  //找到v指出的一条边所指向的结点
            if (distTo[w] > distTo[v] + e.weight()) {  //如果从s到w的距离大于从s到v的距离加上v到w的距离之和，则更新更短路径，如果这个和的值不小于distTo[w]，则边e失效并被忽略，跳过一轮循环
                distTo[w] = distTo[v] + e.weight();
                edgeTo[w] = e;
                if (!onQ[w]) {  //如果v指向的结点w不在队列中，则将w加入到队列中作为接下来被放松边的结点，这样可保证队列中不出现重复结点，且改变了edgeTo[]和distTo[]值的所有结点都会在下一轮relax()中处理
                    queue.enqueue(w);
                    onQ[w] = true;
                }
            }
            if (cost++ % G.V() == 0)  //如果relax()的放松操作执行的轮数是V轮的整数倍（包括V轮），则检测图中是否存在负权重环，即每调用relax()V次就检测一次环
                findNegativeCycle();
        }
    }

    private void findNegativeCycle() {
        int V = edgeTo.length;
        EdgeWeightedDigraph spt = new EdgeWeightedDigraph(V);  //根据edgeTo[]中的边构造一幅检测环的子图，在所有边放松V轮后，若队列非空说明一定存在环，edgeTo[]表示的子图中一定有这个环
        for (int v = 0; v < V; v++)  //spt一开始为无边的结点图，根据edgeTo[]中的边添加子图中结点间的指向边
            if (edgeTo[v] != null)
                spt.addEdge(edgeTo[v]);
        EdgeWeightedCycleFinder cf = new EdgeWeightedCycleFinder(spt);  //利用有向环检测类的构造函数来检测负权重环
        cycle = cf.cycle();  //将检测到的环或null保存
    }

    public boolean hasNegativeCycle() {  //是否含有负权重环
        return cycle != null;
    }

    public Iterable<DirectedEdge> negativeCycle() {  //返回检测到的负权重环或null
        return cycle;
    }

    public double distTo(int v) {  //返回从起点通往结点v的路径距离
        return distTo[v];
    }

    public boolean hasPathTo(int v) {  //是否有通往结点v的路径，即判断从起点到v的路径距离是否是有限值
        return distTo[v] < Double.POSITIVE_INFINITY;
    }

    public Iterable<DirectedEdge> pathTo(int v) {  //从起点s到结点v的路径，如果不存在则为null
        if (!hasPathTo(v)) return null;
        Stack<DirectedEdge> path = new Stack<DirectedEdge>();
        for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()])  //从指向结点v的那条边开始，不断回退到指向父结点的边，最终回退到起点s指出的有向边
            path.push(e);  //将不断回退的有向边加入栈中，这样栈中路径按照相反顺序弹出后，就是正序的从起点s到结点v的路径
        return path;
    }
}
