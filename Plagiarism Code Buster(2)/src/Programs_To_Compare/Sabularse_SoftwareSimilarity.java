import java.io.File;
import java.util.Scanner;

public class SoftwareSimilarity {

    public static void main(String[] args) throws Exception
    {
        System.out.print("PAIR 1\n\n");
        //System.out.print("PAIR 2\n\n");

        StringBuilder prog1String = new StringBuilder();
        StringBuilder prog2String = new StringBuilder();

        //PAIR 1
        File prog1File = new File("C:\\Users\\User\\IdeaProjects\\Module 0\\src\\prog1.java");
        File prog2File = new File("C:\\Users\\User\\IdeaProjects\\Module 0\\src\\prog2.java");

        //PAIR 2
//        File prog1File = new File("C:\\Users\\User\\IdeaProjects\\Module 0\\src\\prog1.cpp.txt");
//        File prog2File = new File("C:\\Users\\User\\IdeaProjects\\Module 0\\src\\prog2.cpp.txt");

        Scanner prog1Scan = new Scanner(prog1File);
        Scanner prog2Scan = new Scanner(prog2File);

        String longestSamedtString = "";
        float sameLines = 0,totalLines = 0,lineLength = 0;

        while(prog1Scan.hasNextLine() || prog2Scan.hasNextLine()){
            String prog1Word = prog1Scan.nextLine();
            String prog2Word = prog2Scan.nextLine();

            prog1String.append(prog1Word + "\n");
            prog2String.append(prog2Word + "\n");

            if(prog1Word.equals(prog2Word)){
                int newLineLength = prog1Word.length();

                if(newLineLength > lineLength){
                    longestSamedtString = prog1Word;
                    lineLength = newLineLength;
                }

                sameLines++;
            }

            totalLines++;
        }

        float percentage = (sameLines / totalLines) * 100;

        System.out.println("PROG #1: \n" + prog1String);
        System.out.println("PROG #2: \n" + prog2String);

        System.out.println("\nLongest Similar String: \n" + longestSamedtString);


//        System.out.println(sameLines);
//        System.out.println(diffLines);
//        System.out.println(totalLines);
//        System.out.println(percentage);

        System.out.println("\nSimilarity Percentage: " + percentage + "%");

    }



}
