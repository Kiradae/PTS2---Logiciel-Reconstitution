package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class MainController {

	@FXML
	private TabPane wholeTabPane;

	@FXML
	private Tab nouvProj;

	@FXML
	private Button creerProjet;

	@FXML
	public void initialise() {
		creerProjet.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				Tab temp = nouvProj;
				wholeTabPane.getTabs().add(temp);
			}
		});
	}

}
