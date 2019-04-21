package Chapter1_5Low;

//Exercise 1.5.1
public class QuickFind {
    private int[] id; //access to component id(site indexed)
    private int count; //number of components
    int eachDoUnionArrayAccessTimes=0; //数组访问次数

    public QuickFind(int N){
        id=new int[N];
        count=N;
        for(int i=0;i<N;i++){
            id[i]=i;
        }
    }
    public int find(int p){
        eachDoUnionArrayAccessTimes++;
        return id[p];
    }
    public void union(int p,int q){
        //put p and q into the same component.
        boolean printDetail=true;
        if(printDetail){
            eachDoUnionArrayAccessTimes=0;  //对每次不一样的整数对进行连接时都把上一次的次数清零
            System.out.println("开始连通分量"+p+"和"+q);
        }
        int pID=find(p);
        int qID=find(q);
        //Nothing to do if p and q are already in the same component
        if(pID==qID) return;
        //Rename p's component to q's name
        for(int i=0;i<id.length;i++){
            eachDoUnionArrayAccessTimes++;
            if(id[i]==pID){
                eachDoUnionArrayAccessTimes++;
                id[i]=qID;
            }
        }
        count--;
        if(printDetail){
            /*
            * 以下代码输出数组元素
            * */
            System.out.print("id:{");
            for(int i=0;i<id.length;i++){
                if(i==id.length-1){
                    System.out.print(id[i]); //最后一个元素不需要加逗号
                }else{
                    System.out.print(id[i]+"，");
                }
            }
            System.out.println("}");
            System.out.println("数组访问的次数："+eachDoUnionArrayAccessTimes);
        }
    }
    public static void main(String[] args){
        QuickFind find=new QuickFind(10);
        find.union(9,0);
        find.union(3,4);
        find.union(5,8);
        find.union(7,2);
        find.union(2,1);
        find.union(5,7);
        find.union(0,3);
        find.union(4,2);
    }
}
