package Chapter1_1Low;

//Exercise 1.1.5
public class Between0And1 {
    public boolean between0And1(double x,double y){
        if((x<1)&&(x>0)&&(y<1)&&(y>0)){
            return true;
        }
        return false;
    }
}
