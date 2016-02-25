package de.icybits.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javafx.beans.Observable;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory.IntegerSpinnerValueFactory;
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
	private Spinner<Integer> spinnerCount;
	@FXML
	private ToggleGroup toggleGroupEvaluationType;
	@FXML
	private RadioButton radioButtonByPlayerCount, radioButtonByTeamCount;

	@FXML
	private void initialize() {
		listViewPlayers.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		listViewPlayers.setOnKeyReleased(e -> {
			if (e.getCode() == KeyCode.BACK_SPACE || e.getCode() == KeyCode.DELETE) {
				deletePlayers();
			}
		});
		textFieldNewPlayer.setOnKeyReleased(e -> {
			if (e.getCode() == KeyCode.ENTER) {
				addPlayer();
			}
		});
		IntegerSpinnerValueFactory valueFactory = new IntegerSpinnerValueFactory(1, 1, 0);
		spinnerCount.setValueFactory(valueFactory);
		listViewPlayers.getItems().addListener((Observable o) -> {
			valueFactory.setMax(listViewPlayers.getItems().size());
			textAreaResult.clear();
		});
	}

	@FXML
	private void addPlayer() {
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
			teamSize = spinnerCount.getValue();
		else
			teamSize = spinnerCount.getValue() > 0 ? playerList.size() / spinnerCount.getValue() : 0;
		textAreaResult.setText(buildTeams(playerList, teamSize));
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
