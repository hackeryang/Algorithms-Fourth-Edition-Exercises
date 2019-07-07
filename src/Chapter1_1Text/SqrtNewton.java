package Chapter1_1Text;

public class SqrtNewton {  //利用牛顿迭代法求平方根
    public static double sqrt(double c) { //c为要开方的数，如2则计算根号2
        if (c < 0) return Double.NaN;
        double err = 1e-15; //接近于0的数
        double t = c;
        while (Math.abs(t - c / t) > err * t) //随着迭代t会变，c/t越来越接近要计算的开平方值t，误差超过err*t时就继续迭代计算，误差小于这个值则代表结果足够接近
            t = (c / t + t) / 2.0; //详细公式解释链接：https://www.zhihu.com/question/20690553
        return t;
    }

    public static void main(String[] args) {
        SqrtNewton a = new SqrtNewton();
        System.out.println(a.sqrt(2));
    }
}
