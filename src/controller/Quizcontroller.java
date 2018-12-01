package controller;

import database.CategoryText;
import database.DBcontext;
import database.DatabaseFacade;
import database.TxtDBStrategy;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Category;
import model.ModelFacade;
import model.Question;
import view.ViewFacade;
import view.panels.*;

import javax.swing.*;
import java.sql.SQLOutput;
import java.util.ArrayList;


// Moet dit een singleton zijn? -TB

public class Quizcontroller {
    ArrayList<Category> categories;
    ArrayList<Question> questions;
    ArrayList<String> primitieveCategories;
    ArrayList<String> primitieveQuestions;
    //-> context wordt aangesproken via de modelFacade -TB
    //DBcontext context = new DBcontext();
    ModelFacade modelFacade = new ModelFacade();
    ViewFacade viewFacade = new ViewFacade();
    DatabaseFacade databaseFacade = new DatabaseFacade();

    public Quizcontroller(){
        primitieveCategories = databaseFacade.loadCategorieElementen();
        primitieveQuestions = databaseFacade.loadQuestionElementen();
        modelFacade.makeCategories(primitieveCategories);
        modelFacade.makeQuestions(primitieveQuestions);
        categories = modelFacade.getCategories();
        questions = modelFacade.getQuestions();

    }

    public ArrayList<Category> getCategories(){
        if (categories == null) {
            System.out.println("leeg");
            return null;
        }
        else {
            return modelFacade.getCategories();
        }
    }

    public void addCategory(String name, String description, String mainCategory){
        modelFacade.addCategory(name,description, mainCategory);
    }

    public ArrayList<Question> getQuestions(){
        if (questions == null) {
            System.out.println("leeg");
            return null;
        }
        else {
            return  modelFacade.getQuestions();
        }
    }

    public void addQuestion(String question, ArrayList<String> statements, String category, String feedback){
        modelFacade.addQuestion(question,statements,category,feedback);
    }



    public void toonCategoryDetailPanel(){
        viewFacade.toonCategoryDetailPanel();
    }

    public void toonQuestionDetailPanel(){
        viewFacade.toonQuestionDetailPanel();
    }

    public void sluitDetailPanel(){
        viewFacade.sluitDetailPanel();
    }


    public void start(Quizcontroller quizcontroller){
        viewFacade.start(quizcontroller);
    }



}
