package controlador;

import java.io.File;
import java.io.IOException;

import modelo.ExportExcelBaseDiagnosticos;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class ControladorReportes {
	@FXML Button btnReporFecha,btnCorteCaja, btnReporVenta, btnReporCliente, btnReporUsuario, btnReporServicio, btnReporProductos, btnReporProveedores, btnBuscar ;
	private ControladorVentanas ventanas;
	private ControladorErrores ce;
	
	
	@FXML 
	public void ReporFecha(){
			        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ReportesxFecha.fxml"));
			        fxmlLoader.setRoot(this);
			        fxmlLoader.setController(this);

			        }
		
	
	@FXML 
	public void CorteCaja(){
			ventanas = ControladorVentanas.getInstancia();
			ventanas.asignarEscenaReportexfecha("../vista/fxml/CorteCaja.fxml", null);
		}
	
	@FXML
 	public void ReporCliente() {
        new ExportExcelBaseDiagnosticos().WriteExcel();
        String File = new String("src/archivos/Clientes.xls"); 
		   
		 try{ 
		   //definiendo la ruta en la propiedad file
		   Runtime.getRuntime().exec("cmd /c start "+File);
		     
		   }catch(IOException ex){
			  ce.printlog(ex.getMessage(), this.getClass().toString());
		   } 
	}
	
	@FXML
 	public void ReporUsuario() {
        new ExportExcelBaseDiagnosticos().WriteExcel();
        String File = new String("src/archivos/Usuario.xls"); 
		   
		 try{ 
		   //definiendo la ruta en la propiedad file
		   Runtime.getRuntime().exec("cmd /c start "+File);
		     
		   }catch(IOException ex){
			  ce.printlog(ex.getMessage(), this.getClass().toString());
		   } 
	}
	
	@FXML public void buscar(){
		FileChooser fileChooser = new FileChooser();
		 fileChooser.setTitle("Buscar archivo");
		 fileChooser.getExtensionFilters().addAll(
		         new ExtensionFilter("Text Files", "*.*"));
		 File selectedFile = fileChooser.showOpenDialog(null);
		if (selectedFile != null) {
		}
	}
	
	
	
}
