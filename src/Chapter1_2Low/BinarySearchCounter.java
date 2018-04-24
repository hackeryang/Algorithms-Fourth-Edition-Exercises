package Chapter1_2Low;

import Chapter1_2Text.Counter;

public class BinarySearchCounter {

    public static void main(String[] args){
        int[] numArray={1,2,3,4,67,88,89,101,222,788,999};
        Counter counter=new Counter();
        int index=rank(222,numArray,counter);
        System.out.println("index: "+index+"\ncounter: "+counter.counter);
    }
    public static int rank(int t,int[] array,Counter counter){
        int lo=0;
        int hi=array.length-1;
        int mid=lo+(lo-hi)/2;
        while(t!=array[mid]){
            counter.counter++;
            if(t<array[mid]){
                if(hi==mid){
                    return -1;
                }
                hi=mid;
            }else if(t>array[mid]){
                if(lo==mid){
                    return -1;
                }
                lo=mid;
            }else{
                return mid;
            }
            mid=(lo+hi)/2;
        }
        return mid;
    }
}
