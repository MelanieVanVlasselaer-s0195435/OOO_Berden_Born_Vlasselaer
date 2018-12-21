package view.panels;

import controller.Quizcontroller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;


public class QuestionOverviewPane extends GridPane {
    private TableView table;
    private Button btnNew;
    private Quizcontroller controller;

    public QuestionOverviewPane(Quizcontroller quizcontroller) {
        this.controller = quizcontroller;
        this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);

        this.add(new Label("Questions:"), 0, 0, 1, 1);

        table = new TableView<>();
        table.setPrefWidth(REMAINING);
        TableColumn nameCol = new TableColumn<>("Question");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("question"));
        table.getColumns().add(nameCol);
        TableColumn descriptionCol = new TableColumn<>("Stataments");
        descriptionCol.setCellValueFactory(new PropertyValueFactory("statements"));
        table.getColumns().add(descriptionCol);
        this.add(table, 0, 1, 2, 6);

        table.setItems(quizcontroller.getQuestions());

        btnNew = new Button("New");
        this.add(btnNew, 0, 11, 1, 1);

        btnNew.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                quizcontroller.toonQuestionDetailPanel();
            }
        });

        // Oproepen actie achter "Edit"
        Button btnEdit = new Button("Edit");
        this.add(btnEdit, 1, 11, 1, 1);
        btnEdit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                onEdit(table);
            }
        });

    }

    public void onEdit(TableView table) {
        if (table.getSelectionModel().getSelectedItem() != null) {
            controller.toonQuestionEditPane(table.getSelectionModel().getSelectedItem());
        }
        else {
            controller.toonErrorPage("Geen question geselecteerd!");
        }
    }


    public void setNewAction(EventHandler<ActionEvent> newAction) {
        btnNew.setOnAction(newAction);
    }

    public void setEditAction(EventHandler<MouseEvent> editAction) {
        table.setOnMouseClicked(editAction);
    }

}
