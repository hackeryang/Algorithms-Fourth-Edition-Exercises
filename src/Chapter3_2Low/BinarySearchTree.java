package Chapter3_2Low;

import edu.princeton.cs.algs4.StdIn;

public class BinarySearchTree<Key extends Comparable<Key>, Value> {
    //Exercise 3.2.13
    private Node root;

    private class Node {  //二叉树，左子树小于当前节点，右子树大于当前节点
        private Key key;
        private Value val;
        private Node left, right;

        public Node(Key key, Value val) {
            this.key = key;
            this.val = val;
        }
    }

    public Value get(Key key) {
        Node x = root;
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp == 0) {
                return x.val;
            } else if (cmp < 0) {
                x = x.left;
            } else {
                x = x.right;
            }
        }
        return null;
    }

    public void put(Key key, Value val) {
        if (root == null) {
            root = new Node(key, val);
            return;
        }
        Node x = root, parent = null;
        while (x != null) {
            parent = x;
            int cmp = key.compareTo(x.key);
            if (cmp == 0) {
                x.val = val;
                return;
            } else if (cmp < 0) {
                x = x.left;
            } else {
                x = x.right;
            }
        }
        int cmp = key.compareTo(parent.key);
        if (cmp < 0) {
            parent.left = new Node(key, val);  //如果要插入的键比当前父节点小，则插入到左节点
        } else {
            parent.right = new Node(key, val);  //如果要插入的键比当前父节点大，则插入到右节点
        }
    }

    public static void main(String[] args) {
        BinarySearchTree<String, Integer> st = new BinarySearchTree<>();
        for (int i = 0; !StdIn.isEmpty(); i++) {
            String key = StdIn.readString();
            st.put(key, i);
        }
        for (String key : args) {
            System.out.println(key + " " + st.get(key));
        }
    }
}
