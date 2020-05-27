

import controller.MainEtuController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainEtu extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("view/Version_etudiant.fxml"));
		Scene root = loader.load();

		// primaryStage.getIcons().add(new Image(""));
		primaryStage.setTitle("Reconstitution_etudiant");
		primaryStage.setScene(root);
		primaryStage.show();

		// MainEtuController controller = loader.getController();
		// controller.initialisation("Texte_save.xml");

		// Controller1 controller = loader.getController();
		// controller.init();

		// controller.initialise();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
