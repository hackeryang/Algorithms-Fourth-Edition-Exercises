package Chapter1_2Low;

import java.util.ArrayList;

//Exercise 1.2.1
public class Point2D {
    public double x;
    public double y;

    //初始化函数
    public Point2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    //计算两点间的距离
    public double distTo(Point2D that) {
        double xDistance = this.x - that.x;
        double yDistance = this.y - that.y;
        return Math.sqrt(xDistance * xDistance + yDistance * yDistance);
    }

    public static void main(String[] args) {
        int N = 10;
        ArrayList<Point2D> points = new ArrayList<Point2D>();
        //创建N个元素的数组
        for (int i = 0; i < N; i++) {
            Point2D point2d = new Point2D(Math.random(), Math.random());
            points.add(point2d);
        }
        //计算第一个点到第二个点的距离
        double resultDistance = points.get(0).distTo(points.get(1));
        for (int i = 0; i < N; i++) {
            Point2D point2d = points.get(i);
            for (int j = i + 1; j < N; j++) {
                //计算第i个点和第j个点之间的距离
                double distance = point2d.distTo(points.get(j));
                //如果距离比resultDistance小，则赋值给resultDistance
                if (distance < resultDistance) {
                    resultDistance = distance;
                }
            }
        }
        System.out.println("resultDistance: " + resultDistance);
    }
}
