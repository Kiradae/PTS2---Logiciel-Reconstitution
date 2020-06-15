package controller;

import java.io.File;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import model.ExternalFileReader;

public class MainEnsController {

	@FXML
	private Button buttonOuvrir;

	public void openFile() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Selection");
		fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Save", "*.xml"),
				new ExtensionFilter("All Files", "*.*"));

		Stage chooser_stage = new Stage();
		File selectedFile = fileChooser.showOpenDialog(chooser_stage);
		if (selectedFile != null) {
			try {
				openSave(selectedFile.getAbsolutePath());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void openNew() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/Version_enseignant_nouveau_projet.fxml"));
		Scene root = loader.load();

		Stage primaryStage = (Stage) buttonOuvrir.getScene().getWindow();

		primaryStage.setScene(root);
		primaryStage.show();
	}

	private void openSave(String path) throws IOException {
		ExternalFileReader ext = new ExternalFileReader(path);
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/Version_enseignant_projet.fxml"));
		Scene root = loader.load();

		Controller2 controller = loader.getController();
		controller.initialisation(ext);

		Stage primaryStage = (Stage) buttonOuvrir.getScene().getWindow();

		primaryStage.setScene(root);
		primaryStage.show();
	}

	public void openRecent() throws IOException {
		openSave("./src/Recent_file.xml");
	}

}
