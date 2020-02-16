import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Compare {


     float checkFiles(String file1, String file2) throws FileNotFoundException {
        File program1 = new File(file1);
        File program2 = new File(file2);

        Scanner sc1 = new Scanner(program1);
        Scanner sc2 = new Scanner(program2);

        float count=0;
        float similar=0;
        String string1;
        String string2;
        while (sc1.hasNext() || sc2.hasNext()){
            string1 = sc1.next();
            string2 = sc2.next();
            if(string1.equalsIgnoreCase(string2)){
                similar++;
            }
            count++;
        }

        return similar/count *100;
    }
}

