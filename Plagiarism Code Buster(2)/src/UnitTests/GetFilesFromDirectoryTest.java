package UnitTests;

import CodeBusterClasses.GetFilesFromDirectory;
import org.junit.jupiter.api.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

class GetFilesFromDirectoryTest {

    @Test
    void getAccpetedFiles() {

        GetFilesFromDirectory test = new GetFilesFromDirectory("src/UnitTests");
        assertTrue(test.getAccpetedFiles().isEmpty());

        test = new GetFilesFromDirectory("src/Test_Programs");
        assertEquals(4,test.getAccpetedFiles().size());
        assertEquals("Test1_program1Java.java", test.getAccpetedFiles().get(0).getName());

    }
}