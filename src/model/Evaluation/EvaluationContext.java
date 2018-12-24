package model.Evaluation;


import java.util.ArrayList;
import java.util.List;

public class EvaluationContext {
    private EvaluationStrategy strat;


    public void setEvaluationStrategy(EvaluationStrategy x) {
            strat = x;
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
