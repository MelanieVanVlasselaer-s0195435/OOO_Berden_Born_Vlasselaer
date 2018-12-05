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

    public String getResult() {
        return modelFacade.getResult();
    }

    public LinkedList<String> getNextQuestion(int questionIndex) {
        if (questionIndex >= modelFacade.getQuestions().size()) {
            System.out.println("error");
        } else {
            LinkedList<String> nextQuestion = new LinkedList<>();
            nextQuestion.add(modelFacade.getQuestions().get(questionIndex).getQuestion());
            ArrayList<String> statements = modelFacade.getQuestions().get(questionIndex).getStatements();
            Collections.shuffle(statements);
            for (String x : statements) {
                nextQuestion.add(x);
            }
            return nextQuestion;
        }
        return null;
    }

    public void displayResult() {
        viewFacade.showResultPane();
    }
}
