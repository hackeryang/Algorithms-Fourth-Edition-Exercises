package Chapter4_2High;

import edu.princeton.cs.algs4.Queue;

//Exercise 4.2.30
public class QueueTopologySort {  //基于队列的拓扑排序
    private Queue<Integer> order;  //用于标记起点队列中被删除的结点，如果图的结点拓扑有序最终会存放过图的所有结点
    public QueueTopologySort(Digraph G){
        int[] indegrees=new int[G.V()];  //保存所有结点入度的数组
        Queue<Integer> sources=new Queue<>();  //包含所有起点的队列
        for(int v=0;v<G.V();v++){
            indegrees[v]=G.indegree(v);
            if(indegrees[v]==0){  //如果某个结点的入度为0，将其插入起点队列
                sources.enqueue(v);
            }
        }
        order=new Queue<>();
        while(!sources.isEmpty()){  //起点队列非空时，删去一个结点
            int source=sources.dequeue();
            order.enqueue(source);  //将删去的起点标记，放入拓扑有序队列中
            for(int i:G.adj(source)){  //遍历被删去起点指出的所有邻接结点，即指出的边
                if(--indegrees[i]==0){  //因为指向这些邻接结点的起点已从起点队列删除，因此被指向的邻接结点的入度减1，之后如果该邻接结点的入度为0，插入起点队列
                    sources.enqueue(i);
                }
            }
        }
        if(order.size()!=G.V()){  //如果拓扑有序队列中不包括图中所有结点，说明该图并不是拓扑有序
            order=null;
        }
    }

    public Iterable<Integer> order(){
        return order;
    }

    public boolean hasOrder(){
        return order!=null;
    }

    public static void main(String[] args){
        SymbolDigraph sg=new SymbolDigraph(args[0],args[1]);
        QueueTopologySort qts=new QueueTopologySort(sg.G());
        if(qts.hasOrder()){  //当该图中所有结点拓扑有序时遍历输出所有结点
            for(int v:qts.order()){
                System.out.println(sg.name(v));
            }
        }else{
            System.out.println("The graph doesn't have order.");
        }
    }
}
