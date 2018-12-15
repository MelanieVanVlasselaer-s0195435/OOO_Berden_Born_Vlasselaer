package model.Evaluation;


import model.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EvaluationContext {
    private EvaluationStrategy strat;

    public EvaluationStrategy getEvaluationStrategy(){
        return strat;
    }
    public void setEvaluationStrategy(EvaluationStrategy x) {
        if (x instanceof EvaluationStrategy) {
            strat = x;
        }
    }
    public ArrayList<String> getEvaluation() {
        return strat.getEvaluation();
    }

    public void setNextResult() {
        strat.setNextResult();
    }

    public List<String> getList() {
        List<String> lijst = new ArrayList<>();
        for (EvaluationEnum evaluation :EvaluationEnum.values()) {
            lijst.add(evaluation.toString());
        }
        return lijst;

    }
}
