package Chapter2_4Text;

import edu.princeton.cs.algs4.*;

public class TopM {  //最小优先队列，留下最大的，删掉最小的
    public static void main(String[] args) {
        //打印输入流中最大的M行
        int M = Integer.parseInt(args[0]);
        MinPQ<Transaction> pq = new MinPQ<Transaction>(M + 1);  //优先队列的容量为M+1，只存最大的这么多个元素
        while (StdIn.hasNextLine()) {
            //为下一行输入创建一个元素并放入优先队列中
            pq.insert(new Transaction(StdIn.readLine()));
            if (pq.size() > M)
                pq.delMin();  //如果优先队列中存在M+1个元素则删除其中最小的元素，如果新加入的元素最小也会被删掉
        }  //最大的M个元素都在优先队列中
        Stack<Transaction> stack = new Stack<Transaction>();
        while (!pq.isEmpty()) stack.push(pq.delMin());   //栈中存放顺序是最小优先队列中，较小的先push进来放在栈底，最大的在栈顶
        for (Transaction t : stack) StdOut.println(t);  //因为上一行的存放方式，较大元素在栈顶所以先被输出
    }
}
