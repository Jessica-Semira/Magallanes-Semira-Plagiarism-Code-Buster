import java.io.*;

public class Main {
    public static void main(String[]args) throws IOException{
        Main check = new Main();
        check.check("Assets/test_program1.java", "Assets/test_program2.java");
        check.check("Assets/test_program1.cpp", "Assets/test_program2.cpp");
    }

    void check(String one, String two) throws IOException {
        double similar = 0, total = 0;

        File fileOne = new File(one);
        File fileTwo = new File(two);

        BufferedReader readOne = new BufferedReader(new FileReader(fileOne));
        String lineOne;
        BufferedReader readTwo = new BufferedReader(new FileReader(fileTwo));
        String linetwo;
        lineOne = readOne.readLine();
        linetwo = readTwo.readLine();
        while ((lineOne != null) && (linetwo != null)){
            total ++;

            if (lineOne.equals(linetwo)){
                similar++;
            }
            lineOne = readOne.readLine();
            linetwo = readTwo.readLine();
        }

        double percentage = 0.0;
        percentage = similar/total*100;
        System.out.println("Similarity Score: " + percentage + "%.");
    }

}
