package Chapter3_3Text;
//红黑二叉查找树，用标准的二叉查找树（完全由2-节点构成）和一些额外的信息（替换3-节点）来表示2-3树。红黑树将树中的链接分为两种类型：红链接将两个2-节点连接起来构成一个3-节点，黑链接则为2-3树中的普通链接，即将3-节点表示为由一条左斜的加粗红色链接（两个2-节点之一是另一个的左子节点）。
public class RedBlackBST<Key extends Comparable<Key>,Value> {
    private Node root;
    private static final boolean RED=true;
    private static final boolean BLACK=false;
    private class Node{
        //含有color变量的Node对象
        Key key;  //键
        Value val;  //相关联的值
        Node left,right;  //左右子树
        int N;  //这棵子树中的节点总数
        boolean color;  //由父节点指向它的链接的颜色

        Node(Key key,Value val,int N,boolean color){
            this.key=key;
            this.val=val;
            this.N=N;
            this.color=color;
        }
    }
    private boolean isRed(Node x){  //判断父节点指向自己的链接是否是红链接
        if(x==null) return false;
        return x.color==RED;
    }
    private int size(Node x){
        if(x==null) return 0;
        else return x.N;
    }
    private Node rotateLeft(Node h){  //将指向右链接的红链接旋转为指向左链接
        Node x=h.right;
        h.right=x.left;  //将节点h的右链接指向原来节点x的左子树，即介于h与x的键之间的键子树
        x.left=h;  //开始将节点h变为节点x的左子树
        x.color=h.color;  //指向h的链接的颜色赋予指向x的链接，迁移过去h作为子树根节点时的颜色属性，让节点x接过节点h的属性
        h.color=RED;  //节点h已经是节点x的左子树根节点，指向h的链接颜色变为红色，这样红色左链接完成
        x.N=h.N;  //x成为新的子树根节点，所以子树链接数不变
        h.N=1+size(h.left)+size(h.right);  //节点h已经成为新根节点x的左子树根节点，因此要更新一下链接数
        return x;  //返回新的子树根节点
    }
    private Node rotateRight(Node h){  //将指向左链接的红链接旋转为指向右链接
        Node x=h.left;
        h.left=x.right;  //将节点h的左链接指向原来节点x的右子树，即介于h与x的键之间的键子树
        x.right=h;  //开始将节点h变为节点x的右子树
        x.color=h.color;  //指向h的链接的颜色赋予指向x的链接，迁移过去h作为子树根节点时的颜色属性，让节点x接过节点h的属性
        h.color=RED;   //节点h已经是节点x的右子树根节点，指向h的链接颜色变为红色，这样红色右链接完成
        x.N=h.N;  //x成为新的子树根节点，所以子树链接数不变
        h.N=1+size(h.left)+size(h.right);  //节点h已经成为新根节点x的右子树根节点，因此要更新一下链接数
        return x;  //返回新的子树根节点
    }
    private void flipColors(Node h){  //将左右子节点的链接颜色由红变黑之外，父节点的链接颜色由黑变红
        h.color=RED;
        h.left.color=BLACK;
        h.right.color=BLACK;
    }
    public Value get(Key key){
        return get(root,key);
    }
    private Value get(Node x,Key key){
        if(x==null){
            return null;
        }
        int cmp=key.compareTo(x.key);
        if(cmp<0){
            return get(x.left,key);
        }else if(cmp>0){
            return get(x.right,key);
        }else{
            return x.val;
        }
    }
    public Key min(){
        return min(root).key;
    }
    private Node min(Node x){
        if(x.left==null){
            return x;
        }
        return min(x.left);
    }
    public void put(Key key,Value val){
        //查找key，找到则更新其值，否则为它新建一个节点
        root=put(root,key,val);
        root.color=BLACK;
    }
    private Node put(Node h,Key key,Value val){
        if(h==null)  //标准的插入操作，和父节点用红链接相连,若不存在根节点，则插入根节点
            return new Node(key,val,1,RED);
        int cmp=key.compareTo(h.key);
        if(cmp<0) h.left=put(h.left,key,val);  //如果要插入的键比根节点的键小，则递归查找左子树
        else if(cmp>0) h.right=put(h.right,key,val);  //如果要插入的键比根节点的键大，则递归查找右子树
        else h.val=val;  //如果找到了对应的键，则更新值
        if(isRed(h.right) && !isRed(h.left)) h=rotateLeft(h);  //如果当前节点指向右子树的链接为红色，并且指向左子树的链接为黑色，对自身进行左旋转
        if(isRed(h.left) && isRed(h.left.left)) h=rotateRight(h);  //如果当前节点指向左子树的链接为红色，并且左子树根节点指向其左子节点的链接也为红色，对自身进行右旋转
        if(isRed(h.left) && isRed(h.right)) flipColors(h);  //如果当前节点指向左子树和右子树的链接都是红色，则进行颜色转换
        h.N=size(h.left)+size(h.right)+1;  //从底部向上递归更新黑链接的数目
        return h;  //每次递归从底部一层层向上返回当前递归循环的子树根节点，最终返回根节点
    }
    //Exercise 3.3.39
    private Node moveRedLeft(Node h){  //删除最小键之前的变换
        flipColors(h);
        //根节点的右子节点的左子节点链接为红色，意味着根节点的右子树根节点可看作一个3-节点或者4-节点，右子树可以把该红色左子节点移到根节点，然后原根节点移到左子节点也形成一个3-节点，避免要删除的最小键节点处是个2-节点，删了破坏树的有序性
        if(isRed(h.right.left)){
            h.right=rotateRight(h.right);
            //在删除最小节点的情况下，最小键节点的父节点h虽然是根节点，但是从右子树分支来看h是最小键节点，所以将原子树根节点h移动到左子树位置和最小键节点一起，形成了便于删除节点的3-节点或4-节点
            h=rotateLeft(h);
        }
        return h;
    }
    public void deleteMin(){  //删除最小键
        if(!isRed(root.left) && !isRed(root.right)){
            root.color=RED;  //将指向根节点的链接颜色变为红色
        }
        root=deleteMin(root);
        if(root!=null){
            root.color=BLACK;  //删除最小键完成后再把指向根节点的颜色重置为黑色
        }
    }
    private Node deleteMin(Node h){
        if(h.left==null){
            return null;
        }
        if(!isRed(h.left) && !isRed(h.left.left)){  //如果最小键位置连续两个左链接都是黑色，说明只有2-节点不利于删除后树平衡，需要转换出一个左下角的3-节点便于删除
            h=moveRedLeft(h);
        }
        h.left=deleteMin(h.left);  //递归查找左子树直到找到最小键节点然后删除
        return balance(h);  //删除最小键节点后将红黑树进行结构平衡
    }
    private Node balance(Node h){
        if(isRed(h.right)){
            h=rotateLeft(h);  //发现有向右的红链接，则进行左旋转
        }
        if(isRed(h.left) && isRed(h.left.left)){  //如果连续两条指向左子树的链接都是红色，则右旋转当前节点指向左子树的链接
            h=rotateRight(h);
        }
        if(isRed(h.left) && isRed(h.right)){  //如果左右两条左子树的链接为红色，则改变颜色
            flipColors(h);
        }
        h.N=size(h.left)+size(h.right)+1;  //因为有deleteMin的递归，所以从底层一层层向上更新链接数
        return h;
    }
    //Exercise 3.3.40
    private Node moveRedRight(Node h){  //删除最大键之前的变换
        flipColors(h);
        //根节点的左子节点的左子节点链接为红色，意味着根节点的左子树根节点可看作一个3-节点或者4-节点，左子树可以把自己移到根节点，然后原根节点移到右子节点也形成一个3-节点，避免要删除的最小键节点处是个2-节点，删了破坏树的有序性
        if(isRed(h.left.left)){
            //在删除最大节点的情况下，节点h虽然是根节点，但是从左子树分支来看h是最大键节点，所以将原子树根节点h移动到右下角右子树位置，且形成了便于删除节点的3-节点或4-节点
            h=rotateRight(h);
        }
        return h;
    }
    public void deleteMax(){
        if(!isRed(root.left) && !isRed(root.right)){
            root.color=RED;
        }
        root=deleteMax(root);
        if(root!=null){
            root.color=BLACK;
        }
    }
    private Node deleteMax(Node h){
        if(isRed(h.left)){
            h=rotateRight(h);
        }
        if(h.right==null){
            return null;
        }
        //如果子树根节点的右子树链接和右子树的左子树链接都是黑色，说明右子树分支没有3-节点，需要从左子树兄弟分支借一个节点过来组成3-节点便于删除
        if(!isRed(h.right) && !isRed(h.right.left)){
            h=moveRedRight(h);
        }
        h.right=deleteMax(h.right);  //从右子树递归查找并删除最大键
        return balance(h);  //删除最大键节点后将红黑树进行结构平衡
    }
    //Exercise 3.3.41
    public void delete(Key key){
        if(!isRed(root.left) && !isRed(root.right)){
            root.color=RED;
        }
        root=delete(root,key);
        if(root!=null){
            root.color=BLACK;
        }
    }
    private Node delete(Node h,Key key){
        if(key.compareTo(h.key)<0){  //当要删除的键小于根节点的键
            if(!isRed(h.left) && !isRed(h.left.left)){  //如果左子树中不存在3-节点，则为了便于删除节点之后的树平衡，制造出一个3-节点
                h=moveRedLeft(h);
            }
            h.left=delete(h.left,key);  //递归向底部查找左子树，直到删除对应的键的节点
        }else{  //要删除的键大于等于根节点的键
            if(isRed(h.left)){
                h=rotateRight(h);
            }
            if(key.compareTo(h.key)==0 && h.right==null){  //如果要删除的是根节点，而根节点没有右子树，返回空
                return null;
            }
            if(!isRed(h.right) && !isRed(h.right.left)){
                h=moveRedRight(h);  //如果子树根节点的右子树链接和右子树的左子树链接都是黑色，说明右子树分支没有3-节点，需要从左子树兄弟分支借一个节点过来组成3-节点便于删除
            }
            if(key.compareTo(h.key)==0){  //如果要删除的键就是子树根节点，则将后继节点设置为右子树的最小键节点，并解除对该根节点的引用
                h.val=get(h.right,min(h.right).key);
                h.key=min(h.right).key;
                //用deleteMin()将后继节点的父节点的左链接（原本指向后继节点）解除（也就是说，父节点解除对后继节点的链接,即在被删除节点的右子树中删除后继节点，因为该右子树会成为后继节点的右子树，不需要自己在里面），最后通过这一行的赋值再次将后继节点的右子树链接到被删除节点的右子树根节点上去
                h.right=deleteMin(h.right);
            }else{  //当要删除的键大于根节点的键
                h.right=delete(h.right,key);  //递归向底部查找右子树，直到删除对应的键的节点
            }
        }
        return balance(h);  //删除节点后将红黑树进行结构平衡
    }
}
