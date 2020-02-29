package CodeBusterClasses;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class GetCharacters {

    private FileReader file;
    private String filepath;
    private ArrayList<Character> charactersFromFile;

    public GetCharacters(String filepathProject) throws IOException {
        file = new FileReader(filepathProject);
        this.filepath = filepathProject;
    }

    public void reset() throws IOException{
        //this will reset the reader of the file
        file = new FileReader(filepath);
    }

    public ArrayList<Character> CharactersFromFile() throws IOException{
       //this function will return an array of characters which is contained in the file
        //this function will exclude characters such as spaces, tabs and next line

        int tempInt = 0;
        charactersFromFile = new ArrayList<>();
        while(tempInt != -1){

            tempInt = file.read();

            if(tempInt != 32 && tempInt != 13 && tempInt != 10) charactersFromFile.add((char)tempInt);
        }
        reset();
        return charactersFromFile;
    }

    public Integer TotalCharacterFile () throws IOException{
        //this function will return the sum of the ascii values in the text file
        //this function will exclude characters such as spaces, tabs and next line

        int tempInt = 0;
        int sum = 0;

        while(tempInt != -1){

            tempInt = file.read();

            if(tempInt != 32 && tempInt != 13 && tempInt != 10) sum += tempInt;
        }
        reset();
        return sum;
    }

    public void setFilepath(String filepath) throws IOException {
        this.filepath = filepath;
        reset();
    }

    public String getFilepath() {
        return filepath;
    }

}
