package controller;


import database.DatabaseFacade;
import javafx.collections.ObservableList;
import model.Category;
import model.ModelFacade;
import model.Question;
import view.ViewFacade;

import java.util.*;


// Moet dit een singleton zijn? -TB
// Quizcontroller extenden we van de klassen Observable
// Is niet hetzelfde als de interface Observable!!! -FB

public class Quizcontroller extends Observable {
    //-> context wordt aangesproken via de modelFacade -TB
    //DBcontext context = new DBcontext();
    ModelFacade modelFacade = new ModelFacade();
    ViewFacade viewFacade = new ViewFacade();
    DatabaseFacade databaseFacade = new DatabaseFacade();

    public Quizcontroller(){
        modelFacade.makeCategories(databaseFacade.loadCategorieElementen());
        modelFacade.makeQuestions(databaseFacade.loadQuestionElementen());
        modelFacade.setPreviousScore(databaseFacade.loadPreviousScore());
    }

    public ObservableList<Category> getCategories(){
        return modelFacade.getCategories();
    }

    public void addCategory(String name, String description, String mainCategory){
        modelFacade.addCategory(name,description, mainCategory);
    }

    public ObservableList<Question> getQuestions(){
        return  modelFacade.getQuestions();
    }

    public void addQuestion(String question, ObservableList<String> statements, String category, String feedback){
        modelFacade.addQuestion(question,statements,category,feedback);
    }

    public void toonCategoryDetailPanel(){
        viewFacade.toonCategoryDetailPanel();
    }

    public void toonQuestionDetailPanel(){
        viewFacade.toonQuestionDetailPanel();
    }

    public void toonTestPanel(){
        viewFacade.toonTestPanel();
    }

    public void closeSecondStage(){ viewFacade.closeSecondStage();
    }

    public void start(Quizcontroller quizcontroller){
        viewFacade.start(quizcontroller);
    }

    public void controlAnswer(String antwoord, int questionIndex) {
        modelFacade.controlAnswer(antwoord,questionIndex);
    }

    public ArrayList<String> getResult() {
        return modelFacade.getResult();
    }

    public LinkedList<String> getNextQuestion(int questionIndex) {
     return modelFacade.getNextQuestion(questionIndex);
    }

    public void displayResult() {
        viewFacade.showResultPane();
    }

    public void setEvaluationMethode() {

        modelFacade.setEvaluationStrategy(databaseFacade.loadEvalutionStrategy());
    }

    public void writeCategories() {
        ArrayList<String> categoryElements = modelFacade.getCategoryElements();
        databaseFacade.saveCategories(categoryElements);

    }

    public void writeScore(ArrayList<String> score) {
        databaseFacade.saveScore(score);
    }

    public ArrayList<String> getPreviousScore() {
        return modelFacade.getPreviousScore();
    }
}
