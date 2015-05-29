package vista;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Principal extends Application {
	private static Stage primaryStage;
	private static BorderPane rootLayout;
	
	@Override
	public void start(Stage primaryStage) {
		
		
		try {
			this.primaryStage = primaryStage;
			
			//Parent root = FXMLLoader.load(getClass().getResource("fxml/Conexion.fxml"));
			FXMLLoader loader = new FXMLLoader(Principal.class.getResource("fxml/Conexion.fxml"));
			rootLayout = (BorderPane) loader.load();
			Scene scene = new Scene(rootLayout);
			primaryStage.setTitle("PCMAX");
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			//primaryStage.setFullScreen(false);
			
			primaryStage.show();
			
			//controlleroot = loader.getController();
			//controlleroot.setMain(this);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Stage getPrimaryStage() {
		return primaryStage;
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
