package Chapter1_4High;

import java.util.Arrays;

public class StaticSETofInts {
    private int[] a;
    public StaticSETofInts(int[] keys){
        a=new int[keys.length];
        for(int i=0;i<keys.length;i++)
            a[i]=keys[i]; //defensive copy
        Arrays.sort(a);
    }
    public boolean contains(int key){
        return rank(key)!=-1;
    }
    private int rank(int key){
        //Binary search
        int lo=0;
        int hi=a.length-1;
        while(lo<=hi){
            //key is in a[lo..hi] or not present.
            int mid=lo+(lo+hi)/2;
            if(a[mid]>key)
                hi=mid-1;
            else if(a[mid]<key)
                lo=mid+1;
            else return mid;
        }
        return -1;
    }
}
