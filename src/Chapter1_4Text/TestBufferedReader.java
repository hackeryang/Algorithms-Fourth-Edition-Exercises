package Chapter1_4Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TestBufferedReader {
    public static void main(String[] args) throws IOException {
        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("请输入一串字符串");
        String text = buffer.readLine();
        System.out.println("您输入的字符串是：" + text);
    }
}
