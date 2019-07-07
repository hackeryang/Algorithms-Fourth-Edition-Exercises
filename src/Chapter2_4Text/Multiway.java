package Chapter2_4Text;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.IndexMinPQ;
import edu.princeton.cs.algs4.StdOut;

public class Multiway {  //使用优先队列的多向归并
    public static void merge(In[] streams) {
        int N = streams.length;  //例如命令行参数里是m1.txt，m2.txt，m3.txt，每个文件里各有一些字符串，这里的N为命令行参数的数量，所以为3
        IndexMinPQ<String> pq = new IndexMinPQ<String>(N);
        for (int i = 0; i < N; i++)
            if (!streams[i].isEmpty())
                pq.insert(i, streams[i].readString());  //在三个文件内每个文件各拿出一个元素插入到优先队列中
        while (!pq.isEmpty()) {
            StdOut.println(pq.minKey());
            int i = pq.delMin();
            if (!streams[i].isEmpty())
                pq.insert(1, streams[i].readString());  //删去的最小元素属于哪一个文件或输入，就从该文件或输入中再插入一个元素到优先队列中
        }
    }

    public static void main(String[] args) {
        int N = args.length;
        In[] streams = new In[N];
        for (int i = 0; i < N; i++)
            streams[i] = new In(args[i]);
        merge(streams);
    }
}
