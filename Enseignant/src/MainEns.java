
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainEns extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("view/Version_enseignant_accueil.fxml"));

		Scene root = loader.load();

		primaryStage.setTitle("Reconstitution_enseignant");
		primaryStage.setScene(root);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
