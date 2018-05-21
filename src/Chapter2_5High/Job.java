package Chapter2_5High;

public class Job implements Comparable<Job> {
    //Exercise.2.5.12、2.5.13，SPT.java和LPT.java的数据类型和方法类
    private String name;
    private double time;
    public Job(String name,double time){
        this.name=name;
        this.time=time;
    }
    public String getName(){return name;}
    public double getTime(){return time;}

    @Override
    public int compareTo(Job that) {
        if(this.time>that.time){
            return 1;
        }else if(this.time<that.time){
            return -1;
        }else{
            return 0;
        }
    }

    @Override
    public String toString() {
        return name+": "+time;
    }
}
