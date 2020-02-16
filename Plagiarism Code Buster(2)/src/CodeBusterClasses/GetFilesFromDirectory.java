package CodeBusterClasses;

import java.io.File;
import java.util.ArrayList;

public class GetFilesFromDirectory {

    private String filepathDirectory;
    private File directory;
    private ArrayList<File> prelimFiles;
    private ArrayList<File> acceptedFiles;

    public GetFilesFromDirectory(String filepathDirectory){
        this.filepathDirectory = filepathDirectory;
        directory = new File(filepathDirectory);
        prelimFiles = new ArrayList<>();
        acceptedFiles = new ArrayList<>();
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

    public ArrayList<File> getAccpetedFiles(){

        listFilesForFolder(directory);


        for (File f: prelimFiles) {

            if(isFileExtensionAccept(f.getName())){
                acceptedFiles.add(f);
            }
        }

        return acceptedFiles;
    }

    private boolean isFileExtensionAccept(String filename){

        return (filename.endsWith(".cpp") || filename.endsWith(".cc") || filename.endsWith(".c") ||
                filename.endsWith(".h") || filename.endsWith(".java")) && (!filename.endsWith("Test.java"));

    }

}
