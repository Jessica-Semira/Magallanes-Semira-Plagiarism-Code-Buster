package sample;

import CodeBusterClasses.GetFilesFromProject;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.shape.Rectangle;
import javafx.stage.DirectoryChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Controller {

    @FXML Label folderNameLabel;
    @FXML Label filepathFolderLabel;
    @FXML Label indicator;

    @FXML Button runTestBtn;
    @FXML Button exitBtn;

    @FXML Rectangle input;

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

        new Thread(() -> {

            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    checkIfCorrectInput(files);
                }
            });
        }).start();

    }

    @FXML
    public void exit (ActionEvent e){
        ((Node)e.getTarget()).getScene().getWindow().hide();
    }

    @FXML
    public void getFileFolder(ActionEvent e){

        DirectoryChooser directoryChooser = new DirectoryChooser();

        File folder = directoryChooser.showDialog(((Node)e.getTarget()).getScene().getWindow());

        List<File> files = new ArrayList<>();
        files.add(folder);

        if(folder == null){

        }
        else{
            checkIfCorrectInput(files);
        }

    }

    @FXML
    public void runTestBtnAction (ActionEvent e){

        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("TestResults.fxml"));
            Stage testResults= new Stage();
            testResults.setMaximized(true);
            Scene scene = new Scene(fxmlLoader.load(), testResults.getWidth(), testResults.getHeight());
            scene.getStylesheets().add("CSS/test_window.css");
            testResults.setScene(scene);
            testResults.setTitle("Test Results of the Similarity Software");
            testResults.initModality(Modality.NONE);
            testResults.toFront();


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

    private boolean checkIfCorrectInput(List<File> files){

        //this function will check if the input is correct.
        if(files.size()>1){
            Alert alert = new Alert(Alert.AlertType.INFORMATION,"Please only input one folder file");
            alert.getDialogPane().getStylesheets().add("CSS/notification.css");
            alert.showAndWait();
            return false;
        }

        else if (files.get(0).isDirectory()){
            File file = files.get(0);
            int i =0;
            if (file.listFiles() == null || file.listFiles().length == 0){
                Alert alert = new Alert(Alert.AlertType.INFORMATION,"The inputted folder doesn't contain a project");
                alert.getDialogPane().getStylesheets().add("CSS/notification.css");
                alert.showAndWait();

                return false;
            }
            else{

                for (File f:file.listFiles()) {
                    GetFilesFromProject isFiles = new GetFilesFromProject(f.getAbsolutePath());
                    if(!isFiles.getAcceptedFiles().isEmpty()) i++;
                }

            }

            if(i < 2) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION,"There is no suffiecient projects to compare");
                alert.getDialogPane().getStylesheets().add("CSS/notification.css");
                alert.showAndWait();
                return false;
            }else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION,"There are " + i + " projects in the folder");
                alert.getDialogPane().getStylesheets().add("CSS/notification.css");
                alert.showAndWait();
                setLabels(files.get(0).getName(),files.get(0).getAbsolutePath());
                return true;
            }

        }
        else{
            Alert alert =  new Alert(Alert.AlertType.INFORMATION,"Please input a folder which contains the programs to be compared");
            alert.getDialogPane().getStylesheets().add("CSS/notification.css");
            alert.showAndWait();
            return false;
        }
    }


}
