package Chapter1_3High;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

public class Josephus {
    public static void main(String[] args){
        int m=3;
        int N=41;
        Queue<Integer> queue=new Queue<Integer>();
        for(int i=0;i<N;i++)
            queue.enqueue(i);
        while(!queue.isEmpty()){
            for(int i=0;i<m-1;i++)
                queue.enqueue(queue.dequeue());   //每次都把队列从队列头上踢出m-1项，再把踢出的项加回到队列尾部，这样第m项就会出现在队列头上
            StdOut.print(queue.dequeue()+" ");    //踢掉队列头上的第一项，就是上一个步骤被排出来的第m项，然后在while大循环中继续这样的排列踢出流程，直到所有项都被踢出去
        }
        StdOut.println();
    }
}
