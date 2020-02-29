package CodeBusterClasses;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class HalsteadMetrics {

    private String filepathOfProject;

    private final String[] operators = {"++","--","*","/","%","+","-",">>","<<",">>>",">","<","<=",">="
                                        , "instane of", "==","!=","^","&&","||","?",":","=","+=","-=","%=",
                                            "^=","(","{","[","if","else","else if","while","do","return","new","class"
                                            , "public","private","protected","int","float","double","bool","char","long","String"
                                        };
    private int[] numOperators;
    private Set<String> uniqueOperands;
    private ArrayList<String> stringFromProject;
    private int numTotalOperand = 0;

    public HalsteadMetrics(String filepathOfProject){
        numOperators= new int[operators.length];
        uniqueOperands = new HashSet<>();
        this.filepathOfProject = filepathOfProject;
        GetStringFromProject getStringFromProject = new GetStringFromProject(filepathOfProject);
        stringFromProject = getStringFromProject.getStrings();

        initializeNumOperators();
        countOperators();
    }

    public Integer getProgramLength() {return getTotalNumOperands() + getTotalNumOperators(); }

    public Integer getProgramVocabulary() {return  getNumUniqueOperands() + getNumUniqueOperators();}

    public Float getProgramDifficulty(){return (float)((getNumUniqueOperators()/2) *(getTotalNumOperands()/getNumUniqueOperands()));}

    public Integer getNumUniqueOperators(){
        int sum = 0;
        for(int i = 0 ; i<numOperators.length; i++){
            if(numOperators[i] !=0) sum++;
        }
        return sum;
    }

    public Integer getTotalNumOperators(){
        int sum = 0;
        for(int i = 0 ; i<numOperators.length; i++){
            if(numOperators[i] !=0) sum+= numOperators[i];
        }
        return sum;
    }

    public Integer getNumUniqueOperands(){
        return uniqueOperands.size();
    }

    public Integer getTotalNumOperands(){
        return stringFromProject.size();
    }

    private void initializeNumOperators(){
        for (int i = 0; i < numOperators.length ; i++) {
            numOperators[i] = 0;
        }
    }

    private void countOperators(){

        for(String s : stringFromProject){

            for (int i = 0 ; i < operators.length; i++) {
                if(s.contains(operators[i])){
                    numOperators[i] ++;
                }
                else{
                    uniqueOperands.add(s);
                }

            }
        }
    }


}
