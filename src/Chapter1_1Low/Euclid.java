package Chapter1_1Low;

//exercise 1.1.24
public class Euclid {
    public static int gcd(int m,int n){
        int rem=0;
        int M=m;
        int N=n;
        if(m<n){
            M=n;
            N=m;
        }
        rem=M%N;
        if(0==rem)
            return N;
        System.out.println("m: "+N+";n: "+rem);
        return gcd(N,rem);
    }
    public static void main(String[] args){
        int a=gcd(1111111,1234567);
        System.out.println(a+"");
    }
}
