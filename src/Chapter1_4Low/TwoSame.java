package Chapter1_4Low;

import edu.princeton.cs.algs4.In;

//exercise 1.4.8
public class TwoSame {
    public static int twoSame(long[] a){
        int cnt=0;
        for(int i=0;i<a.length;i++){
            for(int j=i+1;j<a.length;j++){
                if(a[i]==a[j])
                    cnt++;
            }
        }
        return cnt;
    }
    public static void main(String[] args){
        String filePathString=System.getProperty("user.dir");
        String intFileString=filePathString+"/src/1Kints.txt";
        In in=new In(intFileString);
        long[] a=in.readAllLongs();
        System.out.println("相同的整数的数量为："+twoSame(a));
    }
}
