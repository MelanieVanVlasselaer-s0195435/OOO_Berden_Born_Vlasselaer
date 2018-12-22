package model.State;

import javafx.collections.ObservableList;
import model.Category;
import model.Evaluation.EvaluationFactory;
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
        //array aanmaken die voor elke categorie standaard 10/10 geeft (de 10 is gelijk aan het aantal vragen dat een categorie heeft) -FB
        HashMap<String, int[]> resultaten = new HashMap<>();
        for (Category x : test.getCategories()) {
            int[] Array = new int [2];
            Array[0] =  x.getQuestions().size();
            Array[1] =  x.getQuestions().size();
            resultaten.put(x.getName(), Array);
        }
        EvaluationFactory strategyFactory = new EvaluationFactory();
        test.getEvaluationContext().setEvaluationStrategy(strategyFactory.createStrategy(list.get(0), test, resultaten));

        /* - Vervangen door factory/reflection - TB
        if (list.get(0).equals("SCORE")) {
            evaluationContext.setEvaluationStrategy(new Score(this, resultaten));
        }
        else {
            evaluationContext.setEvaluationStrategy(new Feedback(this));
        }
        */
    }

    @Override
    public void modifyQuestion(String oldquestion, String question, ObservableList<String> statements, String category, String feedback) {
        if (test.getCategoryObjectByCategoryName(category)!= null) {
            if (test.getCategoryObjectByCategoryName(test.findCategory(oldquestion)).getName().equals(category)) {
                System.out.println("niet gewijzigd");
                //de categorie-naam van het oude question is hetzelfde als de nieuwe category -> we kunnen dus gewoon schrappen/adden
                test.getCategoryObjectByCategoryName(category).modifyQuestion(oldquestion, question, statements, category, feedback);
            }
            else {
                System.out.println("wel gewijzigd");
                test.getCategoryObjectByCategoryName(test.findCategory(oldquestion)).getQuestions().remove(test.getQuestionObjectByQuestionName(oldquestion));
                test.getCategoryObjectByCategoryName(category).addQuestionWithObservableList(question, statements, feedback);
            }
            test.getAllQuestions();
        } else {
            // als de categorie nog niet bestaat? -> niet mogelijk aangezien het uit een lijst gekozen moet worden
        }
    }
}
