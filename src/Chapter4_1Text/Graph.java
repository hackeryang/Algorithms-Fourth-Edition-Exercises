package Chapter4_1Text;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;

public class Graph {  //图的实现
    private final int V;  //顶点数目
    private int E;  //边的数目
    private Bag<Integer>[] adj;  //邻接表，adj[v]即和结点v相邻的所有顶点组成的链表
    public Graph(int V){  //创建一个含有V个顶点但不含有边的图
        this.V=V;
        this.E=0;
        adj=(Bag<Integer>[]) new Bag[V];  //新建邻接表
        for(int v=0;v<V;v++)  //将各顶点对应的所有邻接链表初始化为空
            adj[v]=new Bag<Integer>();
    }
    public Graph(In in){  //从标准输入流in读入一幅图
        this(in.readInt());  //读取V并将图初始化
        int E=in.readInt();  //读取E
        for(int i=0;i<E;i++){  //添加一条边
            int v=in.readInt();
            int w=in.readInt();
            addEdge(v,w);
        }
    }
    public int V(){return V;}  //返回结点数
    public int E(){return E;}  //返回边数
    public void addEdge(int v,int w){  //向图中添加一条边“v-w”
        adj[v].add(w);  //将结点w添加到结点v的邻接链表中
        adj[w].add(v);  //将结点v添加到结点w的邻接链表中
        E++;
    }
    public Iterable<Integer> adj(int v){return adj[v];}  //返回和结点v相邻的所有结点
}
