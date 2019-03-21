package Chapter1_5Low;

//exercise 1.5.2
public class QuickUnion {
    private int[] id; //access to component id(site indexed)
    private int count; //number of components
    int eachDoUnionArrayAccessTimes=0; //数组访问次数

    public QuickUnion(int N){
        id=new int[N];
        for(int i=0;i<N;i++){
            id[i]=i;
        }
    }
    private int find(int p){
        //Find component name
        while(p!=id[p]){
            p=id[p];
            eachDoUnionArrayAccessTimes+=2; //遍历读取某个元素算一次，设置值也算一次，所以加2次
        }
        eachDoUnionArrayAccessTimes++; //查询p的时候依然会读取一次
        return p;
    }
    public void union(int p,int q){
        boolean printDetail=true;
        if(printDetail){
            eachDoUnionArrayAccessTimes=0; //对每次不一样的整数对进行连接时都把上一次的次数清零
            System.out.println("开始连通分量"+p+"和"+q);
        }
        //Give p and q the same root
        int pRoot=find(p);
        int qRoot=find(q);
        if(pRoot==qRoot) return;
        id[pRoot]=qRoot;
        eachDoUnionArrayAccessTimes++;
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
        }
        System.out.println("数组访问的次数："+eachDoUnionArrayAccessTimes);
    }

    public static void main(String[] args){
        QuickUnion find=new QuickUnion(10);
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
