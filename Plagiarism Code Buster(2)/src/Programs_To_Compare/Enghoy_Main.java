import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class Main {
        public static void main(String[] args) throws IOException {
            BufferedReader reader1 = new BufferedReader(new FileReader("G:/DLSU/TERM 5/LBYCP2D/src/test_program1.cpp"));
            BufferedReader reader2 = new BufferedReader(new FileReader("G:/DLSU/TERM 5/LBYCP2D/src/test_program2.cpp"));

            String line1 = reader1.readLine();
            String line2 = reader2.readLine();

            boolean areEqual = true;
            int lineNum = 1;
            double differentLines = 0;

            while (line1 != null || line2 != null){
                if(line1 == null || line2 == null){
                    areEqual = false;
                    System.out.println();
                    System.out.println("At line"+lineNum+", File 1 has"+line1+" and File 2 has"+line2);
                    differentLines++;
                }

                else if(!line1.equalsIgnoreCase(line2)){
                    areEqual = false;
                    System.out.println();
                    System.out.println("At line"+lineNum+", File 1 has"+line1+" and File 2 has"+line2);
                    differentLines++;
                }

                line1 = reader1.readLine();
                line2 = reader2.readLine();
                lineNum++;
            }

            if(areEqual){
                System.out.println("Two files have same content.");
                System.out.println();
            }

            double totalLines = lineNum;
            double sameLines = totalLines - differentLines;
            double similarityPercent = (sameLines / totalLines) * 100;

            System.out.println("The programs have "+sameLines+" similar lines.");
            System.out.println("With a similarity percentage of "+similarityPercent+"%.");



            reader1.close();
            reader2.close();
        }
    }
