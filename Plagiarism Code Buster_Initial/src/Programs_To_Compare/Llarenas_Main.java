import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class Main {

    public static void main(String[] args) throws IOException {
        String file1 = "C:\\Users\\11812508\\IdeaProjects\\Module0\\test_program1.java";
        String file2 = "C:\\Users\\11812508\\IdeaProjects\\Module0\\test_program2.java";
        BufferedReader reader1 = new BufferedReader(new FileReader(file1));
        BufferedReader reader2 = new BufferedReader(new FileReader(file2));
        int i=0;
        int score=0;

        StringBuilder code1 = new StringBuilder();
        StringBuilder code2 = new StringBuilder();
        String line1 = "";
        String line2 = "";
        while(((line1=reader1.readLine())!=null)&&((line2=reader2.readLine())!=null)){
            if(line1.equals(line2)){
                score++;
            }
            i++;
        }
        double grade = (Double.valueOf(score)/Double.valueOf(i-1))*100;
        System.out.println("Programs are " + (int)grade + "% similar!");
        if(grade>=70){
            System.out.println("Programs are plagiarized");
        }
    }
}
