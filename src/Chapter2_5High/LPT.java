package Chapter2_5High;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdIn;

import java.util.ArrayList;
import java.util.Arrays;

public class LPT {
    //Exercise 2.5.13，使用Job.java和Processor.java的数据类型和方法
    public static void main(String[] args){
        int M=Integer.parseInt(args[0]);  //M个处理器
        int N= StdIn.readInt();  //N个任务
        Job[] jobs=new Job[N];
        for(int i=0;i<N;i++){
            jobs[i]=new Job(StdIn.readString(),StdIn.readDouble());
        }
        Arrays.sort(jobs);  //对处理任务进行优先级排序
        MinPQ<Processor> processorPQ=new MinPQ<>(M);  //将M个处理器加入到最小优先队列中
        for(int i=0;i<M;i++){
            processorPQ.insert(new Processor());
        }
        for(int i=N-1;i>=0;i--){
            Processor processor=processorPQ.delMin();  //释放已有任务占用总时间最小的处理器
            processor.insert(jobs[i]);  //使用处理器来处理任务
            processorPQ.insert(processor);  //任务完成后继续将该处理器插入回优先队列中
        }
        for(int i=0;!processorPQ.isEmpty();i++){
            Processor processor=processorPQ.delMin();
            ArrayList<Job> jobList=processor.getJobs();
            System.out.println("Processor "+i+": ");
            for(int j=0;j<jobList.size();j++){
                System.out.println(jobList.get(j));
            }
        }
    }
}
