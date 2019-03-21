package Chapter1_4High;

//exercise 1.4.17
public class FastestPairLinear {
    public static void fastestPairLinear(double[] x){  //算法复杂度为线性的，因为有一个遍历循环，比较次数和数组长度有关
        double max=Double.MIN_VALUE;
        double min=Double.MAX_VALUE;
        for(int i=0;i<x.length;i++){
            if(x[i]>=max){
                max=x[i];
            }
            if(x[i]<min){
                min=x[i];
            }
        }
        System.out.println("最遥远的两个数为："+max);
        System.out.println("和"+min);
    }
    public static void main(String[] args){
        double[] a={1,2,3,4,5,888,76,45};
        fastestPairLinear(a);
    }
}
