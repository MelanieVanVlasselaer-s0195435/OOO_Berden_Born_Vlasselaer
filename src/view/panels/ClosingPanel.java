package view.panels;

import controller.Quizcontroller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import javafx.scene.layout.GridPane;

import java.util.ArrayList;

public class ClosingPanel extends GridPane {


    private Quizcontroller quizcontroller;
    Label lblsource;
    ComboBox<String> sourceComboBox;
    private Button closeButton;

    public ClosingPanel(Quizcontroller quizcontroller) {
        this.quizcontroller = quizcontroller;
        this.setPrefHeight(250);
        this.setPrefWidth(200);

        this.setPadding(new Insets(0, 1, 1, 1));
        this.setVgap(5);
        this.setHgap(5);


        //voor te kiezen welke inleesMethode
        lblsource = new Label("van waar moet de test ingelezen worden?");
        this.add(lblsource, 2, 2, 1, 1);
        sourceComboBox = new ComboBox<>();
        ObservableList<String> sourcelist = FXCollections.observableList(quizcontroller.getTestSources());
        sourceComboBox.setItems(sourcelist);
        this.add(sourceComboBox, 2, 3, 1, 1);
        closeButton = new Button("done");
        this.add(closeButton,2,4,1,1);
        closeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String source = sourceComboBox.getValue();
                quizcontroller.saveTestSource(source);
                System.exit(0);
            }
        });


    }
}
