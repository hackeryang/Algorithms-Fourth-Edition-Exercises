package Chapter1_5Text;

public class WeightedQuickUnionUF {
    private int[] id; //父链接数组（由触点索引）
    private int[] sz; //（由触点索引的）各个根节点所对应的分量的大小
    private int count; //连通分量的数量
    public WeightedQuickUnionUF(int N){
        count=N;
        id=new int[N];
        for(int i=0;i<N;i++) id[i]=i;
        sz=new int[N];
        for(int i=0;i<N;i++) sz[i]=1;  //开始的时候每一个树都只有自己节点这一层
    }
    public int count(){return count;}
    public boolean connected(int p,int q){return find(p)==find(q);}
    public int find(int p){
        //跟随链接找到根节点
        while(p!=id[p]) p=id[p];
        return p;
    }
    public void union(int p,int q){
        int i=find(p);
        int j=find(q);
        if(i==j) return;
        //将小树的根节点连接到大树的根节点
        if(sz[i]<sz[j]){
            id[i]=j;
            sz[j]+=sz[i];
        }else{
            id[j]=i;
            sz[i]+=sz[j];
        }
        count--;
    }
}
