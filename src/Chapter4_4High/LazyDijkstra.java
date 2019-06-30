package Chapter4_4High;

import Chapter4_4Text.DirectedEdge;
import Chapter4_4Text.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

import java.util.Comparator;

//Exercise 4.4.39
public class LazyDijkstra {  //Dijkstra算法的延时实现
    private boolean[] marked;  //最短路径树中的结点，如果结点v在树中，marked[v]为true
    private DirectedEdge[] edgeTo;  //最短路径树中连接结点v和它的父节点的边，即edgeTo[v]是从起点s到v的最短路径上的最后一条边
    private double[] distTo;  //从起点s到v的最短路径长度，如果不存在则路径距离为无穷大
    private MinPQ<DirectedEdge> pq;  //横切边的最小优先队列，包括已失效的横切边（即横切边另一端本不在最短路径树中的结点也已经加入到了树中）

    private class ByDistanceFromSource implements Comparator<DirectedEdge> {  //自定义一个有向权重边的比较器
        @Override
        public int compare(DirectedEdge e, DirectedEdge f) {  //比较起点开始直到边e和边f的权重
            double dist1=distTo[e.from()]+e.weight();
            double dist2=distTo[f.from()]+f.weight();
            return Double.compare(dist1,dist2);
        }
    }

    public LazyDijkstra(EdgeWeightedDigraph G,int s){
        marked=new boolean[G.V()];
        edgeTo=new DirectedEdge[G.V()];
        distTo=new double[G.V()];
        pq=new MinPQ<>(new ByDistanceFromSource());  //最小优先队列中使用自定义的比较器

        for(int v=0;v<G.V();v++){  //将从起点s到所有结点的距离都初始化为无穷大
            distTo[v]=Double.POSITIVE_INFINITY;
        }
        distTo[s]=0.0;  //起点到自己的距离为0
        relax(G,s);
        while(!pq.isEmpty()){
            int w=pq.delMin().to();  //从最小优先队列中得到权重最小的边，不断放松该边指向的结点
            if(!marked[w]){  //放松还未放松过的结点
                relax(G,w);
            }
        }
    }
    //边的松弛，就像橡皮筋的两端如果靠近，就会变松，两端拉远就会紧绷，所以意思就是选择两个结点间的更短路径
    private void relax(EdgeWeightedDigraph G,int v){
        marked[v]=true;  //标记结点v（即放入最短路径树中）
        for(DirectedEdge e:G.adj(v)){  //放松从一个给定结点指出的所有边
            int w=e.to();  //找到v指出的一条边所指向的结点
            if(distTo[w]>distTo[v]+e.weight()){  //如果从s到w的距离大于从s到v的距离加上v到w的距离之和，则更新更短路径，如果这个和的值不小于distTo[w]，则边e失效并被忽略，跳过一轮循环
                distTo[w]=distTo[v]+e.weight();
                edgeTo[w]=e;
                pq.insert(e);  //对于该最短边指向的结点w，起点s到达w接下来指向的结点的最短路径距离也会因为这条边而变化，所以需要把这条边放入最小优先队列，以后取出再放松w指向的结点
            }
        }
    }

    public double distTo(int v){return distTo[v];}  //返回从起点通往结点v的路径距离
    public boolean hasPathTo(int v){return distTo[v]<Double.POSITIVE_INFINITY;}  //是否有通往结点v的路径，即判断从起点到v的路径距离是否是有限值

    public Iterable<DirectedEdge> pathTo(int v){  //从起点s到结点v的路径，如果不存在则为null
        if(!hasPathTo(v)){
            return null;
        }
        Stack<DirectedEdge> path=new Stack<DirectedEdge>();
        for(DirectedEdge e=edgeTo[v];e!=null;e=edgeTo[e.from()]){  //从指向结点v的那条边开始，不断回退到指向父结点的边，最终回退到起点s指出的有向边
            path.push(e);  //将不断回退的有向边加入栈中，这样栈中路径按照相反顺序弹出后，就是正序的从起点s到结点v的路径
        }
        return path;
    }

    public static void main(String[] args) {
        EdgeWeightedDigraph G = new EdgeWeightedDigraph(new In(args[0]));  //接受一个输入流和一个起点作为命令行参数
        int s = Integer.parseInt(args[1]);
        LazyDijkstra sp = new LazyDijkstra(G, s);  //从输入流中读取有向图，根据起点计算有向图的最短路径树

        for (int t = 0; t < G.V(); t++) {  //打印从指定起点到所有结点的最短路径
            StdOut.print(s + " to " + t);
            StdOut.printf(" (%4.2f): ", sp.distTo(t));
            if (sp.hasPathTo(t)) {
                for (DirectedEdge e : sp.pathTo(t)) {
                    StdOut.print(e + "   ");
                }
            }
            StdOut.println();
        }
    }
}
