package controller;

import java.io.File;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.media.MediaView;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import model.ExternalFileReader;
import model.ExternalFileWriter;
import model.Project;

public class NouvProjController {

	private File fileEmplacement;

	@FXML
	private MediaView myMedia;

	@FXML
	private TextField getTitle;

	@FXML
	private TextField save;

	@FXML
	private TextField ressource;

	@FXML
	private TextField saveR;

	@FXML
	private Text titre;

	public void retour() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/Version_enseignant_accueil.fxml"));
		Scene root = loader.load();

		Stage primaryStage = (Stage) save.getScene().getWindow();

		primaryStage.setScene(root);
		primaryStage.show();
	}

	public void changeName() {
		titre.setText(getTitle.getText());
	}

	public void linkSave() {
		ExternalFileWriter ext = new ExternalFileWriter();
		fileEmplacement = ext.chooseWhere();

		save.setText(fileEmplacement.getAbsolutePath());
	}

	public void getRessource() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Selection");
		fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Video", "*.mp4"),
				new ExtensionFilter("Audio", "*.mp3"), new ExtensionFilter("All Files", "*.*"));

		Stage chooser_stage = new Stage();
		File selectedFile = fileChooser.showOpenDialog(chooser_stage);
		if (selectedFile != null) {
			ExternalFileReader ext = new ExternalFileReader(selectedFile.getAbsolutePath());
			ext.loadVideo(myMedia);
			myMedia.getMediaPlayer().pause();
			ressource.setText(selectedFile.getAbsolutePath());
		}
	}

	public void createProj() throws IOException {
		if (myMedia.getMediaPlayer() != null && fileEmplacement != null) {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/Version_enseignant_projet.fxml"));
			Scene root = loader.load();

			Controller2 controller = loader.getController();
			Project proj = new Project(titre.getText());
			proj.setFileEmplacement(fileEmplacement);
			controller.initialisation(proj, myMedia);

			Stage primaryStage = (Stage) save.getScene().getWindow();

			primaryStage.setScene(root);
			primaryStage.show();
		}
	}

}
