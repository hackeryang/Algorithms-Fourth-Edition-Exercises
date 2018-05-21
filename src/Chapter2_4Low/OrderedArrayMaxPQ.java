package Chapter2_4Low;

import edu.princeton.cs.algs4.StdIn;

public class OrderedArrayMaxPQ<Key extends Comparable<Key>> {
    //Exercise 2.4.3，用有序数组实现优先队列
    private Key[] pq;
    private int N=0;  //存储于pq[0..N-1]中

    public OrderedArrayMaxPQ(int maxN){
        pq=(Key[])new Comparable[maxN];
    }
    public boolean isEmpty(){return N==0;}
    public int size(){return N;}
    public void insert(Key v){  //插入元素
        int i=N-1;
        for(;i>=0 && pq[i].compareTo(v)>0;i--){
            pq[i+1]=pq[i];  //将比插入的元素v大的后面所有元素都右移一位，留出插入空间
        }
        pq[i+1]=v;  //在空出的位置插入元素v
        N++;  //元素数量加一
    }
    public Key delMax(){
        Key key=pq[--N];
        pq[N]=null;  //删除交换到最后一个元素的最大项，并防止对象游离，这里的N其实就是上一行的N-1，只不过上一行已经将N的大小减一
        return key;
    }
    public static void main(String[] args){
        OrderedArrayMaxPQ<String> pq=new OrderedArrayMaxPQ<>(100);
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
