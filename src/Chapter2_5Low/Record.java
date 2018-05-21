package Chapter2_5Low;

public class Record implements Comparable<Record> {
    //Exercise 2.5.8，作为Frequency.java的数据结构与方法类
    private String word;
    private int freq;

    public Record(String word){
        this.word=word;
        freq=1;
    }
    public String getWord(){return word;}
    public int getFreq(){return freq;}
    public void addFreq(){freq++;}

    @Override
    public int compareTo(Record that) {
        if(this.freq>that.freq){
            return -1;
        }else if(this.freq<that.freq){
            return 1;
        }else{
            return 0;
        }
    }
}
