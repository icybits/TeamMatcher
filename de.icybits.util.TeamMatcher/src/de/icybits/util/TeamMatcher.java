package de.icybits.util;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TeamMatcher extends Application {

	@Override
	public void start(Stage primaryStage) throws IOException {
		VBox box = FXMLLoader.load(getClass().getResource("TeamMatcher.fxml"));
		Scene scene = new Scene(box);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
