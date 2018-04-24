package Chapter1_1Low;

public class Rank {
    public static int rank(int key,int[] a){
        return rank(key,a,0,16,1);
    }
    public static int rank(int key,int[] a,int lo,int hi,int deep){
        //如果key存在于a[]中，它的索引不会小于lo且不会大于hi
        if(lo>hi)
            return -1;
        int mid=lo+(hi-lo)/2;
        for(int i=0;i<deep;i++)
            System.out.print(" ");
        System.out.println("lo: "+lo+" hi: "+hi);
        if(key<a[mid])
            return rank(key,a,lo,mid-1,deep+1);
        else if(key>a[mid])
            return rank(key,a,mid+1,hi,deep+1);
        else
            return mid;
    }
    public static void main(String[] args){
        int[] array={0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16};
        rank(10,array);
    }
}
