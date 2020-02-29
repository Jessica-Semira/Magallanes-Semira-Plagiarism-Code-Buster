package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Controller {

    @FXML Label folderNameLabel;
    @FXML Label filepathFolderLabel;
    @FXML Label indicator;

    @FXML Button runTestBtn;
    @FXML Button exitBtn;

    @FXML
    ProgressBar pb;

    String folderName;
    String filepathFolder;

    @FXML
    void initialize(){
        runTestBtn.setDisable(true);
    }

    @FXML
    public void dragOverFunction (DragEvent ed){
        if(ed.getDragboard().hasFiles()){
            ed.acceptTransferModes(TransferMode.ANY);
        }
    }

    @FXML
    public void DragOverApllication (DragEvent ed){((Stage)((Node)ed.getTarget()).getScene().getWindow()).toFront();}

    @FXML
    public void dragDropFunction (DragEvent ed){
        List<File> files = ed.getDragboard().getFiles();

        if(files.size()>1){
            new Alert(Alert.AlertType.INFORMATION,"Please only input one folder file").showAndWait();
        }

        else if (files.get(0).isDirectory()){

            setLabels(files.get(0).getName(),files.get(0).getAbsolutePath());
        }
        else{
            new Alert(Alert.AlertType.INFORMATION,"Please input a folder which contains the programs to be compared").showAndWait();
        }


    }

    @FXML
    public void exit (ActionEvent e){
        ((Node)e.getTarget()).getScene().getWindow().hide();
    }

    @FXML
    public void getFileFolder(ActionEvent e){

        DirectoryChooser directoryChooser = new DirectoryChooser();

        File folder = directoryChooser.showDialog(((Node)e.getTarget()).getScene().getWindow());

        if(folder == null){

        }
        else{
            setLabels(folder.getName(),folder.getAbsolutePath());
        }

    }

    @FXML
    public void runTestBtnAction (ActionEvent e){

        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("TestResults.fxml"));
            Stage testResults= new Stage();
            testResults.setScene(new Scene(fxmlLoader.load(), 800,600));
            testResults.setTitle("Test Results of the Similarity Software");
            testResults.initModality(Modality.NONE);

            testResults.show();
            TestResultsController testResultsController = fxmlLoader.getController();
            testResultsController.InitializeTestResultWindow(filepathFolder);

            ((Node)e.getTarget()).getScene().getWindow().hide();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public String getFilepathFolder(){return filepathFolder;}

    private void setLabels(String filename, String filepath){
        folderName = filename;
        filepathFolder = filepath;

        folderNameLabel.setText(folderName);
        filepathFolderLabel.setText(filepathFolder);
        indicator.setText("Drag and Drop the Folder to Overwrite the Input");

        runTestBtn.setDisable(false);
    }

}
