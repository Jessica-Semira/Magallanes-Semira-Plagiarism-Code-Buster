import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Module0Vasquez {
        public static void main(String[] args) throws IOException {
            boolean areEqual = true;
            int lineCounter=0, sameLine=0, simScore=0;
            BufferedReader text1 = new BufferedReader(new FileReader(“/home/ece/IdeaProjects/HelloWorld/assets/test_program1.cpp”));
            BufferedReader text2 = new BufferedReader(new FileReader(“/home/ece/IdeaProjects/HelloWorld/assets/test_program2.cpp”));
            String line1 = text1.readLine();
            String line2 = text2.readLine();

            while(line1 != null || line2 != null){
                if(line1 == null || line2 == null)
                {
                    areEqual = false;
                    break;
                }
                /**
                else if(! line1.equalsIgnoreCase(line2))
                {
                    areEqual = false;
                    break;
                }**/

                else if(line1.equalsIgnoreCase(line2))
                {
                    sameLine++;
                }
                line1 = text1.readLine();
                line2 = text2.readLine();
                lineCounter++;
            }

            simScore = (sameLine/lineCounter)*100;
            if(areEqual){
                System.out.println("The Similarity Score of the two programs are: "+simScore+"%");
            }
            else{
                System.out.println("The Similarity Score of the two programs are: 0%");
            }
        }
}
