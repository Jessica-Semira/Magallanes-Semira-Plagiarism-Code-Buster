/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package module.pkg0;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Rough Java MOSS Program
 * Functionalities:
 * Compares Two Java and C++ Programs Similarity by Comparing the Lines
 * @Theodore Benito C. Coteok II
 * START - 01/21/2020 - 1009
 * END - 01/21/2020 - 1145
 */
public class TeddyMoss {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        //initializing counters
        int stringCountJ1 = 0;
        int stringCountJ2 = 0;
        int stringCountC1 = 0;
        int stringCountC2 = 0;
        
        int pRawScore = 0;
        int pRawScoreC = 0;
        
        int lineCount = 0;
        int lineCountC = 0;
        
        double pGrade = 0;
        double pGradeC = 0;
        //opening files
        File fileJ1 = new File("C:\\Users\\ECE\\Documents\\NetBeansProjects\\Module 0\\src\\module\\pkg0\\HelloAgain.java");
        File fileJ2 = new File("C:\\Users\\ECE\\Documents\\NetBeansProjects\\Module0Pair2\\src\\module0pair2\\HelloAgain.java");
        File fileC1 = new File("C:\\Users\\ECE\\Desktop\\Module 0\\test_program1.cpp");
        File fileC2 = new File("C:\\Users\\ECE\\Desktop\\Module 0\\test_program2.cpp");
        
        FileInputStream fileStreamJ1 = new FileInputStream(fileJ1);
        FileInputStream fileStreamJ2 = new FileInputStream(fileJ2);
        FileInputStream fileStreamC1 = new FileInputStream(fileC1);
        FileInputStream fileStreamC2 = new FileInputStream(fileC2);
        
        InputStreamReader inputJ1 = new InputStreamReader(fileStreamJ1); 
        InputStreamReader inputJ2 = new InputStreamReader(fileStreamJ2);
        InputStreamReader inputC1 = new InputStreamReader(fileStreamC1); 
        InputStreamReader inputC2 = new InputStreamReader(fileStreamC2);
        
        BufferedReader readerJ1 = new BufferedReader(inputJ1); 
        BufferedReader readerJ2 = new BufferedReader(inputJ2);
        BufferedReader readerC1 = new BufferedReader(inputC1); 
        BufferedReader readerC2 = new BufferedReader(inputC2); 
        
        String lineJ1;
        String lineJ2;
        String lineC1;
        String lineC2;
        
        String[] charsJ1;
        String[] charsJ2;
        String[] charsC1;
        String[] charsC2;
        //compare and count similar words
        while(((lineJ1 = readerJ1.readLine()) != null) && ((lineJ2 = readerJ2.readLine()) != null)){
            if(!(lineJ1.equals(""))){
                charsJ1 = lineJ1.split("\\s+");
                stringCountJ1 += charsJ1.length;
            }
            if(!(lineJ2.equals(""))){
                charsJ2 = lineJ2.split("\\s+");
                stringCountJ2 += charsJ2.length;
            }
            if(lineJ1.equals(lineJ2)) pRawScore++;
            lineCount++;
            //if(checkEquality(charsJ1, charsJ2)) pRawScore++;
        }
        //score computation
        pGrade = (Double.valueOf(pRawScore)/Double.valueOf(lineCount))*100;
        
        //sa C naman
        //compare and count similar words
        while(((lineC1 = readerC1.readLine()) != null) && ((lineC2 = readerC2.readLine()) != null)){
            if(!(lineC1.equals(""))){
                charsC1 = lineC1.split("\\s+");
                stringCountC1 += charsC1.length;
            }
            if(!(lineC2.equals(""))){
                charsC2 = lineC2.split("\\s+");
                stringCountC2 += charsC2.length;
            }
            if(lineC1.equals(lineC2)) pRawScoreC++;
            lineCountC++;
        }
        //score computation
        pGradeC = (Double.valueOf(pRawScoreC)/Double.valueOf(lineCountC))*100;
        
        //display results
        System.out.println("Number of words in Java File 1: " + stringCountJ1);
        System.out.println("Number of words in Java File 2: " + stringCountJ2);
        //System.out.println("Number of similar words: " + pRawScore);
        System.out.println("Number of similar lines in the two Java Files: " + pRawScore);
        System.out.println("Java Program Similarity Score: " + pGrade);
        if(pGrade >= 50) System.out.println("The two Java Programs are similar!");
        else System.out.println("The two Java Programs are not similar");
        System.out.println();
        System.out.println("Number of words in C File 1: " + stringCountC1);
        System.out.println("Number of words in C File 2: " + stringCountC2);
        System.out.println("Number of similar lines in the two C Files: " + pRawScoreC);
        System.out.println("C Program Similarity Score: " + pGradeC);
        if(pGrade >= 50) System.out.println("The two C Programs are similar!");
        else System.out.println("The two C Programs are not similar");
    }
}