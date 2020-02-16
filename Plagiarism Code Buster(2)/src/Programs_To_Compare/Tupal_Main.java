import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public  class Main {

    static ArrayList<String> codeList1= new ArrayList<>(), codeList2 = new ArrayList<>();

    static void getFileToList(String pathName,ArrayList codeList){

         File file = new File(pathName);//open file
         BufferedReader br;
         try {

             br = new BufferedReader(new FileReader(file));
             String line;
             while ((line = br.readLine()) != null) {//iterates through the file until endfile
                 ArrayList<String> lineList = new ArrayList<>(Arrays.asList(line.split("[ \\t\\n]+")));
                 codeList.addAll(lineList);
             }

         } catch (IOException e) {
             e.printStackTrace();
         }
     }

    static void getSimilarity(){
        int similarOccurence=0;
        int i;
        for(i = 0;i<codeList1.size()&&i<codeList2.size();i++){
            if(codeList1.get(i).equals(codeList2.get(i) )){
                similarOccurence++;
            }
        }
        System.out.println(" Similarity between two files are: "+(float)similarOccurence/i);
    }


    public static void main(String[] args){
        System.out.println("For testProgram1.java and testProgram2.java");
        getFileToList("Document//test1.java",codeList1);
        getFileToList("Document//test2.java",codeList2);
        getSimilarity();

    }

}
