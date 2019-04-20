package Chapter4_1Text;

public class DepthFirstSearch {  //使用深度优先搜索(DFS)来寻找与起点s连通的结点
    private boolean[] marked;  //用于标记结点是否与起点s连通，如果从起点s可以到达结点v，经过结点v就会使marked[v]置为true，没经过的结点w会使marked[w]为false
    private int count;  //与起点s连通的顶点总数

    public DepthFirstSearch(Graph G,int s){
        marked=new boolean[G.V()];  //创建一个布尔值数组，元素数量为图中的结点数量，初始化数组中每个位置都为false
        dfs(G,s);  //从起点s开始深度优先搜索
    }

    private void dfs(Graph G,int v){
        marked[v]=true;  //经过的结点设置为已访问过
        count++;  //可以到达的结点数加1
        for(int w:G.adj(v))  //遍历当前结点的所有邻接结点，如果有邻接结点没有被访问过，就从该邻接结点继续进行递归搜索，跳过到达过的邻接结点
            if(!marked[w]) dfs(G,w);
    }

    public boolean marked(int w){return marked[w];}  //结点w和起点s是否连通，即从起点s能不能访问到w
    public int count(){return count;}  //与起点s连通的结点总数
}
