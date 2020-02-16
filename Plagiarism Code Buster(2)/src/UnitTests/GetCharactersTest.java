package UnitTests;

import CodeBusterClasses.GetCharacters;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GetCharactersTest {

    @org.junit.jupiter.api.Test
    void charactersFromFile() throws IOException {
        GetCharacters test = new GetCharacters("src/Test_Programs/Test2_program2.java");
        ArrayList<Character> testStore = test.CharactersFromFile();
        assertEquals('c',testStore.get(21));
        assertEquals('s',testStore.get(25));
    }

    @org.junit.jupiter.api.Test
    void totalCharacterFile() {
    }
}