package UnitTests;

import CodeBusterClasses.FingerPrint;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FingerPrintTest {

    @Test
    void findMin() {

        int[] test = new int [] {18,13,6,23,15};
        FingerPrint testPrint = new FingerPrint();

        assertEquals(6, testPrint.findMin(test));
    }

    @Test
    void findMinIndex() {
        int[] test = new int [] {18,13,6,23,15};
        FingerPrint testPrint = new FingerPrint();

        assertEquals(2, testPrint.findMinIndex(test));
    }
}