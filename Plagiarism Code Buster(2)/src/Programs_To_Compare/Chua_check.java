import javax.sound.sampled.Line;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class check {

    public static void main(String[] args) throws FileNotFoundException {
        final String file1 = "C:\\Users\\11811099\\act1\\src\\test1";
        final String file2 = "C:\\Users\\11811099\\act1\\src\\test2";

        try {
            BufferedReader read1 = new BufferedReader(new FileReader(file1));
            BufferedReader read2 = new BufferedReader(new FileReader(file2));
            String firstL = read1.readLine();
            String secondL = read2.readLine();

            int similarCheck = 0;
            int LinesRead = 1;


            while(firstL!=null || secondL!=null)
            {
                if(firstL.contentEquals(secondL))
                {
                    similarCheck++;
                    System.out.println("The files have the same content at line " + LinesRead);
                }
                firstL = read1.readLine();
                secondL = read2.readLine();
                LinesRead++;
            }
            if(similarCheck==0)
            {
                System.out.println("The contents of two files are completely different.");
            }
            double magicPerc;
            magicPerc = (Double.valueOf(similarCheck)/ (Double.valueOf(LinesRead)-1.0))*100;
            System.out.println("The percentage of similar lines is: " + magicPerc);
            read1.close();
            read2.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}