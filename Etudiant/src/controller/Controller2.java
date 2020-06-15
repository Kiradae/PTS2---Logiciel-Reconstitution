package controller;

import java.io.File;
import java.io.IOException;

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
import javafx.scene.media.MediaView;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import model.ExternalFileReader;
import model.Project;
import model.Section;
import model.Verification;

public class Controller2 {

	private Project projet;

	private Tab currentTab;

	@FXML
	private MediaView myMedia;

	@FXML
	private TabPane tabPane;

	@FXML
	private TextFlow sectionConsigne;

	@FXML
	private TextFlow sectionTexte;

	@FXML
	private TextField motSaisie;

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

	public void initialisationExo(ExternalFileReader ext) {
		tabPane.getTabs().clear();

		projet = ext.loadTeacherFile();
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

	public void openVideo() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Selection");
		fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Video", "*.mp4"),
				new ExtensionFilter("Audio", "*.mp3"), new ExtensionFilter("All Files", "*.*"));

		Stage chooser_stage = new Stage();
		File selectedFile = fileChooser.showOpenDialog(chooser_stage);
		if (selectedFile != null) {
			ExternalFileReader ext = new ExternalFileReader(selectedFile.getAbsolutePath());
			ext.loadVideo(myMedia);
		}
	}

	public void play() {
		myMedia.getMediaPlayer().play();
	}

	public void pause() {
		myMedia.getMediaPlayer().pause();
	}

	public void stop() {
		myMedia.getMediaPlayer().stop();
	}

	public void solution() {
		Section[] s = projet.getSections();
		for (int i = 0; i < s.length; i++) {
			s[i].setContentHidden(s[i].getContent());
		}

		motSaisie.setDisable(true);
		compteurFautes.setDisable(true);
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
			Section currentSection = projet.getSections()[getCurrentTabIndex() - 1];

			sectionTexte.getChildren().clear();
			sectionTexte.getChildren().add(new Text(currentSection.getContentHidden()));

			if (projet.isModeEval()) {
				timeStart.setText(String.valueOf(currentSection.getStart()).replace('.', ':'));

				timeEnd.setText(String.valueOf(currentSection.getEnd()).replace('.', ':'));
			}
		}
	}

	public int getCurrentTabIndex() {
		return ((int) currentTab.getText().charAt(currentTab.getText().length() - 1)) - 48;
	}

	public void nextTab() {
		tabPane.getSelectionModel().clearAndSelect(tabPane.getSelectionModel().getSelectedIndex() + 1);
	}

	public void prevTab() {
		tabPane.getSelectionModel().clearAndSelect(tabPane.getSelectionModel().getSelectedIndex() - 1);
	}

	public void checkEntree() {
		String saisie = motSaisie.getText().trim();
		if (!saisie.contentEquals("")) {
			Section currentSection = projet.getSections()[getCurrentTabIndex() - 1];
			Verification verif = new Verification(currentSection, saisie, projet.isSensiCasse());

			if (verif.isSaisieValid()) {
				verif.affichTexte();

				sectionTexte.getChildren().clear();
				sectionTexte.getChildren().add(new Text(currentSection.getContentHidden()));

			} else {
				projet.setFautes(projet.getFautes() + 1);
				compteurFautes.setText(String.valueOf(projet.getFautes()));
				System.out.println("Mot invalide");
				projet.setStockFautes(saisie);
			}

			motSaisie.setText("");
		} else {
			System.out.println("Saisie vide");
		}
	}
}
