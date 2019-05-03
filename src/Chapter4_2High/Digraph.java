package Chapter4_2High;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
//用于Exercise 4.2.30的依赖类
public class Digraph {  //有向图的数据结构
    private final int V;  //图中的结点数
    private int E;  //图中的连接边数
    private Bag<Integer>[] adj;  //元素索引为结点ID，数组元素为对应索引结点所连接的邻接结点集合

    public Digraph(int V){  //创建一幅含有V个结点但没有边的有向图
        this.V=V;
        this.E=0;
        adj=(Bag<Integer>[]) new Bag[V];  //存放V个结点的邻接结点集合
        for(int v=0;v<V;v++)
            adj[v]=new Bag<Integer>();  //初始化每个结点的邻接结点集合
    }

    public Digraph(In in){  //从输入流中读取图文件并构造有向图
        this(in.readInt());  //读取V并将图初始化
        int E=in.readInt();  //读取E
        for(int i=0;i<E;i++){  //添加一条边
            int v=in.readInt();
            int w=in.readInt();
            addEdge(v,w);
        }
    }
    //Exercise 4.2.3
    public Digraph(Digraph G){
        this(G.V());  //调用上面的Digraph(int V)构造函数
        E=G.E();
        for(int v=0;v<G.V();v++){
            Stack<Integer> reverse=new Stack<>();
            for(int i:G.adj(v)){
                reverse.push(i);  //将结点v的所有邻接结点放入栈中
            }
            for(int i:reverse){  //将栈中的邻接结点放入结点v的邻接数组中
                adj[v].add(i);
            }
        }
    }

    public Digraph() {
        V = 0;
    }

    public int V(){return V;}  //返回图中结点总数
    public int E(){return E;}  //返回图中边的总数

    public void addEdge(int v,int w){  //添加一条结点v指向结点w的边
        adj[v].add(w);  //邻接结点集合数组中在结点v的索引上加入w
        E++;  //增加边的总数
    }

    public Iterable<Integer> adj(int v){return adj[v];}  //返回结点v指向的所有邻接结点

    public Digraph reverse(){  //返回图的反向图，即所有边的方向反转
        Digraph R=new Digraph(V);  //建立一个没有边的有向图
        for(int v=0;v<V;v++)  //遍历所有结点的邻接结点集合，在所有邻接结点上添加指向该结点的边
            for(int w:adj(v))
                R.addEdge(w,v);
        return R;
    }
    //Exercise 4.2.20的准备函数，有向图的出度和入度，出度指该结点指出的边的总数，入度为指向该结点的边的总数
    public int outdegree(int v){return adj[v].size();}
    public int indegree(int v){return reverse().adj[v].size();}

    @Override
    public String toString() {
        String s=V+" vertices, "+E+" edges\n";
        for(int v=0;v<V;v++){  //遍历所有结点，每一行打印出每个结点的邻接结点
            s+=v+": ";
            for(int w:this.adj(v)){
                s+=w+" ";
            }
            s+="\n";
        }
        return s;
    }
}
