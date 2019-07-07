package Chapter2_1High;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Shell;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

//Exercise 2.1.22
public class SortTransactions {
    public static Transaction[] readTransactions() {
        //Exercise 1.3.17
        Queue<Transaction> queue = new Queue<>();
        while (StdIn.hasNextLine()) {
            String line = StdIn.readLine();
            queue.enqueue(new Transaction(line));
        }
        Transaction[] transactions = new Transaction[queue.size()];
        for (int i = 0; i < transactions.length; i++) {
            transactions[i] = queue.dequeue();
        }
        return transactions;
    }

    public static void main(String[] args) {
        Transaction[] transactions = readTransactions();
        Shell.sort(transactions);
        for (Transaction t : transactions)
            StdOut.println(t);
    }
}
