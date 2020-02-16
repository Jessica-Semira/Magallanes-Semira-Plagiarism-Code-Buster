package ScratchWork;

import CodeBusterClasses.CompareClass;
import CodeBusterClasses.GetCharacters;

import java.io.IOException;

public class TestClass {


    public static void main(String[] args)  throws IOException {
        // TODO code application logic here
        System.out.println(new CompareClass("src/Test_Programs/Test1_program1Java.java", "src/Test_Programs/Test2_program2.java").getSimilarityScore());
    }
}
