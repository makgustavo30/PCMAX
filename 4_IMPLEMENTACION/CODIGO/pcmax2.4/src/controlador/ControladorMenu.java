package controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


import modelo.Reporte;
import vista.Mail;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;


public class ControladorMenu implements Initializable {
	
	Reporte r;
	private ControladorVentanas ventanas;
	public static Stage primaryStage;
	public static String nivel;
	private ControladorErrores ce;
	@FXML Button btnvender,btnVentas, btnServicios, btnClientes, btnProveedores, btnProducto, btnUsuarios, btnCheck ;
	
	
	
	// menubar
		@FXML
		private MenuBar menuadmin;
		@FXML
		private Menu archivo;
		@FXML
		private Menu acerca;
		@FXML
		private MenuItem salir;
		@FXML
		private MenuItem reportes;
		@FXML
		private MenuItem autor;
		@FXML
		private MenuItem AbrirLog;
		@FXML
		private MenuItem AbrirEnviarMail;
		@FXML
		private MenuItem backup;
		@FXML
		private MenuItem CerrarSesion;
		
	
		
	public ControladorMenu(){
		r = new Reporte();
	}
	
	@FXML
	public void abrirVentas(){
		ventanas.asignarCentro("../vista/fxml/Venta.fxml");
	}
	
 
	@FXML 
	public void abrirVender(){
		ventanas.modal2("../vista/fxml/Vender.fxml","Vender");
		}
	
	
	@FXML
	public void abrirServicios(){
		ventanas.asignarCentro("../vista/fxml/Servicio.fxml");
	}
	
	@FXML
	public void abrirClientes(){
		ventanas.asignarCentro("../vista/fxml/Clientes.fxml");
	}
	
	@FXML
	public void abrirCategorias(){
		ventanas.asignarCentro2("../vista/fxml/Categorias.fxml");
	}
	
	@FXML
	public void abrirProductos(){
		ventanas.asignarCentro("../vista/fxml/Productos.fxml");
	}
	
	@FXML
	public void abrirProveedores(){
		ventanas.asignarCentro3("../vista/fxml/Proveedores.fxml");
	}
	
	@FXML
	public void abrirUsuarios(){
		ventanas.asignarCentro("../vista/fxml/Usuarios.fxml");
	}
	
	
	@FXML
	public void abrirChecks(){
		ventanas.asignarCentro("../vista/fxml/DiagnosticoEquipo.fxml");
	}
	
	@FXML
	public void abrirBackup(){
		ventanas.asignarCentro("../vista/fxml/Backups.fxml");
	}
	
	@FXML
	public void abrirReportes(){
		ventanas.asignarCentro("../vista/fxml/Reportes.fxml");
	}
	
	@FXML 
	public void SalirAplicacion(){
		System.exit(0);
	}
	
	@FXML 
	public void CerrarSesion(){
			ventanas.asignarEscenaLogin("../vista/fxml/Conexion.fxml", null);
		}
	
	@FXML 
	public void AbrirEnviarMail(){
			Mail thisClass = new Mail();
			thisClass.setVisible(true);
		}
	
	@FXML public void clickProductos(){
		r.cargarReporte("src/vista/Reportes/Cherry.jrxml");
		r.mostrarReporte();
	}

	@FXML public void clickVentas(){
		r.cargarReporte("src/vista/Reportes/Clientes.jrxml");
		r.mostrarReporte();
	}
	
	
	@FXML                                             
	public void Abrirlogger() {
		  //ruta del archivo en el pc
	     String File = new String("src/archivos/log.txt"); 
		   
		 try{ 
		   //definiendo la ruta en la propiedad file
		   Runtime.getRuntime().exec("cmd /c start "+File);
		     
		 }catch(IOException ex){
			  ce.printlog(ex.getMessage(), this.getClass().toString());}
		   } 
	
	@FXML
	public void onExit() {
		System.exit(0);
	}
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		ventanas = ControladorVentanas.getInstancia();
		if(ControladorMenu.nivel.equals("usuario")){
			btnProveedores.setDisable(true);btnUsuarios.setDisable(true);
			backup.setDisable (true);AbrirLog.setDisable (true);
			
		}
	}

}
