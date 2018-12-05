package view.panels;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import controller.Quizcontroller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;


public class TestPane extends GridPane {
	private Label questionField;
	private Button submitButton;
	private ToggleGroup radioGroup;
	private Quizcontroller quizcontroller;
	private ArrayList<RadioButton> radiobuttons;
	private LinkedList<String> nextQuestion;
	int questionIndex;
	private int juisteAntwoorden;



	public TestPane (Quizcontroller quizcontroller){
		questionIndex = 0;
		this.quizcontroller = quizcontroller;
		nextQuestion = quizcontroller.getNextQuestion(questionIndex);

		radiobuttons = new ArrayList<>();
		this.setPrefHeight(300);
		this.setPrefWidth(750);

		this.setPadding(new Insets(5, 5, 5, 5));
		this.setVgap(5);
		this.setHgap(5);

		questionField = new Label();
		questionField.setText(nextQuestion.poll());
		add(questionField, 0, 0, 1, 1);

		radioGroup = new ToggleGroup();
		for (String x : nextQuestion) {
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
			quizcontroller.closeSecondStage();
			quizcontroller.displayResult();
		}
		else {
			nextQuestion = quizcontroller.getNextQuestion(questionIndex);
			questionField.setText(nextQuestion.poll());

			int teller = 0;
			for (String x : nextQuestion) {
				radiobuttons.get(teller).setText(x);
				teller++;
			}
		}

	}

	// functie van Fox wordt niet gebruikt? -FB

	public List<String> getSelectedStatements() {
		List<String> selected = new ArrayList<String>();
		if(radioGroup.getSelectedToggle()!=null){
			selected.add(radioGroup.getSelectedToggle().getUserData().toString());
		}
		return selected;
	}

}
