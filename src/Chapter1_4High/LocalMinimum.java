package Chapter1_4High;

//exercise 1.4.18
public class LocalMinimum {
    public static int localMinimum(int[] x){
        if(x==null || x.length==0){
            return -1;
        }
        if(x.length==1 || x[0]<x[1]){
            return 0;
        }
        if(x[x.length-1]<x[x.length-2]){
            return x.length-1;
        }
        int mid=0;
        int left=1;
        int right=x.length-2;
        while(left<right){
            mid=(left+right)/2;
            if(x[mid-1]<x[mid]){
                right=mid-1;
            }else if(x[mid+1]<x[mid]){
                left=mid+1;
            }else{
                return mid;
            }
        }
        return left;
    }
    public static void main(String[] args){
        int[] a={2,1,3,4,5,6,11,14,8,25};
        int index=localMinimum(a);
        System.out.println("局部最小元素，"+"a["+index+"]值为"+a[index]);
    }
}
