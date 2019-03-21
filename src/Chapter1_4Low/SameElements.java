package Chapter1_4Low;

import java.util.Arrays;

//exercise 1.4.12
public class SameElements {
    //x和y是已经被排序过的数组
    public static void sameElements(int[] x,int[] y){
        for(int i=0,j=0;i<x.length && j<y.length;){
            if(x[i]<y[j]){
                i++;
            }else if(x[i]>y[j]){
                j++;
            }else{
                System.out.println(""+x[i]);
                i++;
                j++;
            }
        }
    }
    public static void main(String[] args){
        int[] b1={1,2,3, 5, 4, 5, 6, 77, 7, 8, 8, 9, 1, 11, 22, 234, 90,234, 345 };
        int[] b2={1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
        Arrays.sort(b1);
        Arrays.sort(b2);
        sameElements(b1,b2);
    }
}
