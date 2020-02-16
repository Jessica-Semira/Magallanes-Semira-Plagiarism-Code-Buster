import java.io.*;
public class PlagiarismChecker {
    public static void main(String[] args) throws Exception {
        float same_counter = 0;
        float line_counter = 0;

        File file1 = new File("D:\\Coding\\LBYCP2D\\Module 0\\test_program1.cpp");
        File file2 = new File("D:\\Coding\\LBYCP2D\\Module 0\\test_program2.cpp");

        BufferedReader reader1 = new BufferedReader(new FileReader(file1));
        BufferedReader reader2 = new BufferedReader(new FileReader(file2));

        String text1 = reader1.readLine();
        String text2 = reader2.readLine();

        while(text1!=null||text2!=null){
            if(text1.equalsIgnoreCase(text2)){
                same_counter++;
            }

            text1 = reader1.readLine();
            text2 = reader2.readLine();
            line_counter++;
        }

        System.out.println("Number of lines that are the same: "+same_counter);
        System.out.println("Total number of lines: "+line_counter);

        float score = same_counter/line_counter*100;
        System.out.println("Your plagiarism score is: "+score+"%");
    }
}
