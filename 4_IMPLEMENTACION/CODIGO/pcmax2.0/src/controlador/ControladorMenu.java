package controlador;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class ControladorMenu implements Initializable {
	private ControladorVentanas ventanas;
	
	@FXML
	public void abrirServicios(){
		ventanas.asignarCentro("../vista/fxml/servicio.fxml");
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		ventanas = ControladorVentanas.getInstancia();
		
	}

}
