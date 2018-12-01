package view.panels;

import controller.Quizcontroller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Category;

import java.util.ArrayList;


public class CategoryOverviewPane extends GridPane {
	private TableView table;
	private Button btnNew;


	// controller aangemaakt
	Quizcontroller quizcontroller;

	//test voor categorieën weer te geven (voorlopige, niet volgens Design pattern - TB)
	ArrayList<Category> categories;

	
	public CategoryOverviewPane(Quizcontroller quizcontroller) {
		this.quizcontroller = quizcontroller;
		this.setPadding(new Insets(5, 5, 5, 5));
		this.setVgap(5);
		this.setHgap(5);

		this.add(new Label("Categories:"), 0, 0, 1, 1);

		table = new TableView<>();
		table.setPrefWidth(REMAINING);
		TableColumn nameCol = new TableColumn<>("Name");
		// deze PropertyValueFactory is aangepast! -> naar name veranderd mag dit? - FB
		// Of moeten we het attribuut van object Categorie aanpassen? - FB
		nameCol.setCellValueFactory(new PropertyValueFactory("name"));
		table.getColumns().add(nameCol);
		TableColumn descriptionCol = new TableColumn<>("Description");
		descriptionCol.setCellValueFactory(new PropertyValueFactory("description"));
		table.getColumns().add(descriptionCol);
		this.add(table, 0, 1, 2, 6);

		//invoegen van de categories
		categories = quizcontroller.getCategories();
		for (Category x : categories) {
			table.getItems().add(x);
		}

		btnNew = new Button("New");
		this.add(btnNew, 0, 11, 1, 1);
		btnNew.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				quizcontroller.toonCategoryDetailPanel();
			}
		});
	}


	
	public void setNewAction(EventHandler<ActionEvent> newAction) {
		btnNew.setOnAction(newAction);
	}
	
	public void setEditAction(EventHandler<MouseEvent> editAction) {
		table.setOnMouseClicked(editAction);
	}

}
