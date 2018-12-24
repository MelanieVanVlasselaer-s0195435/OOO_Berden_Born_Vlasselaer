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
            throw new ModelException("Categorie naam mag geen cijfer bevatten");
        }
        if (name == null) {
            throw new ModelException("De Categorie naam mag niet leeg zijn");
        }
        else {
            this.name = name.toLowerCase();
        }
    }
    private void setDescription(String description) {
        if (description == null) {
            throw new ModelException("De categorie omschrijving mag niet leeg zijn");
        }
        if (description.matches(".*\\d+.*")) {
            throw new ModelException("Categorie description mag geen cijfer bevatten");
        } else {
            this.description = description.toLowerCase();
        }

    }
    private void setHoofdcategorie(String hoofdcategorie) {
        if (hoofdcategorie.matches(".*\\d+.*")) {
            throw new ModelException("De hoofdcategorie mag geen cijfer bevatten");
        } else {
            this.hoofdcategorie = hoofdcategorie.toLowerCase();
        }
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
        statementsArray.addAll(statements);
        addQuestion(question, statementsArray, feedback);
    }

    public ObservableList<Question> getQuestions() {
        return questions;
    }

    public void modifyQuestion(String oldquestion, String question, ObservableList<String> statements, String feedback) {
        ArrayList<String> statsList = new ArrayList<>();
        statsList.addAll(statements);
        Question newQuestion = new Question(question, statsList,feedback);
        questions.remove(this.getQuestionObject(oldquestion));
        questions.add(newQuestion);
    }
    @Override
    public String toString() {
        return name;
    }
}
