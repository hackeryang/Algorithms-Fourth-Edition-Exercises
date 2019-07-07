package Chapter2_5High;

public class StablePQ<Key extends Comparable<Key>> {
    //Exercise 2.5.24，实现一个稳定的优先队列，即重复的元素按照它们被插入的顺序依然不变
    private Key[] pq;  //基于堆的完全二叉树，每个节点都大于等于它的两个子节点
    private int N = 0;  //存储于pq[1..N]中，pq[0]没有使用
    private int[] time;
    private int timestamp;

    public StablePQ(int maxN) {
        pq = (Key[]) new Comparable[maxN + 1];
        time = new int[maxN];
        timestamp = 0;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    private boolean less(int i, int j) {
        if (pq[i].compareTo(pq[j]) < 0) {
            return true;
        } else if (pq[i].compareTo(pq[j]) > 0) {
            return false;
        }
        return time[i] < time[j];
    }

    private void exch(int i, int j) {
        Key t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
        int temp = time[i];  //在交换优先队列的元素时，同时交换元素被创建的时间
        time[i] = time[j];
        time[j] = temp;
    }

    private void swim(int k) {
        while (k > 1 && less(k / 2, k)) {
            exch(k / 2, k);
            k = k / 2;
        }
    }

    public void insert(Key v) {
        pq[++N] = v;  //从二叉树的末尾插入元素，并根据插入元素的大小上浮至合适的层级
        time[N] = ++timestamp;  //更新该元素的创建时间
        swim(N);
    }

    public Key delMax() {
        Key max = pq[1];  //从根节点得到最大元素
        exch(1, N--);  //将其和最后一个节点交换
        pq[N + 1] = null;   //删除交换到最后一个元素的原根节点，并防止对象游离，这里的N+1其实就是上一行的N，只不过上一行已经将N的大小减一，将引用指向了原来最后一个元素的前一个元素
        time[N + 1] = 0;  //将删除的最大元素的创建时间清零
        sink(1);  //原来最后一个元素插入到了树的根节点处，根据元素大小下沉至合适的层级
        return max;
    }

    private void sink(int k) {  //堆的有序状态因为某个节点变得比它的两个子节点或者其中之一更小而打破，则将它和它的两个子节点中较大者进行交换
        while (2 * k <= N) {
            int j = 2 * k;  //找到k的下一层子节点
            if (j < N && less(j, j + 1)) {  //取两个子节点的更大者
                j++;
            }
            if (!less(k, j)) {
                break;
            }
            exch(k, j);
            k = j;
        }
    }
}
