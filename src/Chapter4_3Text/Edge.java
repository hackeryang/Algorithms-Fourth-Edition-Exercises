package Chapter4_3Text;

public class Edge implements Comparable<Edge> {  //加权无向图中的加权边
    private final int v;  //一个结点
    private final int w;  //另一个结点
    private final double weight;  //边的权重

    public Edge(int v,int w,double weight){
        this.v=v;
        this.w=w;
        this.weight=weight;
    }

    public double weight(){return weight;}  //返回边的权重
    public int either(){return v;}  //返回边两端的其中一个结点

    public int other(int vertex){  //返回边两端的另一个结点
        if(vertex==v) return w;
        else if(vertex==w) return v;
        else throw new RuntimeException("Iconsistent edge");
    }

    @Override
    public int compareTo(Edge that) {  //将这条边与that表示的另一条边比较权重
        if(this.weight()<that.weight()) return -1;
        else if(this.weight()>that.weight()) return +1;
        else return 0;
    }

    public String toString(){return String.format("%d-%d %.2f",v,w,weight);}  //用字符串表示两条边的连接以及权重
}
