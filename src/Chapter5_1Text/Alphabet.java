package Chapter5_1Text;

import edu.princeton.cs.algs4.StdOut;

public class Alphabet {  //各场景所涵盖字符的字母表数据结构
    public static final Alphabet BINARY = new Alphabet("01");  //二进制
    public static final Alphabet OCTAL = new Alphabet("01234567");  //八进制
    public static final Alphabet DECIMAL = new Alphabet("0123456789");  //十进制
    public static final Alphabet HEXADECIMAL = new Alphabet("0123456789ABCDEF");  //十六进制
    public static final Alphabet DNA = new Alphabet("ACGT");  //DNA
    public static final Alphabet PROTEIN = new Alphabet("ACDEFGHIKLMNPQRSTVWY");  //蛋白质
    public static final Alphabet LOWERCASE = new Alphabet("abcdefghijklmnopqrstuvwxyz");  //小写字母
    public static final Alphabet UPPERCASE = new Alphabet("ABCDEFGHIJKLMNOPQRSTUVWXYZ");  //大写字母
    public static final Alphabet BASE64 = new Alphabet("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/");  //base64编码
    public static final Alphabet ASCII = new Alphabet(128);  //ASCII字符（0-127）
    public static final Alphabet EXTENDED_ASCII = new Alphabet(256);  //扩展ASCII字符（0-256）
    public static final Alphabet UNICODE16 = new Alphabet(65536);  //Unicode字符集（0-65535）

    private char[] alphabet;  //字母表中的字符，用字符数组表示
    private int[] inverse;  //字符所在的数组索引
    private final int R;  //基数，即字母表中的字符数量

    public Alphabet(String alpha) {  //根据alpha中的字符创建一张新的字母表
        boolean[] unicode = new boolean[Character.MAX_VALUE];  //char就是16位的Unicode字符，所以最大值为65535
        for (int i = 0; i < alpha.length(); i++) {  //检查字母表中的字符是否有重复
            char c = alpha.charAt(i);
            if (unicode[c])
                throw new IllegalArgumentException("Illegal alphabet: repeated character = '" + c + "'");
            unicode[c] = true;  //每访问过一个字符，就将boolean数组对应位置置为true，用于检测重复
        }
        alphabet = alpha.toCharArray();  //将字符串形式的字母表转化为字符数组
        R = alpha.length();
        inverse = new int[Character.MAX_VALUE];
        for (int i = 0; i < inverse.length; i++)
            inverse[i] = -1;  //每个字符的索引都初始化为-1，表示找不到
        for (int c = 0; c < R; c++)  //将每个字符对应一个0到R-1之间的索引
            inverse[alphabet[c]] = c;
    }

    private Alphabet(int radix) {  //对于ASCII和char，利用介于0到R-1的索引数构造字母表
        this.R = radix;
        alphabet = new char[R];
        inverse = new int[R];
        for (int i = 0; i < R; i++)
            alphabet[i] = (char) i;
        for (int i = 0; i < R; i++)
            inverse[i] = i;
    }

    public Alphabet() {  //创建256个字符的ASCII字母表
        this(256);
    }

    public boolean contains(char c) {  //字母表中是否包含字符c
        return inverse[c] != -1;
    }

    public int R() {
        return R;
    }

    public int lgR() {  //一个字符索引所需的二进制比特数
        int lgR = 0;
        for (int t = R - 1; t >= 1; t /= 2)
            lgR++;
        return lgR;
    }

    public int toIndex(char c) {  //获取字符的索引，在0到R-1之间
        if (c >= inverse.length || inverse[c] == -1)  //如果c的索引超出了字符数组最大长度或者索引为-1，说明不在字母表中
            throw new IllegalArgumentException("Character " + c + " not in alphabet");
        return inverse[c];
    }

    public int[] toIndices(String s) {  //将字符串转换为R进制的整数，即每个字符都转化为0到R-1之间的索引
        char[] source = s.toCharArray();
        int[] target = new int[s.length()];
        for (int i = 0; i < source.length; i++)
            target[i] = toIndex(source[i]);
        return target;
    }

    public char toChar(int index) {  //获取字母表对应索引处的字符
        if (index < 0 || index >= R)
            throw new IllegalArgumentException("index must be between 0 and " + R + ": " + index);
        return alphabet[index];
    }

    public String toChars(int[] indices) {  //将R进制的整数（即各字符索引）转换为字符数组格式的字母表
        StringBuilder s = new StringBuilder(indices.length);  //StringBuilder将一个个字符追加到尾部的速度远快于String，String在这个过程中的所需时间是平方级别的
        for (int i = 0; i < indices.length; i++)
            s.append(toChar(indices[i]));  //indices[i]的值是alphabet[i]的int格式，所以需要转化为char
        return s.toString();
    }

    public static void main(String[] args) {  //将一段字符串根据对应字母表进行编码（转化为数字索引）与解码（从数字索引还原成字符串）
        int[] encoded1 = Alphabet.BASE64.toIndices("NowIsTheTimeForAllGoodMen");
        String decoded1 = Alphabet.BASE64.toChars(encoded1);
        StdOut.println(decoded1);

        int[] encoded2 = Alphabet.DNA.toIndices("AACGAACGGTTTACCCCG");
        String decoded2 = Alphabet.DNA.toChars(encoded2);
        StdOut.println(decoded2);

        int[] encoded3 = Alphabet.DECIMAL.toIndices("01234567890123456789");
        String decoded3 = Alphabet.DECIMAL.toChars(encoded3);
        StdOut.println(decoded3);
    }
}
