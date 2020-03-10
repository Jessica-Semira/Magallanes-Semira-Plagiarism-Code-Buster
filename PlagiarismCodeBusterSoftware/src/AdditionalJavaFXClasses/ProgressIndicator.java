package AdditionalJavaFXClasses;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ProgressIndicator  {

    ProgressBar progressBar;
    Label actionTaken;
    Stage stage;

    public ProgressIndicator(){

        initializeProgressBar(" ");
    }
    public ProgressIndicator (String initialActionIndicator){
        initializeProgressBar(initialActionIndicator);
    }

    public void setProgress(double progress){
        progressBar.setProgress(progress);
    }

    public void setActionIndicator (String actionIndicator){
        actionTaken.setText(actionIndicator);
    }

    public void show(){stage.show();}

    public void close(){stage.hide();}

    private void initializeProgressBar(String initialActionIndicator){

        progressBar = new ProgressBar();
        progressBar.setPrefWidth(300);

        actionTaken = new Label(initialActionIndicator);

        VBox vBox = new VBox(20,actionTaken,progressBar);
        vBox.setAlignment(Pos.CENTER);

        Scene scene = new Scene( vBox,400,100);
        scene.getStylesheets().add("CSS/progress_bar.css");
        Platform.runLater(() ->{
            stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Progress Status");
            stage.setAlwaysOnTop(true);
            show();
        });

    }
}
