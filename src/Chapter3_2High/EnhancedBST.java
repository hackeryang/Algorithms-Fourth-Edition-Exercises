package Chapter3_2High;

import edu.princeton.cs.algs4.BST;
import edu.princeton.cs.algs4.StdIn;

import java.util.Arrays;

public class EnhancedBST {
    //Exercise 3.2.25，用一组键构造一棵和二分查找等价的二分查找树
    public static BST<String,Integer> balance(String[] a){
        Arrays.sort(a);
        BST<String,Integer> bst=new BST<>();
        balance(bst,a,0,a.length-1);
        return bst;
    }
    private static void balance(BST<String,Integer>bst,String[] a,int lo,int hi){
        if(lo>hi){
            return;
        }
        int mid=lo+(hi-lo)/2;
        bst.put(a[mid],mid);
        balance(bst,a,lo,mid-1);  //在左子树中递归插入中间键和值
        balance(bst,a,mid+1,hi);  //在右子树中递归插入中间键和值
    }
    public static void main(String[] args){
        String[] a= StdIn.readAllStrings();
        BST<String,Integer> bst=balance(a);
        for(String key:bst.keys()){
            System.out.println(key+" "+bst.get(key));
        }
    }
}
