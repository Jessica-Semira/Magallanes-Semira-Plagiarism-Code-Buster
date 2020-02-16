import java.io.*;

public class Similaritor {

    private static float DifferenceScore (int compare1, int compare2){
        //this function will return in percent
        float difference = Math.abs(compare1 - compare2);
        float average = (compare1 + compare2)/2;

        return (difference/average)*100;
    }

    private static int CharacterTotalFile (String filepath) throws IOException {

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

    private static int NumLinesTotalFile (String filepath) throws IOException {
        int line = 0;
        BufferedReader testProgram_line = new BufferedReader(new FileReader(filepath));
        while(testProgram_line.readLine() != null) line++;
        testProgram_line.close();
        return line;
    }

    private static float ScoreSimilarity (String filepath1, String filepath2) throws IOException {

        int sumProg1 = 0;
        int sumProg2 = 0;

        int lineProg1 = 0;
        int lineProg2 = 0;

        //for the java files
        lineProg1 = NumLinesTotalFile(filepath1);
        lineProg2 = NumLinesTotalFile(filepath2);

        sumProg1 = CharacterTotalFile(filepath1);
        sumProg2 = CharacterTotalFile(filepath2);

        float scoreSimiliarity = (float) (100 - ((DifferenceScore(lineProg1, lineProg2) * 0.7) + (DifferenceScore(sumProg1, sumProg2) * 0.3) ));

        return scoreSimiliarity;
    }

    private static void Print2DArray(float array[][], String arraySecond[], int length){
        //length is the size of the matrix

        System.out.print("\t\t\t");
        for (String name : arraySecond) {
            System.out.printf("%-11s ", name);
        }
        System.out.println();

        for (int i = 0; i < length; i++) {

            System.out.printf("%-10s \t",arraySecond[i]);

            for (int j = 0 ; j < length ; j++){

                System.out.printf("%7.2f \t",array[i][j]);

            }

            System.out.println();

        }

    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        // TODO code application logic here

        float matrixSimilarity[][];
        String names[];
        File folder = new File("./src/Programs_To_Compare");
        matrixSimilarity = new float[folder.listFiles().length][folder.listFiles().length];
        names = new String[folder.listFiles().length];

        float highestScore = 0;
        int jIndex = 0;
        int iIndex =0;

        System.out.println("File names inside the directory:");
        for (int i = 0; i < folder.listFiles().length; i++) {
            String temp [] = folder.listFiles()[i].getName().split("_");
            names[i] = temp[0];
            System.out.println(names[i] + " : " + folder.listFiles()[i].getName());
        }

        System.out.println("\n\nPairs with more than 90 as score:");
        for (int i = 0; i < folder.listFiles().length ; i++) {

            for (int j = 0; j<folder.listFiles().length;j++){

                matrixSimilarity[i][j] = (ScoreSimilarity(folder.listFiles()[i].getAbsolutePath(),folder.listFiles()[j].getAbsolutePath())/100);

                if(matrixSimilarity[i][j] * 100 > 90 && matrixSimilarity[i][j] * 100 < 100){
                    System.out.printf("%s and %s with a score of %.2f\n", names[i],names[j],matrixSimilarity[i][j] *100);

                }

                if(matrixSimilarity[i][j] * 100>highestScore && matrixSimilarity[i][j] *100 != 100){
                    highestScore = matrixSimilarity[i][j] *100;
                    jIndex = j;
                    iIndex = i;
                }

            }
        }

        System.out.printf("\n\n\nPair with the highest score is %s and %s with the score of %.2f",  names[iIndex], names[jIndex], matrixSimilarity[iIndex][jIndex] * 100);

        /*
        System.out.println("File names with the corresponding index in the matrix array");
        for (int i = 0; i < folder.listFiles().length ; i++) {
            System.out.println(i + " : " + folder.listFiles()[i].getName());

        }*/

        System.out.println("\n\nMatrix:");

        Print2DArray(matrixSimilarity, names, folder.listFiles().length);

    }

}
