package ScratchWork;

import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class CharGetter {

    FileReader file;

    LinkedList<Character> characters;

    //this class is for getting the characters from the file program
    //it shall exclude any spaces, nextline and variable name

    CharGetter(String filename) throws IOException {
        file = new FileReader(filename);
    }


    void ReturnFilteredCharacters () throws IOException{
        int tempInt = 0;
        int sum =0;

        while(tempInt != -1){
            tempInt = file.read();

            if(tempInt != 32 && tempInt != 13 && tempInt != 10) sum += tempInt;
        }
    }

}
