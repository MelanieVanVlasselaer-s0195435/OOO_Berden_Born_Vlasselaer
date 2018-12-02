package application;

import controller.Quizcontroller;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import view.panels.AssesMainPane;
import view.panels.CategoryDetailPane;
import view.panels.CategoryOverviewPane;
import view.panels.MessagePane;
import view.panels.QuestionDetailPane;
import view.panels.QuestionOverviewPane;
import view.panels.TestPane;

public class Main extends Application {
	private Quizcontroller quizcontroller;

	@Override
	public void start(Stage primaryStage) {

		quizcontroller = new Quizcontroller();
		quizcontroller.start(quizcontroller);



	}

	public static void main(String[] args) {
		launch(args);
	}
}
