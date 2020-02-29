package CodeBusterClasses;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class GetStringFromProject {

    private ArrayList<File> filesInProj;
    private String filePathProject;

    public GetStringFromProject(String filePathProject){
        this.filePathProject = filePathProject;
    }

    private ArrayList<String> getString(String filepath){

        ArrayList<String> tempStorage = new ArrayList<>();

       try{
           StringBuilder stringBuilder = new StringBuilder();

           BufferedReader bufferedReader = new BufferedReader(new FileReader(filepath));

           int character = bufferedReader.read();
           Boolean isString = true;

           while(character != -1){

               if(character != 32 && character != 13 && character != 10 ){
                   isString= true;
                   stringBuilder.append((char)character);
               }

               if((character == 32 || character == 13 || character == 10) && isString){
                   isString = false;
                   tempStorage.add(stringBuilder.toString());
                   stringBuilder = new StringBuilder();
               }

               character = bufferedReader.read();
           }

       }catch (IOException e){
           e.printStackTrace();
       }

       return tempStorage;
    }

    public ArrayList<String> getStrings(){

        ArrayList<String> charProj = new ArrayList<>();
        GetFilesFromProject getterFiles = new GetFilesFromProject(filePathProject);
        filesInProj = getterFiles.getAcceptedFiles();

        for(File f : filesInProj){
            charProj.addAll(getString(f.getAbsolutePath()));
        }

        return charProj;
    }

}
