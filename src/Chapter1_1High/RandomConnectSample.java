package Chapter1_1High;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

//exercise 1.1.31
public class RandomConnectSample {
    static class Point{
        double x;
        double y;
        public Point(double x,double y){
            super();
            this.x=x;
            this.y=y;
        }
    }
    public static void main(String[] args){
        int N=10;
        double p= StdRandom.uniform();
        //算出角度
        double angle=360.0/N;
        StdDraw.circle(0.5,0.5,0.5);
        Point[] points=new Point[N];
        for(int i=0;i<N;i++){
            points[i]=new Point(0.5+0.5*Math.cos(angle*i*Math.PI/180),0.5+0.5*Math.sin(angle*i*Math.PI/180));
            StdDraw.point(points[i].x,points[i].y);
        }
        StdDraw.setPenColor(StdDraw.GRAY);
        for(int i=0;i<N-1;i++){
            for(int j=i+1;j<N;j++){
                if(StdRandom.bernoulli(p)){
                    StdDraw.line(points[i].x,points[i].y,points[j].x,points[j].y);
                }
            }
        }
    }
}
