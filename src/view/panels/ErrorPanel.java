package view.panels;

import controller.Quizcontroller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class ErrorPanel extends GridPane {
    private Button closeButton;
    private Label lblerror;
    private Quizcontroller quizcontroller;

    public ErrorPanel(Quizcontroller quizcontroller, String errorText) {
        this.quizcontroller = quizcontroller;

        this.setPadding(new Insets(2, 2, 2, 2));
        this.setVgap(5);
        this.setHgap(5);
        lblerror = new Label();
        lblerror.setText(errorText);
        add(lblerror, 0, 0, 1, 1);
        closeButton = new Button("Close");
        add(closeButton,0, 1, 1, 1);
        closeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                quizcontroller.closeSecondStage();
            }
        });

        setHalignment(closeButton, HPos.CENTER);
    }
}