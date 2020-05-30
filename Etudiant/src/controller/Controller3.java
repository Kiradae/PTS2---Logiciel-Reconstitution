package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import model.ExternalFile;
import model.Project;
import model.Section;

public class Controller3 {

	@FXML
	private TextFlow consigneRecap;

	@FXML
	private TextFlow texteRecap;

	@FXML
	private Text projectRecap;

	@FXML
	private Label compteurFautes;
	
	@FXML
	private Button buttonToMenu;

	public void initialisationRecap(Project projet) {
		Section[] s = projet.getSections();
		String temp = "";

		projectRecap.setText(projet.getTitre());
		consigneRecap.getChildren().add(new Text(projet.getConsigne()));
		compteurFautes.setText(String.valueOf(projet.getFautes()));		
		
		temp = temp + s[0].getContentHidden();
		
		for (int i=1; i<s.length; i++) {
			temp = temp + " " + s[i].getContentHidden();
		}
		
		texteRecap.getChildren().add(new Text(temp));
	}
	
	public void openMenu() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/Version_etudiant.fxml"));
		Scene root = loader.load();

		Stage primaryStage = (Stage) buttonToMenu.getScene().getWindow();
		// primaryStage.getIcons().add(new Image("https://.jpg"));
		primaryStage.setScene(root);
		primaryStage.show();
	}
}
