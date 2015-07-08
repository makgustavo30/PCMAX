package controlador;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import modelo.Marca;

public class ControladorMarca implements Initializable {
	
	private ObservableList<Marca> lista;
	//private FilteredList<Categoria> datosBusquedas;
	private Marca m;
	private boolean modificar;
	@FXML Label lblMensaje2, lblMensaje3, lblID;
	@FXML TextField txtNombre2;
	@FXML ListView<Marca> lvMarca;
	//@FXML ObservableList<Categoria> lista;
	@FXML Button btnEliminar, btnGuardar;
	private ObservableList<Marca> datos;
	//private FilteredList<Categoria> datosBusqueda;
		
		
		
	public ControladorMarca() {
		m = new Marca();
		lista= FXCollections.observableArrayList();
		modificar=false;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		try {
			llenarListView();
			//Desactivar botones
			btnEliminar.setDisable(true);
			btnGuardar.setDisable(true);
			txtNombre2.setDisable(true);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
		
	
	
	// Método para seleccionar elemento del ListView
	@FXML public void click_ListView(){
		m = lvMarca.getSelectionModel().getSelectedItem();
		if(m!=null){
			lblID.setText(m.getId_marca().toString());
			txtNombre2.setText(m.getNombre_marca());
			//Activar botones
			btnEliminar.setDisable(false);
			btnGuardar.setDisable(false);
			txtNombre2.setDisable(false);
			modificar=true;
		}
	}
	
	//Método para guardar o modificar
	@FXML public void guardar(){
		boolean resultado=false;
		try{
			m.setNombre_marca(new SimpleStringProperty(txtNombre2.getText()));
			if(modificar==true){
				m.setId_marca(new SimpleIntegerProperty(Integer.valueOf(lblID.getText())));
				resultado=m.actualizar();
				modificar=false;
			}
			else{
				resultado=m.insertar();
			}
			if(resultado){
				llenarListView();
				lblMensaje3.setText("Operacion realizada exitosamente.");
				deshabilitar();
			}
			else
				lblMensaje3.setText("Se ha presentado un error en el servidor.");
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	@FXML public void eliminar(){
		try {
			m.setId_marca(new SimpleIntegerProperty(Integer.valueOf(lblID.getText())));
			if(m.eliminar()){
				llenarListView();
				lblMensaje3.setText("Operacion realizada exitosamente.");
				deshabilitar();			}
			else
				lblMensaje3.setText("Se ha presentado un error en el servidor.");
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@FXML public void nuevo(){
		try {
			btnGuardar.setDisable(false);
			btnEliminar.setDisable(true);
			txtNombre2.setDisable(false);
			txtNombre2.clear();
			modificar=false;
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void deshabilitar(){
		btnGuardar.setDisable(true);
		btnEliminar.setDisable(true);
		txtNombre2.setDisable(true);
	}
	
	public void llenarListView(){
		try {
			lista= m.getMarca();
			lvMarca.setItems(lista);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
