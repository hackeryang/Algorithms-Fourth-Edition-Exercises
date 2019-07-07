package Chapter3_1Low;

import edu.princeton.cs.algs4.Queue;

public class ArrayST<Key, Value> {
    //Exercise 3.1.2，开发一个符号表实现，使用无序数组来实现基本API
    private Key[] keys;
    private Value[] vals;
    private int N;

    public ArrayST(int capacity) {
        keys = (Key[]) new Object[capacity];
        vals = (Value[]) new Object[capacity];
        N = 0;
    }

    public void put(Key key, Value val) {  //查找给定的键，找到则更新其值，否则在符号表中新建键值对，并增加数目N
        for (int i = 0; i < N; i++) {
            if (keys[i].equals(key)) {
                vals[i] = val;
                return;
            }
        }
        keys[N] = key;
        vals[N] = val;
        N++;
    }

    public Value get(Key key) {
        for (int i = 0; i < N; i++) {
            if (keys[i].equals(key)) {
                return vals[i];
            }
        }
        return null;
    }

    public void delete(Key key) {
        for (int i = 0; i < N; i++) {
            if (keys[i].equals(key)) {
                keys[i] = keys[N - 1];  //将最后一个元素的键值对覆盖要删除的键值对
                vals[i] = vals[N - 1];
                keys[N - 1] = null;  //将最后一项置为空，相当于删除了对应项，还保留了最后一项的键值对
                vals[N - 1] = null;
                N--;  //将符号表元素数量减一
                return;
            }
        }
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return N;
    }

    public Iterable<Key> keys() {
        Queue<Key> queue = new Queue<>();
        for (int i = 0; i < N; i++) {
            queue.enqueue(keys[i]);
        }
        return queue;
    }
}
