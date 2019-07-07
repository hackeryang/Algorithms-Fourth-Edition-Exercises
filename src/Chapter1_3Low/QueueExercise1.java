package Chapter1_3Low;

//Exercise 1.3.15
public class QueueExercise1 {
    public static void main(String[] args) {
        String a[] = {
                "我的",
                "名字",
                "叫",
                "yyc",
                "hacker"
        };
        int k = 3;
        Queue<String> stringQueue = new Queue<String>();
        for (int i = 0; i < a.length; i++) {
            stringQueue.enqueue(a[i]);
        }
        int index = a.length - k;
        for (int j = 0; j <= index; ++j) {
            String dequeuedString = stringQueue.dequeue();
            if (j == index)
                System.out.print(dequeuedString);
        }
    }
}
