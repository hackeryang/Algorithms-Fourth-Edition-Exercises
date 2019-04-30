package Chapter4_2Text;

public class KosarajuSCC {  //计算有向图中强连通分量（多个互相强连通的有向图结点组成的子图）的Kosaraju算法
    private boolean[] marked;  //用于标记结点是否由起点s可达，如果从起点s可以到达结点v，经过结点v就会使marked[v]置为true，没经过的结点w会使marked[w]为false
    private int[] id;  //结点所属强连通分量的标识符(ID)
    private int count;  //强连通分量的数量

    public KosarajuSCC(Digraph G){
        marked=new boolean[G.V()];
        id=new int[G.V()];
        //计算图的反向图，即后序顺序的拓扑排列，该对象在构造反向图之前已经形成了正常顺序的有向图，原图按正常顺序DFS能证明存在一条从s到v的路径
        DepthFirstOrder order=new DepthFirstOrder(G.reverse());
        //在后序顺序拓扑（反向图）的基础上再遍历它的逆后序，原图正向遍历存在s到v的路径，如果是对反向图进行逆后序的DFS可以证明原图存在一条从v到s的路径，即反向图中存在s到v的路径
        for(int s:order.reversePost())
            if(!marked[s]){
                dfs(G,s);
                count++;  //一轮DFS递归结束后，所有处于同一轮递归中被访问到的结点都属于同一个强连通分量，因此个数加1
            }
    }

    private void dfs(Digraph G,int v){
        marked[v]=true;
        id[v]=count;  //给同一轮DFS递归访问到的结点加上相同的强连通分量ID
        for(int w:G.adj(v))  //继续在同一轮DFS递归中遍历未访问过的邻接结点
            if(!marked[w])
                dfs(G,w);
    }

    public boolean stronglyConnected(int v,int w){return id[v]==id[w];}  //结点v和w是否强连通，就看他们所属的强连通分量ID是否相同
    public int id(int v){return id[v];}
    public int count(){return count;}
}
