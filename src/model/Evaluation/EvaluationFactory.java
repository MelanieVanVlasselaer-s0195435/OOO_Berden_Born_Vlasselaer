package model.Evaluation;

public class EvaluationFactory {
    public EvaluationStrategy createStrategy(String evaluationType){
        EvaluationEnum evaluationEnum = EvaluationEnum.valueOf(evaluationType);
        String klasseNaam = evaluationEnum.getClassName();
        EvaluationStrategy strategy = null;
        try {
            Class dbClass = Class.forName(klasseNaam);
            Object dbObject = dbClass.newInstance();
            strategy = (EvaluationStrategy) dbObject;
        }
        catch (Exception e){
        }

        return strategy;
    }

}
