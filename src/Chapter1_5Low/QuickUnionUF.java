package Chapter1_5Low;

//Exercise 1.5.7
public class QuickUnionUF {
    private int[] id; //access to component id(site indexed)
    private int count; //number of components

    public QuickUnionUF(int N) {
        id = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
        }
    }

    private int find(int p) {
        //Find component name
        while (p != id[p]) p = id[p];
        return p;
    }

    public void union(int p, int q) {
        //Give p and q the same root
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot) return;
        id[pRoot] = qRoot;
        count--;
    }
}
