package CodeBusterClasses;

import java.util.ArrayList;

public class FingerPrint {

    //this class will get hashes of the inputted so that it will determine the
    //fingerprint of the sourcode for the comparison
    // an implementation of winnowing alogrithm

    private ArrayList<Integer> HashesKGram;
    private ArrayList<Integer> fingerPrint;
    private final int windowSize = 12;
    private final int p =50;

    public FingerPrint(){

    }

    public FingerPrint(ArrayList<Integer> HashesKGram){
        this.HashesKGram = HashesKGram;
        FindFingerPrints();
    }

    public ArrayList<Integer> ReturnFingerPrint(){return fingerPrint;}

    private void FindFingerPrints(){
        int tempWindow[];

        int location = -1;//it will save the index of the minimum hash of the current window

        int minLocation = -1; // it will save the indexx of the last selected fingerprint

        fingerPrint = new ArrayList<>();

        for (int i = 0; i < HashesKGram.size() - windowSize; i++) {

            tempWindow = new int[windowSize];

            for(int j =0 ; j<windowSize; j++){
                //this willl build the window
                tempWindow[j] = HashesKGram.get(i+j);
            }

            //it will get the index of the minimum hash based on the formed window
            location = i + findMinIndex(tempWindow);

            if(location != minLocation){
                //it will check if the minmum hash is not selected
                fingerPrint.add(findMin(tempWindow));
                minLocation = location;
            }

        }

    }

    public Integer findMin(int[] arr){
        int tempMin = 1000000;

        for (int i = 0; i < arr.length; i++) {
            if(tempMin > arr[i]){
                tempMin = arr[i];
            }
        }

        return tempMin;
    }

    public Integer findMinIndex(int[] arr){
        int tempMin = 10000;
        int index = -1;

        for (int i = 0; i < arr.length; i++) {
            if(tempMin > arr[i]){
                tempMin = arr[i];
                index = i;
            }
        }

        return index;
    }

}
