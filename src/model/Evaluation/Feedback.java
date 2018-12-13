package model.Evaluation;


import model.Test;

import java.util.ArrayList;

public class Feedback implements EvaluationStrategy {
    private ArrayList<String> feedbacklist;
    private Test test;
    private Boolean noFaults;

    public Feedback (Test test) {
        feedbacklist = new ArrayList<>();
        this.test = test;
        noFaults = false;
    }
    @Override
    public ArrayList getEvaluation() {
        String boodschap = "";
        if (noFaults) {
           return feedbacklist;
        }
        else {
            feedbacklist = new ArrayList<>();
            feedbacklist.add("schitterend! Alles perfect");
            return feedbacklist;
        }
    }

    @Override
    public void setNextResult() {
        noFaults = true;
        feedbacklist.add(test.getCurrentQuestion().getFeedback());
    }
}
