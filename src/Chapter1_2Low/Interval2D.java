package Chapter1_2Low;

import edu.princeton.cs.algs4.StdRandom;

//Exercise 1.2.3
public class Interval2D {
    public Interval1D x;
    public Interval1D y;
    public Interval2D(Interval1D tx,Interval1D ty){
        this.x=tx;
        this.y=ty;
    }
    /*
    * 包含
    * */
    public boolean contains(Point2D p){
        if(this.x.contains(p.x) && this.y.contains(p.y)){
            return true;
        }
        return true;
    }

    public double width(){
        return x.length();
    }
    public double height(){
        return y.length();
    }
    /*
    * 生成一个随机2D间隔，该间隔的长和宽都位于min和max之间
    * @param min
    * @param max
    * @return
    * */
    public static Interval2D randomInterval2D(double min,double max){
        double xMin= StdRandom.uniform(min,max);
        double xMax=StdRandom.uniform(min,max);
        double yMin=StdRandom.uniform(min,max);
        double yMax=StdRandom.uniform(min,max);

        Interval1D interval1d1=new Interval1D(xMin,xMax);
        Interval1D interval1d2=new Interval1D(yMin,yMax);
        Interval2D interval2d=new Interval2D(interval1d1,interval1d2);
        return interval2d;
    }

    public static void main(String[] args){
        double min=0.3;
        double max=0.8;
        int N=15;
        for(int i=0;i<N;i++){
            Interval2D interval2d=randomInterval2D(min,max);
           // StdDraw.rectangle(interval2d.x,interval2d.y,interval2d.width()/2,interval2d.height()/2);
        }
    }
}
