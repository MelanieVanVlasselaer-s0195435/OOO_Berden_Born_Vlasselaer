package model.Evaluation;


import model.Test;

import java.util.HashMap;

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
    public String getEvaluation() {
        return strat.getEvaluation();
    }

    public void setNextResult() {
        strat.setNextResult();
    }
}
