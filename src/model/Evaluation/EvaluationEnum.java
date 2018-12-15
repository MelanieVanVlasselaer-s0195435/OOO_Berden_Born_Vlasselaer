package model.Evaluation;

public enum EvaluationEnum {
    FEEDBACK ("model.Evaluation.Feedback"),
    SCORE ("model.Evaluation.Score");

    private final String className;

    EvaluationEnum (String className){
        this.className = className;
    }

    public String getClassName(){
        return className;
    }
}
