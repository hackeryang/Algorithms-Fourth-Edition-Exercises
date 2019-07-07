package Chapter1_2High;

import edu.princeton.cs.algs4.StdOut;

//Exercise 1.2.19
public class Transaction {
    private Date when;
    private String who;
    private double amount;

    public Date when() {
        return this.when;
    }

    public double amount() {
        return this.amount;
    }

    public String who() {
        return this.who;
    }

    public Transaction(String who, Date when, double amount) {
        this.who = who;
        this.when = when;
        this.amount = amount;
    }

    //1.2.19
    public Transaction(String transaction) {
        String[] fields = transaction.split("\\s+");
        who = fields[0];
        when = new Date(fields[1]);
        amount = Double.parseDouble(fields[2]);
    }

    public String toString() {
        return "" + this.who + " at " + this.when + " transaction " + this.amount;
    }

    public boolean equals(Object x) {
        if (this == x) return true;
        if (x == null) return false;
        if (this.getClass() != x.getClass()) return false;
        Transaction that = (Transaction) x;
        if (!this.when.equals(that.when)) return false;
        if (!this.who.equals(that.who)) return false;
        if (this.amount != that.amount) return false;
        return true;
    }

    public static void main(String[] args) throws Exception {
        Date date = new Date(1996, 12, 5);
        Transaction tran = new Transaction("Kyson", date, 1000);
        StdOut.println(tran.toString());
    }

}
