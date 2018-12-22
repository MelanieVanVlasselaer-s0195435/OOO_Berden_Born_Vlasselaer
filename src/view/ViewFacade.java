package view;

import controller.Quizcontroller;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import view.panels.*;

import java.util.ArrayList;

public class ViewFacade {
    Quizcontroller quizcontroller;
    Stage primaryStage = new Stage();
    Stage secondStage = new Stage();

    public void start(Quizcontroller quizcontroller){
        this.quizcontroller = quizcontroller;
        try {
            InfoPanel infoPanel = new InfoPanel(quizcontroller);
            QuestionOverviewPane questionOverviewPane = new QuestionOverviewPane(quizcontroller);
            CategoryOverviewPane categoryOverviewPanel = new CategoryOverviewPane(quizcontroller);
            MessagePane messagePane = new MessagePane(quizcontroller);

            Group root = new Group();
            Scene scene = new Scene(root, 750, 400);

            BorderPane borderPane = new AssesMainPane(infoPanel,messagePane, categoryOverviewPanel, questionOverviewPane);
            borderPane.prefHeightProperty().bind(scene.heightProperty());
            borderPane.prefWidthProperty().bind(scene.widthProperty());

            root.getChildren().add(borderPane);
            primaryStage.setScene(scene);
            primaryStage.sizeToScene();

            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void toonCategoryDetailPanel(){
        Group root = new Group();
        Scene scene = new Scene(root,300,150);
        CategoryDetailPane detailpanel = new CategoryDetailPane(quizcontroller);
        BorderPane border = new BorderPane(detailpanel);
        root.getChildren().add(border);
        secondStage.setScene(scene);
        secondStage.show();
    }

    // Pane voor wijzigen van een Categorie

    public void toonCategoryEditPanel(String name, String description, String mainCategory) {
        Group root = new Group();
        Scene scene = new Scene(root, 300, 150);
        EditCategoryPane editPane = new EditCategoryPane(quizcontroller, name, description, mainCategory);
        BorderPane border = new BorderPane(editPane);
        root.getChildren().add(border);
        secondStage.setScene(scene);
        secondStage.show();
    }

    public void toonQuestionEditPanel(String question, String feedback, ArrayList<String> statements) {
        Group root = new Group();
        Scene scene = new Scene(root, 400, 350);
        EditQuestionPane editPane = new EditQuestionPane(quizcontroller, question, feedback, statements);
        BorderPane border = new BorderPane(editPane);
        root.getChildren().add(border);
        secondStage.setScene(scene);
        secondStage.show();
    }


    public void toonQuestionDetailPanel() {
        Group root = new Group();
        Scene scene = new Scene(root,400,350);
        QuestionDetailPane detailpanel = new QuestionDetailPane(quizcontroller);
        BorderPane border = new BorderPane(detailpanel);
        root.getChildren().add(border);
        secondStage.setScene(scene);
        secondStage.show();
    }
    public void toonTestPanel() {
        Group root = new Group();
        Scene scene = new Scene(root,450,350);
        TestPane testPanel = new TestPane(quizcontroller);
        BorderPane border = new BorderPane(testPanel);
        root.getChildren().add(border);
        secondStage.setScene(scene);
        secondStage.show();
    }
    public void showErrorpage(String errorText) {
        Group root = new Group();
        Scene scene = new Scene(root,200,75);
        ErrorPanel errorPanel = new ErrorPanel(quizcontroller, errorText);
        BorderPane border = new BorderPane(errorPanel);
        root.getChildren().add(border);
        secondStage.setScene(scene);
        secondStage.show();
    }



    public void closeSecondStage(){
        secondStage.close();
    }

    public void  showResultPane() {
        Group root = new Group();
        Scene scene = new Scene(root,200,200);
        ResultPane resultpanel = new ResultPane(quizcontroller);
        BorderPane border = new BorderPane(resultpanel);
        root.getChildren().add(border);
        secondStage.setScene(scene);
        secondStage.show();
    }

    public void showClosingScreen() {
        Group root = new Group();
        Scene scene = new Scene(root,300,100);
        ClosingPanel closingPanel = new ClosingPanel(quizcontroller);
        BorderPane border = new BorderPane(closingPanel);
        root.getChildren().add(border);
        secondStage.setScene(scene);
        secondStage.show();
    }
}
