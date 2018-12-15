package view.panels;

import controller.Quizcontroller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import model.Category;

public class EditCategoryPane extends GridPane {
    private Button btnOK, btnCancel;
    private Quizcontroller controller;

    // String mainCategory eventueel weglaten gezien alle categorieën moeten weergegeven worden - MVV
    public EditCategoryPane(Quizcontroller controller, String name, String description, String mainCategory) {
        this.controller = controller;
        this.setPrefHeight(150);
        this.setPrefWidth(300);

        this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);

        this.add(new Label("Title:"), 0, 0, 1, 1);
        TextField titleField = new TextField(name);
        this.add(titleField, 1, 0, 1, 1);


        this.add(new Label("Description:"), 0, 1, 1, 1);
        TextField descriptionField = new TextField(description);
        this.add(descriptionField, 1, 1, 1, 1);

        this.add(new Label("Main Category:"), 0, 2, 1, 1);
        ComboBox categoryField = new ComboBox<>();
        for(Category x:controller.getCategories()){
            categoryField.getItems().addAll(x.getName());
        }
        this.add(categoryField, 1, 2, 1, 1);



        btnOK = new Button("Save");
        btnOK.isDefaultButton();
        this.add(btnOK, 1, 3, 1, 1);
        btnOK.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controller.editCategory(name, titleField.getText(), descriptionField.getText(), (String) categoryField.getValue());
                controller.closeSecondStage();
            }
        });

        btnCancel = new Button("Cancel");
        this.add(btnCancel, 0, 3, 1, 1);
        btnCancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controller.closeSecondStage();
            }
        });
    }


}
