package de.icybits.util;

import java.util.Collections;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TeamMatcher extends Application {

	@Override
	public void start(Stage primaryStage) {
		VBox box = new VBox();
		TextField addPlayerField = new TextField();
		ListView<String> playerListView = new ListView<String>();
		Button button = new Button("Auswerten");
		TextArea textArea = new TextArea();
		textArea.setEditable(false);
		box.getChildren().addAll(addPlayerField, playerListView, button,
				textArea);
		Scene scene = new Scene(box);
		addPlayerField.setOnKeyReleased(e -> {
			if (e.getCode() == KeyCode.ENTER) {
				playerListView.getItems().add(addPlayerField.getText().trim());
				addPlayerField.clear();
			}
		});
		playerListView.setOnKeyReleased(e -> {
			if (e.getCode() == KeyCode.BACK_SPACE
					|| e.getCode() == KeyCode.DELETE) {
				playerListView.getItems().remove(
						playerListView.getSelectionModel().getSelectedItem());
			}
		});
		button.setOnAction(e -> {
			Collections.shuffle(playerListView.getItems());
			textArea.clear();
			StringBuilder builder = new StringBuilder();
			int teamsize = playerListView.getItems().size() / 2;
			int team = 1;
			for (int i = 0; i < playerListView.getItems().size(); i++) {
				if (i % teamsize == 0) {
					builder.append("Team " + team + "\n");
					team++;
				}
				builder.append(playerListView.getItems().get(i) + "\n");
			}
			textArea.setText(builder.toString());
		});
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
