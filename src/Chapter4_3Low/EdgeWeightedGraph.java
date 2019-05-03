package Chapter4_3Low;

import Chapter4_3Text.Edge;
import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;

public class EdgeWeightedGraph {  //加权无向图
    private final int V;  //结点总数
    private int E;  //边的总数
    private Bag<Edge>[] adj;  //和结点v相连的所有边的集合的数组

    public EdgeWeightedGraph(int V){  //创建一副含有V个结点的空图，还没有边
        this.V=V;
        this.E=0;
        adj=(Bag<Edge>[]) new Bag[V];
        for(int v=0;v<V;v++)
            adj[v]=new Bag<Edge>();
    }
    //Exercise 4.3.9
    public EdgeWeightedGraph(In in){  //从输入流中读取图文件并构造加权无向图
        this(in.readInt());  //读取V并将图初始化
        int E=in.readInt();  //读取E
        for(int i=0;i<E;i++){  //遍历文件中每一行的边连接，读取其中的两个相连结点与边的权重
            int v=in.readInt();
            int w=in.readInt();
            double weight=in.readDouble();
            Edge edge=new Edge(v,w,weight);
            addEdge(edge);  //根据Edge对象中的边属性向图中添加边
        }
    }

    public void addEdge(Edge e){  //向图中添加一条边
        int v=e.either(),w=e.other(v);  //获得一条边的两端结点
        adj[v].add(e);  //与结点v相连的边中添加边e
        adj[w].add(e);  //与结点w相连的边中添加边e
        E++;  //图中边的数量加1
    }

    public int V(){return V;}  //返回图的结点数
    public int E(){return E;}  //返回图的边数
    public Iterable<Edge> adj(int v){return adj[v];}  //返回结点v相连的所有边

    public Iterable<Edge> edges(){  //返回图的所有边
        Bag<Edge> b=new Bag<Edge>();
        for(int v=0;v<V;v++)
            for(Edge e:adj[v])  //由于每条边的Edge对象都会在两个结点的adj[]数组对应的索引位置各出现一次，所以只在存放所有边的背包对象b中加入一次边e
                if(e.other(v)>v) b.add(e);
        return b;
    }
    //Exercise 4.3.17
    @Override
    public String toString() {
        String s=V+" vertices, "+E+" edges\n";
        for(int v=0;v<V;v++){  //遍历所有结点，每一行打印出每个结点连接的边
            s+=v+": ";
            for(Edge w:this.adj(v)){
                s+=w+" ";
            }
            s+="\n";
        }
        return s;
    }
}
