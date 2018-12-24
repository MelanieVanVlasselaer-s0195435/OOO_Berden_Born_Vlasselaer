package model.Evaluation;


import model.Test;

import java.util.ArrayList;
import java.util.HashMap;

public class Feedback implements EvaluationStrategy {
    private ArrayList<String> feedbacklist;
    private Test test;
    private Boolean noFaults;
    private HashMap resultaten;

    public Feedback () {
        feedbacklist = new ArrayList<>();
        noFaults = false;
    }
    @Override
    public ArrayList getEvaluation() {
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

    @Override
    public void setResultaten(HashMap<String, int[]> resultaten) {
        this.resultaten=resultaten;
    }

    @Override
    public void setTest(Test test) {
        this.test=test;

    }
}
