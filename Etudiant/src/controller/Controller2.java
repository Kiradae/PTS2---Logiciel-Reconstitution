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

public class Controller2 {
	private Project projet;
	private Tab currentTab;

	@FXML
	private TabPane tabPane;

	@FXML
	private TextFlow sectionConsigne;

	@FXML
	private TextFlow sectionTexte;

	@FXML
	private Label timeStart;

	@FXML
	private Label timeEnd;

	@FXML
	private Text projectName;

	@FXML
	private Button buttonToSave1;

	@FXML
	private Button buttonToSave2;

	public void initialisationExo(String path) {
		tabPane.getTabs().clear();

		ExternalFile ext = new ExternalFile();
		projet = ext.loadTeacherFile(path);
		Section[] sections = projet.getSections();

		projectName.setText(projet.getTitre());
		sectionConsigne.getChildren().add(new Text(projet.getConsigne()));

		for (int i = 0; i < sections.length; i++) {
			Tab temp = new Tab("Section" + (i + 1));
			temp.setOnSelectionChanged(new EventHandler<Event>() {
				public void handle(Event event) {
					setSectionByTab(temp);
				}
			});
			tabPane.getTabs().add(i, temp);
		}

	}

	public void openSave1() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/Version_etudiant_4.fxml"));
		Scene root = loader.load();

		Stage primaryStage = (Stage) buttonToSave1.getScene().getWindow();

		primaryStage.setScene(root);
		primaryStage.show();
	}

	public void openSave2() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/Version_etudiant_4.fxml"));
		Scene root = loader.load();

		Stage primaryStage = (Stage) buttonToSave2.getScene().getWindow();

		primaryStage.setScene(root);
		primaryStage.show();
	}

	public void setSectionByTab(Tab current) {
		if (currentTab == null || !currentTab.getText().equals(current.getText())) {
			currentTab = current;
			int index = ((int) current.getText().charAt(current.getText().length() - 1)) - 48;
			Section currentSection = projet.getSections()[index - 1];

			sectionTexte.getChildren().clear();
			sectionTexte.getChildren().add(new Text(currentSection.getContent()));

			timeStart.setText(String.valueOf(currentSection.getStart()).replace('.', ':'));

			timeEnd.setText(String.valueOf(currentSection.getEnd()).replace('.', ':'));
		}
	}

	public void nextTab() {
		tabPane.getSelectionModel().clearAndSelect(tabPane.getSelectionModel().getSelectedIndex() + 1);
	}

	public void prevTab() {
		tabPane.getSelectionModel().clearAndSelect(tabPane.getSelectionModel().getSelectedIndex() - 1);
	}

}
