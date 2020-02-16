import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CompareFile{
    ArrayList<String> list1 = new ArrayList<>();
    ArrayList<String> list2 = new ArrayList<>();
    int count;
    int totalWords;
    float percentage;

    public static void main(String[] args) throws IOException {
        CompareFile cf = new CompareFile();
        System.out.println("Comparing two files...");
        cf.readFile("src\\file1.txt",cf.list1);
        cf.readFile("src\\file2.txt",cf.list2);
        cf.compareLists();
        System.out.println("Similarity Percentage: " + cf.getPercentage() + "%");
        System.out.println("Longest word in FILE 1: " + cf.getLongestWord(cf.list1));
        System.out.println("Longest word in FILE 2: " + cf.getLongestWord(cf.list2));
    }

    public void readFile(String filename, ArrayList<String> list) throws IOException {
        File file = new File(filename);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line;
        while((line = br.readLine()) != null){
            String[] words = line.split(" ");
            for(String word : words) {
                word = word.replaceAll("[^a-zA-Z0-9]", "");
                list.add(word.trim());
            }
        }
        br.close();
    }

    public void compareLists() {
        System.out.println("LIST 1 Size: " + list1.size());
        System.out.println("LIST 2 Size: " + list2.size());
        if(list1.size() > list2.size()) {
            ArrayList<String> found = new ArrayList<>();
            for(String word: list1) {
                if(list2.contains(word) && !found.contains(word)) {
                    count++;
                    found.add(word);
                }
            }
            totalWords = list2.size();
        }

        else {
            ArrayList<String> found = new ArrayList<>();
            for(String word : list2) {
                if(list1.contains(word) && !found.contains(word)) {
                    count++;
                    found.add(word);
                }
            }
            totalWords = list1.size();
        }

        System.out.println("WORD MATCH COUNT " + count);
        System.out.println("TOTAL WORDS " + totalWords);
        percentage = (float) count/totalWords * 100;
    }

    public float getPercentage() {
        if (percentage > 100) return 100;
        else return percentage;
    }

    public String getLongestWord(List<String> list) {
        String test = "";
        for(String word : list) {
            if(test.length() < word.length()) {
                test = word;
            }
        }
        return test;
    }
}
