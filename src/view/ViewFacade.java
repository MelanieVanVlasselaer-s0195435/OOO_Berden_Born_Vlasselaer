package view;

import controller.Quizcontroller;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import view.panels.*;

public class ViewFacade {
    Quizcontroller quizcontroller;
    Stage primaryStage = new Stage();
    Stage secondStage = new Stage();

    public void start(Quizcontroller quizcontroller){
        this.quizcontroller = quizcontroller;
        try {
            QuestionOverviewPane questionOverviewPane = new QuestionOverviewPane(quizcontroller);
            CategoryOverviewPane categoryOverviewPanel = new CategoryOverviewPane(quizcontroller);

            TestPane testPane = new TestPane();
            MessagePane messagePane = new MessagePane(quizcontroller);

            Group root = new Group();
            Scene scene = new Scene(root, 750, 400);

            BorderPane borderPane = new AssesMainPane(messagePane, categoryOverviewPanel, questionOverviewPane);
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
        Scene scene = new Scene(root,400,350);
        TestPane testPanel = new TestPane();
        BorderPane border = new BorderPane(testPanel);
        root.getChildren().add(border);
        secondStage.setScene(scene);
        secondStage.show();
    }



    public void sluitDetailPanel(){
        secondStage.close();
    }
}
