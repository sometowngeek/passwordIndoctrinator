package application;
	
import java.io.File;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			File file = new File("resources/application/resources/PasswordIndoctrinator.fxml");
			file.exists();
			final Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("application/resources/PasswordIndoctrinator.fxml"));
			final Scene scene = new Scene(root);
			
			
			
			primaryStage.setTitle("Password Indoctrinator");
			primaryStage.setScene(scene);
//			primaryStage.setOnHidden(e -> controller.shutdown());
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
			Runtime.getRuntime().exit(1);
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void shutdown() {
		
	}
}
