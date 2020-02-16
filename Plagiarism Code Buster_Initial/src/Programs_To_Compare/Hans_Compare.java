import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Compare {
    public static void main(String[] args) throws IOException {
        Compare compare = new Compare();
        compare.javaCompare();
        compare.cprogCompare();

    }
    void javaCompare() throws IOException{
        File test_program1 = new File("Assets/test_program1.java");
        File test_program2 = new File("Assets/test_program2.java");

        BufferedReader tp1 = new BufferedReader(new FileReader(test_program1));
        BufferedReader tp2 = new BufferedReader(new FileReader(test_program2));

        String string1=tp1.readLine();
        String string2=tp2.readLine();

        double total=0;
        double same=0;

        while (string1!=null){
            total++;
            if(string1.equals(string2)){
                same++;
            }
            string1=tp1.readLine();
            string2=tp2.readLine();
        }
        double percent = same/total*100;
        System.out.println("Java programs are "+percent+" % similar");
    }

    void cprogCompare() throws IOException{
        File test_program1 = new File("Assets/test_program1.cpp");
        File test_program2 = new File("Assets/test_program2.cpp");

        BufferedReader tp1 = new BufferedReader(new FileReader(test_program1));
        BufferedReader tp2 = new BufferedReader(new FileReader(test_program2));

        String string1=tp1.readLine();
        String string2=tp2.readLine();

        double total=0;
        double same=0;

        while (string1!=null){
            total++;
            if(string1.equals(string2)){
                same++;
            }
            string1=tp1.readLine();
            string2=tp2.readLine();
        }
        double percent = same/total*100;
        System.out.println("C programs are "+percent+" % similar");
    }
}
