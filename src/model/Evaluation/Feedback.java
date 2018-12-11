package model.Evaluation;


import model.Test;

import java.util.ArrayList;

public class Feedback implements EvaluationStrategy {
    ArrayList<String> feedbacklist;
    Test test;

    public Feedback (Test test) {
        feedbacklist = new ArrayList<>();
        this.test = test;
    }
    @Override
    public String getEvaluation() {
        String boodschap = "";
        for (String x : feedbacklist) {
            boodschap += x + "\n";
        }
        return boodschap;
    }

    @Override
    public void setNextResult() {
        feedbacklist.add(test.getCurrentQuestion().getFeedback());
    }
}
