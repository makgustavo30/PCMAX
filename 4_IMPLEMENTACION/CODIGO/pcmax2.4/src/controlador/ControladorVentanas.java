package controlador;


import modelo.Marca;
import modelo.Venta;
import vista.Principal;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import controlador.ControladorErrores;


public class ControladorVentanas {
	/*
	 * Atributos
	 */
	private ControladorErrores ce;
	public static ControladorVentanas instancia;
	private Stage primaryStage;
	private Stage dialogEscenario;
	private Scene escena;
	private BorderPane contenedor;
	private BorderPane contenedorDialog;
	private AnchorPane subcontenedorDialog;
	/*
	 * constructor privado
	 */
	
	private ControladorVentanas(){
		ce= new ControladorErrores();
	}
	
	/*
	 * recuperar la instancia de la clase
	 */
	
	public static ControladorVentanas getInstancia(){
		if(instancia==null){
			instancia= new ControladorVentanas();
		}
		return instancia;
	}
	
	/*
	 * establecer escenario principal
	 */
	public void setPrimaryStage(Stage primaryStage){
		this.primaryStage = primaryStage;
	}
	
	/*
	 * asignar el menu principal de la ventana
	 */
	
	public void asignarMenu(String ruta, String titulo){
		try{
			FXMLLoader interfaz = new FXMLLoader(getClass().getResource(ruta));
			contenedor = (BorderPane)interfaz.load();
			Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
			escena = new Scene(contenedor,screenBounds.getWidth(),
					screenBounds.getHeight());
			primaryStage.setScene(escena);
			primaryStage.setTitle(titulo);
			//primaryStage.setFullScreen(true);
			primaryStage.show();
		}catch(Exception ex){
			ce.printlog(ex.getMessage(), this.getClass().toString());
		}
	}
	
	/*
	 * para cambiar entre escenas
	 */
	 
	public void asignarEscena(String ruta, String titulo,String nivel){
		try{
			ControladorMenu.nivel = nivel;
			FXMLLoader interfaz = new FXMLLoader(getClass().getResource(ruta));
			contenedorDialog = (BorderPane)interfaz.load();
			Stage dialogEscenario = Principal.getPrimaryStage();
			dialogEscenario.setTitle(titulo);
			escena = new Scene(contenedorDialog);
			dialogEscenario.setScene(escena);
			dialogEscenario.centerOnScreen();
			//dialogEscenario.setMaximized(true);
			dialogEscenario.show();			
		}catch (Exception ex){
			ce.printlog(ex.getMessage(), this.getClass().toString());
		}	
	}
	
	
	/*public void modal(String ruta, String titulo){
		try {
			FXMLLoader interfaz = new FXMLLoader(getClass().getResource(ruta));
			contenedorDialog = (BorderPane)interfaz.load();			
			dialogEscenario = new Stage();
			dialogEscenario.setTitle(titulo);
			dialogEscenario.initModality(Modality.WINDOW_MODAL);
			dialogEscenario.initOwner(primaryStage);
			escena = new Scene(contenedorDialog);			
			dialogEscenario.setScene(escena);	
			dialogEscenario.centerOnScreen();
			dialogEscenario.show();
		} catch (Exception ex) {
			ce.printlog(ex.getMessage(), this.getClass().toString());
		}
	}*/
	
