package model.Evaluation;

import model.Test;

public interface EvaluationStrategy {
    String getEvaluation();

    void setNextResult();
}
