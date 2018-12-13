package model;

import javafx.collections.ObservableList;
import java.util.ArrayList;

public class Question {
    private String question;
    private ArrayList<String> statements;
    private String feedback;

    public Question (String question, ArrayList<String> statements, String feedback ) {
        setQuestion(question);
        setStatements(statements);
        setFeedback(feedback);
    }

    //getters
    public String getQuestion() {
        return question;
    }

    public ArrayList<String> getStatements() {
        return statements;
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

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    @Override
    public String toString() {
        return  question + " " + statements + " " + feedback;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Question) {
            Question x = (Question) obj;
            if (x.getFeedback().equals(this.getFeedback()) && x.getQuestion().equals(this.getQuestion())) {
                return true;
            }
            return false;
        }
        else {
            return false;
        }
    }
}
