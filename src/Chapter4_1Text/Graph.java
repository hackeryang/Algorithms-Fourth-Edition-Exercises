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

    public static int degree(Graph G,int v){  //计算结点v的度数
        int degree=0;
        for(int w:G.adj(v)) degree++;  //结点v有一个邻接结点，度数就加1
        return degree;
    }

    public static int maxDegree(Graph G){  //计算所有结点的最大度数
        int max=0;  //保存最大度数的值
        for(int v=0;v<G.V();v++)
            if(degree(G,v)>max)  //遍历所有结点，如果有结点的度数比目前的最大值大，则覆盖最大值
                max=degree(G,v);
        return max;
    }

    public static double avgDegree(Graph G){return 2.0*G.E()/G.V();}  //计算所有结点的平均度数，前面乘以2是因为两个结点有一条互相连接的边，互相都有1的度数，相当于有向图中的两条边

    public static int numberOfSelfLoops(Graph G){  //计算自环的个数
        int count=0;
        for(int v=0;v<G.V();v++)
            for(int w:G.adj(v))  //遍历所有结点，如果有结点的邻接结点就是自己，次数加1
                if(v==w) count++;
        return count/2;  //每条边都被记过2次
    }

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
