package Chapter4_4Low;

import Chapter4_4Text.DirectedEdge;
import Chapter4_4Text.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.Stack;

//Exercise 4.4.12
public class EdgeWeightedCycleFinder {  //在加权有向图中利用DFS寻找有向环
    private boolean[] marked;  //用于标记结点是否由起点s可达，如果从起点s可以到达结点v，经过结点v就会使marked[v]置为true，没经过的结点w会使marked[w]为false
    private DirectedEdge[] edgeTo;  //最短路径树中连接结点v和它的父节点的边，即edgeTo[v]是从起点s到v的最短路径上的最后一条边
    private Stack<DirectedEdge> cycle;  //存放有向环中的所有有向边（如果存在）
    private boolean[] onStack;  //递归调用dfs()期间一条有向路径栈上的所有结点，记录了一条从起点到v的有向路径，当找到一条边v->w且w在栈中时，就找到了一个有向环

    public EdgeWeightedCycleFinder(EdgeWeightedDigraph G) {
        onStack = new boolean[G.V()];
        edgeTo = new DirectedEdge[G.V()];
        marked = new boolean[G.V()];
        for (int v = 0; v < G.V(); v++)  //对所有未访问过的结点进行DFS
            if (!marked[v]) dfs(G, v);
    }

    private void dfs(EdgeWeightedDigraph G, int v) {
        onStack[v] = true;
        marked[v] = true;
        for (DirectedEdge e : G.adj(v)) {  //递归访问起点v的所有邻接结点
            int w = e.to();
            if (this.hasCycle()) return;  //如果存在环则不需要继续递归遍历
            else if (!marked[w]) {
                //如果有邻接结点没有访问过，则记录到该邻接结点为止的路径，方式是例如边“v->w”第一次访问到w时，将edgeTo[w]设为e，即“v->w”是从起点s到w路径上最后一条已知的边，edgeTo[]是一棵由父结点链接表示的树
                edgeTo[w] = e;
                dfs(G, w);
            } else if (onStack[w]) {  //如果DFS路径上某个结点w之前被访问过，说明出现了环
                cycle = new Stack<DirectedEdge>();
                DirectedEdge x = e;
                for (; x.from() != w; x = edgeTo[x.from()])  //将环入口结点w的上一个结点v，到结点w的所有路径上结点对应的有向边都压入存放有向环边的栈中
                    cycle.push(x);  //栈中先放入的是当前结点的父节点指出的边，因为edgeTo[]数组放置的元素就是当前索引结点的上一个结点指出的边，这样最后栈顶层是环入口结点的下一个结点指出的边
                cycle.push(x);
                return;
            }
        }
        onStack[v] = false;  //当前路径的DFS递归调用结束，该结点可用于其他路径的递归
    }

    public boolean hasCycle() {  //判断是否存在有向环，即存放有向环结点的栈中是否有元素
        return cycle != null;
    }

    public Iterable<DirectedEdge> cycle() {
        return cycle;
    }
}
