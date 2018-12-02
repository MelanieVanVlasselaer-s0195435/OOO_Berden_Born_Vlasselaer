package view.panels;

import java.util.ArrayList;
import java.util.List;

import controller.Quizcontroller;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import model.Question;

public class TestPane extends GridPane {
	private Label questionField;
	private Button submitButton;
	private ToggleGroup radioGroup;
	private Quizcontroller quizcontroller;
	private Question question;
	private ArrayList<RadioButton> radiobuttons;


	
	public TestPane (Quizcontroller quizcontroller){
		//aanmaken controller
		this.quizcontroller = quizcontroller;
		//geeft een observableList terug met allemaal Question objecten in en neemt het eerste object er uit
		this.question = quizcontroller.getQuestions().get(1);
		//probleem als ik van dit object de statements opvraag dan krijg ik ALLE statements van alle objecten?!?!
		//probleem +- opgespoort -> worden verkeerd aangemaakt?!

		radiobuttons = new ArrayList<>();
		this.setPrefHeight(300);
		this.setPrefWidth(750);
		
		this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);

		questionField = new Label();
		questionField.setText(question.getQuestion());
		add(questionField, 0, 0, 1, 1);
		
		radioGroup = new ToggleGroup();

		for (String x : question.getStatements()) {
			RadioButton y = new RadioButton(x);
			this.radiobuttons.add(y);
		}

		int teller = 1;
		for (RadioButton x: radiobuttons) {
			x.setToggleGroup(radioGroup);
			add(x, 0, teller);
			teller++;
		}


		submitButton = new Button("Submit");
		add(submitButton, 0,5,1,1);
	}
	
	public void setProcessAnswerAction(EventHandler<ActionEvent> processAnswerAction) {
		submitButton.setOnAction(processAnswerAction);
	}

	/*public List<String> getSelectedStatements() {
		 List<String> selected = new ArrayList<String>();
		if(statementGroup.getSelectedToggle()!=null){
			selected.add(statementGroup.getSelectedToggle().getUserData().toString());
		}
		return selected;
	}
	*/
}
