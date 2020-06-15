package controller;

import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.media.MediaView;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

import model.ExternalFileReader;
import model.ExternalFileWriter;
import model.Project;
import model.Section;

public class Controller2 {

	private double maxTime;

	private Project projet;

	private Tab currentTab;

	private NumberFormat format;

	@FXML
	private MediaView myMedia;

	@FXML
	private TabPane tabPane;

	@FXML
	private TextArea sectionConsigne;

	@FXML
	private TextArea sectionTexte;

	@FXML
	private TextArea sectionAide;

	@FXML
	private Text timeStart;

	@FXML
	private Text timeEnd;

	@FXML
	private Text projectName;

	@FXML
	private Button buttonToSave;

	@FXML
	private CheckBox sensiCasse;

	@FXML
	private CheckBox modeEval;

	public void initialisation(ExternalFileReader ext) {
		this.format = NumberFormat.getInstance();
		this.format.setMaximumFractionDigits(2);
		tabPane.getTabs().clear();

		projet = ext.loadTeacherFile();
		ext.loadVideo(myMedia);

		projectName.setText(projet.getTitre());
		sectionConsigne.setText(projet.getConsigne());

		for (int i = 0; i < projet.getSections().size(); i++) {
			Tab temp = new Tab("Section" + (i + 1));
			temp.setOnSelectionChanged(new EventHandler<Event>() {
				public void handle(Event event) {
					setSectionByTab(temp);
				}
			});
			tabPane.getTabs().add(i, temp);
		}

		sensiCasse.setSelected(projet.isSensiCasse());
		modeEval.setSelected(projet.isModeEval());
	}

	public void initialisation(Project projet, MediaView media) {
		this.format = NumberFormat.getInstance();
		this.format.setMaximumFractionDigits(2);
		myMedia.setMediaPlayer(media.getMediaPlayer());

		this.maxTime = myMedia.getMediaPlayer().getTotalDuration().toMinutes();
		tabPane.getTabs().clear();
		projet.getSections().add(new Section(0.0, maxTime));
		projectName.setText(projet.getTitre());
		this.projet = projet;

		Tab temp = new Tab("Section 1");
		temp.setOnSelectionChanged(new EventHandler<Event>() {
			public void handle(Event event) {
				setSectionByTab(temp);
			}
		});
		tabPane.getTabs().add(0, temp);
		currentTab = temp;

		timeStart.setText(format.format(this.projet.getSections().get(0).getStart()).replace(".", ":"));
		timeEnd.setText(format.format(this.projet.getSections().get(0).getEnd()).replace(".", ":"));
	}

	public void ajSection() throws IOException {
		Tab temp = new Tab("Section " + (tabPane.getTabs().size() + 1));
		temp.setOnSelectionChanged(new EventHandler<Event>() {
			public void handle(Event event) {
				setSectionByTab(temp);
			}
		});
		tabPane.getTabs().add(temp);

		Section new_section = new Section(0.0, 0.0);
		this.projet.getSections().add(new_section);
	}

	public void supprSection() {
		if (tabPane.getTabs().size() > 1) {
			int index = tabPane.getTabs().indexOf(currentTab);
			ArrayList<Section> s = projet.getSections();
			// n'est pas premier
			if (index > 0) {
				// n'est pas dernier
				if (index < tabPane.getTabs().size() - 1) {
					s.get(index - 1).setEnd(s.get(index).getStart());
					s.get(index + 1).setStart(s.get(index).getEnd());
				} else
					s.get(index - 1).setEnd(maxTime);
			} else
				s.get(1).setStart(0.0);

			s.remove(index);
			tabPane.getTabs().remove(index);
		}
	}

	public void enregistrer() {
		this.projet.setConsigne(sectionConsigne.getText());
		Section current = this.projet.getSections().get(getCurrentTabIndex() - 1);
		current.setContent(sectionTexte.getText());
		current.setHelp(sectionAide.getText());

		this.projet.setSensiCasse(sensiCasse.isSelected());
		this.projet.setModeEval(modeEval.isSelected());

		ExternalFileWriter ext = new ExternalFileWriter();
		ext.saveExo(projet);
	}

	public void retour() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/Version_enseignant_accueil.fxml"));
		Scene root = loader.load();

		Stage primaryStage = (Stage) myMedia.getScene().getWindow();

		primaryStage.setScene(root);
		primaryStage.show();
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

	public void setSectionByTab(Tab current) {
		if (currentTab == null || !currentTab.getText().equals(current.getText())) {
			currentTab = current;
			Section currentSection = projet.getSections().get(getCurrentTabIndex() - 1);

			sectionTexte.clear();
			sectionTexte.setText(currentSection.getContent());

			sectionAide.clear();
			sectionAide.setText(currentSection.getHelp());

			timeStart.setText(format.format(currentSection.getStart()).replace(".", ":"));
			timeEnd.setText(format.format(currentSection.getEnd()).replace(".", ":"));
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

}
