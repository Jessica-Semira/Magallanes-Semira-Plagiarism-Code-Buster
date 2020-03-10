package CodeBusterClasses;

import java.io.File;
import java.util.ArrayList;

public class GetFilesFromProject {

    private File directory;//file object
    private ArrayList<File> prelimFiles;
    private ArrayList<File> acceptedFiles;

    public GetFilesFromProject(String filepathProject){
        directory = new File(filepathProject);
        prelimFiles = new ArrayList<>();
        acceptedFiles = new ArrayList<>();
    }

    /*
    public boolean isProject(){
        if(directory.isDirectory()){
            return findAProjectFile(directory);
        }
        else{
            return isFileExtensionAccept(directory.getName());
        }

    }*/

    private boolean findAProjectFile(File folder) {
        //this is will add file in the prelimFiles

        for ( File fileEntry : folder.listFiles()) {

            if (fileEntry.isDirectory()) {
                return findAProjectFile(fileEntry);
            } else {
                System.out.println(fileEntry.getName());
                return isFileExtensionAccept(fileEntry.getName());
            }

        }

        return false;
    }


    private void listFilesForFolder(File folder) {
        //this is will add file in the prelimFiles

        for ( File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                listFilesForFolder(fileEntry);
            } else {
                prelimFiles.add(fileEntry);
            }
        }
    }

    public ArrayList<File> getAcceptedFiles(){

        if(directory.isDirectory()){
            listFilesForFolder(directory);
            for (File f: prelimFiles) {

                if(isFileExtensionAccept(f.getName())){
                    acceptedFiles.add(f);
                }
            }
        }
        else{
            if(isFileExtensionAccept(directory.getName())) acceptedFiles.add(directory);
        }

        return acceptedFiles;
    }

    private boolean isFileExtensionAccept(String filename){

        return (filename.endsWith(".cpp") || filename.endsWith(".cc") || filename.endsWith(".c") ||
                filename.endsWith(".h") || filename.endsWith(".java")) && (!filename.endsWith("Test.java"));

    }


}
