package Chapter3_2High;

import edu.princeton.cs.algs4.Queue;

public class BST<Key extends Comparable<Key>, Value> {  //基于二叉查找树的符号表
    private Node root;  //二叉查找树的根节点

    private class Node {
        private Key key;  //键
        private Value val;  //值
        private Node left, right;  //指向左右子树的链接
        private int N;  //以该节点为根的子树中的节点总数

        public Node(Key key, Value val, int N) {
            this.key = key;
            this.val = val;
            this.N = N;
        }
    }

    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null) return 0;
        else return x.N;
    }

    public Value get(Key key) {
        return get(root, key);
    }

    private Value get(Node x, Key key) {
        //在以x为根节点的子树中查找并返回key所对应的值，如果找不到则返回null
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return get(x.left, key);  //如果键比根节点小，则递归查找左子树
        else if (cmp > 0) return get(x.right, key);  //如果键比根节点大，则递归查找右子树
        else return x.val;
    }

    public void put(Key key, Value val) {
        //查找key，找到则更新它的值，否则为它创建一个新的节点
        root = put(root, key, val);
    }

    private Node put(Node x, Key key, Value val) {
        //如果key存在于以x为根节点的子树中则更新它的值，否则将以key和val为键值对的新节点插入到该子树中
        if (x == null) return new Node(key, val, 1);
        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = put(x.left, key, val);  //如果键比根节点小，则递归查找左子树并插入新节点的键值
        else if (cmp > 0) x.right = put(x.right, key, val);  //如果键比根节点大，则递归查找右子树并插入新节点的键值
        else x.val = val;  //如果查找插入的键就是根节点，则修改根节点的值
        x.N = size(x.left) + size(x.right) + 1;
        return x;  //返回根节点
    }

    public Key min() {
        return min(root).key;
    }

    private Node min(Node x) {
        if (x.left == null) return x;  //如果根节点左链接为空，则最小键就是根节点
        return min(x.left);  //如果左链接非空，则树中的最小键就是左子树中的最小键，不断递归到左子树的底部
    }

    public Key max() {
        return max(root).key;
    }

    private Node max(Node x) {
        if (x.right == null) return x;  //如果根节点右链接为空，则最大键就是根节点
        return max(x.right);  //如果右链接非空，则树中的最大键就是右子树中的最大键，不断递归到右子树的底部
    }

    public Key floor(Key key) {  //查找小于等于key的最大键，向下取整
        Node x = floor(root, key);
        if (x == null) return null;
        return x.key;
    }

    private Node floor(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;  //如果查找的向下取整键与根的键相同，返回根节点
        if (cmp < 0) return floor(x.left, key);  //如果查找的向下取整键比根的键小，则递归查找左子树
        Node t = floor(x.right, key);  //如果查找的向下取整键大于根的键，则查找右子树，再在右子树中递归查找左子树，并找到向下取整键的节点
        if (t != null) return t;
        else return x;  //如果最终还是没找到向下取整键，则返回开始进入右子树时该右子树的父节点
    }

    public Key ceiling(Key key) {  //查找大于等于key的最小键，向上取整
        Node x = ceiling(root, key);
        if (x == null) return null;
        return x.key;
    }

    private Node ceiling(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;  //如果查找的向上取整键与根的键相同，返回根节点
        if (cmp > 0) return ceiling(x.right, key);   //如果查找的向上取整键比根的键大，则递归查找右子树
        Node t = ceiling(x.left, key);  //如果查找的向上取整键小于根的键，则查找左子树，再在左子树中递归查找右子树，并找到向上取整键的节点
        if (t != null) return t;
        else return x;  //如果最终还是没找到向上取整键，则返回最初开始进入左子树时该左子树的父节点
    }

    public Key select(int k) {
        return select(root, k).key;
    } //找到排名为k的键，即树中正好有k个小于它的键

    private Node select(Node x, int k) {
        //返回排名为k的节点
        if (x == null) return null;
        int t = size(x.left);
        if (t > k) return select(x.left, k);  //如果左子树中的节点数t大于k，则左子树节点数量充足可以继续在左子树中查找排名为k的键
        else if (t < k) return select(x.right, k - t - 1); //如果左子树中的节点数t小于k，则不需要查找左子树，在右子树中查找排名为k-t-1的键
        else return x;  //如果t等于k，就返回根节点
    }

    public int rank(Key key) {
        return rank(key, root);
    }  //返回键的排名

    private int rank(Key key, Node x) {
        //返回以x为根节点的子树中小于x.key的键的数量
        if (x == null) return 0;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return rank(key, x.left);  //如果给定的键小于根节点，返回该键在左子树中的排名（递归）
        else if (cmp > 0) return 1 + size(x.left) + rank(key, x.right);  //如果给定的键大于根节点，返回t+1(包括根节点）加上它在右子树的排名（递归）
        else return size(x.left);  //如果给定的键和根节点的键相等，返回左子树中的节点总数t
    }

    public void deleteMin() {
        root = deleteMin(root);
    }

    private Node deleteMin(Node x) {
        if (x.left == null) return x.right;  //如果当前节点已经没有左子树，说明自己已经是最小节点，则返回右子树
        x.left = deleteMin(x.left);  //如果存在左子树，则一直递归直到找到没有左子树的最小键的节点，这行当前节点的左子树已经指向递归后上一行返回的右链接，也就是说，将最小键节点的父节点的左子树链接指向该节点的右子树，这样父节点不再指向该节点，最小键节点会被垃圾回收
        x.N = size(x.left) + size(x.right) + 1;  //更新节点计数器
        return x;  //在递归中不断的return当前递归中的节点，一层层往上最终在递归语句那一行return根节点的下一层左子树根节点，这样递归结束后最后在这一行返回根节点
    }

    public void deleteMax() {
        root = deleteMax(root);
    }

    private Node deleteMax(Node x) {
        if (x.right == null) return x.left;   //如果当前节点已经没有右子树，说明自己已经是最大节点，则返回左子树
        x.right = deleteMax(x.right);   //如果存在右子树，则一直递归直到找到没有右子树的最大键的节点，这行当前节点的右子树已经指向递归后上一行返回的左链接，也就是说，将最大键节点的父节点的右子树链接指向该节点的左子树，这样父节点不再指向该节点，最大键节点会被垃圾回收
        x.N = size(x.left) + size(x.right) + 1;   //更新节点计数器
        return x;   //在递归中不断的return当前递归中的节点，一层层往上最终在递归语句那一行return根节点的下一层右子树根节点，这样递归结束后最后在这一行返回根节点
    }

    public void delete(Key key) {
        root = delete(root, key);
    }  //删除对应键的节点

    private Node delete(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = delete(x.left, key);  //如果要删除的键小于当前节点的键，则递归查找左子树并删除节点
        else if (cmp > 0) x.right = delete(x.right, key);  //如果要删除的键大于当前节点的键，则递归查找右子树并删除节点
        else {
            //如果当前节点的键等于要删除的键
            if (x.right == null) return x.left;
            if (x.left == null) return x.right;
            Node t = x;  //将指向即将被删除的节点的链接保存为t
            x = min(t.right);  //将x指向将删除节点的后继节点，该节点是将删除节点的右子树中的最小节点，这样后继节点补上删除节点的位置后依然能保持二叉树有序
            x.right = deleteMin(t.right);  //用deleteMin将后继节点的父节点的左链接（原本指向后继节点）链接到后继节点的右链接（也就是说，父节点解除对后继节点的链接），右链接即后继节点的右子树，最后通过这一行的赋值再次将后继节点的右子树链接到原本它的父节点上去
            x.left = t.left;  //将后继节点的左子树链接到原来被删除节点的左子树上去
        }
        x.N = size(x.left) + size(x.right) + 1;  //更新节点数
        return x; //返回根节点
    }

    public Iterable<Key> keys() {
        return keys(min(), max());
    }  //二叉查找树的范围查找

    public Iterable<Key> keys(Key lo, Key hi) {
        Queue<Key> queue = new Queue<Key>();  //将所有在给定范围内的键加入到一个队列中
        keys(root, queue, lo, hi);
        return queue;
    }

    private void keys(Node x, Queue<Key> queue, Key lo, Key hi) {
        if (x == null) return;
        int cmplo = lo.compareTo(x.key);
        int cmphi = hi.compareTo(x.key);
        if (cmplo < 0) keys(x.left, queue, lo, hi);  //如果范围下限的键小于根节点的键，则递归查找左子树
        if (cmplo <= 0 && cmphi >= 0) queue.enqueue(x.key);  //如果当前子树根节点的键处在查找范围内，则在队列中加入根节点的键
        if (cmphi > 0) keys(x.right, queue, lo, hi);  //如果范围上限的键大于根节点的键，则递归查找右子树
    }

    //Exercise 3.2.6
    public int height() {
        return height(root);
    }

    private int height(Node x) {
        if (x == null) {
            return -1;
        }
        return 1 + Math.max(height(x.left), height(x.right));  //递归求出左子树和右子树之间的高度最大值，再加上根节点即1，得到树的总高度
    }

    //Exercise 3.2.32
    private boolean isBST() {
        return isBST(root, null, null);
    }

    private boolean isBST(Node x, Key min, Key max) {
        if (x == null) {
            return true;
        }
        if (min != null && x.key.compareTo(min) <= 0) {  //如果键小于等于最小节点，则不是有序
            return false;
        }
        if (max != null && x.key.compareTo(max) >= 0) {  //如果键大于等于最大节点，则不是有序
            return false;
        }
        return isBST(x.left, min, x.key) && isBST(x.right, x.key, max);  //递归向下查找键是否有序
    }

    //Exercise 3.2.33
    public boolean isRankConsistent() {
        for (int i = 0; i < size(); i++) {
            if (i != rank(select(i))) {
                return false;
            }
        }
        for (Key key : keys()) {
            if (!key.equals(select(rank(key)))) {
                return false;
            }
        }
        return true;
    }
}
