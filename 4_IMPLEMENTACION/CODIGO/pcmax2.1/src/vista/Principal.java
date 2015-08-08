package vista;

import java.io.IOException;

import controlador.ControladorErrores;
import controlador.ControladorSplash;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;



public class Principal extends Application {
	private ControladorErrores ce;
	private static Stage primaryStage;
	private static BorderPane rootLayout;
	public Stage dStage;
	
	@SuppressWarnings("static-access")
	@Override
	public void start(Stage primaryStage) {
		
		ce= new ControladorErrores();
		try {
			this.primaryStage = primaryStage;
			
			//Parent root = FXMLLoader.load(getClass().getResource("fxml/Conexion.fxml"));
			FXMLLoader loader = new FXMLLoader(Principal.class.getResource("fxml/Conexion.fxml"));
			rootLayout = (BorderPane) loader.load();
			Scene scene = new Scene(rootLayout);
			primaryStage.setTitle("PCMAX");
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.getIcons().add(new Image("vista/imagenes/logo.png"));
			primaryStage.setResizable(false);
			//primaryStage.setFullScreen(false);
			
			primaryStage.show();
			
			//controlleroot = loader.getController();
			//controlleroot.setMain(this);
			
		} catch(Exception ex) {
			ce.printlog(ex.getMessage(), this.getClass().toString());
		}
	}
	
	public static Stage getPrimaryStage() {
		return primaryStage;
	}
	
	public void splash(){
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Principal.class.getResource("fxml/Splash.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			dStage = new Stage();
			dStage.getIcons().add(new Image("vista/imagenes/logo.png"));
			dStage.initStyle(StageStyle.TRANSPARENT); 
			//dialogStage.initModality(Modality.WINDOW_MODAL);
			dStage.setResizable(false);
			dStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			dStage.setScene(scene);
			ControladorSplash controller = loader.getController();
			controller.setDialogStage(dStage);
			//System.out.println("show and wait");
			dStage.showAndWait();
			//controller.initJoin();
			//System.out.println("wait 4000 milis");
			//Thread.sleep(4000);
			//System.out.println("close");
			//dialogStage.close();
			//primaryStage.show();
		} catch (IOException e) {	
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		SplashWindow s = new SplashWindow(new java.awt.Frame(),1500);
		launch(args);
	}
}
