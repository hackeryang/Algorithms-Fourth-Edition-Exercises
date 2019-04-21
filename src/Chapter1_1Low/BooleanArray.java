package Chapter1_1Low;

import java.util.Locale;

//Exercise 1.1.11
public class BooleanArray {
    private static void printout(boolean[][] al){
        for(int i=0;i<al.length;i++){
            for(int j=0;j<al[i].length;j++){
                if(al[i][j]){
                    System.out.println(String.format(Locale.CHINA,"%d %d *",i+1,j+1));
                }else{
                    System.out.println(String.format(Locale.CHINA,"%d %d /",i+1,j+1));
                }
            }
        }
    }
}
