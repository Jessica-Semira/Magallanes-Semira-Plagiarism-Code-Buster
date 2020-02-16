package com.company;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileToString {

//    public static void main  (String args[]) throws IOException {
//    InputStream is = new FileInputStream("test_program1.java");
//    BufferedReader buf = new BufferedReader(new InputStreamReader((is)));
//
//    String line = buf.readLine();
//    StringBuilder sb = new StringBuilder();
//
//    while (line !=null){
//        sb.append(line).append("\n");
//        line = buf.readLine();
//    }
//    String FileAsString = sb.toString();
//    System.out.println(FileAsString);
//    }

    public static String readFileAsString(String fileName)throws Exception
    {
        String data = "";
        data = new String(Files.readAllBytes(Paths.get(fileName)));
        return data;
    }

    public static void main(String[] args) throws Exception
    {
        int percent = 0;
        int wordcount = 0;
        int wordpercent;
        String text1 = readFileAsString("C:\\Users\\11838604.MANILA\\Desktop\\test_program1.java");
        String text2 = readFileAsString("C:\\Users\\11838604.MANILA\\Desktop\\test_program2.java");

        System.out.println(text1);
        System.out.println(text2);

        String[] words1 = text1.split("\\W+");
        String[] words2 = text2.split("\\W+");

        System.out.println(words1.length);


        for(int i =0; i< words1.length;i++){
            wordcount ++;
           System.out.println(words1[i]);
            System.out.println(words2[i]);

            }

        wordpercent = 100/wordcount;

        for(int i =0; i< words1.length;i++){
                if (words1[i].equals(words2[i])){
                percent = percent + wordpercent;
            }
        }

        System.out.println(percent+"%");

    }


}
