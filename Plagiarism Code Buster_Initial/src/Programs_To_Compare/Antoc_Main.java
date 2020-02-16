/*
This program is designed to compare the contents of two files. Specifically, on this program,
the contents of tho programs would be compared upon starting this program. The programmer expects
that the similarity percentage between the program should be high.

The program compares the content character by character.

Author: Jan Luis V. Antoc
 */

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // For reading the file
        String firstFilePath = "C:\\Users\\Admin\\Documents\\DLSU - 2nd Year\\2nd Term\\LBYCPD2\\Module 0\\test_program1\\src\\HelloAgain.java";
        String secondFilePath = "C:\\Users\\Admin\\Documents\\DLSU - 2nd Year\\2nd Term\\LBYCPD2\\Module 0\\test_program2\\src\\HelloAgain.java";

        String firstFile = readAllLines(firstFilePath);
        String secondFile = readAllLines(secondFilePath);
        //System.out.println(secondFile);

        assert firstFile != null;
        assert secondFile != null;

        // For removing the white spaces
        firstFile = firstFile.replace(" ", "");
        secondFile = secondFile.replace(" ", "");

        // For determining the basis for the comparison
        int totalCount, otherCount;
        if (firstFile.length() >= secondFile.length()) {
            totalCount = firstFile.length();
            otherCount = secondFile.length();
        } else {
            totalCount = secondFile.length();
            otherCount = firstFile.length();
        }
        // For checking the similarity of each character between the first file contents and the second file contents
        float counter = 0;
        for (int i = 0; i < totalCount; i++) {
            // For the index before it goes out of bounds with the shorter file
            if (i < otherCount){
                /*
                 * If the character at a certain index of the two strings are not similar,
                 * then the checking would be terminated.
                 * Subject for Testing: Longevity of Similarity
                 */
                if (firstFile.charAt(i) != secondFile.charAt(i)) {
                    break;
                } else {
                    // If the characters are similar, then the counter for similarity would be incremented.
                    counter++;
                }
            }
        }
        float similarityPercentage = (counter / totalCount) * 100;
        System.out.println("Structural Plagiarism Score: " + similarityPercentage + "%");
    }
    // Method for reading the contents of the program
    private static String readAllLines(String address) {
        try {
            // List of string is being utilized for storing the contents of a specific path
            List<String> contents = Files.readAllLines(Paths.get(address));
            return contents.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}