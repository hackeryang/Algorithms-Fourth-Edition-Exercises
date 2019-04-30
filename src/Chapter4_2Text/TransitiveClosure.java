package Chapter4_2Text;

public class TransitiveClosure {  //计算结点可达性的传递闭包
    private DirectedDFS[] all;
    TransitiveClosure(Digraph G){
        all=new DirectedDFS[G.V()];
        for(int v=0;v<G.V();v++)
            all[v]=new DirectedDFS(G,v);  //每个结点对应的索引号都初始化一个检查结点可达性的对象
    }

    boolean reachable(int v,int w){return all[v].marked(w);}  //判断结点w是否可以从结点v到达
}
