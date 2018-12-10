package model;


import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class ModelFacade {
    private Test test;


    public ModelFacade(){
       test = new Test();
    }

    public void makeCategories(ArrayList<String> primitieveCategories){
        test.makeCategories(primitieveCategories);
    }

    public void makeQuestions(ArrayList<String> primitieveQuestions) {
        test.makeQuestions(primitieveQuestions);
    }


    public ObservableList<Category> getCategories(){
        return test.getCategories();
    }
    public ObservableList<Question> getQuestions() {
        return test.getAllQuestions();
    }

    public void addCategory(String name, String description, String mainCategory){
        Category newCategory = new Category(name, description, mainCategory);
        test.addCategory(newCategory);
    }


    public void addQuestion(String question, ObservableList<String> statements, String category, String feedback){
        test.addQuestionWithObservableList(question, statements, category, feedback);
    }

    public void controlAnswer(String antwoord, int questionIndex) {
        test.controlAnswer(antwoord,questionIndex);
    }

    public String getResult() {
        return test.getResult();
    }

    public LinkedList<String> getNextQuestion(int questionIndex) {
        return test.getNextQuestion(questionIndex);
    }
}
