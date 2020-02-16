import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.Scanner;

public class Compare {
    private static double similarCPP;
    private static double totalCPP;
    private static double similarJAVA;
    private static double totalJAVA;
    private static DecimalFormat two_point = new DecimalFormat("#.##");
    private static void read_files() {
        String data1 = null, data2 = null;
        try {
            File first_program = new File("Directory/test_program1.cpp");
            File second_program = new File("Directory/test_program2.cpp");
            Scanner first_cmp = new Scanner(first_program);
            Scanner second_cmp = new Scanner(second_program);
            while (first_cmp.hasNextLine() || second_cmp.hasNextLine()) {
                     data1 = first_cmp.nextLine();
                     data2 = second_cmp.nextLine();
                     totalCPP++;
                    if(data1.equals(data2)){
                        similarCPP++;
                    }
                }
            first_cmp.close();
            second_cmp.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        try {
            File first_program = new File("Directory/test_program1.java");
            File second_program = new File("Directory/test_program2.java");
            Scanner first_cmp = new Scanner(first_program);
            Scanner second_cmp = new Scanner(second_program);
            while (first_cmp.hasNextLine() || second_cmp.hasNextLine()) {
                data1 = first_cmp.nextLine();
                data2 = second_cmp.nextLine();
                totalJAVA++;
                if(data1.equals(data2)){
                    similarJAVA++;
                }
            }
            first_cmp.close();
            second_cmp.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        double percentage;
        read_files();
        percentage = (similarCPP/totalCPP)*100;
        System.out.print("\n\nCPP Similarity: "+two_point.format(percentage)+"%");
        percentage = (similarJAVA/totalJAVA)*100;
        System.out.print("\nJAVA Similarity: "+two_point.format(percentage)+"%");
    }
}

