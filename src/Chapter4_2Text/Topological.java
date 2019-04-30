package Chapter4_2Text;

import edu.princeton.cs.algs4.StdOut;

public class Topological {  //结点的拓扑排序
    private Iterable<Integer> order;  //结点的拓扑顺序
    public Topological(Digraph G){
        DirectedCycle cyclefinder=new DirectedCycle(G);
        if(!cyclefinder.hasCycle()){
            DepthFirstOrder dfs=new DepthFirstOrder(G);
            order=dfs.reversePost();  //使用逆后序的排列顺序
        }
    }

    public Iterable<Integer> order(){return order;}  //返回拓扑有序的所有结点
    public boolean isDAG(){return order!=null;}  //是否是有向无环图

    public static void main(String[] args){
        String filename=args[0];  //从命令行第一个参数指定读取的文件
        String separator=args[1];  //从命令行第二个参数指定元素分隔符
        SymbolDigraph sg=new SymbolDigraph(filename,separator);
        Topological top=new Topological(sg.G());  //从符号有向图对象中构造图
        for(int v:top.order())  //以逆后序的顺序遍历输出结点名
            StdOut.println(sg.name(v));
    }
}
