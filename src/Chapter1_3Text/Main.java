package Chapter1_3Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] arg) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n, k;
        int count = 0;
        String line = bf.readLine().trim();
        String[] str = line.split(" ");
        n = Integer.parseInt(str[0]);
        k = Integer.parseInt(str[1]);
        if (n >= 1 && n <= 100000 && k >= 0 && k <= n - 1) {
            for (int x = k; x <= n; x++) {
                for (int y = 1; y <= n; y++) {
                    if ((x % y) >= k)
                        count++;
                }
            }
        }
        System.out.println(count);
    }

}
