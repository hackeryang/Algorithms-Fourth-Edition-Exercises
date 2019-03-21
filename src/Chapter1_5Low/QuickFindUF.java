package Chapter1_5Low;

//exercise 1.5.7
public class QuickFindUF {
    private int[] id; //access to component id(site indexed)
    private int count; //number of components

    public QuickFindUF(int N){
        id=new int[N];
        for(int i=0;i<N;i++){
            id[i]=i;
        }
    }
    public int find(int p){
        return id[p];
    }
    public void union(int p,int q){
        //put p and q into the same component
        int pID=find(p);
        int qID=find(q);
        //Nothing to do if p and q are already in the same component
        if(pID==qID) return;
        //Rename p's component to q's name
        for(int i=0;i<id.length;i++)
            if(id[i]==pID) id[i]=qID;
        count--;
    }
}
