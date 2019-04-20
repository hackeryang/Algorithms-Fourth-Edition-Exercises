package Chapter4_1Text;

public class Cycle {  //检测图G是否是有环图（假设不存在自环和平行边）
   private boolean[] marked;  //用于标记结点是否与起点s连通，如果从起点s可以到达结点v，经过结点v就会使marked[v]置为true，没经过的结点w会使marked[w]为false
   private boolean hasCycle;
   public Cycle(Graph G){
       marked=new boolean[G.V()];
       for(int s=0;s<G.V();s++)  //遍历整张图的所有结点
           if(!marked[s])
               dfs(G,s,s);
   }

   private void dfs(Graph G,int v,int u){
       marked[v]=true;  //经过的结点设置为已访问过
       for(int w:G.adj(v))  //递归遍历所有邻接节点
           if(!marked[w])
               dfs(G,w,v);  //递归遍历邻接结点的邻接结点，深入一条搜索路径
           else if(w!=u) hasCycle=true;  //如果起点s在访问该邻接结点时，发现在之前递归访问别的邻接结点时连通路径到达过该结点，且该结点不是自环，说明遇到了环，是有环图
   }

   public boolean hasCycle(){return hasCycle;}  //返回是否为有环图
}
