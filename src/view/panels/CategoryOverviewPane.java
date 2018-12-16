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
import model.Category;

import java.util.Observable;
import java.util.Observer;


public class CategoryOverviewPane extends GridPane implements Observer {
	private TableView table;
	private Button btnNew;


	// controller aangemaakt
	Quizcontroller quizcontroller;


	
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

		table.setItems(quizcontroller.getCategories());

		btnNew = new Button("New");
		this.add(btnNew, 0, 11, 1, 1);
		btnNew.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				quizcontroller.toonCategoryDetailPanel();
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

	// De geselecteerde lijn in table ophalen (voorlopig door middel van cast naar Category object)

	public void onEdit(TableView table) {
		if (table.getSelectionModel().getSelectedItem() != null) {
			String name = ((Category) table.getSelectionModel().getSelectedItem()).getName();
			String description = ((Category) table.getSelectionModel().getSelectedItem()).getDescription();
			String mainCategory = ((Category) table.getSelectionModel().getSelectedItem()).getHoofdcategorie();

			quizcontroller.toonCategoryEditPane(name, description, mainCategory);
		}
	}


	
	public void setNewAction(EventHandler<ActionEvent> newAction) {
		btnNew.setOnAction(newAction);
	}
	
	public void setEditAction(EventHandler<MouseEvent> editAction) {
		table.setOnMouseClicked(editAction);
	}


	//deze methode wordt door de observable opgeroepen
	@Override
	public void update(Observable o, Object arg) {
		//arg is het object dat de observable kan meegeven
		System.out.println("server");
	}
}
