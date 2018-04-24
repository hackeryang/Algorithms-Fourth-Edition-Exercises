package Chapter1_4High;

import edu.princeton.cs.algs4.Stack;

public class QueueWithTwoStacks<T> {
    private Stack<T> stack1;
    private Stack<T> stack2;
    public QueueWithTwoStacks(){
        stack1=new Stack<T>();
        stack2=new Stack<T>();
    }
    public void enqueue(T item){
        stack1.push(item);
    }
    public T dequeue(){
        if(stack1.size()<1 && stack2.size()<1){
            System.out.println("Queue is empty");
            return null;
        }
        //把stack1清空
        while(stack1.size()>1){ //这里不是大于0，所以stack1最终还有一个元素没有放到stack2中，还在stack1里
            T element=stack1.pop();
            stack2.push(element);
        }
        T ele=stack1.pop();  //这里才把原来stack1中最后一个剩余的元素Pop()出来，存在最后要返回的变量ele里
        //把stack2清空
        while(stack2.size()>0){  //stack2只含有原来stack1中从第二个元素开始到最后一个元素，所以再push回stack1时，第一个元素已经没了，相当于队列中的dequeue
            T element=stack2.pop();
            stack1.push(element);
        }
        return ele;
    }
    public static void main(String[] args){
        QueueWithTwoStacks gfg=new QueueWithTwoStacks();
        gfg.enqueue("我的");
        gfg.enqueue("名字");
        gfg.enqueue("叫");
        gfg.enqueue("yyc");
        gfg.enqueue("hacker");
        System.out.println(gfg.dequeue());
        System.out.println(gfg.dequeue());
        System.out.println(gfg.dequeue());
        System.out.println(gfg.dequeue());
        System.out.println(gfg.dequeue());
        System.out.println(gfg.dequeue());
    }
}
