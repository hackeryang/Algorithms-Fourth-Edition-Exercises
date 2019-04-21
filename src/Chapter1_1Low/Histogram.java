package Chapter1_1Low;

//Exercise 1.1.15
public class Histogram {  //柱状图的实现
    public static int[] histogram(int[] a,int M){
        int[] b=new int[M];
        int n=0;
        int m=0;
        for(int i=0;i<M;i++){
            for(int j=0;j<a.length;j++){
                if(i==a[j]){
                    n++;
                }
                b[i]=n;
            }
            n=0;
        }
        for(int i=0;i<M;i++){
            m=m+b[i];
        }
        return b;
    }
    public static int[] histogram2(int[] a,int M){
        int[] h=new int[M];
        int N=a.length;
        for(int i=0;i<N;i++)
            if(a[i]<M)
                h[a[i]]++;
        return h;
    }
    public static void main(String[] args){
        int a[]={1,2,3,4,5,6,7,8,9,10,11,11,11,12};
        int M=13;
        int b[]=histogram(a,M);
        System.out.println("调用函数后获取的数组：");
        for(int i=0;i<b.length;i++){
            System.out.println(b[i]);
        }
    }
}
