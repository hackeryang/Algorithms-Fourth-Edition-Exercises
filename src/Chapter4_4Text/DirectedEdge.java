package Chapter4_4Text;

public class DirectedEdge {  //加权有向边的数据结构
    private final int v;  //边的起点
    private final int w;  //边的终点
    private final double weight;  //边的权重

    public DirectedEdge(int v,int w,double weight){
        this.v=v;
        this.w=w;
        this.weight=weight;
    }

    public double weight(){return weight;}  //返回加权有向边的权重
    public int from(){return v;}  //返回这条边的起点
    public int to(){return w;}  //返回这条边指向的顶点
    public String toString(){return String.format("%d->%d %.2f",v,w,weight);}  //按照格式输出
}
