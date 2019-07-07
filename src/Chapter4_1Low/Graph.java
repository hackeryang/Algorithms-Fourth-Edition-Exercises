package Chapter4_1Low;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;

public class Graph {  //图的实现
    private final int V;  //顶点数目
    private int E;  //边的数目
    private Bag<Integer>[] adj;  //邻接表，adj[v]即和结点v相邻的所有顶点组成的链表

    public Graph(int V) {  //创建一个含有V个顶点但不含有边的图
        this.V = V;
        this.E = 0;
        adj = (Bag<Integer>[]) new Bag[V];  //新建邻接表
        for (int v = 0; v < V; v++)  //将各顶点对应的所有邻接链表初始化为空
            adj[v] = new Bag<Integer>();
    }

    public Graph(In in) {  //从标准输入流in读入一幅图
        this(in.readInt());  //读取V并将图初始化
        int E = in.readInt();  //读取E
        for (int i = 0; i < E; i++) {  //添加一条边
            int v = in.readInt();
            int w = in.readInt();
            addEdge(v, w);
        }
    }

    //Exercise 4.1.3
    public Graph(Graph G) {
        this(G.V());
        E = G.E();
        for (int v = 0; v < G.V(); v++) {
            Stack<Integer> reverse = new Stack<>();
            for (int w : G.adj[v]) {
                reverse.push(w);
            }
            for (int w : reverse) {  //邻接结点的Bag对象数组中加入栈内放置的邻接结点
                adj[v].add(w);
            }
        }
    }

    public int V() {  //返回结点数
        return V;
    }

    public int E() {  //返回边数
        return E;
    }

    public void addEdge(int v, int w) {  //向图中添加一条边“v-w”
        adj[v].add(w);  //将结点w添加到结点v的邻接链表中
        adj[w].add(v);  //将结点v添加到结点w的邻接链表中
        E++;
    }

    public Iterable<Integer> adj(int v) {  //返回和结点v相邻的所有结点
        return adj[v];
    }
}
