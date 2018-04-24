package Chapter1_1High;

public class EquivalentKey {
    public static void main(String[] args){
        int[] N={1,2,3,4,5,6,7,8,9,89,89,100};
        System.out.println(rank(89,N)+"");
        System.out.println(count(89,N)+"");
    }
    public static int binarySearch(int target,int[] N){
        int lo=0;
        int hi=N.length-1;
        while(lo<hi){
            int mid=lo+(hi-lo)/2;
            if(target>N[mid])
                lo=mid+1;
            else if(target<N[mid])
                hi=mid-1;
            else return mid;
        }
        return -1;
    }
    public static int rank(int key,int[] N){
        int searchResult=binarySearch(key,N);
        System.out.println("searchResult: "+searchResult);
        int resultNum=0;
        if(searchResult!=-1){ //如果二分查找结果存在
            for(int i=0;i<N.length;i++){
                if(N[i]==N[searchResult]){
                    resultNum=i;
                    break;
                }
            }
        }
        return resultNum;
    }
    public static int count(int key,int[] N){
        int searchResult=binarySearch(key,N);
        System.out.println("searchResult: "+searchResult);
        int resultNum=0;
        if(searchResult!=-1){
            for(int i=0;i<N.length;i++){
                if(N[i]==N[searchResult]){
                    resultNum++;
                }
            }
        }
        return resultNum;
    }
}
