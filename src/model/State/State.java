package model.State;

import javafx.collections.ObservableList;
import model.Category;

import java.util.ArrayList;
import java.util.LinkedList;

public interface State {
    //kan enkel uitgevoerd worden wanneer er geen Test Bezig - TB
    void editCategory(String oldName, String name, String description, String mainCategory);
    void editQuestion(String oldQuestion, String question, String category, String feedback, ObservableList<String> statements);
    void addCategory(Category category);
    void addQuestionWithArrayList(String question, ArrayList<String> statements, String category, String feedback);
    void addQuestionWithObservableList(String question, ObservableList<String> statements, String category, String feedback);
    void setEvaluationStrategy(ArrayList<String> list);

}
