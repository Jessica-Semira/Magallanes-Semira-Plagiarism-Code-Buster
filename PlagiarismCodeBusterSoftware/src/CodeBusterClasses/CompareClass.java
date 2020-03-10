package CodeBusterClasses;

import java.io.IOException;
import java.util.ArrayList;

public class CompareClass {

    //this class is now the integration of the classes FingerPrint, GetCharacters, HashKgram, and K-Gram
    //input: filepath of the two files to be compared (as a string)
    //Output: similarity score (out of 100)

    String filepath1;
    String filepath2;
    float SimilarityScore;


    public CompareClass(String filepath1, String filepath2) {
        this.filepath1 = filepath1;
        this.filepath2 = filepath2;
        CompareTwoFiles();
    }

    private void CompareTwoFiles() {

        //this is for the first file
        GetCharactersFromProject prog1Char;
        KGram prog1KGram;
        HashKGram prog1HashKGram;
        FingerPrint prog1FingerPrint;
        ArrayList<Integer> fingerprintProg1 = null;

        GetCharactersFromProject prog2Char;
        KGram prog2KGram;
        HashKGram prog2HashKGram;
        FingerPrint prog2FingerPrint;
        ArrayList<Integer> fingerprintProg2 = null;

        //for the first file
        try {
            prog1Char = new GetCharactersFromProject(filepath1);
            prog1KGram = new KGram(prog1Char.getCharacters());
            prog1HashKGram = new HashKGram(prog1KGram.ReturnProcessedKGram());
            prog1FingerPrint = new FingerPrint(prog1HashKGram.ReturnProcessedHashes());
            fingerprintProg1 = prog1FingerPrint.ReturnFingerPrint();

        } catch (IOException e) {
            e.printStackTrace();
        }

        //this is for the second file
        try {
            prog2Char = new GetCharactersFromProject(filepath2);
            prog2KGram = new KGram(prog2Char.getCharacters());
            prog2HashKGram = new HashKGram(prog2KGram.ReturnProcessedKGram());
            prog2FingerPrint = new FingerPrint(prog2HashKGram.ReturnProcessedHashes());
            fingerprintProg2 = prog2FingerPrint.ReturnFingerPrint();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        //this is for jaccard index. Jaccard index will be the basis of similarity
        float numSimilarity = 0;
        boolean isSimilarEncounter = false;
        int sizeFingerprint1 = fingerprintProg1.size();
        int sizeFingerprint2 = fingerprintProg2.size();

        for (Integer integer : fingerprintProg1) {

            for (Integer value : fingerprintProg2) {


                if ((integer.intValue() == value.intValue()) && !isSimilarEncounter) {
                    numSimilarity++;
                    isSimilarEncounter = true;
                }

            }


            isSimilarEncounter = false;
        }

        SimilarityScore = (numSimilarity / (sizeFingerprint1 + sizeFingerprint2 - numSimilarity));
    }

    public float getSimilarityScore () {

        return SimilarityScore;
    }
}
