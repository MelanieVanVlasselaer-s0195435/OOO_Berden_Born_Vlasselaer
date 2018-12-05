package view.panels;

import controller.Quizcontroller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.layout.GridPane;

public class ResultPane extends GridPane {
    private Label questionField;
    private Label resultField;
    private Button closeButton;
    private Quizcontroller quizcontroller;

    public ResultPane(Quizcontroller quizcontroller) {
        this.quizcontroller = quizcontroller;
        this.setPrefHeight(300);
        this.setPrefWidth(750);

        this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);

        questionField = new Label();
        questionField.setText("Uw resultaat");
        add(questionField, 0, 0, 1, 1);

        resultField = new Label();
        resultField.setText(quizcontroller.getResult());
        add(resultField, 0, 1, 1, 1);

        closeButton = new Button("Close");
        add(closeButton, 0,5,1,1);
        closeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                quizcontroller.sluitDetailPanel();
            }
        });
    }
    }

