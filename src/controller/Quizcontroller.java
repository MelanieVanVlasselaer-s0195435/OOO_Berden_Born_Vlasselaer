package controller;

import database.CategoryText;
import database.DBcontext;
import database.TxtDBStrategy;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Category;
import model.ModelFacade;
import view.ViewFacade;
import view.panels.*;

import javax.swing.*;
import java.util.ArrayList;


// Moet dit een singleton zijn? -TB

public class Quizcontroller {
    ArrayList<Category> categories;
    //-> context wordt vervangen modelFacade
    //DBcontext context = new DBcontext();
    ModelFacade modelFacade = new ModelFacade();
    ViewFacade viewFacade = new ViewFacade();

    public Quizcontroller(){
        categories = modelFacade.getCategories();
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

    public void saveCategory(String name, String description, String mainCategory){
        modelFacade.addCategory(name,description, mainCategory);
    }

    public void toonDetailPanel(){
        viewFacade.toonDetailPanel();
    }

    public void sluitDetailPanel(){
        viewFacade.sluitDetailPanel();
    }

    public void start(Quizcontroller quizcontroller){
        viewFacade.start(quizcontroller);
    }

    /*public void toonDetail(){
        Group root = new Group();
        Stage secondStage = new Stage();
        Scene scene = new Scene(root,300,150);
        CategoryDetailPane detailpanel = new CategoryDetailPane();
        BorderPane border = new BorderPane(detailpanel);
        root.getChildren().add(border);
        secondStage.setScene(scene);
        secondStage.show();
    }*/


}
