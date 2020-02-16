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


    public CompareClass(String filepath1, String filepath2) throws IOException{
        this.filepath1 = filepath1;
        this.filepath2 = filepath2;
        CompareTwoFiles();
    }

    private void CompareTwoFiles() throws IOException {
        //this is for the first file
        GetCharacters prog1Char = new GetCharacters(filepath1);
        KGram prog1KGram = new KGram(prog1Char.CharactersFromFile());
        HashKGram prog1HashKGram = new HashKGram(prog1KGram.ReturnProcessedKGram());
        FingerPrint prog1FingerPrint = new FingerPrint(prog1HashKGram.ReturnProcessedHashes());
        ArrayList<Integer> fingerprintProg1= prog1FingerPrint.ReturnFingerPrint();

        //this is for the second file
        GetCharacters prog2Char = new GetCharacters(filepath2);
        KGram prog2KGram = new KGram(prog2Char.CharactersFromFile());
        HashKGram prog2HashKGram = new HashKGram(prog2KGram.ReturnProcessedKGram());
        FingerPrint prog2FingerPrint = new FingerPrint(prog2HashKGram.ReturnProcessedHashes());
        ArrayList<Integer> fingerprintProg2= prog2FingerPrint.ReturnFingerPrint();

        //this is for jaccard index. Jaccard index will be the basis of similarity
        float numSimilarity = 0;
        int sizeFingerprint1 = fingerprintProg1.size();
        int sizeFingerprint2 = fingerprintProg2.size();

        for (Integer integer : fingerprintProg1) {

            for (Integer value : fingerprintProg2) {


                if ((integer.intValue() == value.intValue())) {
                    numSimilarity++;
                }

            }


        }

        SimilarityScore = (numSimilarity / (sizeFingerprint1 + sizeFingerprint2 - numSimilarity) )  ;

    }

    public float getSimilarityScore() {
        return SimilarityScore;
    }

}
