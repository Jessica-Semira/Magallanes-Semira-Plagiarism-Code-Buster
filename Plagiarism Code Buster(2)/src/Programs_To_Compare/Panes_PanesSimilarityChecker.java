import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;

public class SimilarityChecker {
    public static void main (String args[]) throws Exception{

        String text1 = "";
        String text2 = "";
        text1 = new String(Files.readAllBytes(Paths.get("src/text/test1")));
        System.out.println(text1);
        text1=text1.replaceAll("\\s+","-");
        String[] test1 = text1.split("-");

        text2 = new String(Files.readAllBytes(Paths.get("src/text/test2")));
        System.out.println(text2);
        text2=text2.replaceAll("\\s+","-");
        String[] test2 = text2.split("-");

        double difference=0;
        for(int i=0;i<test1.length;i++){
            if(test1[i].compareTo(test2[i])==0)difference++;
        }
        DecimalFormat df = new DecimalFormat("###.###");
        double percentage = (difference/test1.length)*100;
        System.out.println("Similarities = "+df.format(percentage)+"%");


    }
}
