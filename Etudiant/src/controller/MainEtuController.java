package controller;

import java.io.File;
import java.io.IOException;

import org.w3c.dom.Document;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import model.ExternalFileReader;
import model.Project;
import model.Section;
import model.Texte;

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
		fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Reconstitution", "*.xml"),
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

		FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/Version_etudiant_2.fxml"));
		Scene root = loader.load();

		Controller2 controller = loader.getController();
		controller.initialisationExo(path);

		Stage primaryStage = (Stage) buttonOuvrir.getScene().getWindow();

		primaryStage.setScene(root);
		primaryStage.show();

	}

	public void openRecent() throws IOException {
		openExo("./src/Recent_file.xml");
	}

}
