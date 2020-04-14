package CodeBusterClasses;

import java.util.ArrayList;

public class HashKGram {
    //this is for hashing of the k-grams

    private ArrayList<String> kGrams;
    private ArrayList<Integer> hashKGrams;
    private final int prime = 2;// this is the b in the formula

    public HashKGram(ArrayList<String> kGrams){
        this.kGrams = kGrams;
        ProcessHashKGrams();
    }


    private Integer HashOneString(String string){

        int k = string.length();
        int sumComputed = 0;

        for (int i = 0; i < string.length() ; i++) {
            k --;
            sumComputed += string.charAt(i) * Math.pow(prime,k);


        }

        return sumComputed;
    }

    private void  ProcessHashKGrams(){
        hashKGrams = new ArrayList<>();

        int k = kGrams.get(0).length();

        hashKGrams.add(HashOneString(kGrams.get(0)));

        for (int i = 1; i < kGrams.size(); i++) {
            hashKGrams.add( (int)((hashKGrams.get(i-1)- (kGrams.get(i-1).charAt(0) * Math.pow(prime,k-1))) * prime + kGrams.get(i).charAt(k-1)) );
        }

    }

    public ArrayList<Integer> ReturnProcessedHashes(){ return hashKGrams;}

    public Integer getHash(int index){
        return hashKGrams.get(index);
    }

}
