package Chapter3_5Text;

import edu.princeton.cs.algs4.*;

import java.io.File;

public class FileIndex {
    public static void main(String[] args){
        ST<String,SET<File>> st=new ST<String,SET<File>>();  //单词及包含该单词的所有文件集合
        for(String filename:args){
            File file=new File(filename);  //根据命令行输入的文件名创建File对象，并作为In的输入
            In in=new In(file);
            while(!in.isEmpty()){
                String word=in.readString();
                if(!st.contains(word)) st.put(word,new SET<File>()); //每个不重复的单词都插入一个该单词及包含它的文件集合的符号表项
                SET<File> set=st.get(word);  //在上一行创立的文件集合容器中，一个个插入包含该单词的文件名
                set.add(file);
            }
        }
        while(!StdIn.isEmpty()){
            String query=StdIn.readString();
            if(st.contains(query))
                for(File file:st.get(query))
                    StdOut.println(" "+file.getName());  //根据单词来查找出现该单词的所有文件名
        }
    }
}
