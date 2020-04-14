package CodeBusterClasses;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class GetCharactersFromProject {

    private ArrayList<File> filesInProj;
    private String filePathProject;

    public GetCharactersFromProject(String filePathProject){
       this.filePathProject = filePathProject;
    }

    public ArrayList<Character> getCharacters() throws IOException {

        ArrayList<Character> charProj = new ArrayList<>();
        GetFilesFromProject getterFiles = new GetFilesFromProject(filePathProject);
        filesInProj = getterFiles.getAcceptedFiles();

        GetCharacters getter;

        for(File f : filesInProj){
            getter = new GetCharacters(f.getAbsolutePath());
            charProj.addAll(getter.CharactersFromFile());
        }

        return charProj;
    }



}
