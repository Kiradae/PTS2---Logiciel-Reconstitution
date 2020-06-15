package controller;

import java.io.File;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TabPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import model.ExternalFileReader;

public class MainEtuController {

	@FXML
	private Button buttonOuvrir;

	@FXML
	private TabPane TabPaneEva;

	@FXML
	private TabPane TabPaneEntr;

	@FXML
	private CheckBox SensiCasse;

	public void openFile() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Selection");
		fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Save", "*.xml"),
				new ExtensionFilter("All Files", "*.*"));

		Stage chooser_stage = new Stage();
		File selectedFile = fileChooser.showOpenDialog(chooser_stage);
		if (selectedFile != null) {
			try {
				openExo(selectedFile.getAbsolutePath());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void openExo(String path) throws IOException {
		ExternalFileReader ext = new ExternalFileReader(path);

		if (!ext.isNull()) {
			String openFxml = "../view/Version_etudiant_";
			if (ext.isEval()) {
				openFxml += "2";
			} else
				openFxml += "3";

			FXMLLoader loader = new FXMLLoader(getClass().getResource(openFxml + ".fxml"));
			Scene root = loader.load();

			Controller2 controller = loader.getController();
			controller.initialisationExo(ext);

			Stage primaryStage = (Stage) buttonOuvrir.getScene().getWindow();

			primaryStage.setScene(root);
			primaryStage.show();
		}
	}

	public void openRecent() throws IOException {
		openExo("./src/Recent_file.xml");
	}

}
