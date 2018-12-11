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
    public String getEvaluation() {
        String boodschap = "";
        if (noFaults) {
            for (String x : feedbacklist) {
                boodschap += x + "\n";
            }
            return boodschap;
        }
        else {
            boodschap = "Schitterend! Alles perfect!";
            return boodschap;
        }
    }

    @Override
    public void setNextResult() {
        noFaults = true;
        feedbacklist.add(test.getCurrentQuestion().getFeedback());
    }
}
