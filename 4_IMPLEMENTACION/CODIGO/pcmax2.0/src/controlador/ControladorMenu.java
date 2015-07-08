package controlador;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class ControladorMenu implements Initializable {
	private ControladorVentanas ventanas;
	public static String nivel;
	@FXML Button btnVentas, btnServicios, btnClientes, btnProveedores, btnProducto, btnCategoria;
	
	@FXML
	public void abrirServicios(){
		ventanas.asignarCentro("../vista/fxml/Servicio.fxml");
	}
	
	@FXML
	public void abrirClientes(){
		ventanas.asignarCentro2("../vista/fxml/Clientes.fxml");
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
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		ventanas = ControladorVentanas.getInstancia();
		if(ControladorMenu.nivel.equals("usuario")){
			btnProveedores.setDisable(true);
			btnCategoria.setDisable(true);
			
		}
	}

}
