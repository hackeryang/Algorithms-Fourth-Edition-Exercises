package Chapter1_2Low;

//exercise 1.2.11
public class SmartDate {
    @SuppressWarnings("unused")
    private final int year;
    @SuppressWarnings("unused")
    private final int month;
    @SuppressWarnings("unused")
    private final int day;

    public SmartDate(int year,int month,int day) throws Exception{
        if(year<0 || month<0 || day<0){
            Exception exception=new Exception("年月日要大于0");
            throw exception;
        }
        if(month>12){
            Exception exception=new Exception("年份要小于等于12");
            throw exception;
        }
        switch(month){
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
            case 1:{
                if(day>31){
                    Exception exception=new Exception("月小于31号");
                    throw exception;
                }
            }
            break;
            case 2:{
                if(day>29){
                    Exception exception=new Exception("月小于31号");
                    throw exception;
                }
                int leapYear=year%4;
                if(leapYear!=0){
                    if(day>28){
                        Exception exception=new Exception(month+"月小于29号");
                        throw exception;
                    }
                }
            }
            break;
            case 4:
            case 6:
            case 9:
            case 11:{
                if(day>30){
                    Exception exception=new Exception(month+"月小于30号");
                    throw exception;
                }
            }
            break;
            default:
                break;
        }
        this.day=day;
        this.year=year;
        this.month=month;
    }
    public static void main(String[] args) throws Exception{
        SmartDate date=new SmartDate(2007,1,50);
    }
}
