import java.io.*;

public class Main {

    public static void main(String args[]) throws IOException {
        float total=0, diff=0;
        BufferedReader reader1 = new BufferedReader(new FileReader("D:\\IntelliJ\\Mod1\\test_program1.txt"));
        BufferedReader reader2 = new BufferedReader(new FileReader("D:\\IntelliJ\\Mod1\\test_program2.txt"));
        String line1 = reader1.readLine();
        String line2 = reader2.readLine();

        while ((line1 != null)&&(line2 != null)){
            if (!line1.equalsIgnoreCase(line2)) {
                diff++;
            }
            line1 = reader1.readLine();
            line2 = reader2.readLine();
            total++;
        }
        if(diff == 0){
            System.out.println("The similarity percentage of two files is 100%.");
        }
        else {
            float perc = (diff/total)*100;
            System.out.println("The similarity percentage of two files is "+ perc + "%.");
        }
        reader1.close();
        reader2.close();
    }
}
