import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Similaritor {

    private static float differenceScore (int compare1, int compare2){
        //this function will return in percent
        float difference = Math.abs(compare1 - compare2);
        float average = (compare1 + compare2)/2;

        return (difference/average)*100;
    }

    private static int characterTotalFile (String filepath) throws IOException {

        int sum =0;
        int tempInt =0;

        FileReader testProgram1 = new FileReader(filepath);
        while(tempInt != -1){
            tempInt = testProgram1.read();

            if(tempInt != 32 && tempInt != 13 && tempInt != 10) sum += tempInt;
        }

        testProgram1.close();
        return sum;
    }

    private static int numLinesTotalFile (String filepath) throws IOException {
        int line = 0;
        BufferedReader testProgram_line = new BufferedReader(new FileReader(filepath));
        while(testProgram_line.readLine() != null) line++;
        testProgram_line.close();
        return line;
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        // TODO code application logic here

        int sumProg1 = 0;
        int sumProg2 = 0;

        int lineProg1 = 0;
        int lineProg2 = 0;

        //for the java files
        lineProg1 = numLinesTotalFile("./src/Test_Programs/Test_program1Java.java");
        lineProg2 = numLinesTotalFile("./src/Test_Programs/Test_program2.java");

        sumProg1 = characterTotalFile("./src/Test_Programs/Test_program1Java.java");
        sumProg2 = characterTotalFile("./src/Test_Programs/Test_program2.java");

        float scoreSimiliarity = 100 - (differenceScore(lineProg1, lineProg2) + differenceScore(sumProg1, sumProg2));

        System.out.println("Similarity Score of the Two Java Test Files: " + scoreSimiliarity);

        //for the .cpp files

        lineProg1 = numLinesTotalFile("./src/Test_Programs/Test_Program1.cpp");
        lineProg2 = numLinesTotalFile("./src/Test_Programs/Test_Program2.cpp");

        sumProg1 = characterTotalFile("./src/Test_Programs/Test_Program1.cpp");
        sumProg2 = characterTotalFile("./src/Test_Programs/Test_Program2.cpp");

        scoreSimiliarity = 100 - (differenceScore(lineProg1, lineProg2) + differenceScore(sumProg1, sumProg2));

        System.out.println("Similarity Score of the Two C++ Test Files: " + scoreSimiliarity);
    }
}
