package Chapter1_2High;

//exercise 1.2.19
public class Date {
    @SuppressWarnings("unused")
    private final int year;
    @SuppressWarnings("unused")
    private final int month;
    @SuppressWarnings("unused")
    private final int day;
    /*
    * 与SmartDate2不同，这里本来是写死的现在不写死，因为yearFirstTwo的意思是获取年份的前两位，在构造函数中再指定
    * */
    private  int yearFirstTwo;
    private static final int DAYPERWEEK=7;
    public Date(int year, int month, int day) throws Exception{
        if(year<0 || month<0 || day<0){
            throw new Exception("年月日要大于0");
        }
        if(month>12){
            throw new Exception("月份要小于等于12");
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
                    throw new Exception(month+"月小于31号");
                }
            }
            break;
            case 2:{
                if(day>29){
                    throw new Exception(month+"月小于等于29号");
                }
                boolean leapYear=this.isLeapYear(year);
                if(!leapYear){
                    if(day>28){
                        throw new Exception(month+"月小于等于28号");
                    }
                }
            }
            break;
            case 4:
            case 6:
            case 9:
            case 11:{
                if(day>30){
                    throw new Exception(month+"月小于30号");
                }
            }
            break;
            default:
                break;
        }
        this.day=day;
        this.year=year;
        this.month=month;
        yearFirstTwo=year/100;
    }
    //1.2.19
    public Date(String date){
        String[] fields=date.split("/");
        month=Integer.parseInt(fields[0]);
        day=Integer.parseInt(fields[1]);
        year=Integer.parseInt(fields[2]);
    }
    /*
    * 改动2：判断闰年的方法单独写出来，以前只是单纯的取余4，现在单独写一个方法
    * 判断是否为闰年
    * 方法：闰年满足两个条件（满足一个即为闰年）
    * 1.能被4整除但不能被100整除
    * 2.能被400整除
    * @param year
    * @return
    * */
    private boolean isLeapYear(int year){
        if (year % 100 != 0 && year % 4 == 0) {
            return true;
        } else if (year % 100 == 0 && year % 400 == 0) {
            return true;
        }else{
            return false;
        }
    }
    public String dayOfTheWeek(){
        String resultWeek="";
        int tempMonth=this.month;
        int tempYear=this.year;
        int tempDay=this.day;
        if(this.month==1 || this.month==2){
            tempMonth+=12;
            tempYear--;
        }
        int y=tempYear-yearFirstTwo*100;
        int floor1=(int)Math.floor(y/4);
        int floor2=(int)(yearFirstTwo/4);
        int floor3=(int)Math.floor(26*(tempMonth+1)/10);
        int w=y+floor1+floor2-2*yearFirstTwo+floor3+tempDay-1;
        int key=w%DAYPERWEEK;
        if(key<0){
            key=key+7;
        }
        switch(key){
            case 0:
                resultWeek="星期日";
                break;
            case 1:
                resultWeek="星期一";
                break;
            case 2:
                resultWeek="星期二";
                break;
            case 3:
                resultWeek="星期三";
                break;
            case 4:
                resultWeek="星期四";
                break;
            case 5:
                resultWeek="星期五";
                break;
            case 6:
                resultWeek="星期六";
                break;
            default:
                break;
        }
        return resultWeek;
    }
    public String toString(){
        return ""+month+"/"+day+"/"+year;
    }
    public boolean equals(Object that){
        if(this==that){
            return true;
        }
        if(that==null){
            return false;
        }
        Date xDate=(Date)that;
        if(this.year!=xDate.year) return false;
        if(this.month!=xDate.month) return false;
        if(this.day!=xDate.day) return false;
        return true;
    }
    public static void main(String[] args) throws Exception{
        Date date=new Date(1996,12,5);
        String week=date.dayOfTheWeek();
        System.out.println(date+" is: "+week);
    }
}
