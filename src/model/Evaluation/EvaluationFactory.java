package model.Evaluation;

import model.Test;

import java.util.HashMap;

public class EvaluationFactory {
    public EvaluationStrategy createStrategy(String evaluationType, Test test, HashMap<String, int[]> resultaten){
        EvaluationEnum evaluationEnum = EvaluationEnum.valueOf(evaluationType);
        String klasseNaam = evaluationEnum.getClassName();
        EvaluationStrategy strategy;
        try {
            Class dbClass = Class.forName(klasseNaam);
            Object dbObject = dbClass.newInstance();
            strategy = (EvaluationStrategy) dbObject;
            strategy.setResultaten(resultaten);
            strategy.setTest(test);
        }
        catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }

        return strategy;
    }

}
