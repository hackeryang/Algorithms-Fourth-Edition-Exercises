package Chapter3_4Text;

import edu.princeton.cs.algs4.Date;

public class Transaction {
    private String who;
    private Date when;
    private double amount;

    public int hashCode(){  //利用公式(((hash*R+who.hashCode())*R+when.hashCode())*R+amount.hashCode()来计算哈希值
        int hash=17;
        hash=31*hash+who.hashCode();
        hash=31*hash+when.hashCode();
        hash=31*hash+((Double)amount).hashCode();
        return hash;
    }
}
