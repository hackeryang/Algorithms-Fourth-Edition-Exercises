package Chapter4_4Text;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.IndexMinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class DijkstraSP {  //无环加权有向图中非负权重边最短路径的Dijkstra算法
    private DirectedEdge[] edgeTo;  //最短路径树中连接结点v和它的父节点的边，即edgeTo[v]是从起点s到v的最短路径上的最后一条边
    private double[] distTo;  //从起点s到v的最短路径长度，如果不存在则路径距离为无穷大
    private IndexMinPQ<Double> pq;  //索引最小优先队列，保存需要被relax()方法放松的结点并确认下一个被放松的结点

    public DijkstraSP(EdgeWeightedDigraph G,int s){
        edgeTo=new DirectedEdge[G.V()];
        distTo=new double[G.V()];
        pq=new IndexMinPQ<Double>(G.V());
        for(int v=0;v<G.V();v++)  //将从起点s到所有结点的距离都初始化为无穷大
            distTo[v]=Double.POSITIVE_INFINITY;
        distTo[s]=0.0;  //起点到自己的距离为0
        pq.insert(s,0.0);  //用起点s和权重0初始化pq
        while(!pq.isEmpty())  //优先队列非空时，不断松弛最短路径树中的所有边，所有从s可达的结点都会按照从s开始最短路径的总权重顺序被放松，松驰过的路径和对应结点会被加入到最短路径树中
            relax(G,pq.delMin());
    }
    //边的松弛，就像橡皮筋的两端如果靠近，就会变松，两端拉远就会紧绷，所以意思就是选择两个结点间的更短路径
    private void relax(EdgeWeightedDigraph G,int v){
        for(DirectedEdge e:G.adj(v)){  //放松从一个给定结点指出的所有边
            int w=e.to();  //找到v指出的一条边所指向的结点
            if(distTo[w]>distTo[v]+e.weight()){  //如果从s到w的距离大于从s到v的距离加上v到w的距离之和，则更新更短路径，如果这个和的值不小于distTo[w]，则边e失效并被忽略，跳过一轮循环
                distTo[w]=distTo[v]+e.weight();
                edgeTo[w]=e;
                if(pq.contains(w)) pq.change(w,distTo[w]);  //如果优先队列中有到结点w的路径距离信息，则更新从起点s到达w的距离，否则插入关于w的新条目，w会成为接下来被relax()方法放松的结点
                else pq.insert(w,distTo[w]);
            }
        }
    }

    public double distTo(int v){return distTo[v];}  //返回从起点通往结点v的路径距离
    public boolean hasPathTo(int v){return distTo[v]<Double.POSITIVE_INFINITY;}  //是否有通往结点v的路径，即判断从起点到v的路径距离是否是有限值

    public Iterable<DirectedEdge> pathTo(int v){  //从起点s到结点v的路径，如果不存在则为null
        if(!hasPathTo(v)) return null;
        Stack<DirectedEdge> path=new Stack<DirectedEdge>();
        for(DirectedEdge e=edgeTo[v];e!=null;e=edgeTo[e.from()])  //从指向结点v的那条边开始，不断回退到指向父结点的边，最终回退到起点s指出的有向边
            path.push(e);  //将不断回退的有向边加入栈中，这样栈中路径按照相反顺序弹出后，就是正序的从起点s到结点v的路径
        return path;
    }

    public static void main(String[] args){
        EdgeWeightedDigraph G=new EdgeWeightedDigraph(new In(args[0]));  //接受一个输入流和一个起点作为命令行参数
        int s=Integer.parseInt(args[1]);
        DijkstraSP sp=new DijkstraSP(G,s);  //从输入流中读取有向图，根据起点计算有向图的最短路径树
        for(int t=0;t<G.V();t++){  //打印从指定起点到所有结点的最短路径
            StdOut.print(s+" to "+t);
            StdOut.printf(" (%4.2f): ",sp.distTo(t));
            if(sp.hasPathTo(t))
                for(DirectedEdge e:sp.pathTo(t))
                    StdOut.print(e+" ");
            StdOut.println();
        }
    }
}
