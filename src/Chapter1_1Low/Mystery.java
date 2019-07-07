package Chapter1_1Low;

//Exercise 1.1.18
public class Mystery {
    public static int mystery(int a, int b) {
        if (b == 0)
            return 0;
        if (b % 2 == 0)
            return mystery(a + a, b / 2);
        return mystery(a + a, b / 2) + a;
    }

    public static int mystery1(int a, int b) {
        if (b == 0)
            return 1;
        if (b % 2 == 0)
            return mystery1(a * a, b / 2);
        return mystery(a * a, b / 2) * a;
    }

    public static void main(String[] args) {
        System.out.println(mystery(2, 25));//输出50
        System.out.println(mystery(3, 11));//输出33
        System.out.println(mystery1(2, 25));//输出33554432
        System.out.println(mystery1(3, 11));//输出177147
    }
}
