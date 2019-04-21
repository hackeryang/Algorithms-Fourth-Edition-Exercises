package Chapter1_3Low;

import edu.princeton.cs.algs4.In;

//Exercise 1.3.16
public class Date1 {
    private int month;
    private int day;
    private int year;

    public Date1(String date){
        String[] fields=date.split("/");
        month=Integer.parseInt(fields[0]);
        day=Integer.parseInt(fields[1]);
        year=Integer.parseInt(fields[2]);
    }
    public String toString(){
        return ""+month+"/"+day+"/"+year;
    }
    public static Date1[] readDates(String name){
        In in=new In(name);
        Queue<Date1> q=new Queue<Date1>();
        while(!in.isEmpty()){
            String readedString=in.readString();
            Date1 date1=new Date1(readedString);
            q.enqueue(date1);
        }
        int N=q.size();
        Date1[] a=new Date1[N];
        for(int i=0;i<N;i++)
            a[i]=q.dequeue();
        return a;
    }
    public static void main(String[] args){
        String filePathString=System.getProperty("user.dir");
        String dateFileString=filePathString+"/src/"+"b.txt";
        System.out.println("即将读取"+dateFileString+"文件中得到的数组为：");
        Date1[] date1=readDates(dateFileString);
        System.out.println("读取文件中得到的数组为：");
        for(int i=0;i<date1.length;i++){
            System.out.println(date1[i]);
        }
    }
}
