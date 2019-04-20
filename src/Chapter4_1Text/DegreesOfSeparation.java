package Chapter4_1Text;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class DegreesOfSeparation {  //根据结点的度数来找到一个结点通向另一个结点的最短路径
    public static void main(String[] args){
        SymbolGraph sg=new SymbolGraph(args[0],args[1]);  //根据命令行第1个参数指定输入文件，第2个参数指定分隔符来构造图文件的数据结构
        Graph G=sg.G();  //初始化图对象

        String source=args[2];  //命令行第3个参数指定起点
        if(!sg.contains(source)){
            StdOut.println(source+"not in database.");
            return;
        }
        int s=sg.index(source);  //获取起点对应的索引ID
        BreadthFirstPaths bfs=new BreadthFirstPaths(G,s);  //利用BFS查找最短路径

        while(!StdIn.isEmpty()){
            String sink=StdIn.readLine();  //从标准输入流命令行中读取目的结点
            if(sg.contains(sink)){
                int t=sg.index(sink);  //获取目的结点的索引ID
                if(bfs.hasPathTo(t))  //如果能够找到从起点通向目的结点的最短路径，打印经过的结点名
                    for(int v:bfs.pathTo(t))
                        StdOut.println(" "+sg.name(v));
                else StdOut.println("Not connected");
            }else StdOut.println("Not in database.");
        }
    }
}
