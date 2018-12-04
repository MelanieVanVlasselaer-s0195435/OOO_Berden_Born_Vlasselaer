package view.panels;

import java.util.ArrayList;
import java.util.Enumeration;
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
	int questionIndex;
	private int juisteAntwoorden;



	public TestPane (Quizcontroller quizcontroller){
		questionIndex = 0;
		this.quizcontroller = quizcontroller;
		this.question = quizcontroller.getQuestions().get(questionIndex);
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
		submitButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				String antwoord = "";
				for (RadioButton x : radiobuttons){
					if (x.isSelected()){
						antwoord = x.getText();
					}
				}
				quizcontroller.controlAnswer(antwoord,questionIndex);
				questionIndex++;
				generateNextQuestion(questionIndex);
			}
		});
	}

	public void generateNextQuestion(int questionIndex) {
		if (questionIndex >= quizcontroller.getQuestions().size()) {
			quizcontroller.sluitDetailPanel();
			//tijdelijke code, moet een hashmap met score per categorie teruggeven - TB
			//quizcontroller.getResult();
			System.out.println(quizcontroller.getResult());
		}
		else {
			this.question = quizcontroller.getQuestions().get(questionIndex);
			questionField.setText(question.getQuestion());

			int teller = 0;
			for (String x : question.getStatements()) {
				radiobuttons.get(teller).setText(x);
				teller++;
			}
		}

	}
	// deze setAnswerAction is niet nodig als we de controller meegeven -TB
	public void setProcessAnswerAction(EventHandler<ActionEvent> processAnswerAction) {
		submitButton.setOnAction(processAnswerAction);
	}

	public List<String> getSelectedStatements() {
		List<String> selected = new ArrayList<String>();
		if(radioGroup.getSelectedToggle()!=null){
			selected.add(radioGroup.getSelectedToggle().getUserData().toString());
		}
		return selected;
	}

}
