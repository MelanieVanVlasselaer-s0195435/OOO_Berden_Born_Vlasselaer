package model.Evaluation;

import model.Test;

import java.util.ArrayList;

public interface EvaluationStrategy {
    ArrayList getEvaluation();

    void setNextResult();
}
