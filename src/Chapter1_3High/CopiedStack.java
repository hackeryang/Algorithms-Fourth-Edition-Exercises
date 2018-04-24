package Chapter1_3High;

import edu.princeton.cs.algs4.Stack;

public class CopiedStack {
    public static void main(String[] args){
        Stack<Integer> stack1=new Stack<>();
        for(int i=0;i<10;i++){
            stack1.push(i);
        }
        Stack<Integer> stack2=new Stack<Integer>();  //括号里加入stack1失败
        stack1.pop();
        stack1.push(10);
        while(!stack1.isEmpty()){
            System.out.print(stack1.pop()+" ");
        }
        System.out.println();
        while(!stack2.isEmpty()){
            System.out.print(stack2.pop()+" ");
        }
        System.out.println();
    }
}
