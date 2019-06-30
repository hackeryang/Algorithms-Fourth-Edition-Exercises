package Chapter4_4Text;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;

public class DepthFirstOrder {  //有向图中基于DFS的结点排序（可用于任务调度中的优先级排序），与Chapter4_2Text同名文件相比将图G的类改为了EdgeWeightedDigraph
    private boolean[] marked;  //用于标记结点是否由起点s可达，如果从起点s可以到达结点v，经过结点v就会使marked[v]置为true，没经过的结点w会使marked[w]为false
    private Queue<Integer> pre;  //所有结点的前序排列，即递归搜索之前将结点加入队列
    private Queue<Integer> post;  //所有结点的后续排列，即递归搜索之后将结点加入队列
    private Stack<Integer> reversePost;  //所有结点的逆后序排列，即递归搜索之后将结点压入栈

    public DepthFirstOrder(EdgeWeightedDigraph G){
        pre=new Queue<Integer>();
        post=new Queue<Integer>();
        reversePost=new Stack<Integer>();
        marked=new boolean[G.V()];

        for(int v=0;v<G.V();v++)  //遍历所有未访问结点
            if(!marked[v]) dfs(G,v);
    }

    private void dfs(EdgeWeightedDigraph G,int v){
        pre.enqueue(v);
        marked[v]=true;
        for(DirectedEdge e:G.adj(v)){  //递归搜索未访问的邻接结点
            int w=e.to();
            if(!marked[w])
                dfs(G,w);
        }
        post.enqueue(v);
        reversePost.push(v);
    }

    public Iterable<Integer> pre(){return pre;}
    public Iterable<Integer> post(){return post;}
    public Iterable<Integer> reversePost(){return reversePost;}
}
