package Chapter1_4Text;

import java.util.Scanner;

public class TestScanner {
    public static void main(String[] args){
        Scanner scanner=new Scanner(System.in);
        System.out.println("请输入一个字符串");
        System.out.println("您输入的字符串是："+scanner.next());
    }
}
