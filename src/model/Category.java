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
    // SETTERS PUBLIC GEMAAKT, WEET NIET OF DIT MOCHT / MAG ??? -MVV
    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setHoofdcategorie(String hoofdcategorie) {
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

    public void setQuestions(ObservableList<Question> questions) {
        this.questions = questions;
    }

    @Override
    public String toString() {
        return name;
    }
}
