package Chapter1_5Low;

//Exercise 1.5.3
public class WeightedQuickUnion {
    private int[] id;
    private int[] sz; //树的深度
    private int count;
    int eachDoUnionArrayAccessTimes = 0; //数组访问次数

    //parent link(site indexed)
    //size of component for roots(site indexed)
    //number of components
    public WeightedQuickUnion(int N) {
        count = N;
        id = new int[N];
        for (int i = 0; i < N; i++) id[i] = i;
        sz = new int[N];
        for (int i = 0; i < N; i++) sz[i] = 1; //一开始每个节点的树深度都是1
    }

    public int count() {
        return count;
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    private int find(int p) {
        //Follow links to find a root
        while (p != id[p]) {
            p = id[p];
            eachDoUnionArrayAccessTimes += 2; //遍历读取某个元素算一次，设置值也算一次，所以加2次
        }
        eachDoUnionArrayAccessTimes++; //查询p的时候依然会读取一次
        return p;
    }

    public void union(int p, int q) {
        boolean printDetail = true;
        if (printDetail) {
            eachDoUnionArrayAccessTimes = 0; //对每次不一样的整数对进行连接时都把上一次的次数清零
            System.out.println("开始连通分量" + p + "和" + q);
        }
        int i = find(p);
        int j = find(q);
        if (i == j) return;
        //Make smaller root point to larger one
        if (sz[i] < sz[j]) {
            id[i] = j;
            sz[j] += sz[i];
        } else {
            id[j] = i;
            sz[i] += sz[j];
        }
        count--;
        eachDoUnionArrayAccessTimes++;
        if (printDetail) {
            /*
             * 以下代码输出数组元素
             * */
            System.out.print("id:{");
            for (int index = 0; index < id.length; index++) {
                if (index == id.length - 1) {
                    System.out.print(id[index]);
                } else {
                    System.out.print(id[index] + "，");
                }
            }
            System.out.println("}");
        }
        System.out.println("数组访问的次数：" + eachDoUnionArrayAccessTimes);
    }

    public static void main(String[] args) {
        WeightedQuickUnion weightedQuickUnion = new WeightedQuickUnion(10);
        weightedQuickUnion.union(9, 0);
        weightedQuickUnion.union(3, 4);
        weightedQuickUnion.union(5, 8);
        weightedQuickUnion.union(7, 2);
        weightedQuickUnion.union(2, 1);
        weightedQuickUnion.union(5, 7);
        weightedQuickUnion.union(0, 3);
        weightedQuickUnion.union(4, 2);
    }
}
