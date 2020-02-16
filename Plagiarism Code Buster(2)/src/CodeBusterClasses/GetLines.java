package CodeBusterClasses;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class GetLines {

    private BufferedReader progLines;

    public GetLines(String filepath) throws IOException {
        progLines= new BufferedReader(new FileReader(filepath));
    }

    public Integer ReturnNumLines() throws IOException{
        //this function will return the number of lines in the given program file

        int line = 0 ;

        while(progLines.readLine() != null) line++;
        return line;
    }
}
