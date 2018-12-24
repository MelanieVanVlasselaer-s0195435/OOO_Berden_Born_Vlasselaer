package controller;


import database.DatabaseFacade;
import javafx.collections.ObservableList;
import model.Category;
import model.Evaluation.EvaluationFactory;
import model.ModelFacade;
import model.Question;
import view.ViewFacade;

import java.util.*;


public class Quizcontroller {
    private ModelFacade modelFacade = new ModelFacade();
    private ViewFacade viewFacade = new ViewFacade();
    private DatabaseFacade databaseFacade = new DatabaseFacade();
    private static Quizcontroller controller = null;

    private Quizcontroller(){
        databaseFacade.setSource();
        ArrayList<ArrayList<String>> elementen = databaseFacade.loadTest();
        ArrayList<String> categorieElementen = elementen.get(0);
        modelFacade.makeCategories(categorieElementen);
        ArrayList<String> vraagelementen = elementen.get(1);
        modelFacade.makeQuestions(vraagelementen);
        modelFacade.setPreviousScore(databaseFacade.loadPreviousScore());
    }

    public static Quizcontroller getInstance() {
        if (controller == null) {
            controller = new Quizcontroller();
        }
       return controller;
    }


    public void editCategory(String oldName, String name, String description, String mainCategory) {
        modelFacade.editCategory(oldName, name, description, mainCategory);
    }

    public void addCategory(String name, String description, String mainCategory){
        modelFacade.addCategory(name,description, mainCategory);
    }

    public void toonCategoryEditPane(String name, String description, String mainCategory) {
        viewFacade.toonCategoryEditPanel(name, description, mainCategory);
    }

    public void toonQuestionEditPane(Object selectedObject) {
        if (selectedObject instanceof Question) {
            Question selectedQuestion = (Question) selectedObject;
            viewFacade.toonQuestionEditPanel(selectedQuestion.getQuestion(), selectedQuestion.getFeedback(), selectedQuestion.getStatements());
        }
    }

    public void addQuestion(String question, ObservableList<String> statements, String category, String feedback){
        modelFacade.addQuestion(question,statements,category,feedback);
    }

    public void toonErrorPage(String errorText) {
        viewFacade.showErrorpage(errorText);
    }
    public void displayResult() {
        viewFacade.showResultPane();
    }
    public void showClosingScreen() {
        viewFacade.showClosingScreen();
    }
    public void toonCategoryDetailPanel(){
        viewFacade.toonCategoryDetailPanel();
    }
    public void toonQuestionDetailPanel(){
        viewFacade.toonQuestionDetailPanel();
    }
    public void start(Quizcontroller quizcontroller){
        viewFacade.start(quizcontroller);
    }

    public void toonTestPanel(){
        modelFacade.setTestStateActive();
        viewFacade.toonTestPanel();
    }

    public void closeSecondStage(){
        modelFacade.setTestStateInactive();
        viewFacade.closeSecondStage();
    }

    public ObservableList<String> getPreviousScore() {
        return modelFacade.getPreviousScore();
    }
    public List<String> getEvaluationMethods() {
        return modelFacade.getEvaluationMethods();
    }
    public ObservableList<Category> getCategories(){
        return modelFacade.getCategories();
    }
    public ObservableList<Question> getQuestions(){
        return  modelFacade.getQuestions();
    }
    public ArrayList<String> getResult() {
        return modelFacade.getResult();
    }
    public LinkedList<String> getNextQuestion(int questionIndex) {
     return modelFacade.getNextQuestion(questionIndex);
    }
    public void controlAnswer(String antwoord, int questionIndex) {
        modelFacade.controlAnswer(antwoord,questionIndex);
    }
    public void setEvaluationMethode() {
        modelFacade.setEvaluationStrategy(databaseFacade.loadEvalutionStrategy());
    }



    public void writeTest() {
        ArrayList<ArrayList<String>> testElements = new ArrayList<>();
        ArrayList<String> categoryElements = modelFacade.getCategoryElements();
        testElements.add(0,categoryElements);
        ArrayList<String> questionElements = modelFacade.getQuestionElements();
        testElements.add(questionElements);
        databaseFacade.saveTest(testElements);
    }

    public void setScore(ArrayList<String> score) {
        modelFacade.setPreviousScore(score);
        databaseFacade.saveScore(score);
    }

    public void modifyQuestion(String oldquestion, String question, ObservableList<String> statements, String category, String feedback) {
        modelFacade.modifyQuestion(oldquestion, question, statements, category, feedback);
    }

    public List getTestSources() {
        return databaseFacade.getTestSources();
    }
    public void saveTestSource(String source) {
        databaseFacade.saveTestSource(source);
    }

    public void saveEvaluationMethode(String evaluationStrategy) {
        databaseFacade.saveEvalutionStrategy(evaluationStrategy);
        modelFacade.setEvaluationStrategy(evaluationStrategy);
    }

}
