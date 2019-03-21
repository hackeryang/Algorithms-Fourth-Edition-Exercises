package Chapter1_1Low;

//exercise 1.1.20
public class Logarithmic {
    public static double logarithmic(int N){
        if(N==0)
            return 0;
        if(N==1)
            return 0;
        return (logarithmic(N-1)+Math.log(N));
    }
    public static void main(String[] args){
        double result=logarithmic(2);
        System.out.println(result);
    }
}