	public void modal(String ruta, String titulo){
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(ControladorVentanas.class.getResource(ruta));			
			BorderPane page = (BorderPane) loader.load();
			
			Stage dialogStage = new Stage();
			dialogStage.setTitle(titulo);
			dialogStage.initModality(Modality.WINDOW_MODAL);
			
			Scene scene= new Scene(page);			
			dialogStage.setScene(scene);	
			
			ControladorMarca controller = loader.getController();
			controller.setStage(dialogStage);
			dialogStage.showAndWait();
			
			dialogEscenario.show();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void modal2(String ruta, String titulo){
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(ControladorVentanas.class.getResource(ruta));			
			BorderPane page = (BorderPane) loader.load();
			
			Stage dialogStage = new Stage();
			dialogStage.setTitle(titulo);
			dialogStage.initModality(Modality.WINDOW_MODAL);
			
			Scene scene= new Scene(page);			
			dialogStage.setScene(scene);	
			
			ControladorCategoria controller = loader.getController();
			controller.setStage(dialogStage);
			dialogStage.showAndWait();
			
			dialogEscenario.show();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	
	public void asignarCentro(String ruta){
		try{
			FXMLLoader interfaz = new FXMLLoader(getClass().getResource(ruta));
			subcontenedorDialog = (AnchorPane)interfaz.load();
			contenedorDialog.setCenter(subcontenedorDialog);
			dialogEscenario = Principal.getPrimaryStage();
			//Scene escena = new Scene(contenedorDialog);
			dialogEscenario.setScene(escena);
			dialogEscenario.show();			
		}catch (Exception ex){
			ce.printlog(ex.getMessage(), this.getClass().toString());
		}	
	}	
	
	
	public void asignarCentro2(String ruta){
		try{
			FXMLLoader interfaz = new FXMLLoader(getClass().getResource(ruta));
			subcontenedorDialog = (AnchorPane)interfaz.load();
			contenedorDialog.setCenter(subcontenedorDialog);
			dialogEscenario = Principal.getPrimaryStage();
			//Scene escena = new Scene(contenedorDialog);
			dialogEscenario.setScene(escena);
			dialogEscenario.show();			
		}catch (Exception ex){
			ce.printlog(ex.getMessage(), this.getClass().toString());
		}	
	}	
	
	
	public void asignarCentro3(String ruta){
		try{
			FXMLLoader interfaz = new FXMLLoader(getClass().getResource(ruta));
			subcontenedorDialog = (AnchorPane)interfaz.load();
			contenedorDialog.setCenter(subcontenedorDialog);
			dialogEscenario = Principal.getPrimaryStage();
			//Scene escena = new Scene(contenedorDialog);
			dialogEscenario.setScene(escena);
			dialogEscenario.centerOnScreen();
			dialogEscenario.show();			
		}catch (Exception ex){
			ce.printlog(ex.getMessage(), this.getClass().toString());
		}	
	}	

	public void asignarEscenaLogin(String ruta, String titulo){
		try{
			FXMLLoader interfaz = new FXMLLoader(getClass().getResource(ruta));
			contenedorDialog = (BorderPane)interfaz.load();
			Stage dialogEscenario = Principal.getPrimaryStage();
			dialogEscenario.setTitle(titulo);
			escena = new Scene(contenedorDialog);
			dialogEscenario.setScene(escena);
			dialogEscenario.centerOnScreen();
			dialogEscenario.showAndWait();
			
		}catch (Exception ex){
			ce.printlog(ex.getMessage(), this.getClass().toString());
		}	
	}
	
		public void asignarEscenaVender(String ruta, String titulo){
			try{
				FXMLLoader interfaz = new FXMLLoader(getClass().getResource(ruta));
				contenedorDialog = (BorderPane)interfaz.load();
				dialogEscenario.setTitle(titulo);
				escena = new Scene(contenedorDialog);
				dialogEscenario.setScene(escena);
				dialogEscenario.centerOnScreen();
				dialogEscenario.showAndWait();
				
			}catch (Exception ex){
				ce.printlog(ex.getMessage(), this.getClass().toString());
			}	
	}
		public void asignarEscenaReportexfecha(String ruta, String titulo){
			try{
				FXMLLoader interfaz = new FXMLLoader(getClass().getResource(ruta));
				contenedorDialog = (BorderPane)interfaz.load();
				Stage dialogEscenario = Principal.getPrimaryStage();
				dialogEscenario.setTitle(titulo);
				escena = new Scene(contenedorDialog);
				dialogEscenario.setScene(escena);
				dialogEscenario.centerOnScreen();
				dialogEscenario.show();
				
			}catch (Exception ex){
				ce.printlog(ex.getMessage(), this.getClass().toString());
			}	
	}
}
