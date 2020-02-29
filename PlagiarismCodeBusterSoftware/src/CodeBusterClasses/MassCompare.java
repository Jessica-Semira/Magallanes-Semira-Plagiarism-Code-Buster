package CodeBusterClasses;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

public class MassCompare {

    private final int sizeTop = 10;
    //this class will executed the similarity algorithm of the project;
    private PairScore[][] matrixSimilarity;
    private LinkedList<PairScore> topSimilarity;
    private File mainDirectory;
    private File[] manyFiles;//contains the different project files;



    public MassCompare(String filePathMainDirectory) {

        mainDirectory = new File(filePathMainDirectory);
        manyFiles = mainDirectory.listFiles();
        matrixSimilarity = new PairScore[manyFiles.length][manyFiles.length];
        topSimilarity = new LinkedList<>();

        initializeArray();
    }

    public PairScore[][] getSimilarityMatrix() {
        return matrixSimilarity;
    }

    public ArrayList<String> getFileNames() {
        ArrayList<String> fileNames = new ArrayList<>();

        for (File f : manyFiles) {
            fileNames.add(f.getName());
        }

        return fileNames;
    }

    public LinkedList<PairScore> getTopScores() {
        return topSimilarity;
    }

    public int sizeOfMatrix() {
        return manyFiles.length;
    }

    public void ExecuteComparison() {

        try {


            for (int i = 0; i < manyFiles.length; i++) {

                for (int j = 0; j < manyFiles.length; j++) {

                    if (matrixSimilarity[i][j].getScore() == -1) {
                        float score = new CompareClass(manyFiles[i].getAbsolutePath(), manyFiles[j].getAbsolutePath()).getSimilarityScore();

                        matrixSimilarity[i][j].setScore(score);
                        matrixSimilarity[j][i].setScore(score);

                        InTopScore(matrixSimilarity[i][j]);
                    }



                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void initializeArray() {

        //for the matrix
        for (int i = 0; i < matrixSimilarity.length; i++) {

            for (int j = 0; j < matrixSimilarity.length; j++) {

                matrixSimilarity[i][j] = new PairScore(-1, manyFiles[i].getName(), manyFiles[j].getName());

            }
        }

    }

    private void InTopScore(PairScore input) {

        if (topSimilarity.isEmpty() && input.getPair1Name().compareTo(input.getPair2Name()) != 0) {
            topSimilarity.add(input);
        } else {
            for (int i = 0; i < topSimilarity.size() && i < sizeTop; i++) {

                if (input.getScore() > topSimilarity.get(i).getScore() && input.getPair1Name().compareTo(input.getPair2Name()) != 0) {

                    topSimilarity.add(i, input);
                    break;

                } else if ((i + 1 == sizeTop || i + 1 == topSimilarity.size()) && input.getPair1Name().compareTo(input.getPair2Name()) != 0) {
                    topSimilarity.add(i + 1, input);
                }
            }

            if (topSimilarity.size() > sizeTop) {
                topSimilarity.removeLast();
            }
        }

    }


}
