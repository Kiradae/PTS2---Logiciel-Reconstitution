package controller;

import java.io.File;
import java.io.IOException;

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
import model.ExternalFile;
import model.Project;
import model.Section;
import model.Texte;

public class MainEtuController {

	@FXML
	private Button buttonOuvrir;

	@FXML
	private Button buttonToMenu;

	@FXML
	private TabPane TabPaneEva;

	@FXML
	private TabPane TabPaneEntr;

	@FXML
	private CheckBox SensiCasse;

	@FXML
	private Text sec1_consigne;

	@FXML
	private Text sec2_consigne;

	@FXML
	private Text sec3_consigne;

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
		// primaryStage.getIcons().add(new Image("https://.jpg"));
		primaryStage.setScene(root);
		primaryStage.show();
	}

	public void openMenu() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/Version_etudiant.fxml"));
		Scene root = loader.load();

		Stage primaryStage = (Stage) buttonToMenu.getScene().getWindow();
		// primaryStage.getIcons().add(new Image("https://.jpg"));
		primaryStage.setScene(root);
		primaryStage.show();
	}

	public void suivantEva() throws IOException {
		SingleSelectionModel<Tab> selectionModel = TabPaneEva.getSelectionModel();
		selectionModel.select(1);
	}

	public void suivantEntr() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/Version_etudiant.fxml"));
		Scene root = loader.load();

		Stage primaryStage = (Stage) buttonToMenu.getScene().getWindow();
		// primaryStage.getIcons().add(new Image("https://.jpg"));
		primaryStage.setScene(root);
		primaryStage.show();
	}

	public void nextTab() {
		TabPaneEntr.getSelectionModel().clearAndSelect(TabPaneEntr.getSelectionModel().getSelectedIndex() + 1);
	}

	public void prevTab() {
		TabPaneEntr.getSelectionModel().clearAndSelect(TabPaneEntr.getSelectionModel().getSelectedIndex() - 1);
	}

}
