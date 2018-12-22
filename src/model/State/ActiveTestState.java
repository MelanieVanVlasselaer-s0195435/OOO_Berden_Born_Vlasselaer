package model.State;

import javafx.collections.ObservableList;
import model.Category;
import model.Question;
import model.Test;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

public class ActiveTestState implements State {

    Test test;

    public ActiveTestState(Test test){
        this.test = test;
    }

    @Override
    public void editCategory(String oldName, String name, String description, String mainCategory) {
        JOptionPane.showMessageDialog(null,"Kan geen Category aanpassen tijdens een test");
    }

    @Override
    public void addCategory(Category category) {
        JOptionPane.showMessageDialog(null,"Kan geen Category toevoegen tijdens een test");
    }

    @Override
    public void addQuestionWithArrayList(String question, ArrayList<String> statements, String category, String feedback) {
        JOptionPane.showMessageDialog(null,"Kan geen Question toevoegen tijdens een test");
    }

    @Override
    public void addQuestionWithObservableList(String question, ObservableList<String> statements, String category, String feedback) {
        JOptionPane.showMessageDialog(null,"Kan geen Question toevoegen tijdens een test");
    }

    @Override
    public void setEvaluationStrategy(ArrayList<String> list) {
        JOptionPane.showMessageDialog(null,"Kan de evaluatiemethode niet aanpassen tijdens een test");

    }

    @Override
    public void modifyQuestion(String oldquestion, String question, ObservableList<String> statements, String category, String feedback) {
        JOptionPane.showMessageDialog(null, "Kan geen vraag aanpassen");
    }

}
