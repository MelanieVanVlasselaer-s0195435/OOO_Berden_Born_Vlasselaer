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
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class InfoPanel extends GridPane {
    private Button closeButton;
    private Label questionField;
    Label lblstrategy;
    ComboBox<String> stratComboBox;
    private Quizcontroller quizcontroller;
    private ArrayList<String> previousValue;

    public InfoPanel(Quizcontroller quizcontroller) {
        this.quizcontroller = quizcontroller;
        setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);
        questionField = new Label();

        //Deze code hier plaatsen is toch niet MVC? - TB
        previousValue = quizcontroller.getPreviousScore();
        String txt = " ";
        for (String x: previousValue) {
            txt += x + "\n";
        }
        if (!txt.equals(" ")) {
            questionField.setText(txt);
        }
        else {
            questionField.setText("U hebt deze test nog nooit afgelegd");
        }
        add(questionField, 0, 0, 1, 1);
        closeButton = new Button("Finish");
        add(closeButton,0, 1, 1, 1);
        closeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                quizcontroller.writeCategories();
                quizcontroller.writeQuestions();
                System.exit(0);
            }
        });
        lblstrategy = new Label("Kies uw gewenste strategy");
        this.add(lblstrategy, 0, 2, 1, 1);
        stratComboBox = new ComboBox<>();
        ObservableList<String> strategylist = FXCollections.observableList(quizcontroller.getEvaluationMethods());
        stratComboBox.setItems(strategylist);
        this.add(stratComboBox, 0, 3, 1, 1);
        Button submitButton = new Button("submit");
        this.add(submitButton,0,4,1,1);
        submitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String strategy = stratComboBox.getValue();
                quizcontroller.saveEvaluationMethode(strategy);
            }
        });
        setHalignment(closeButton, HPos.CENTER);
    }
}