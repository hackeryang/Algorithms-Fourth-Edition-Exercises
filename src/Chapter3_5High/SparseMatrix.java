package Chapter3_5High;

//Exercise 3.5.23
public class SparseMatrix {
    private SparseVector[] rows;  //矩阵每行的向量
    private int n;  //向量的行数

    public SparseMatrix(int n){
        this.n=n;
        rows=new SparseVector[n];
        for(int i=0;i<n;i++){
            rows[i]=new SparseVector();  //矩阵的每一行都初始化一个向量
        }
    }
    public void put(int i,int j,double val){  //在矩阵中插入键值对
        if(i<0 || i>=n){
            throw new IllegalArgumentException("Illegal index");
        }
        if(j<0 || j>=n){
            throw new IllegalArgumentException("Illegal index");
        }
        rows[i].put(j,val);  //i为矩阵的行，j为矩阵的列
    }
    public double get(int i,int j){
        if(i<0 || i>=n){
            throw new IllegalArgumentException("Illegal index");
        }
        if(j<0 || j>=n){
            throw new IllegalArgumentException("Illegal index");
        }
        return rows[i].get(j);
    }
    public SparseMatrix plus(SparseMatrix that){  //矩阵加法
        if(this.n!=that.n){
            throw new RuntimeException("Dimensions disaggree");  //两个矩阵的行列数都必须相同才能相加
        }
        SparseMatrix matrix=new SparseMatrix(n);
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                double sum=rows[i].get(j)+that.get(i,j);
                if(sum!=0){
                    matrix.put(i,j,sum);  //将矩阵元素相加结果插入到新创建的空矩阵中
                }
            }
        }
        return matrix;
    }
    public SparseVector multiply(SparseVector that){  //矩阵与向量相乘
        SparseVector vector=new SparseVector();
        for(int i=0;i<n;i++){
            double sum=rows[i].dot(that);  //矩阵的每一行向量点乘被乘的向量
            if(sum!=0){
                vector.put(i,sum);  //将相乘结果插入到新的空向量中
            }
        }
        return vector;
    }
    public SparseMatrix multiply(SparseMatrix that){  //两个矩阵的乘法
        if(this.n!=that.n){
            throw new RuntimeException("Dimensions disagree");
        }
        SparseMatrix matrix=new SparseMatrix(n);
        SparseVector[] thatLines=new SparseVector[n];
        for(int i=0;i<n;i++){
            thatLines[i]=new SparseVector();  //新的空矩阵每一行都创建一个向量
        }
        for(int i=0;i<n;i++){
            for(int j=0;i<n;j++){
                double val=that.get(i,j);  //获得第i行第j列的元素值
                if(val!=0){
                    thatLines[j].put(i,val);  //将其中一个矩阵进行转置，行和列元素颠倒，将上一行获得的元素值插入到第j行第i列
                }
            }
        }
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                double sum=rows[i].dot(thatLines[j]);  //将两个矩阵的元素点乘
                if(sum!=0){
                    matrix.put(i,j,sum);  //将点乘结果依次放入到一个空的矩阵中
                }
            }
        }
        return matrix;
    }

    @Override
    public String toString() {
        String s="n= "+n+"\n";
        for(int i=0;i<n;i++){
            s+=i+": "+rows[i]+"\n";
        }
        return s;
    }
    public static void main(String[] args){
        SparseMatrix A=new SparseMatrix(5);
        SparseVector x=new SparseVector();
        A.put(0,0,1.0);
        A.put(1,1,1.0);
        A.put(2,2,1.0);
        A.put(3,3,1.0);
        A.put(4,4,1.0);
        A.put(2,4,0.3);
        x.put(0,0.75);
        x.put(2,0.11);
        System.out.println("x: "+x);
        System.out.println("A: "+A);
        System.out.println("A+A: "+A.plus(A));
        System.out.println("A*x: "+A.multiply(x));
        System.out.println("A*A: "+A.multiply(A));
    }
}
