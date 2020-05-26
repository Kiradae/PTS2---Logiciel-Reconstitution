

import controller.MainEtuController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainEtu extends Application {
	//public static ;

	@Override
	public void start(Stage primaryStage) throws Exception {

		Scene root = FXMLLoader.load(getClass().getResource("view/Version_etudiant.fxml"));
        //primaryStage.getIcons().add(new Image(""));
        primaryStage.setTitle("Reconstitution");
        primaryStage.setScene(root);
        primaryStage.show();

		//controller.initialise();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
