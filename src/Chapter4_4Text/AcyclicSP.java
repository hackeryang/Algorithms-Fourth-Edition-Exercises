package Chapter4_4Text;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class AcyclicSP {  //无环加权有向图中利用拓扑排序的最短路径算法，比Dijkstra算法更快、更简单
    private DirectedEdge[] edgeTo;  //最短路径树中连接结点v和它的父节点的边，即edgeTo[v]是从起点s到v的最短路径上的最后一条边
    private double[] distTo;  //从起点s到v的最短路径长度，如果不存在则路径距离为+∞

    public AcyclicSP(EdgeWeightedDigraph G,int s){
        edgeTo=new DirectedEdge[G.V()];
        distTo=new double[G.V()];
        for(int v=0;v<G.V();v++)
            distTo[v]=Double.POSITIVE_INFINITY;
        distTo[s]=0.0;  //起点到达自己的权重当然是0
        EdgeWeightedTopological top=new EdgeWeightedTopological(G);
        for(int v:top.order())
            relax(G,v);
    }
    //边的松弛，就像橡皮筋的两端如果靠近，就会变松，两端拉远就会紧绷，所以意思就是选择两个结点间的更短路径
    private void relax(EdgeWeightedDigraph G,int v){
        for(DirectedEdge e:G.adj(v)){  //放松从一个给定结点指出的所有边
            int w=e.to();  //找到v指出的一条边所指向的结点
            if(distTo[w]>distTo[v]+e.weight()){  //如果从s到w的距离大于从s到v的距离加上v到w的距离之和，则更新更短路径，如果这个和的值不小于distTo[w]，则边e失效并被忽略，跳过一轮循环
                distTo[w]=distTo[v]+e.weight();
                edgeTo[w]=e;
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
