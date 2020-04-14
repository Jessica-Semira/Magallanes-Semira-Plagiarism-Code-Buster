package sample;

import AdditionalJavaFXClasses.HalsteadObject;
import AdditionalJavaFXClasses.ProgressIndicator;
import AdditionalJavaFXClasses.ProjectFiles;
import CodeBusterClasses.MassCompare;
import CodeBusterClasses.MassHalsteadTest;
import CodeBusterClasses.PairScore;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Optional;

public class TestResultsController extends Thread {

    @FXML AnchorPane halsteadMetricsPane;
    @FXML AnchorPane topScorePane;
    @FXML AnchorPane filesScannedPane;

    @FXML Button saveBtn;
    @FXML Button rerunTestBtn;
    @FXML Button exitBtn;

    @FXML TableView<ProjectFiles> projectTestedTable;
    @FXML TableView<PairScore> topScoresTable;
    @FXML TableView<HalsteadObject> halsteadTestResults;

    @FXML  ScrollPane correlationMatrixAnchrPn;
    private GridPane correlationMatrix;

    private final int sizeColumnSpanName = 400;

    private String filepathDirectory;
    private PairScore[][] scores;
    private ArrayList<String> fileNames;
    private LinkedList<PairScore> topScores;
    private ArrayList<HalsteadObject> halsteadObjects;

    @FXML
    public void initialize(){

    }

    @FXML
    public void exit (ActionEvent e){
        ((Node)e.getTarget()).getScene().getWindow().hide();
    }

    @FXML
    public void RerunTestBtnAction(ActionEvent e){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("sample.fxml"));
            Stage  mainWindow= new Stage();
            mainWindow.setScene(new Scene(fxmlLoader.load(), 640,480));
            mainWindow.setTitle("Plagiarism Code Buster Tester");
            mainWindow.initModality(Modality.APPLICATION_MODAL);
            mainWindow.show();

