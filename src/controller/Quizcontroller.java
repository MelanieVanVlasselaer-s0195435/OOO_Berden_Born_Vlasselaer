package controller;


import database.DatabaseFacade;
import model.Category;
import model.ModelFacade;
import model.Question;
import view.ViewFacade;
import java.util.ArrayList;
import java.util.Observable;


// Moet dit een singleton zijn? -TB
// Quizcontroller extenden we van de klassen Observable
// Is niet hetzelfde als de interface Observable!!! -FB

public class Quizcontroller extends Observable {
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
