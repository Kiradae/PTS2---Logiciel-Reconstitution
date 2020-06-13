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
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import model.ExternalFileReader;
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
	private TextField motSasie;

	@FXML
	private Label timeStart;
	
	@FXML
	private Label compteurFautes;
	
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

		ExternalFileReader ext = new ExternalFileReader();
		projet = ext.loadTeacherFile(path);
		Section[] sections = projet.getSections();

		projectName.setText(projet.getTitre());
		sectionConsigne.getChildren().add(new Text(projet.getConsigne()));
		compteurFautes.setText(String.valueOf(projet.getFautes()));

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

		Controller3 controller3 = loader.getController();
		controller3.initialisationRecap(projet);

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
			sectionTexte.getChildren().add(new Text(currentSection.getContentHidden()));

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

	public void checkEntree() {
		Tab current = tabPane.getSelectionModel().getSelectedItem();
		int index = ((int) current.getText().charAt(current.getText().length() - 1)) - 48;
		Section currentSection = projet.getSections()[index - 1];
		String newHiddenContent = currentSection.getContentHidden();

		if (!motSasie.getText().trim().contentEquals("")) {
			if (currentSection.getContent().contains(motSasie.getText().trim())) {
				
				currentSection.setContentHidden(
				newHiddenContent = newHiddenContent.substring(0,currentSection.getContent().indexOf(motSasie.getText().trim()))
						 		 + motSasie.getText().trim()
						 		 + newHiddenContent.substring(currentSection.getContent().indexOf(motSasie.getText().trim()) + motSasie.getText().trim().length())
				);
				
				sectionTexte.getChildren().clear();
				sectionTexte.getChildren().add(new Text(currentSection.getContentHidden()));
			} 
			else {
				projet.setFautes(projet.getFautes() + 1);
				compteurFautes.setText(String.valueOf(projet.getFautes()));
				System.out.println("no");
			}
		} 
		else {
			System.out.println("faut tapper quelque chose hein");
		}
	}
}
