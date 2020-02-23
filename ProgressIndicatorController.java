package sample;

import CodeBusterClasses.MassCompare;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ProgressIndicatorController {

    MassCompare compare;

    @FXML Label actionTaken;
    @FXML ProgressBar progressBar;

    public void InitializeProgress(String filepathDirectory){

        compare = new MassCompare(filepathDirectory);

        compare.ExecuteComparison();



    }

    public MassCompare getCompare() { return compare; }

}
