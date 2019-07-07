package Chapter1_3Low;

import edu.princeton.cs.algs4.In;

//Exercise 1.3.17
public class Transaction1 {
    private Date1 when;
    private String who;
    private double amount;

    public Transaction1(String date) {
        String[] fields = date.split("-");
        when = new Date1(fields[0]);
        who = fields[1];
        amount = Integer.parseInt(fields[2]);
    }

    public String toString() {
        return "" + this.who + " at " + this.when + " transaction " + this.amount;
    }

    public static Transaction1[] readTransactions(String name) {
        In in = new In(name);
        Queue<Transaction1> q = new Queue<Transaction1>();
        while (!in.isEmpty()) {
            String readedString = in.readString();
            Transaction1 transaction1 = new Transaction1(readedString);
            q.enqueue(transaction1);
        }
        int N = q.size();
        Transaction1[] a = new Transaction1[N];
        for (int i = 0; i < N; i++)
            a[i] = q.dequeue();
        return a;
    }

    public static void main(String[] args) {
        String filePathString = System.getProperty("user.dir");
        String intFileString = filePathString + "/src/" + "c.txt";
        System.out.println("即将读取" + intFileString + "文件中得到的数组为：");
        Transaction1[] transaction1s = readTransactions(intFileString);
        System.out.println("读取文件中得到的数组为：");
        for (int i = 0; i < transaction1s.length; i++) {
            System.out.println(transaction1s[i]);
        }
    }
}
