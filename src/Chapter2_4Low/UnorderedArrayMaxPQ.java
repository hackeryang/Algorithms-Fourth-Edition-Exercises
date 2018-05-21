package Chapter2_4Low;

import edu.princeton.cs.algs4.StdIn;

public class UnorderedArrayMaxPQ<Key extends Comparable<Key>> {
    //Exercise 2.4.3，用无序数组实现优先队列
    private Key[] pq;
    private int N=0;  //存储于pq[0..N-1]中
    public UnorderedArrayMaxPQ(int maxN){
        pq=(Key[])new Comparable[maxN];
    }
    public boolean isEmpty(){
        return N==0;
    }
    public int size(){return N;}
    public void insert(Key v){pq[N++]=v;}
    public Key delMax(){
        int max=0;
        for(int i=1;i<N;i++){
            if(less(max,i)){
                max=i;
            }
        }
        exch(max,N-1);  //上面找出最大项后，将最大项与最后一项交换位置，然后删除最后一项
        Key key=pq[--N];
        pq[N]=null;   //删除交换到最后一个元素的最大项，并防止对象游离，这里的N其实就是上一行的N-1，只不过上一行已经将N的大小减一
        return key;  //返回被删除的最大项的值
    }
    private boolean less(int i,int j){
        return pq[i].compareTo(pq[j])<0;
    }
    private void exch(int i,int j){
        Key t=pq[i];
        pq[i]=pq[j];
        pq[j]=t;
    }
    public static void main(String[] args){
        UnorderedArrayMaxPQ<String> pq=new UnorderedArrayMaxPQ<>(100);
        while(!StdIn.isEmpty()){
            String key=StdIn.readString();
            if(!key.equals("*")){
                pq.insert(key);
            }else{
                System.out.print(pq.delMax()+" ");
            }
        }
        System.out.println("("+pq.size()+" left on queue)");
    }
}
