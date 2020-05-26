package controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.ExternalFile;
import model.Section;

public class MainEtuController {

	@FXML
	private TabPane tabPane;

	@FXML
	private Tab section;
	
	@FXML
	private Button buttonOuvrir;
	
	@FXML
	private Button buttonToSave1;
	
	@FXML
	private Button buttonToSave2;
	
	@FXML
	private Button buttonToMenu;
	
	@FXML
	private TabPane TabPaneEva;

	@FXML
	private TabPane TabPaneEntr;
	
	public void openFile() {
	FileChooser fileChooser = new FileChooser();
	fileChooser.setTitle("Open Resource File");
	//fileChooser.showOpenDialog(stage);
	}
	
	public void openExo() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/Version_etudiant_2.fxml"));
		Scene root = loader.load();

		Stage primaryStage = (Stage) buttonOuvrir.getScene().getWindow();
		//primaryStage.getIcons().add(new Image("https://.jpg"));
		primaryStage.setScene(root);
		primaryStage.setResizable(false);
		primaryStage.show();
	}
	
	public void openSave1() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/Version_etudiant_4.fxml"));
		Scene root = loader.load();

		Stage primaryStage = (Stage) buttonToSave1.getScene().getWindow();
		//primaryStage.getIcons().add(new Image("https://.jpg"));
		primaryStage.setScene(root);
		primaryStage.setResizable(false);
		primaryStage.show();
	}
	
	public void openSave2() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/Version_etudiant_4.fxml"));
		Scene root = loader.load();

		Stage primaryStage = (Stage) buttonToSave2.getScene().getWindow();
		//primaryStage.getIcons().add(new Image("https://.jpg"));
		primaryStage.setScene(root);
		primaryStage.setResizable(false);
		primaryStage.show();
	}
	
	public void openMenu() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/Version_etudiant.fxml"));
		Scene root = loader.load();

		Stage primaryStage = (Stage) buttonToMenu.getScene().getWindow();
		//primaryStage.getIcons().add(new Image("https://.jpg"));
		primaryStage.setScene(root);
		primaryStage.setResizable(false);
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
		//primaryStage.getIcons().add(new Image("https://.jpg"));
		primaryStage.setScene(root);
		primaryStage.setResizable(false);
		primaryStage.show();
	}

	public void initialisation(String path) {
		tabPane.getTabs().clear();

		ExternalFile ext = new ExternalFile();
		Section[] sections = ext.loadTeacherFile(path);

		for (int i = 0; i < sections.length; i++) {
			Tab temp = section;
			temp.setText(temp.getText() + (i + 1));
			tabPane.getTabs().add(temp);
		}
	}

}
