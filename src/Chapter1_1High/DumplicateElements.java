package Chapter1_1High;

import java.util.Arrays;

//exercise 1.1.28
public class DumplicateElements {
    public static int[] searchDumplicateElements(int[] array){
        int[] tempArray=new int[array.length];
        int sameNumTime=0;
        for(int i=0;i<array.length;i++){
            if(i==array.length-1){
                break;
            }
            if(array[i]==array[i+1]){ //相邻元素产生重复
                tempArray[sameNumTime]=array[i];
                sameNumTime++;
            }
        }
        int[] resultArray=new int[sameNumTime];
        for(int j=0;j<sameNumTime;j++)
            resultArray[j]=tempArray[j];
        return resultArray;
    }
    public static void main(String[] args){
        int[] N={1,2,3,4,5,6,6,7,8,9};
        System.out.println("original array: "+ Arrays.toString(N));
        int[] resultArray=searchDumplicateElements(N);
        System.out.println("result array: "+Arrays.toString(resultArray));
    }
}
