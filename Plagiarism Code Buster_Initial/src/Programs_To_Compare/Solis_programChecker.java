import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class programChecker {
    public static void main(String[] args) throws IOException{

        String adder1;
        String adder2;
        BufferedReader File1 = new BufferedReader(new FileReader("C:\\Users\\11825316\\IdeaProjects\\Solis-Checker-Module0\\src\\test_program3"));
        BufferedReader File2 = new BufferedReader(new FileReader("C:\\Users\\11825316\\IdeaProjects\\Solis-Checker-Module0\\src\\text_program4"));

        List<String> storage1 = new ArrayList<>();
        List<String> storage2 = new ArrayList<>();

        while ((adder1 = File1.readLine()) != null) {
            storage1.add(adder1);
        }

        while ((adder2 = File2.readLine()) != null) {
            storage2.add(adder2);
        }

        File1.close();
        File2.close();
        double counter = 0;
        double counter1 = 0;
        double finalCounter = 0;
        double countComparison = 0;

        String[] Array1 = storage1.toArray(new String[0]);
        for (String temp1 : Array1) {
            System.out.println(temp1);
            counter++;
        }
        String[] Array2 = storage2.toArray(new String[0]);
        for (String temp2 : Array2) {
            counter1++;
            System.out.println(temp2);
        }
        for (String temp1 : Array1) {
            for (String temp2 : Array2) {
                if(temp1.equals(temp2)) {
                    countComparison++;
                }
            }
        }
        if(counter1 < counter) {
            finalCounter = counter;
        }
        else {
            finalCounter = counter1;
        }

        double similarity;
        similarity = (countComparison/finalCounter ) * 100;
        System.out.println("The similarity of the two files is "  + similarity);
    }
}

