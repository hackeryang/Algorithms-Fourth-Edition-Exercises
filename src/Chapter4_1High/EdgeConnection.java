package Chapter4_1High;

import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.In;

//Exercise 4.1.35
public class EdgeConnection {  //如果一条边被删除后图会分为两个独立的连通分量，这条边称为桥，没有桥的图称为边连通图，通过DFS判断一个图是否是边连通图
    private int bridges;  //桥的数量
    private int count;
    private int[] pre;
    private int[] low;

    public EdgeConnection(Graph G) {
        pre = new int[G.V()];
        low = new int[G.V()];
        for (int v = 0; v < G.V(); v++) {
            pre[v] = -1;
            low[v] = -1;
        }
        for (int v = 0; v < G.V(); v++) {
            if (pre[v] == -1) {
                dfs(G, v, v);
            }
        }
    }

    private void dfs(Graph G, int u, int v) {
        pre[v] = count++;
        low[v] = pre[v];
        for (int w : G.adj(v)) {
            if (pre[w] == -1) {
                dfs(G, v, w);
                low[v] = Math.min(low[v], low[w]);
                if (low[w] == pre[w]) {
                    bridges++;
                }
            } else if (w != u) {
                low[v] = Math.min(low[v], pre[w]);
            }
        }
    }

    public boolean isEdgeConnectedGraph() {
        return bridges == 0;
    }

    public static void main(String[] args) {
        Graph G = new Graph(new In(args[0]));
        EdgeConnection ec = new EdgeConnection(G);
        System.out.println("Is Edge Connected Graph? " + ec.isEdgeConnectedGraph());
    }
}
