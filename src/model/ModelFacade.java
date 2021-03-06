package model;


import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ModelFacade {
    private Test test;


    public ModelFacade() {
        test = new Test();
    }

    public void makeCategories(ArrayList<String> primitieveCategories) {
        test.makeCategories(primitieveCategories);
    }

    public void makeQuestions(ArrayList<String> primitieveQuestions) {
        test.makeQuestions(primitieveQuestions);
    }


    public ObservableList<Category> getCategories() {
        return test.getCategories();
    }

    public ObservableList<Question> getQuestions() {
        return test.getAllQuestions();
    }

    // Methode voor edit Category
    public void editCategory(String oldName, String name, String description, String mainCategory) {
        test.editCategory(oldName, name, description, mainCategory);
    }


    public void addCategory(String name, String description, String mainCategory) {
        Category newCategory = new Category(name, description, mainCategory);
        test.addCategory(newCategory);
    }


    public void addQuestion(String question, ObservableList<String> statements, String category, String feedback) {
        test.addQuestionWithObservableList(question, statements, category, feedback);
    }

    public void controlAnswer(String antwoord, int questionIndex) {
        test.controlAnswer(antwoord, questionIndex);
    }

    public ArrayList<String> getResult() {
        return test.getResult();
    }

    public LinkedList<String> getNextQuestion(int questionIndex) {
        return test.getNextQuestion(questionIndex);
    }

    public void setEvaluationStrategy(ArrayList<String> list) {
        test.setEvaluationStrategy(list);
    }

    public ArrayList<String> getCategoryElements() {
        return test.getCategoryElements();
    }

    public ObservableList<String> getPreviousScore() {
        return test.getPreviousScore();
    }

    public void setPreviousScore(ArrayList<String> previousScore) {
        test.setPreviousScore(previousScore);
    }

    public ArrayList<String> getQuestionElements() {
        return test.getQuestionElements();
    }

    public List<String> getEvaluationMethods() {
        return test.getEvaluationMethods();
    }

    public void setTestStateActive() {
        test.setState(test.getActiveTestState());
    }

    public void setTestStateInactive() {
        test.setState(test.getInactiveState());
    }

    public void modifyQuestion(String oldquestion, String question, ObservableList<String> statements, String category, String feedback) {
        test.modifyQuestion(oldquestion, question, statements, category, feedback);
    }
}
