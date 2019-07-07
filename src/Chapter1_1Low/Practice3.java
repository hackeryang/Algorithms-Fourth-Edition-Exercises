package Chapter1_1Low;

import java.util.Scanner;

//Exercise 1.1.3
public class Practice3 {
    public static void main(String[] args) {
        System.out.println("please input 3 integers:");
        Scanner scanner1 = new Scanner(System.in);
        String string1 = scanner1.next(); //next()用于读取下一个单词，默认把空格作为分隔符

        Scanner scanner2 = new Scanner(System.in);
        String string2 = scanner2.next();

        Scanner scanner3 = new Scanner(System.in);
        String string3 = scanner3.next();

        Integer number1 = Integer.valueOf(string1);
        Integer number2 = Integer.valueOf(string2);
        Integer number3 = Integer.valueOf(string3);

        if (number1 == number2 && number1 == number3 && number2 == number3) {
            System.out.println("equal");
        } else {
            System.out.println("not equal");
        }
    }
}
