package Chapter4_1Low;

import Chapter4_1Text.Graph;
import Chapter4_1Text.SymbolGraph;
import edu.princeton.cs.algs4.StdOut;


//Exercise 4.1.22
public class BaconHistogram {  //用柱状图表示与Kevin Bacon的关系度数为0、1、2.。。。等的演员数量分别是多少
    public static void main(String[] args) {
        SymbolGraph sg = new SymbolGraph("movies.txt", "/");
        Graph G = sg.G();

        String source = "Bacon, Kevin";  //将该演员作为图的起点
        if (!sg.contains(source)) {
            StdOut.println(source + " not in database.");
            return;
        }
        int s = sg.index(source);
        BreadthFirstPaths bfs = new BreadthFirstPaths(G, s);  //以s为起点开始BFS

        int[] num = new int[G.V()];  //存储与Kevin Bacon关系度数为i的演员数量
        int infinite = 0;  //度数为无限的演员数量
        for (int v = 0; v < G.V(); v++) {
            int distance = bfs.distTo(v);  //起点到结点v的最短路径长度
            if (distance != Integer.MAX_VALUE) {
                num[distance]++;  //找到对应关系度数的一个演员，就将对应度数位置的演员数量加1
            } else {
                infinite++;  //度数为无限，则将无限度数的演员数量加1
            }
        }

        for (int i = 0; i < num.length; i += 2) {
            if (num[i] != 0) {
                System.out.println("Keven Bacon= " + i / 2 + " : " + num[i]);
            } else {
                break;
            }
        }
        System.out.println("Infinite(actors and movies): " + infinite);
    }
}
