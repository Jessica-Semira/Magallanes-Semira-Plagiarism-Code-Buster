import CodeBusterClasses.*;
import java.io.*;

public class Similaritor {

    private static void initialize2Darray(float[][] array, int size1, int size2){
        for (int i = 0; i < size1; i++) {

            for (int j = 0 ; j < size2 ; j++){

                array[i][j] = -1;

            }
        }

    }

    private static void Print2DArray(float[][] array, String[] arraySecond, int length){
        //length is the size of the matrix

        System.out.print("\t\t\t");
        for (String name : arraySecond) {
            System.out.printf("%-11s ", name);
        }
        System.out.println();

        for (int i = 0; i < length; i++) {

            System.out.printf("%-10s \t",arraySecond[i]);

            for (int j = 0 ; j < length ; j++){

                System.out.printf("%7.3f \t",array[i][j]);

            }

            System.out.println();

        }

    }

    private static float average (float numOne, float numTwo){ return (numOne+numTwo)/2;}

    public static void main(String[] args) throws FileNotFoundException, IOException {
        // TODO code application logic here

        float[][] matrixSimilarity;
        String[] names;

        File folder = new File("./src/Programs_To_Compare");
        File[] listFiles = folder.listFiles();

        matrixSimilarity = new float[listFiles.length][listFiles.length];
        names = new String[listFiles.length];

        System.out.println("File names inside the directory:");

        for (int i = 0; i < listFiles.length; i++) {
            names[i] = listFiles[i].getName().split("_")[0];
            System.out.println(names[i] + " : " + listFiles[i].getName());
        }

        initialize2Darray(matrixSimilarity,listFiles.length,listFiles.length);

        float highest = 0;
        String temname1 = " ";
        String temname2 = " ";


        System.out.println();
        for (int i = 0; i < listFiles.length ; i++) {

            for (int j = 0; j<listFiles.length;j++){

                if(matrixSimilarity[i][j] == -1){
                    float temp1 = new CompareClass(listFiles[i].getAbsolutePath(),listFiles[j].getAbsolutePath()).getSimilarityScore();
                    float temp2 = new CompareClass(listFiles[j].getAbsolutePath(),listFiles[i].getAbsolutePath()).getSimilarityScore();
                    float ave = average(temp1, temp2);
                    matrixSimilarity[i][j] = ave;
                    matrixSimilarity[j][i] = ave;
                }

                if(highest < matrixSimilarity[i][j] &&   matrixSimilarity[i][j] != 1){
                    highest = matrixSimilarity[i][j];
                    temname1= names[i];
                    temname2 = names[j];
                }

            }
        }

        System.out.println("Highest Score:" + highest +" Pairs: " + temname1 + " and " + temname2);
        Print2DArray(matrixSimilarity, names, listFiles.length);

    }

}
