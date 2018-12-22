package view.panels;

import controller.Quizcontroller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class EditQuestionPane extends GridPane {
    private Button btnOK, btnCancel, btnAdd, btnRemove;
    private Quizcontroller quizcontroller;
    private ObservableList<String> statements;
    private TextField questionField, statementField, feedbackField;
    private ListView<String> statementsArea;
    private ComboBox categoryField;

    public EditQuestionPane(Quizcontroller controller, String question, String feedback, ArrayList<String> oldStatements) {
        this.quizcontroller = controller;

        statements = FXCollections.observableArrayList();
        this.setPrefHeight(300);
        this.setPrefWidth(320);

        this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);

        add(new Label("Question: "), 0, 0, 1, 1);
        questionField = new TextField(question);
        add(questionField, 1, 0, 2, 1);

        add(new Label("Statement: "), 0, 1, 1, 1);
        statementField = new TextField();
        add(statementField, 1, 1, 2, 1);

        // ArrayList statements toevoegen aan ObservableList statements
        for (String x : oldStatements) {
            statements.add(x);
        }

        add(new Label("Statements: "), 0, 2, 1, 1);
        statementsArea = new ListView<>();
        //statementsArea.setPrefRowCount(5);
        statementsArea.setEditable(false);
        add(statementsArea, 1, 2, 2, 5);
        statementsArea.setItems(statements);

        Pane addRemove = new HBox();
        btnAdd = new Button("add");
        btnAdd.setOnAction(new EditQuestionPane.AddStatementListener());
        addRemove.getChildren().add(btnAdd);

        btnRemove = new Button("remove");
        btnRemove.setOnAction(new EditQuestionPane.RemoveStatementListener());
        addRemove.getChildren().add(btnRemove);
        add(addRemove, 1, 8, 2, 1);

        add(new Label("Category: "), 0, 9, 1, 1);
        categoryField = new ComboBox();
        add(categoryField, 1, 9, 2, 1);
        categoryField.setItems(quizcontroller.getCategories());
        categoryField.getSelectionModel().select(quizcontroller.getCategories().get(0));

        add(new Label("Feedback: "), 0, 10, 1, 1);
        feedbackField = new TextField();
        feedbackField.setText(feedback);
        add(feedbackField, 1, 10, 2, 1);


        btnCancel = new Button("Cancel");
        btnCancel.setText("Cancel");
        add(btnCancel, 0, 11, 1, 1);
        btnCancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                quizcontroller.closeSecondStage();
            }
        });

        btnOK = new Button("Save");
        btnOK.isDefaultButton();
        btnOK.setText("Save");
        add(btnOK, 1, 11, 2, 1);
        btnOK.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                quizcontroller.modifyQuestion(question, questionField.getText(), statements, categoryField.getValue().toString(), feedbackField.getText());
                quizcontroller.closeSecondStage();
            }
        });
    }


    class AddStatementListener implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e) {
            statements.add(statementField.getText());
            statementField.setText("");
        }
    }

    class RemoveStatementListener implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e) {
            statements.remove(statementsArea.getSelectionModel().getSelectedItem());
            statementField.setText("");
        }
    }
}
