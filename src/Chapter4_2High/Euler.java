package Chapter4_2High;

import Chapter4_2Low.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;

import java.util.Iterator;

//Exercise 4.2.20
public class Euler {  //找出有向图中的欧拉环，即每条边恰好出现一次的有向环，当且仅当有向图G是连通的且每个结点的出度和入度相等时，G才含有一条有向欧拉环
    private Stack<Integer> cycle=null;  //存放欧拉环
    public Euler(Digraph G){
        if(G.E()==0){
            return;
        }
        for(int v=0;v<G.V();v++){
            if(G.outdegree(v)!=G.indegree(v)){
                return;
            }
        }
        Iterator<Integer>[] adj=(Iterator<Integer>[]) new Iterator[G.V()];  //邻接结点集合的迭代器数组
        for(int v=0;v<G.V();v++){
            adj[v]=G.adj(v).iterator();  //adj[v]是一个迭代器，G.adj(v)返回一个Bag<Integer>对象，里面有多个元素
        }
        int s;  //循环后还要用到s，所以定义在循环外
        for(s=0;s<G.V();s++){  //找到出度大于0的结点后退出循环
            if(G.outdegree(s)>0){
                break;
            }
        }
        if(s==G.V()){  //如果整个图中都没有出度大于0的结点，则肯定不存在欧拉环
            s=-1;
        }
        Stack<Integer> stack=new Stack<>();
        stack.push(s);
        cycle=new Stack<>();
        while(!stack.isEmpty()){
            int v=stack.pop();  //欧拉环必然出现在出度大于0的结点上，所以以v为起点将所有图中能到达的结点都放入环中
            while(adj[v].hasNext()){
                stack.push(v);
                v=adj[v].next();
            }
            cycle.push(v);
        }
        if(cycle.size()!=G.E()+1){  //如果有向环的结点数不等于整个图的边数加1，说明不符合图G连通且每条边恰好出现一次同时每个结点出度和入度相等的条件，不存在欧拉环
            cycle=null;
        }
    }

    public boolean hasCycle(){return cycle!=null;}  //是否存在欧拉环
    public Iterable<Integer> cycle(){return cycle;}  //返回欧拉环

    public static void main(String[] args){
        Digraph G=new Digraph(new In(args[0]));  //通过命令行指定的输入流构造有向图
        Euler euler=new Euler(G);  //从有向图对象中读取构造的有向图
        if(euler.hasCycle()){  //如果存在欧拉环，则遍历打印
            for(int v:euler.cycle()){
                System.out.print(v+" ");
            }
            System.out.println();
        }else{
            System.out.println("The graph doesn't has a Euler cycle.");
        }
    }
}
