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

    // Methode voor edit Category
    public void editCategory(String oldName, String name, String description, String mainCategory) {
        modelFacade.editCategory(oldName, name, description, mainCategory);
    }

    // Methode voor edit Question
    public void editQuestion(String oldQuestion, String question, String category, String feedback, ObservableList<String> statements) {
        modelFacade.editQuestion(oldQuestion, question, category, feedback, statements);
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

    // Ophalen EditPane Category

    public void toonCategoryEditPane(String name, String description, String mainCategory) {
        viewFacade.toonCategoryEditPanel(name, description, mainCategory);
    }

    // Ophalen EditPane Question

    public void toonQuestionEditPane(Object selectedObject) {
        if (selectedObject instanceof Question) {
            Question selectedQuestion = (Question) selectedObject;
            viewFacade.toonQuestionEditPanel(selectedQuestion.getQuestion(), selectedQuestion.getFeedback(), selectedQuestion.getStatements());
        }
    }

    public void toonQuestionDetailPanel(){
        viewFacade.toonQuestionDetailPanel();
    }

    public void toonTestPanel(){
        modelFacade.setTestStateActive();
        viewFacade.toonTestPanel();
    }

    public void closeSecondStage(){
        modelFacade.setTestStateInactive();
        viewFacade.closeSecondStage();
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
    //Florians versie van setEvaluationStrategy
        modelFacade.setEvaluationStrategy(databaseFacade.loadEvalutionStrategy());
    }

    public void saveEvaluationMethode(String evaluationStrategy) {
        databaseFacade.saveEvalutionStrategy(evaluationStrategy);
    }

    public void writeCategories() {
        ArrayList<String> categoryElements = modelFacade.getCategoryElements();
        databaseFacade.saveCategories(categoryElements);

    }

    public void setScore(ArrayList<String> score) {
        modelFacade.setPreviousScore(score);
        databaseFacade.saveScore(score);
    }

    public ObservableList<String> getPreviousScore() {
        return modelFacade.getPreviousScore();
    }

    public void writeQuestions() {
        ArrayList<String> questionElements = modelFacade.getQuestionElements();
        databaseFacade.saveQuestions(questionElements);
    }

    public List<String> getEvaluationMethods() {
        return modelFacade.getEvaluationMethods();
    }


    public void toonErrorPage(String errorText) {
        viewFacade.showErrorpage(errorText);
    }

    public void modifyQuestion(String question, ObservableList<String> statements, String category, String feedback) {
        modelFacade.modifyQuestion(question, statements, category, feedback);
    }
}
