package controller;

import database.CategoryText;
import database.DBcontext;
import database.TxtDBStrategy;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Category;
import view.panels.*;

import javax.swing.*;
import java.util.ArrayList;


// Moet dit een singleton zijn? -TB

public class Quizcontroller {
    ArrayList<Category> categories;
    DBcontext context = new DBcontext();

    public Quizcontroller(){
        context.setDBStrategy(new CategoryText());
        categories = context.load();
    }

    public ArrayList<Category> getCategories(){
        if (categories == null) {
            System.out.println("leeg");
            return null;
        }
        else {
            return categories;
        }
    }

    public void start(Stage primaryStage){
        try {

            QuestionOverviewPane questionOverviewPane = new QuestionOverviewPane();
            QuestionDetailPane questionDetailPane = new QuestionDetailPane();

            CategoryOverviewPane categoryOverviewPanel = new CategoryOverviewPane();
            CategoryDetailPane categoryDetailPanel = new CategoryDetailPane();

            TestPane testPane = new TestPane();
            MessagePane messagePane = new MessagePane();

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

    public void toonDetail(){
        Group root = new Group();
        Stage secondStage = new Stage();
        Scene scene = new Scene(root,300,150);
        CategoryDetailPane detailpanel = new CategoryDetailPane();
        BorderPane border = new BorderPane(detailpanel);
        root.getChildren().add(border);
        secondStage.setScene(scene);
        secondStage.show();
    }


}
