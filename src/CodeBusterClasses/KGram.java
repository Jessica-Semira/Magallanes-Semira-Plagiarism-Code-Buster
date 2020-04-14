package CodeBusterClasses;

import java.util.ArrayList;

public class KGram {

    //this function will produce k-grams which each substring having length 7
    private  ArrayList<String> kGrams;
    private ArrayList<Character> streamOfChar;
    private final int kLength = 7;

    public KGram(ArrayList<Character> input){
        streamOfChar = input;
        ProcessKGram();
    }

    public void setStreamOfChar(ArrayList<Character> streamOfChar) {
        this.streamOfChar = streamOfChar;
    }

    public ArrayList<String> ReturnProcessedKGram(){
        return kGrams;
    }

    private void ProcessKGram(){
        kGrams = new ArrayList<>();
        StringBuilder tempString;

        for(int i=0; i<=streamOfChar.size() - kLength; i++){
            tempString = new StringBuilder();

            for(int j = 0 ; j<kLength; j++){
                tempString.append(streamOfChar.get(i+j));
            }

            kGrams.add(tempString.toString());
        }
    }

}
