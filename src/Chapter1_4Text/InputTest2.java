package Chapter1_4Text;

import java.io.*;

public class InputTest2 {
    public static void main(String[] args) throws IOException {  //使用BufferedReader的readLine()方法必须处理IOException异常
        BufferedReader buf=new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter buf2=new BufferedWriter(new FileWriter("abx.txt"));
        String str=buf.readLine();
        while(!str.equals("exit")){
            buf2.write(str);
            buf2.newLine();  //在输出文件中输入换行符
            str=buf.readLine();
        }
        buf.close();
        buf2.close();
    }
}
