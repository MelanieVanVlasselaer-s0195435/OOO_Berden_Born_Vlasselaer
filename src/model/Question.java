package model;

import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Question {
    String question;
    ArrayList<String> statements = new ArrayList();
    String category;
    String feedback;

    public Question (String question, ArrayList<String> statements, String category, String feedback ) {
        setQuestion(question);
        setStatements(statements);
        setCategory(category);
        setFeedback(feedback);
    }
    public Question (String question, ObservableList<String> statements, String category, String feedback ) {
        setQuestion(question);
        for (String x : statements) {
            setStringStatements(x);
        }

        setCategory(category);
        setFeedback(feedback);
    }

    //getters
    public String getQuestion() {
        return question;
    }

    public ArrayList<String> getStatements() {
        return statements;
    }

    public String getCategory() {
        return category;
    }

    public String getFeedback() {
        return feedback;
    }

    //Setters
    public void setQuestion(String question) {
        this.question = question;
    }
    //setter van een meegegeven arrayList
    public void setStatements(ArrayList<String> statements) {
        this.statements = statements;
    }

    //setters als we een string aan onze lijst van statments willen toevoegen
    public void setStringStatements(String statement) {
        this.statements.add(statement);
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
