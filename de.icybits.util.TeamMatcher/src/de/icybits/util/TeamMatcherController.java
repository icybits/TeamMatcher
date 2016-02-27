package de.icybits.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javafx.beans.Observable;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;

/**
 * @author Iceac
 *
 */
public class TeamMatcherController {

	@FXML
	private TextField textFieldNewPlayer;
	@FXML
	private ListView<String> listViewPlayers;
	@FXML
	private TextArea textAreaResult;
	@FXML
	private Slider sliderCount;
	@FXML
	private Label labelCount;
	@FXML
	private ToggleGroup toggleGroupEvaluationType;
	@FXML
	private RadioButton radioButtonByPlayerCount, radioButtonByTeamCount;

	@FXML
	private void initialize() {
		textFieldNewPlayer.setOnKeyReleased(e -> {
			if (e.getCode() == KeyCode.ENTER) {
				addPlayer();
			}
		});

		listViewPlayers.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		listViewPlayers.setOnKeyReleased(e -> {
			if (e.getCode() == KeyCode.BACK_SPACE || e.getCode() == KeyCode.DELETE) {
				deletePlayers();
			}
		});
		listViewPlayers.getItems().addListener((Observable o) -> sliderCount.setMax(listViewPlayers.getItems().size()));
		labelCount.textProperty().bind(sliderCount.valueProperty().asString("%.0f"));
	}

	@FXML
	private void addPlayer() {
		if (isValidPlayerName(textFieldNewPlayer.getText().trim()))
			listViewPlayers.getItems().add(textFieldNewPlayer.getText().trim());
		textFieldNewPlayer.clear();
	}

	@FXML
	private void deletePlayers() {
		if (!listViewPlayers.getSelectionModel().isEmpty())
			listViewPlayers.getItems().removeAll(listViewPlayers.getSelectionModel().getSelectedItems());
	}

	@FXML
	private void evaluateTeams() {
		List<String> playerList = new ArrayList<>(listViewPlayers.getItems());
		Collections.shuffle(playerList);
		int teamSize;
		if (toggleGroupEvaluationType.getSelectedToggle() == radioButtonByPlayerCount)
			teamSize = (int) sliderCount.getValue();
		else
			teamSize = (int) (sliderCount.getValue() > 0 ? playerList.size() / sliderCount.getValue() : 0);
		if (teamSize > 0)
			textAreaResult.setText(buildTeams(playerList, teamSize));
	}

	private boolean isValidPlayerName(String string) {
		if (string == null)
			return false;
		if (string.isEmpty())
			return false;
		if (listViewPlayers.getItems().contains(string))
			return false;
		return true;
	}

	private String buildTeams(List<String> playerList, int teamSize) {
		StringBuilder builder = new StringBuilder();
		int team = 1;
		for (int i = 0; i < playerList.size(); i++) {
			if (i % teamSize == 0) {
				builder.append("Team ");
				builder.append(team);
				builder.append('\n');
				team++;
			}
			builder.append(playerList.get(i));
			builder.append('\n');
		}
		return builder.toString();
	}
}
