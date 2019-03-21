package Chapter1_4High;

import edu.princeton.cs.algs4.Stack;

//exercise 1.4.27
public class QueueWithTwoStacksFaster<T> {
    private Stack<T> stack1;
    private Stack<T> stack2;

    public QueueWithTwoStacksFaster(){
        stack1=new Stack<T>();
        stack2=new Stack<T>();
    }
    public void enqueue(T item){
        stack1.push(item);
    }
    public T dequeue() throws Exception{
        if(stack1.size()<1 && stack2.size()<1){
            System.out.println("Queue is empty");
        }
        T ele=null;
        //stack2为空，则将stack1倒入stack2
        if(stack2.size()<1){
            while(stack1.size()>1){
                T element=stack1.pop();
                stack2.push(element);
            }
            ele=stack1.pop();
        }else{
            ele=stack2.pop();
        }
        return ele;
    }
    public static void main(String[] args){
        QueueWithTwoStacksFaster queueWithTwoStacksFaster=new QueueWithTwoStacksFaster();
        queueWithTwoStacksFaster.enqueue("我的");
        queueWithTwoStacksFaster.enqueue("名字");
        queueWithTwoStacksFaster.enqueue("叫");
        queueWithTwoStacksFaster.enqueue("yyc");
        queueWithTwoStacksFaster.enqueue("hacker");
        try{
            System.out.println(queueWithTwoStacksFaster.dequeue());
            System.out.println(queueWithTwoStacksFaster.dequeue());
            System.out.println(queueWithTwoStacksFaster.dequeue());
            System.out.println(queueWithTwoStacksFaster.dequeue());
            System.out.println(queueWithTwoStacksFaster.dequeue());
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
