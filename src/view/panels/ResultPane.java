package view.panels;

import controller.Quizcontroller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.layout.GridPane;

import java.util.ArrayList;

public class ResultPane extends GridPane {
    private Label questionField;
    private Label resultField;
    private Button closeButton;
    private Quizcontroller quizcontroller;
    private ArrayList<String> score;

    public ResultPane(Quizcontroller quizcontroller) {
        this.quizcontroller = quizcontroller;
        this.score = quizcontroller.getResult();
        this.setPrefHeight(300);
        this.setPrefWidth(750);

        this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);

        questionField = new Label();
        questionField.setText("Your results");
        add(questionField, 0, 0, 1, 1);

        resultField = new Label();
        String boodschap = "";
        for (String x : score) {
            boodschap+= x + "\n";
        }
        resultField.setText(boodschap);
        add(resultField, 0, 1, 1, 1);

        closeButton = new Button("Close");
        add(closeButton, 0,5,1,1);
        closeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                quizcontroller.setScore(score);
                quizcontroller.closeSecondStage();
            }
        });
    }
    }

