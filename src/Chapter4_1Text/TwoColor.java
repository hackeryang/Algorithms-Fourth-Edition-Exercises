package Chapter4_1Text;

public class TwoColor {  //检测一幅图是否是二分图，即用两种颜色标记所有结点，使任意一条边的两个端点的颜色都不相同
    private boolean[] marked;  //用于标记结点是否与起点s连通，如果从起点s可以到达结点v，经过结点v就会使marked[v]置为true，没经过的结点w会使marked[w]为false
    private boolean[] color;  //标记各结点的两种颜色
    private boolean isTwoColorable = true;  //标记是否是二分图

    public TwoColor(Graph G) {
        marked = new boolean[G.V()];
        color = new boolean[G.V()];
        for (int s = 0; s < G.V(); s++)  //遍历图中所有结点
            if (!marked[s])
                dfs(G, s);
    }

    private void dfs(Graph G, int v) {
        marked[v] = true;  //经过的结点设置为已访问过
        for (int w : G.adj(v))  //递归遍历所有邻接节点
            if (!marked[w]) {  //若访问的是新邻接结点，将该邻接结点的颜色置为与起点相反
                color[w] = !color[v];
                dfs(G, w);  //递归搜索邻接结点的邻接结点
            } else if (color[w] == color[v]) isTwoColorable = false;  //如果遇到访问过的邻接结点，且邻接结点的颜色与自己相同，说明不是二分图
    }

    public boolean isBipartite() {  //返回是否是二分图
        return isTwoColorable;
    }
}
