package DataEntry;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{

	Scene scene;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {


		Parent root = FXMLLoader.load(getClass().getResource("VennFXML_Main.fxml"));														

		stage.setTitle("DataEntry");


		this.scene = new Scene(root, 1000, 700);
		stage.setScene(scene);
		stage.show(); 
	}

}


