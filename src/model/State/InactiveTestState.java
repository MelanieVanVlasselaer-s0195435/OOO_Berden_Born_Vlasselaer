package model.State;

import javafx.collections.ObservableList;
import model.Category;
import model.Evaluation.EvaluationFactory;
import model.Evaluation.EvaluationStrategy;
import model.ModelException;
import model.Test;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class InactiveTestState implements State {

    Test test;

    public InactiveTestState(Test test){
        this.test = test;
    }

    @Override
    public void editCategory(String oldName, String name, String description, String mainCategory) {
        int index = 0;

        for (Category x : test.getCategories()) {
            if (x.getName().equals(oldName)) {
                Category category = new Category(name, description, mainCategory);
                test.getCategories().set(index, category);
                break;
            }
            index++;
        }
    }


    @Override
    public void addCategory(Category category) {
        test.getCategories().add(category);
    }

    @Override
    public void addQuestionWithArrayList(String question, ArrayList<String> statements, String category, String feedback) {
        int index = 0;
        boolean categoryExist = false;
        for (Category cat : test.getCategories()) {
            if (cat.getName().equals(category)) {
                index = test.getCategories().indexOf(cat);
                categoryExist = true;
                break;
            }
        }
        if (categoryExist) {
            test.getCategories().get(index).addQuestion(question, statements, feedback);
        }
    }

    @Override
    public void addQuestionWithObservableList(String question, ObservableList<String> statements, String category, String feedback) {
        int index = 0;
        boolean categoryExist = false;
        for (Category cat : test.getCategories()) {

            if (cat.getName().equals(category)) {
                index = test.getCategories().indexOf(cat);
                categoryExist = true;
                break;
            }

        }
        if (categoryExist) {
            test.getCategories().get(index).addQuestionWithObservableList(question, statements, feedback);
        }
    }

    @Override
    public void setEvaluationStrategy(ArrayList<String> list) {
        HashMap<String, int[]> resultaten = new HashMap<>();
        for (Category x : test.getCategories()) {
            int[] Array = new int [2];
            Array[0] =  x.getQuestions().size();
            Array[1] =  x.getQuestions().size();
            resultaten.put(x.getName(), Array);
        }
        EvaluationFactory strategyFactory = new EvaluationFactory();
        test.getEvaluationContext().setEvaluationStrategy(strategyFactory.createStrategy(list.get(0), test, resultaten));
    }

    @Override
    public void setEvaluationStrategy(String strategy) {
        HashMap<String, int[]> resultaten = new HashMap<>();
        EvaluationFactory strategyFactory = new EvaluationFactory();
        EvaluationStrategy newStrategy = strategyFactory.createStrategy(strategy, test, resultaten);
        test.getEvaluationContext().setEvaluationStrategy(newStrategy);
    }

    @Override
    public void modifyQuestion(String oldquestion, String question, ObservableList<String> statements, String category, String feedback) {
        if (test.getCategoryObjectByCategoryName(category)!= null) {
            if (test.getCategoryObjectByCategoryName(test.findCategory(oldquestion)).getName().equals(category)) {
                test.getCategoryObjectByCategoryName(category).modifyQuestion(oldquestion, question, statements, feedback);
            }
            else {
                test.getCategoryObjectByCategoryName(test.findCategory(oldquestion)).getQuestions().remove(test.getQuestionObjectByQuestionName(oldquestion));
                test.getCategoryObjectByCategoryName(category).addQuestionWithObservableList(question, statements, feedback);
            }
            test.getAllQuestions();
        }
    }
}