            ((Node)e.getTarget()).getScene().getWindow().hide();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    @FXML
    public void saveBtnAction(ActionEvent e){

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Saving Test Results");
        fileChooser.setInitialFileName("Similarity_Test_Results");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF files (*.pdf)", "*.PDF", "*.pdf"));

        File saveFile = fileChooser.showSaveDialog(((Node)e.getTarget()).getScene().getWindow());


        if(saveFile != null){
            try{

                new Thread( () -> {

                    ProgressIndicator progressIndicator = new ProgressIndicator("Your Test Results are now being saved. Please Wait");
                    try{

                        Document document = new Document();

                        PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(saveFile.getAbsolutePath()));
                        document.open();

                        Paragraph temp = new Paragraph("Test Results of Software Similarity");
                        temp.setAlignment(Element.ALIGN_CENTER);
                        document.setPageSize(PageSize.LETTER);
                        document.add(temp);

                        temp = new Paragraph("List of Projects Tested: ");
                        document.add(temp);
                        document.add(createFileNameTablePdf());
                        Platform.runLater(() -> progressIndicator.setProgress(0.25));



                        document.newPage();
                        temp = new Paragraph("Top 10 Scores: ");
                        document.add(temp);
                        document.add(createTopScoresTable());
                        Platform.runLater(() -> progressIndicator.setProgress(0.5));


                        document.newPage();
                        temp = new Paragraph("Halstead Metrics");
                        document.add(temp);
                        document.add(createHalsteadMetricTable());
                        Platform.runLater(() -> progressIndicator.setProgress(0.75));
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                progressIndicator.close();
                                Alert alert =  new Alert(Alert.AlertType.INFORMATION,"Your test results have been saved successfully");
                                alert.getDialogPane().getStylesheets().add("CSS/notification.css");
                                alert.showAndWait();
                                document.close();
                                pdfWriter.close();
                            }

                        });//this will close the doucment after the correlation matrix is added

                        document.setPageSize(PageSize.LETTER);
                        document.newPage();
                        temp = new Paragraph("Correlation Matrix: ");
                        document.add(temp);
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    document.add(insertMatrixImage(document));
                                } catch (DocumentException ex) {
                                    ex.printStackTrace();
                                }
                            }
                        });
                        Platform.runLater(() -> progressIndicator.setProgress(1));

                    }catch (Exception e1){
                        e1.printStackTrace();
                    }

                }).start();

            }catch (Exception ex){
                ex.printStackTrace();
            }
        }


    }

    @FXML
    public void helpButton(ActionEvent e){

        ButtonType buttonCorrelation = new ButtonType("Correlation Matirx");
        ButtonType buttonHalstead = new ButtonType("Halstead Metrics");

        Alert whatHelp = new Alert(Alert.AlertType.INFORMATION,"Where do you need help?", buttonCorrelation,buttonHalstead,ButtonType.CANCEL);
        whatHelp.setTitle("Need help?");
        whatHelp.setHeaderText("Need help?");
        whatHelp.getDialogPane().getStylesheets().add("CSS/notification.css");
        Optional<ButtonType> results = whatHelp.showAndWait();

        if(results.isPresent()){

            if(results.get().getText().compareTo("Correlation Matirx") == 0){
                Alert alert = new Alert(Alert.AlertType.INFORMATION,"Correlation Matrix displays the similarity score of the each of the comparison. " +
                        "Hover over the score to determine the what project are being compared to. The lowest score is zero while the highest is one. " +
                        "High similarity score between the two files means it is likely that the files are similar. Conversely, low scores means two files are dissimilar" +
                        "Also, the intensity of the red color is related to the score. Higher intensity of red means a high score while lower intensity of red"
                        +" means a low score");
                alert.getDialogPane().getStylesheets().add("CSS/notification.css");
                alert.showAndWait();
            }
            else if(results.get().getText().compareTo("Halstead Metrics") == 0){
                Alert alert = new Alert(Alert.AlertType.INFORMATION,"It is one of the metrics which measures a program. In the halstead metric, there are three" +
                        "measures that we included which are program length, program vocabulary and program difficulty. Program length tells you how long is the " +
                        "program based on the number of operands and operators. Program Vocabulary tells you the total unique operand and unique operator used in the project. " +
                        "Lastly, the program difficulty shows how difficult to handle the program is based on the number of operator and operands.");
                alert.getDialogPane().getStylesheets().add("CSS/notification.css");
                alert.showAndWait();
            }
        }
    }

    private Image insertMatrixImage(Document document){
        com.itextpdf.text.Image  graph = null;
        try{
            WritableImage wim = correlationMatrix.snapshot(null,null);
            ByteArrayOutputStream  byteOutput = new ByteArrayOutputStream();

            ImageIO.write( SwingFXUtils.fromFXImage( wim, null ), "png", byteOutput );

            graph = com.itextpdf.text.Image.getInstance( byteOutput.toByteArray() );
        }catch (Exception e){
            e.printStackTrace();
        }
        float scaler = ((document.getPageSize().getWidth() - document.leftMargin()
                - document.rightMargin()) / graph.getWidth()) * 100;

        graph.scalePercent(scaler);

        return graph;
    }

    private PdfPTable createHalsteadMetricTable(){

        PdfPTable table = new PdfPTable(4);

        float[] columnWidths = new float[]{30f, 10f, 10f, 10f};
        try{
            table.setWidths(columnWidths);
        }catch (Exception e){
            e.printStackTrace();
        }

        table.setSpacingBefore(20);

        PdfPCell header1 = new PdfPCell(new Paragraph("Project Name"));
        PdfPCell header2 = new PdfPCell(new Paragraph("Program Length"));
        PdfPCell header3 = new PdfPCell(new Paragraph("Program Vocabulary"));
        PdfPCell header4 = new PdfPCell(new Paragraph("Program Difficulty"));

        table.addCell(header1);
        table.addCell(header2);
        table.addCell(header3);
        table.addCell(header4);

        for(HalsteadObject ho : halsteadObjects){

            PdfPCell c1 = new PdfPCell(new Paragraph(ho.getFilename()));
            PdfPCell c2 = new PdfPCell(new Paragraph(Integer.toString(ho.getProgramLength())));
            PdfPCell c3 = new PdfPCell(new Paragraph(Integer.toString(ho.getProgramVocabulary())));
            PdfPCell c4 = new PdfPCell(new Paragraph(Float.toString(ho.getProgramDifficulty())));

            table.addCell(c1);
            table.addCell(c2);
            table.addCell(c3);
            table.addCell(c4);
        }

        return table;
    }

    private PdfPTable createFileNameTablePdf(){

        PdfPTable table = new PdfPTable(2);

        table.setSpacingBefore(20);

        PdfPCell header1 = new PdfPCell(new Paragraph("Index"));
        PdfPCell header2 = new PdfPCell(new Paragraph("Project Names"));

        table.addCell(header1);
        table.addCell(header2);

        for (int i = 0; i < fileNames.size() ; i++) {

            PdfPCell c1 = new PdfPCell(new Paragraph(Integer.toString(i+1)));
            PdfPCell c2 = new PdfPCell(new Paragraph(fileNames.get(i)));

            table.addCell(c1);
            table.addCell(c2);

        }

        return table;
    }

    private PdfPTable createTopScoresTable(){
        PdfPTable table = new PdfPTable(3);

        table.setSpacingBefore(20);

        PdfPCell header1 = new PdfPCell(new Paragraph("Pair 1 Name"));
        PdfPCell header2 = new PdfPCell(new Paragraph("pair 2 Name"));
        PdfPCell header3 = new PdfPCell(new Paragraph("Score"));

        table.addCell(header1);
        table.addCell(header2);
        table.addCell(header3);

        for (int i = 0; i < topScores.size() ; i++) {
            PdfPCell c1 = new PdfPCell(new Paragraph(topScores.get(i).getPair1Name()));
            PdfPCell c2 = new PdfPCell(new Paragraph(topScores.get(i).getPair2Name()));
            PdfPCell c3 = new PdfPCell(new Paragraph(Float.toString(topScores.get(i).getScore())));

            table.addCell(c1);
            table.addCell(c2);
            table.addCell(c3);
        }

        return table;
    }

    private void InitializeProjectFilesView(ArrayList<String> namefiles){

        projectTestedTable.getColumns().removeAll();
        //for the index ccolumn of the table view
        TableColumn<ProjectFiles, Integer> indexColumn = new TableColumn<>("Index");
        indexColumn.setMinWidth(50);
        indexColumn.setCellValueFactory(new PropertyValueFactory<>("index"));

        //for the project name of the table view
        TableColumn<ProjectFiles, String> projectNameColumn = new TableColumn<>("Project Folder Name");
        projectNameColumn.setMinWidth(sizeColumnSpanName);
        projectNameColumn.setCellValueFactory(new PropertyValueFactory<>("projectFileName"));

        projectTestedTable.setItems(getProjectFiles(namefiles));
        projectTestedTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        projectTestedTable.getColumns().addAll(indexColumn, projectNameColumn);
    }

    private void InitializeHalsteadTestView(ArrayList<HalsteadObject> halsteadObjects){

        halsteadTestResults.getColumns().removeAll();

        //filename column
        TableColumn<HalsteadObject, String> fileNameColumn = new TableColumn<>("Project Name");
        fileNameColumn.setMinWidth(sizeColumnSpanName);
        fileNameColumn.setCellValueFactory(new PropertyValueFactory<>("filename"));

        //Program Length column
        TableColumn<HalsteadObject, String> programLengthColumn= new TableColumn<>("Program Length");
        programLengthColumn.setMinWidth(150);
        programLengthColumn.setCellValueFactory(new PropertyValueFactory<>("programLength"));

        //Program Vocabulary column
        TableColumn<HalsteadObject, String> programVocabularyColumn= new TableColumn<>("Program Vocabulary");
        programVocabularyColumn.setMinWidth(150);
        programVocabularyColumn.setCellValueFactory(new PropertyValueFactory<>("programVocabulary"));

        //Program Difficulty column
        TableColumn<HalsteadObject, String> programDifficultyColumn= new TableColumn<>("Program Difficulty");
        programDifficultyColumn.setMinWidth(150);
        programDifficultyColumn.setCellValueFactory(new PropertyValueFactory<>("programDifficulty"));

        halsteadTestResults.setItems(getHalsteadTestResults(halsteadObjects));
        halsteadTestResults.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        halsteadTestResults.getColumns().addAll(fileNameColumn, programLengthColumn, programVocabularyColumn ,programDifficultyColumn);

    }

    private void InitializeTopSimilarityView(LinkedList<PairScore> ps){

        //for the pair 1 name ccolumn of the table view
        TableColumn<PairScore, Integer> pairName1Column = new TableColumn<>("Pair 1 Name");
        pairName1Column.setMinWidth(sizeColumnSpanName);
        pairName1Column.setCellValueFactory(new PropertyValueFactory<>("pair1Name"));

        //for the pair 2 name column of the table view
        TableColumn<PairScore, String> pairName2Column = new TableColumn<>("Pair 2 Name");
        pairName2Column.setMinWidth(sizeColumnSpanName);
        pairName2Column.setCellValueFactory(new PropertyValueFactory<>("pair2Name"));

        //for the score column of the table view
        TableColumn<PairScore, String> scoreColumn = new TableColumn<>("Score");
        scoreColumn.setMinWidth(70);
        scoreColumn.setCellValueFactory(new PropertyValueFactory<>("score"));

        topScoresTable.setItems(getTopScores(ps));
        topScoresTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        topScoresTable.getColumns().addAll(pairName1Column,pairName2Column,scoreColumn);
    }

    private void InitializeCorrelationMatrixView(PairScore[][] pairScores,ArrayList<String> filenames, int sizeMatrix){

        correlationMatrix = new GridPane();
        correlationMatrix.setPadding(new Insets(10,10,10,10));
        correlationMatrix.setVgap(2);
        correlationMatrix.setHgap(2);

        correlationMatrix.add(IndexBox("Index"),0,0);

        for (int i = 0; i < filenames.size();i++ ){
            correlationMatrix.add(IndexHeader(i+1,filenames.get(i)),i+1,0);
        }

        for (int row = 0; row < sizeMatrix; row++) {

            correlationMatrix.add(IndexHeader(row+1,filenames.get(row)),0,row+1);

            for (int col = 0; col < sizeMatrix  ; col++) {

                correlationMatrix.add(CorrelationScore(pairScores[row][col].getScore(),pairScores[row][col].getPair1Name(),pairScores[row][col].getPair2Name()),row +1 ,col+1);

            }
        }

        correlationMatrix.getStylesheets().add("CSS/test_window.css");
        correlationMatrixAnchrPn.setContent(correlationMatrix);
        correlationMatrixAnchrPn.getStylesheets().add("CSS/test_window.css");
    }

    private ObservableList<HalsteadObject> getHalsteadTestResults(ArrayList<HalsteadObject> halsteadObjects){
        ObservableList<HalsteadObject> halsteadtest = FXCollections.observableArrayList();

        halsteadtest.addAll(halsteadObjects);

        return halsteadtest;
    }

    private ObservableList<PairScore> getTopScores(LinkedList<PairScore> ps){
        ObservableList<PairScore> projectFiles = FXCollections.observableArrayList();

        projectFiles.addAll(ps);

        return projectFiles;
    }

    private ObservableList<ProjectFiles> getProjectFiles(ArrayList<String> namefiles){

        ObservableList<ProjectFiles> projectFiles = FXCollections.observableArrayList();

        for (Integer i = 0; i < namefiles.size(); i++) {
            projectFiles.add(new ProjectFiles(i + 1,namefiles.get(i)));
        }

        return projectFiles;
    }

    private StackPane IndexBox(String name){
        StackPane stackPane = new StackPane();

        Rectangle rectangle1 = new Rectangle();
        rectangle1.setHeight(60);
        rectangle1.setWidth(60);
        rectangle1.setFill(Color.YELLOW);
        rectangle1.setStroke(Color.BLACK);

        Text correlationScoreText = new Text(name);

        stackPane.getChildren().addAll(rectangle1,correlationScoreText);

        return stackPane;
    }

    private StackPane IndexHeader (int index, String filename){
        Tooltip tooltip = new Tooltip("( " +  filename + " )");
        StackPane stackPane = new StackPane();

        Rectangle rectangle1 = new Rectangle();
        rectangle1.setHeight(60);
        rectangle1.setWidth(60);
        rectangle1.setFill(Color.YELLOW);
        rectangle1.setStroke(Color.BLACK);
        Tooltip.install(rectangle1,tooltip);


        String correlationScore = Integer.toString(index);

        Text correlationScoreText = new Text(correlationScore);
        correlationScoreText.setMouseTransparent(true);

        stackPane.getChildren().addAll(rectangle1,correlationScoreText);

        return stackPane;
    }

    private StackPane CorrelationScore(float score, String pair1Name, String pair2Name){
        Tooltip tooltip = new Tooltip("(" + pair1Name + ", " + pair2Name + ")");
        StackPane stackPane = new StackPane();

        Rectangle rectangle1 = new Rectangle();
        rectangle1.setHeight(60);
        rectangle1.setWidth(60);
        rectangle1.setFill(Color.RED);
        rectangle1.setStroke(Color.BLACK);
        rectangle1.setOpacity(score);

        Rectangle rectangle2 = new Rectangle();
        rectangle2.setHeight(60);
        rectangle2.setWidth(60);
        rectangle2.setFill(Color.WHITE);
        rectangle2.setStroke(Color.BLACK);
        rectangle2.setOpacity(1);


        String correlationScore = String.format("%.4f", score);

        Text correlationScoreText = new Text(correlationScore);
        correlationScoreText.setMouseTransparent(true);

       Tooltip.install(rectangle1,tooltip);

      //stackPane.setOnMouseEntered(event -> tooltip.show(stackPane,event.getScreenX(),event.getSceneY()+ 15));

      //rectangle1.setOnMouseExited(event ->   tooltip.hide());

        stackPane.getChildren().addAll(rectangle2,rectangle1,correlationScoreText);

        return stackPane;
    }

    public void InitializeTestResultWindow (String filepathDirectory){

        this.filepathDirectory = filepathDirectory;
        MassCompare compare = new MassCompare(filepathDirectory);
        MassHalsteadTest test = new MassHalsteadTest(filepathDirectory);

        new Thread(() -> {

            compare.run();

            Platform.runLater(() -> {

                InitializeProjectFilesView(compare.getFileNames());
                InitializeTopSimilarityView(compare.getTopScores());
                InitializeCorrelationMatrixView(compare.getSimilarityMatrix(),compare.getFileNames(), compare.sizeOfMatrix());
                InitializeHalsteadTestView(test.getHalsteadresults());

                Alert alert =  new Alert(Alert.AlertType.INFORMATION,"Your Test is Finished. Press Ok to view results");
                alert.getDialogPane().getStylesheets().add("CSS/notification.css");
                alert.showAndWait();

            });
        }).start();


        this.scores = compare.getSimilarityMatrix();
        this.halsteadObjects = test.getHalsteadresults();
        this.topScores = compare.getTopScores();
        this.fileNames = compare.getFileNames();

    }

}
