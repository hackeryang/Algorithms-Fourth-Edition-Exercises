package Chapter1_3Low;

//exercise 1.3.14
public class ResizingArrayQueueOfStrings {
    private int N;
    private String[] items;
    public class Node{
        String item;
        Node next;
    }
    public ResizingArrayQueueOfStrings(int cap){
        items=new String[cap];
    }
    public void enqueue(String item){
        if(N==items.length){
            resize(items.length*2);
        }
        items[N++]=item;
    }
    public String dequeue(){
        //拷贝所有的老数组元素到新的数组，并命名为oldItems
        String[] oldItems=new String[N];
        for(int i=0;i<N;i++){
            oldItems[i]=items[i];
        }
        //重新设置stack的大小
        items[--N]=null;
        if(N==items.length/4){
            resize(items.length/2);
        }
        //获取新数据
        for(int i=0;i<N;i++){
            items[i]=oldItems[i+1];
        }
        return oldItems[0];
    }
    public boolean isEmpty(){
        return true;
    }
    public int size(){
        return N;
    }
    private void resize(int max){
        String[] tempItems=new String[max];
        for(int i=0;i<N;i++){
            tempItems[i]=items[i];
        }
        items=tempItems;
    }
    public static void main(String[] args){
        ResizingArrayQueueOfStrings strings=new ResizingArrayQueueOfStrings(2);
        strings.enqueue("我");
        strings.enqueue("的");
        strings.enqueue("名字");
        strings.enqueue("叫做yyc");
        strings.enqueue("hacker");
        System.out.println(strings.dequeue());
        System.out.println(strings.dequeue());
        System.out.println(strings.dequeue());
        System.out.println(strings.dequeue());
        System.out.println(strings.dequeue());
        //  System.out.println(strings.dequeue());
    }
}
