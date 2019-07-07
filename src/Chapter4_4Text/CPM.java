package Chapter4_4Text;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class CPM {  //优先级限制下的并行任务调度问题，通过有向加权无环图中的最长路径计算来找到关键路径
    public static void main(String[] args) {
        int N = StdIn.readInt();  //读取结点数
        StdIn.readLine();
        EdgeWeightedDigraph G = new EdgeWeightedDigraph(2 * N + 2);  //因为每个结点都会分解为一个起始节点和结束结点，所以数量乘以2，再加上一个起点s和一个终点t
        int s = 2 * N, t = 2 * N + 1;  //结点索引是从0开始的，所以倒数2个结点是2N和2N+1，分别作为起点和终点的索引
        for (int i = 0; i < N; i++) {
            String[] a = StdIn.readLine().split("\\s+");  //按空格分割数组元素，因为输入文件jobsPC.txt每行都由空格分割，每一列分别是耗时、在哪些结点任务之前完成当前索引任务
            double duration = Double.parseDouble(a[0]);  //读取分割出的第一列数据，即任务i的耗时
            G.addEdge(new DirectedEdge(i, i + N, duration));  //添加结点i的起始节点指向结束结点的边，权重为任务i的耗时
            G.addEdge(new DirectedEdge(s, i, 0.0));  //添加起点s指向结点i的起始结点的边，权重为0
            G.addEdge(new DirectedEdge(i + N, t, 0.0));  //添加结点i的结束结点指向终点t的边，权重为0
            for (int j = 1; j < a.length; j++) {
                int successor = Integer.parseInt(a[j]);  //jobsPC.txt第二列开始才是限制优先级结点，因此j=1
                G.addEdge(new DirectedEdge(i + N, successor, 0.0));  //添加结点i指向限制优先级结点的边，权重为0
            }
        }
        AcyclicLP lp = new AcyclicLP(G, s);  //找到图中最长路径树，打印出各条最长路径的长度，即每个结点任务的起始时间
        StdOut.println("Start times:");
        for (int i = 0; i < N; i++)
            StdOut.printf("%4d: %5.1f\n", i, lp.distTo(i));
        StdOut.printf("Finish time: %5.1f\n", lp.distTo(t));  //最后输出所有任务完成所需的时间，即有向加权无环图从起点s走到终点t的时间
    }
}
