import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class main {

    public static void main (String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the FIRST file for comparison: ");
        String file1 = sc.nextLine();
        System.out.println("Enter the SECOND file for comparison: ");
        String file2 = sc.nextLine();

        BufferedReader bFile1 = new BufferedReader(new FileReader(file1));
        BufferedReader bFile2 = new BufferedReader(new FileReader(file2));

        String line1;
        String line2;
        String longestLine = "";

        int currentCharacterCount = 0;
        int longestCharacterCount = 0;

        double lineReader = 1;
        double similarityCount = 0;

        while (((line1 = bFile1.readLine()) != null) && ((line2 = bFile2.readLine()) != null)) {
            if(line1.toLowerCase().equals(line2.toLowerCase())){
                System.out.println("Same contents at line " + Math.round(lineReader * 100) / 100);
                similarityCount = similarityCount + 1;

                currentCharacterCount = 0;
                for(int i = 0; i < line1.length(); i++) {
                    if(line1.charAt(i) != ' ')
                        currentCharacterCount++;
                }
                if (currentCharacterCount > longestCharacterCount){
                    longestCharacterCount = currentCharacterCount;
                    longestLine = line1;
                }

            }
            lineReader = lineReader + 1;
        }

        double similarityIndex = (similarityCount/(lineReader-1))*100;
        double roundedSimilarityIndex = Math.round(similarityIndex * 100.0) / 100.0;

        System.out.println("\nSimilarity index: " + roundedSimilarityIndex + "%");

        if(similarityIndex == 0) System.out.println("\nCONCLUSION: These are EXACTLY DIFFERENT files");
        else if (similarityIndex == 100) System.out.println("\nCONCLUSION: These are the SAME files");
        else if (similarityIndex < 50) System.out.println("\nCONCLUSION: These are VERY DIFFERENT files");
        // MORE THAN 50% DIFFERENCE
        else System.out.println("\nCONCLUSION: These are SLIGHTLY DIFFERENT files");

        System.out.println("\nLongest String Similarity: " + longestLine);

        bFile1.close();
        bFile2.close();
    }


}
