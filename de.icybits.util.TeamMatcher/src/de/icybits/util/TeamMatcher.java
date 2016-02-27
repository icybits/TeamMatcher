package de.icybits.util;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TeamMatcher extends Application {

	@Override
	public void start(Stage primaryStage) throws IOException {
		URL url = TeamMatcher.class.getResource("TeamMatcher.fxml");
		ResourceBundle bundle = ResourceBundle.getBundle("i18n.TeamMatcher");
		FXMLLoader loader = new FXMLLoader(url, bundle);
		loader.setController(new TeamMatcherController());
		VBox box = loader.load();
		Scene scene = new Scene(box);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
