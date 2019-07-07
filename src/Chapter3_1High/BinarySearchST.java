package Chapter3_1High;

import edu.princeton.cs.algs4.Queue;

public class BinarySearchST<Key extends Comparable<Key>, Value> {
    private Key[] keys;
    private Value[] vals;
    private int N;

    public BinarySearchST(int capacity) {
        keys = (Key[]) new Comparable[capacity];
        vals = (Value[]) new Object[capacity];
    }

    public int size() {
        return N;
    }

    public Value get(Key key) {
        if (isEmpty()) return null;
        int i = rank(key);
        if (i < N && keys[i].compareTo(key) == 0) return vals[i];
        else return null;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int rank(Key key) {  //找出小于指定键的键的数量
        int lo = 0, hi = N - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;  //也可以为(lo + hi) >> 1，二进制中按位向右移一位等同于除以2
            int cmp = key.compareTo(keys[mid]);
            if (cmp < 0) hi = mid - 1;
            else if (cmp > 0) lo = mid + 1;
            else return mid;
        }
        return lo;
    }

    public void put(Key key, Value val) {
        //查找键，找到则更新值，否则创建新的元素
        int i = rank(key);
        if (i < N && keys[i].compareTo(key) == 0) {
            vals[i] = val;
            return;
        }
        for (int j = N; j > i; j--) {  //将i位置后面的键和值都往后移一位，给i空出位置插入
            keys[j] = keys[j - 1];
            vals[j] = vals[j - 1];
        }
        keys[i] = key;
        vals[i] = val;
        N++;
        assert check();
    }

    public void delete(Key key) {
        //Exercise 3.1.16
        if (isEmpty()) return;
        int i = rank(key);
        if (i < N && keys[i].compareTo(key) == 0) {
            for (int j = i; j < N - 1; j++) {  //将要删除的位置i处后面的键和值都向前移一位，这样最后一位会被空出来，再通过置空值的方式删除最后一位
                keys[j] = keys[j + 1];
                vals[j] = vals[j + 1];
            }
            N--;
            keys[N] = null;
            vals[N] = null;
        }
        assert check();
    }

    public Key min() {
        return keys[0];
    }

    public Key max() {
        return keys[N - 1];
    }

    public Key select(int k) {
        return keys[k];
    }  //找出排名为k的键

    public Key ceiling(Key key) { //找出大于等于该键的最小键
        int i = rank(key);
        return keys[i];
    }

    public Key floor(Key key) {  //找出小于等于该键的最大值
        //Exercise 3.1.17
        int i = rank(key);
        if (i < N) {
            if (keys[i].compareTo(key) == 0) {
                return key;
            } else if (i > 0) {
                return keys[i - 1];
            }
        }
        return null;
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }

    public Iterable<Key> keys(Key lo, Key hi) {
        Queue<Key> q = new Queue<Key>();
        for (int i = rank(lo); i < rank(hi); i++)
            q.enqueue(keys[i]);
        if (contains(hi))
            q.enqueue(keys[rank(hi)]);
        return q;
    }

    //Exercise 3.1.29
    public Iterable<Key> keys() {
        return keys(min(), max());
    }

    public void deleteMin() {
        delete(min());
    }

    public void deleteMax() {
        delete(max());
    }

    //Exercise 3.1.30
    private boolean check() {
        return isSorted() && rankCheck();
    }

    private boolean isSorted() {
        for (int i = 1; i < size(); i++) {
            if (keys[i].compareTo(keys[i - 1]) < 0) {
                return false;
            }
        }
        return true;
    }

    private boolean rankCheck() {  //检验一个新的键是否插入合适位置的基本操作是排名(rank)和选择(select)
        for (int i = 0; i < size(); i++) {
            if (i != rank(select(i))) {
                return false;
            }
        }
        for (int i = 0; i < size(); i++) {
            if (keys[i].compareTo(select(rank(keys[i]))) != 0) {
                return false;
            }
        }
        return true;
    }
}
