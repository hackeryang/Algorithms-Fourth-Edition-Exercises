package Chapter1_2Low;

import java.util.ArrayList;

//Exercise 1.2.2
public class Interval1D {
    private double lo;
    private double hi;
    public Interval1D(double tlo,double thi){
        if(tlo>thi){
            double tempDouble=tlo;
            tlo=thi;
            thi=tempDouble;
        }
        this.hi=thi;
        this.lo=tlo;
    }
    public double length(){
        return Math.abs(lo-hi);
    }
    public boolean contains(double x){
        return (x>lo)&&(x<hi);
    }
    /*
    * 判断两个间隔是否相交
    * @param that
    * @return
    * */
    public boolean intersect(Interval1D that){
        if(this.hi<that.lo){
            return false;
        }else if((this.hi>that.lo)&&(this.lo<that.hi)){ //一个间隔的上界大于另一个间隔的下界，并且该下界小于另一个间隔的上界
            return true;
        }else{
            return false;
        }
    }

    @Override
    public String toString() {
        return "( "+lo+", "+hi+" )";
    }
    public static void main(String[] args){
        ArrayList<Interval1D> interval1ds=new ArrayList<>();
        Interval1D interval1d1=new Interval1D(3,5);
        Interval1D interval1d2=new Interval1D(4,5);
        Interval1D interval1d3=new Interval1D(1,5);
        Interval1D interval1d4=new Interval1D(7,9);
        Interval1D interval1d5=new Interval1D(1,2);
        interval1ds.add(interval1d1);
        interval1ds.add(interval1d2);
        interval1ds.add(interval1d3);
        interval1ds.add(interval1d4);
        interval1ds.add(interval1d5);
        for(int i=0;i<interval1ds.size();i++){
            for(int j=i+1;j<interval1ds.size();j++){
                Interval1D tempInterval1d1=interval1ds.get(i);
                Interval1D tempInterval1d2=interval1ds.get(j);
                if(tempInterval1d1.intersect(tempInterval1d2)){
                    System.out.println("intersect: "+tempInterval1d1+" "+tempInterval1d2);
                }
            }
        }
    }
}
