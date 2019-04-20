package Chapter3_4Text;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.SequentialSearchST;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class SeparateChainingHashST<Key,Value> {  //基于拉链法的散列表
    private int N;  //键值对总数
    private int M;  //散列表大小
    private SequentialSearchST<Key,Value>[] st;  //存放链表对象的数组

    public SeparateChainingHashST(){this(997);}  //默认有997条链表
    public SeparateChainingHashST(int M){
        //创造M条链表
        this.M=M;
        st=(SequentialSearchST<Key,Value>[])new SequentialSearchST[M];
        for(int i=0;i<M;i++)
            st[i]=new SequentialSearchST();
    }
    //每个十六进制数4bit，8位16进制数正好是4Byte，大小刚好是int，F的二进制为1111，7的二进制为0111，0x7fffffff就是最大整数，第一位0表示正数
    private int hash(Key key){return (key.hashCode() & 0x7fffffff)%M;}
    public Value get(Key key){return (Value)st[hash(key)].get(key);}  //根据对应键的哈希值，在哈希值数组中找到该哈希值指向的链表，然后在所在链表中查找键的值
    public void put(Key key,Value val){  //根据对应键的哈希值，在哈希值数组中找到该哈希值指向的链表，然后在所在链表中插入键值对
        if(val==null){
            delete(key);
            return;
        }
        if(N>=8*M) resize(2*M);  //当每条链表平均长度大于等于8时扩容哈希值数组
        if(!st[hash(key)].contains(key)) N++;
        st[hash(key)].put(key,val);
    }
    //Exercise 3.1.5，删除操作,先用哈希值找到含有该键的SequentialSearchST对象，然后调用该对象的delete()方法
    public void delete(Key key){
        int i=hash(key);
        if(st[i].contains(key)) N--;
        st[i].delete(key);
        if(N>0 && N<=2*M) resize(M/2); //当每条链表平均长度小于等于2时，缩减哈希值数组的容量
    }
    //Exercise 3.4.19，在迭代器中将键以符号表的形式遍历
    public Iterable<Key> keys(){
        Queue<Key> queue=new Queue<Key>();
        for(int i=0;i<M;i++){
            for(Key key:st[i].keys())
                queue.enqueue(key);
        }
        return queue;
    }
    private void resize(int cap){  //保持每条键值对链表平均长度在2到8之间，这个范围在put和delete中完成
        SeparateChainingHashST<Key,Value> temp=new SeparateChainingHashST<Key,Value>(cap);  //创建一个新的调整过容量的拉链法散列表
        for(int i=0;i<M;i++){
            for(Key key:st[i].keys()){
                temp.put(key,st[i].get(key));  //将原有键值对放到新的拉链法散列表中
            }
        }
        this.M=temp.M;  //将原有散列表键值对的属性迁移到新散列表中
        this.N=temp.N;
        this.st=temp.st;
    }

    public static void main(String[] args){
        SeparateChainingHashST<String,Integer> st=new SeparateChainingHashST<String,Integer>();
        for(int i=0;!StdIn.isEmpty();i++){
            String key=StdIn.readString();
            st.put(key,i);
        }
        for(String s:st.keys())
            StdOut.println(s+" "+st.get(s));
    }
}
