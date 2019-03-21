package Chapter1_1Low;

//exercise 1.1.14
public class Lg {
    private static int lg(int N){
        int product=1;
        int x=-1;
        while(product<=N) //把等于的情况也纳入进去，从而避免了在如23>5这种情况的自增导致输出结果为3的情况
        {
            product*=2;
            x++;
        }
        return x;
    }
    public static void main(String[] args){
        int N=100;
        System.out.print(lg(N));
    }
}
