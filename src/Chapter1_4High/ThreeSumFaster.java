package Chapter1_4High;

//exercise 1.4.15
public class ThreeSumFaster {
    public int threeSumFaster(long[] a){
        int cnt=0;
        int len=a.length;
        for(int j=0;j<len-2;j++){
            for(int k=j+1,h=len-1;k<h;){
                if(a[j]+a[k]+a[h]<0){
                    k++;
                }else if(a[j]+a[k]+a[h]>0){
                    h--;
                }else{
                    k++;
                    h--;
                    ++cnt;
                }
            }
        }
        return cnt;
    }
}
