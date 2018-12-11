package model.Evaluation;

import model.Test;


import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Score implements EvaluationStrategy {
    private HashMap<String, int[]> resultaten;
    private Test test;
    private Boolean noFaults;

    public Score(Test test, HashMap resultaten) {
        this.resultaten = resultaten;
        this.test = test;
        noFaults = false;

    }

    @Override
    public String getEvaluation() {
        String eindboodschap = "";
        if (noFaults) {
            Iterator i = resultaten.entrySet().iterator();
            int totaal = 0;
            int eigenresultaat = 0;
            String boodschap = "";
            while (i.hasNext()) {
                Map.Entry pair = (Map.Entry) i.next();
                int[] array = (int[]) pair.getValue();
                boodschap += pair.getKey() + ": " + array[0] + "/" + array[1] + "\n";
                totaal += array[1];
                eigenresultaat += array[0];
            }
            eindboodschap += "Your score: " + eigenresultaat + "/" + totaal + "\n";
            eindboodschap += boodschap;
            return eindboodschap;
        }
        else {
            eindboodschap = "Schitterend! Alles perfect";
            return eindboodschap;
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
}

