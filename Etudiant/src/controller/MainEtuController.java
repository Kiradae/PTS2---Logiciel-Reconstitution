package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import model.ExternalFile;
import model.Section;

public class MainEtuController {

	@FXML
	private TabPane tabPane;

	@FXML
	private Tab section;

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
