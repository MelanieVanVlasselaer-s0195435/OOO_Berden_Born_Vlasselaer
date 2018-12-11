package model.Evaluation;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Score implements EvaluationStrategy {
    private HashMap<String,Integer> resultaten;

    public Score() {
        resultaten = new HashMap<>();
    }

    @Override
    public String getEvaluation() {
        int totaleScore = 0;
        String categoryScores = "";
        Iterator it = resultaten.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            categoryScores += pair.getKey() + ":" + pair.getValue() + "/" + findCategoryObject((String)pair.getKey()).getQuestions().size() + "\n";
            totaleScore = totaleScore + (int) pair.getValue();
            it.remove();
        }
        return "Your score: " + totaleScore + "/" + getAllQuestions().size() +  "\n" + categoryScores;
    }


    @Override
    public void setNextResult() {
        if (resultaten.containsKey(categorie)){
            int currentScoreForCategorie = (int) resultaten.get(categorie);
            int nieuweScore = currentScoreForCategorie + 1;
            resultaten.replace(categorie,nieuweScore);
        } else {
            resultaten.put(categorie,1);

        }
     else if (!resultaten.containsKey(categorie)) {
        resultaten.put(categorie,0);
    }
    }
}
