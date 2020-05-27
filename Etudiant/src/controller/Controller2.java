package controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import model.ExternalFile;
import model.Project;
import model.Section;

public class Controller2 {

	@FXML
	private TabPane tabPane;

	@FXML
	private Tab section;

	@FXML
	private TextFlow sectionConsigne;

	@FXML
	private TextFlow sectionTexte;

	@FXML
	private Text timeCode;

	@FXML
	private Text projectName;
	
	@FXML
	private Button buttonToSave1;

	@FXML
	private Button buttonToSave2;

	public void initialisationExo(String path) {
		ExternalFile ext = new ExternalFile();
		Project projet = ext.loadTeacherFile(path);
		Section[] sections = projet.getSections();

		projectName.setText(projet.getTitre());
		for (int i = 0; i < sections.length; i++) {
			Text temp_texte = new Text(sections[i].getHelp());
			sectionConsigne.getChildren().add(temp_texte);
			temp_texte = new Text(sections[i].getContent());
			sectionTexte.getChildren().add(temp_texte);
			timeCode.setText("début " + String.valueOf(sections[i].getStart()).replace('.', ':') + "\nfin "
					+ String.valueOf(sections[i].getEnd()).replace('.', ':'));

			Tab temp = section;
			//temp.setText("Section" + (i + 1));
			//tabPane.getTabs().add(i, temp);

			System.out.println(sections[i]);
		}
	}

	public void openSave1() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/Version_etudiant_4.fxml"));
		Scene root = loader.load();

		Stage primaryStage = (Stage) buttonToSave1.getScene().getWindow();
		// primaryStage.getIcons().add(new Image("https://.jpg"));
		primaryStage.setScene(root);
		primaryStage.show();
	}

	public void openSave2() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/Version_etudiant_4.fxml"));
		Scene root = loader.load();

		Stage primaryStage = (Stage) buttonToSave2.getScene().getWindow();
		// primaryStage.getIcons().add(new Image("https://.jpg"));
		primaryStage.setScene(root);
		primaryStage.show();
	}

}
