package Chapter1_3High;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

//Exercise 1.3.37
public class Josephus {  //约瑟夫环问题
    public static void main(String[] args) {
        int m = 3;
        int N = 41;
        Queue<Integer> queue = new Queue<Integer>();
        for (int i = 0; i < N; i++)
            queue.enqueue(i);
        while (!queue.isEmpty()) {
            for (int i = 0; i < m - 1; i++)
                queue.enqueue(queue.dequeue());   //每次都把队列从队列头上踢出m-1项，再把踢出的项加回到队列尾部，这样第m项就会出现在队列头上
            StdOut.print(queue.dequeue() + " ");    //踢掉队列头上的第一项，就是上一个步骤被排出来的第m项，然后在while大循环中继续这样的排列踢出流程，直到所有项都被踢出去
        }
        StdOut.println();
    }

    //上面的采用队列的方法占用内存较大且算法复杂度较大，下面这种递归方法效率更好
    public int lastRemaining(int n, int m) {
        if (n <= 0) return -1;
        if (m <= 0) return n - 1;
        if (n == 1) return 0;
        /*
         * 采用递归思想，与通过哈希值来编号分区一样，当有n个人时，在第一轮中报数为m-1的人编号为(m-1)%n。设最后留下的人编号结果为f[n]；
         * 同理已经求得n-1个人情况下最后剩下的人的编号为f[n-1]，则f[n]=(f[n-1]+m)%n，
         * 因为上一轮出列一个人后，下一个人重新从0开始编号，所以n人为第一轮，n-1人为第二轮的情况下，第二轮把每个人原来的编号都减了m，
         * 因此重新加上m就能恢复到上一轮原来的编号，如此递归下去，只剩下一个人的情况即f[1]=0
         * */
        return (lastRemaining(n - 1, m) + m) % n;
    }
}
