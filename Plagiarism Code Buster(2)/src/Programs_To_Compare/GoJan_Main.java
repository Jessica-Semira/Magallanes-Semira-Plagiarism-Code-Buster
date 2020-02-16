package sample;

import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        InputStream is1 = new FileInputStream("C:\\Users\\Jan Go\\Documents\\DLSU Subjects\\LBYCPD2\\test_program1.cpp");
        BufferedReader buf1 = new BufferedReader(new InputStreamReader(is1));

        String line = buf1.readLine();
        StringBuilder sb1 = new StringBuilder();

        while(line != null) {
            sb1.append(line).append("\n");
            line = buf1.readLine();
        }

        String fileAsString1 = sb1.toString();
        System.out.println("PROGRAM 1:");
        System.out.println(fileAsString1);

        InputStream is2 = new FileInputStream("C:\\Users\\Jan Go\\Documents\\DLSU Subjects\\LBYCPD2\\test_program2.cpp");
        BufferedReader buf2 = new BufferedReader(new InputStreamReader(is2));

        line = buf2.readLine();
        StringBuilder sb2 = new StringBuilder();

        while (line != null) {
            sb2.append(line).append("\n");
            line = buf2.readLine();
        }

        String fileAsString2 = sb2.toString();
        System.out.println("PROGRAM 2:");
        System.out.println(fileAsString2);

        String code1 = fileAsString1.toString();
        int length1 = code1.length();
        String code2 = fileAsString2.toString();
        int length2 = code2.length();
        int finallength;
        int i, similar = 0;
        double percentage = 0;

        if(length1 < length2) {
            finallength = length1;
        }
        else {
            finallength = length2;
        }

        for (i=0; i<finallength; i++) {
            if(code1.charAt(i) == code2.charAt(i)) {
                //System.out.println("SAME!");
                similar++;
            }
        }

        percentage = ((double)similar/(double)finallength)*100;
        String formattedString = String.format("%.2f", percentage);
        System.out.println("PERCENTAGE OF SIMILARITY: " + formattedString);
    }
}