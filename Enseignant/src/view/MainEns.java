package view;

import controller.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainEns extends Application {
	//public static ;

	@Override
	public void start(Stage primaryStage) throws Exception {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("Version_enseignant.fxml"));
		Parent root = loader.load();
		primaryStage.setTitle("Reconstitution_enseignant");
		primaryStage.setScene(new Scene(root));
		primaryStage.show();

		MainController controller = loader.getController();
		System.out.println(controller);
		//controller.initialise();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
