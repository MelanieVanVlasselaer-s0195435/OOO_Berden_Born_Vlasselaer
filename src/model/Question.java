package model;

import java.util.ArrayList;

public class Question {
    private String question;
    private ArrayList<String> statements;
    private String feedback;

    protected Question (String question, ArrayList<String> statements, String feedback ) {
        setQuestion(question);
        setStatements(statements);
        setFeedback(feedback);
    }

    public String getQuestion() {
        return question;
    }

    public ArrayList<String> getStatements() {
        return statements;
    }

    public String getFeedback() {
        return feedback;
    }


    private void setQuestion(String question) {
        if (question == null) {
            throw new ModelException("De vraag-stelling mag niet leeg zijn");
        }
            this.question = question.toLowerCase();
    }
    private void setStatements(ArrayList<String> statements) {
            this.statements = statements;
        }

    private void setFeedback(String feedback) {
            this.feedback = feedback.toLowerCase();
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
