package UnitTests;

import CodeBusterClasses.KGram;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class KGramTest {

    @Test
    void setStreamOfChar() {
    }

    @Test
    void returnKGram() {
        //creating a new arraylist of characters which contains "Jessica"

        ArrayList<Character> testCharacters = new ArrayList<>();
        testCharacters.add('J');
        testCharacters.add('e');
        testCharacters.add('s');
        testCharacters.add('s');
        testCharacters.add('i');
        testCharacters.add('c');
        testCharacters.add('a');

        KGram test = new KGram(testCharacters);

        ArrayList<String> results = test.ReturnProcessedKGram();

        assertEquals("Jessi", results.get(0));
        assertEquals("essic", results.get(1));
        assertEquals("ssica", results.get(2));
        System.out.println(results);

    }
}