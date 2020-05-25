package view;

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

		FXMLLoader loader = new FXMLLoader(getClass().getResource("Version_etudiant_2.fxml"));
		Parent root = loader.load();
		primaryStage.setTitle("Reconstitution_etudiant");
		primaryStage.setScene(new Scene(root));
		primaryStage.show();

		MainEtuController controller = loader.getController();
		System.out.println(controller);
		//controller.initialise();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
