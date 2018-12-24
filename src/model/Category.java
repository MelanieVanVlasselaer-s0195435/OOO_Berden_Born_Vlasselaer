package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Category {

    private String name;
    private String description;
    private String hoofdcategorie;
    private ObservableList<Question> questions;

    public Category(String name, String description) {
        this(name,description,"null");
    }

    public Category(String name, String description, String hoofdcategorie) {
        this.setName(name);
        this.setDescription(description);
        this.setHoofdcategorie(hoofdcategorie);
        this.questions =  FXCollections.observableArrayList();
    }
    private void setName(String name) {
        if (name.matches(".*\\d+.*")) {
            throw new RuntimeException("Categorie naam mag geen cijfer bevatten");
        }
        else {
            this.name = name.toLowerCase();
        }
    }

    private void setDescription(String description) {
        this.description = description;
    }

    private void setHoofdcategorie(String hoofdcategorie) {
        this.hoofdcategorie = hoofdcategorie;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public String getHoofdcategorie() {
        return hoofdcategorie;
    }

    private Question getQuestionObject (String question) {
        for (Question x : questions) {
            if (x.getQuestion().equals(question)) {
                return x;
            }
        }
        return null;
    }

    public void addQuestion(String question, ArrayList<String> statements, String feedback) {
        questions.add(new Question(question, statements, feedback));
    }

    public void addQuestionWithObservableList(String question, ObservableList<String> statements, String feedback) {
        ArrayList<String> statementsArray = new ArrayList<>();
        for (String x : statements) {
            statementsArray.add(x);
        }
        addQuestion(question, statementsArray, feedback);
    }

    public ObservableList<Question> getQuestions() {
        return questions;
    }

    public void modifyQuestion(String oldquestion, String question, ObservableList<String> statements, String feedback) {
        ArrayList<String> statsList = new ArrayList<>();
        for (String x : statements) {
            statsList.add(x);
        }
        Question newQuestion = new Question(question, statsList,feedback);
        questions.remove(this.getQuestionObject(oldquestion));
        questions.add(newQuestion);
    }
    @Override
    public String toString() {
        return name;
    }
}
