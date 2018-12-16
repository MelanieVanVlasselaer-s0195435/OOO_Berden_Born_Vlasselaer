package model.Evaluation;

import model.Test;

import java.util.ArrayList;
import java.util.HashMap;

public interface EvaluationStrategy {
    ArrayList getEvaluation();

    void setNextResult();

    void setResultaten(HashMap<String, int[]> resultaten);
    void setTest(Test test);
}
