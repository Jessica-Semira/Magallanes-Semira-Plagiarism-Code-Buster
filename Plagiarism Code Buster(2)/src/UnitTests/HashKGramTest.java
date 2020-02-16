package UnitTests;

import CodeBusterClasses.HashKGram;
import CodeBusterClasses.KGram;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class HashKGramTest {

    @Test
    void hashKGrams() {
        //it will test for the hashing roll function

        ArrayList<Character> testCharacters = new ArrayList<>();
        testCharacters.add('J');
        testCharacters.add('e');
        testCharacters.add('s');
        testCharacters.add('s');
        testCharacters.add('i');
        testCharacters.add('c');
        testCharacters.add('a');

        KGram testKGram = new KGram(testCharacters);

        HashKGram testHash = new HashKGram(testKGram.ReturnProcessedKGram());

        assertEquals(2787, testHash.getHash(0));
        assertEquals(3305, testHash.getHash(1));
        assertEquals(3475, testHash.getHash(2));

    }
}