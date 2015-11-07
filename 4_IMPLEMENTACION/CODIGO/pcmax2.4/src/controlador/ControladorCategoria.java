package controlador;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;



import modelo.Categoria;
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
import javafx.stage.Stage;
import controlador.ControladorErrores;


public class ControladorCategoria implements Initializable {
	
	private ControladorErrores ce;
	private ObservableList<Categoria> lista;
	//private FilteredList<Categoria> datosBusquedas;
	private Categoria c;
	private boolean modificar;
	private Stage stage;
	@FXML Label lblMensaje2, lblMensaje3, lblID;
	@FXML TextField txtNombre2;
	@FXML ListView<Categoria> lvCategoria;
	//@FXML ObservableList<Categoria> lista;
	@FXML Button btnEliminar, btnGuardar;
	private ObservableList<Categoria> datos;
	//private FilteredList<Categoria> datosBusqueda;
		
		
		
	public ControladorCategoria() {
		ce= new ControladorErrores();
		c = new Categoria();
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
			
		} catch (Exception ex) {
			// TODO Auto-generated catch block
			ce.printlog(ex.getMessage(), this.getClass().toString());
		}
		
		
	}
	
		
	
	/*	
	public void llenarTableView(){
		try{
			datos=c.getCategoria();
			datosBusqueda = new FilteredList<>(datos);
			paginador.setPageCount(datosBusqueda.size()/filasXPagina);
			paginador.setPageFactory((Integer pagina) -> createPage(pagina));
			lblMensaje.setText(datos.size() + " registros encontrados");
		}catch (Exception e){
			e.printStackTrace();
			lblMensaje.setText("Se ha producido un error al recuperar los datos");
		}
	}
	
	public void llenarTableView2(){
		try{
			datos=c.getCategoria();
			datosBusqueda = new FilteredList<>(datos);
			paginador.setPageCount(datosBusqueda.size()/filasXPagina);
			paginador.setPageFactory((Integer pagina) -> createPage(pagina));
			lblMensaje.setText(datos.size() + " registros encontrados");
		}catch (Exception e){
			e.printStackTrace();
			lblMensaje.setText("Se ha producido un error al recuperar los datos");
		}
	}
	
	private Node createPage(int pageIndex){
		int fromIndex = pageIndex * filasXPagina;
		int toIndex = Math.min(fromIndex + filasXPagina, datosBusqueda.size());
		tvCategorias.setItems(FXCollections.observableArrayList(
				datosBusqueda.subList(fromIndex, toIndex)));
		return new BorderPane(tvCategorias);
	}
	
	@FXML public void click_TableView(){
		if(tvCategorias.getSelectionModel().getSelectedItem()!=null){
			c = tvCategorias.getSelectionModel().getSelectedItem();
			txtID.setText(c.getId_categoria().toString());
			txtNombre.setText(c.getNombre_categoria());
		}
				
	}
	
@FXML public void buscarTexto(){
		
		try {
			if(txtBuscador.getText().trim().isEmpty()){
				//Llenar table view
				datosBusqueda=new FilteredList<>(datos);
				paginador.setPageCount(datosBusqueda.size()/filasXPagina);
				paginador.setPageFactory((Integer pagina) -> createPage(pagina));
				
			}
			else{
				datosBusqueda.setPredicate(Film->Film.getNombre_categoria().toLowerCase().contains(txtBuscador.getText().toLowerCase()));
				if(datosBusqueda.size()<10)
					filasXPagina=datosBusqueda.size();
				else
					filasXPagina=10;
				paginador.setPageCount(datosBusqueda.size()/filasXPagina);
				paginador.setPageFactory((Integer pagina) -> createPage(pagina));
			}
			
			//contar registros
			int entero = datosBusqueda.size();
			String enteroString = Integer.toString(entero);
			lblMensaje2.setText("Se encontraron" + " " + enteroString +"  " + "registros en la tabla");
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
		}
		
	}

public void limpiar(){
	txtID.clear();
	txtNombre.clear();
}

	
	
	@FXML private void click_insertar() {
		try{
			if(
				txtNombre.getText().trim().isEmpty()
					){
				
				lblMensaje.setText("faltan datos por ingresar");
		}else{
			c=new Categoria();
			c.setNombre_categoria(new SimpleStringProperty(txtNombre.getText()));
			
			boolean res = c.insertar();
			if(res){
				lblMensaje.setText("Datos insertados");
			}else
				lblMensaje.setText("Se ha producido un error en el servidor");
			}
		}catch (Exception e){
			e.printStackTrace();
			lblMensaje.setText("Se ha producido un error en el servidor");
	
		}
	}
	
	@FXML public void click_eliminar(){
		if(txtID.getText().isEmpty()){
			lblMensaje.setText("Debe seleccionar un registro");
		}else{
			c=new Categoria();
			c.setId_categoria(new SimpleIntegerProperty(Integer.valueOf(txtID.getText())));
			if(c.eliminar()==true){
				//limpiar 
				//limpiar();
				//llenarTableView();
	
				lblMensaje.setText("Se elimino el registro satisfactoriamente");
				
			}else{
				lblMensaje.setText("Se ha producido un error en el servidor");
			}
		}
	}
	
	@FXML private void click_actualizar() {
		try{
			if(
				txtID.getText().trim().isEmpty()||
				txtNombre.getText().trim().isEmpty()
					){
				
				lblMensaje.setText("faltan datos por ingresar");
		}else{
			c=new Categoria();
			c.setId_categoria(new SimpleIntegerProperty(Integer.valueOf(txtID.getText())));
			c.setNombre_categoria(new SimpleStringProperty(txtNombre.getText()));
			
			boolean res = c.actualizar();
			if(res){
				lblMensaje.setText("Datos insertados");
			}else
				lblMensaje.setText("Se ha producido un error en el servidor");
			}
		}catch (Exception e){
			e.printStackTrace();
			lblMensaje.setText("Se ha producido un error en el servidor");
	
		}
	}*/
	
	// Método para seleccionar elemento del ListView
	@FXML public void click_ListView(){
		c = lvCategoria.getSelectionModel().getSelectedItem();
		if(c!=null){
			lblID.setText(c.getId_categoria().toString());
			txtNombre2.setText(c.getNombre_categoria());
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
			c.setNombre_categoria(new SimpleStringProperty(txtNombre2.getText()));
			if(modificar==true){
				c.setId_categoria(new SimpleIntegerProperty(Integer.valueOf(lblID.getText())));
				resultado=c.actualizar();
				modificar=false;
			}
			else{
				resultado=c.insertar();
			}
			if(resultado){
				llenarListView();
				lblMensaje3.setText("Operacion realizada exitosamente.");
				deshabilitar();
			}
			else
				lblMensaje3.setText("Se ha presentado un error en el servidor.");
		}
		catch(Exception ex){
			ce.printlog(ex.getMessage(), this.getClass().toString());
		}
		
	}
	
	@FXML public void eliminar(){
		try {
			c.setId_categoria(new SimpleIntegerProperty(Integer.valueOf(lblID.getText())));
			if(c.eliminar()){
				llenarListView();
				lblMensaje3.setText("Operacion realizada exitosamente.");
				deshabilitar();			}
			else
				lblMensaje3.setText("Se ha presentado un error en el servidor.");
			
		} catch (Exception ex) {
			ce.printlog(ex.getMessage(), this.getClass().toString());
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
		} catch (Exception ex) {
			ce.printlog(ex.getMessage(), this.getClass().toString());
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
			lista= c.getCategoria();
			lvCategoria.setItems(lista);
		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			ce.printlog(ex.getMessage(), this.getClass().toString());
		}
		
	}


	public Stage getStage() {
		return stage;
	}


	public void setStage(Stage stage) {
		this.stage = stage;
	}

}
