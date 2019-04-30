package Chapter4_2High;

import Chapter4_2Text.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
//用于Exercise 4.2.30的依赖类
public class SymbolDigraph {  //符号有向图，使用字符串代替数字索引来表示图中的结点
    private ST<String,Integer> st;  //符号图的数据结构，一个符号表，键为结点名，值为结点的索引
    private String[] keys;  //用作反向索引，存放每个结点索引对应的结点名
    private Digraph G;  //图对象，使用索引来引用图中的结点

    public SymbolDigraph(String stream, String sp){  //根据stream指定的文件来构造图，使用sp指定的分隔符来区分结点名
        st=new ST<String,Integer>();
        In in=new In(stream);  //第一遍读取图，因为构造Graph对象需要获取结点总数
        while(in.hasNextLine()){
            String[] a=in.readLine().split(sp);  //通过输入流读取字符串，并用指定分隔符分割结点名
            for(int i=0;i<a.length;i++){
                if(!st.contains(a[i])){  //使用符号表为每个结点名关联一个索引值
                    st.put(a[i],st.size());  //st.size()在递增，所以每个结点名的键对应的值不一样
                }
            }
        }
        keys=new String[st.size()];  //初始化通过结点索引来反向查找的结点名数组
        for(String name:st.keys()){
            keys[st.get(name)]=name;  //结点名数组的对应索引处，放置与结点索引对应的结点名
        }

        G=new Digraph(st.size());
        in=new In(stream);  //第二遍读取图，第一遍之后已经获取了图的结点数和键值对等信息，这里是真正为了遍历图连接各结点
        while(in.hasNextLine()){
            String[] a=in.readLine().split(sp);
            int v=st.get(a[0]);
            for(int i=1;i<a.length;i++){  //遍历结点名数组，将图中连通的结点用边连接起来
                G.addEdge(v,st.get(a[i]));
            }
        }
    }

    public boolean contains(String s){return st.contains(s);}  //图中是否包含名为s的结点
    public int index(String s){return st.get(s);}  //返回结点名s的对应索引
    public String name(int v){return keys[v];}  //返回索引v的对应结点名
    public Digraph G(){return G;}

    public static void main(String[] args){
        String filename=args[0];
        String delim=args[1];
        SymbolDigraph sg=new SymbolDigraph(filename,delim);
        Digraph G=sg.G();
        while(StdIn.hasNextLine()){
            String source=StdIn.readLine();
            for(int w:G.adj(sg.index(source))){  //打印每个结点的所有邻接结点名
                StdOut.println(" "+sg.name(w));
            }
        }
    }
}
