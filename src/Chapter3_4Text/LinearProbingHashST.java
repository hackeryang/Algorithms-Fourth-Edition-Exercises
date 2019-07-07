package Chapter3_4Text;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class LinearProbingHashST<Key, Value> {  //基于线性探测的符号表
    private int N;  //符号表中键值对的总数
    private int M;  //线性探测表的大小
    private Key[] keys;  //键
    private Value[] vals;  //值

    public LinearProbingHashST(int cap) {
        M = cap;
        keys = (Key[]) new Object[M];
        vals = (Value[]) new Object[M];
    }

    //每个十六进制数4bit，8位16进制数正好是4Byte，大小刚好是int，F的二进制为1111，7的二进制为0111，0x7fffffff就是最大整数，第一位0表示正数
    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }

    private void resize(int cap) {  //始终保持散列表占用率α=N/M不大于1/2，在put和delete中完成
        LinearProbingHashST<Key, Value> t;
        t = new LinearProbingHashST<Key, Value>(cap);  //创造一个新的线性探测符号表来容纳键值对
        for (int i = 0; i < M; i++) {
            if (keys[i] != null)
                t.put(keys[i], vals[i]);  //将原有键值对放到新的线性探测散列表中
        }
        keys = t.keys;  //将新散列表的键数组和值数组的引用赋值给原散列表引用，从而覆盖掉原散列表引用指向的键和值数组，即把原有散列表键值对的属性迁移到新散列表中
        vals = t.vals;
        M = t.M;
    }

    public void put(Key key, Value val) {
        if (N >= M / 2) resize(2 * M);  //当占用空间超过一半时，将容量M加倍
        int i;
        //先从散列表的键数组中的键哈希值索引处key[hash(key)]向后一位一位遍历（从该索引开始是因为该位置处本应该放这个键，如果已经放了别的键就向后顺延放置），如果找到了对应的键，则修改键的值
        for (i = hash(key); keys[i] != null; i = (i + 1) % M) {
            if (keys[i].equals(key)) {
                vals[i] = val;
                return;
            }
        }
        keys[i] = key;  //如果没找到对应键就插入新键值对
        vals[i] = val;
        N++;  //插入新键值对后更新数目
    }

    public Value get(Key key) {  //查找键的值
        for (int i = hash(key); keys[i] != null; i = (i + 1) % M)
            if (keys[i].equals(key))
                return vals[i];
        return null;
    }

    //Exercise 3.4.19，在迭代器中将键以符号表的形式遍历
    public Iterable<Key> keys() {
        Queue<Key> queue = new Queue<Key>();
        for (int i = 0; i < M; i++)
            if (keys[i] != null) queue.enqueue(keys[i]);
        return queue;
    }

    public void delete(Key key) {  //删除对应键的元素
        if (!contains(key)) return;
        int i = hash(key);
        while (!key.equals(keys[i]))  //当未找到对应键的位置时向后继续查找
            i = (i + 1) % M;
        keys[i] = null;  //找到后将键值对置为空
        vals[i] = null;
        i = (i + 1) % M;  //向后移一位开始处理被删除键值对后面的元素
        while (keys[i] != null) {  //将要删除的键值对删除后，需要把被删除键值对后面的所有键重新插入到散列表以免前面删除的键置为空之后后面的元素都无法访问
            Key keyToRedo = keys[i];
            Value valToRedo = vals[i];
            keys[i] = null;  //将被删除元素后面的所有该键簇的元素都删除，再重新从当初被删除元素的位置开始将后面的元素一一插入
            vals[i] = null;
            N--;
            put(keyToRedo, valToRedo);
            i = (i + 1) % M;
        }
        N--;  //删除元素并处理好后续元素后，将总数量减一
        if (N > 0 && N == M / 8) resize(M / 2);  //当占用率α小于1/8时，减半数组容量
    }

    public static void main(String[] args) {
        LinearProbingHashST<String, Integer> st = new LinearProbingHashST<String, Integer>(10);
        for (int i = 0; !StdIn.isEmpty(); i++) {
            String key = StdIn.readString();
            st.put(key, i);
        }
        for (String s : st.keys())
            StdOut.println(s + " " + st.get(s));
    }
}
