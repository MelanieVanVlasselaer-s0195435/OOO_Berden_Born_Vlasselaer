package model;

import java.util.ArrayList;

public class Question {
    String question;
    String statement;
    ArrayList<String> statements = new ArrayList();
    Category category;
    String feedback;

    public Question (String question, String statement, ArrayList<String> statements, Category category, String feedback ) {
        setQuestion(question);
        setStatement(statement);
        setStatements(statements);
        setCategory(category);
        setFeedback(feedback);
    }

    //getters
    public String getQuestion() {
        return question;
    }

    public String getStatement() {
        return statement;
    }

    public ArrayList<String> getStatements() {
        return statements;
    }

    public Category getCategory() {
        return category;
    }

    public String getFeedback() {
        return feedback;
    }

    //Setters
    public void setQuestion(String question) {
        question = question;
    }

    public void setStatement(String statement) {
        statement = statement;
    }

    public void setStatements(ArrayList<String> statements) {
        statements = statements;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
