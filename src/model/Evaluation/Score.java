package model.Evaluation;

import model.Test;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Score implements EvaluationStrategy {
    private HashMap<String, int[]> resultaten;
    private Test test;
    private Boolean noFaults;

    public Score() {
        noFaults = false;
    }

    @Override
    public ArrayList<String> getEvaluation() {
        ArrayList<String> score = new ArrayList<>();
        if (noFaults) {
            Iterator i = resultaten.entrySet().iterator();
            int totaal = 0;
            int eigenresultaat = 0;
            while (i.hasNext()) {
                Map.Entry pair = (Map.Entry) i.next();
                int[] array = (int[]) pair.getValue();
                score.add(pair.getKey() + ": " + array[0] + "/" + array[1]);
                totaal += array[1];
                eigenresultaat += array[0];
            }
            score.add(0,"Your score: " + eigenresultaat + "/" + totaal);
            return score;
        }
        else {
            score.add("schitterend! Alles perfect");
            return score;
        }
        }



    @Override
    public void setNextResult() {
        noFaults = true;
        String categorie = test.findCategory(test.getCurrentQuestion().getQuestion());
        Iterator it = resultaten.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            if (pair.getKey().equals(categorie)) {
               int [] array = (int[]) pair.getValue();
               array[0] -= 1;
               pair.setValue(array);
            }
        }

    }

    @Override
    public void setResultaten(HashMap<String, int[]> resultaten) {
        this.resultaten =resultaten;
    }

    @Override
    public void setTest(Test test) {
        this.test= test;
    }
}

