package controlador;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import modelo.Usuario1;
import controlador.ControladorErrores;


public class ControladorUsuarios implements Initializable {
	
	private Usuario1 u;
	private ControladorErrores ce;
	
	@FXML TableColumn<Usuario1, String> tcNombre_Usuario, tcNombre, tcApaterno, tcAmaterno, tcTelefono, tcCorreo, tcCiudad, tcTipoUsuario;
	@FXML TableColumn<Usuario1, Integer> tcCodigo;
	@FXML TableView<Usuario1> tvUsuario;
	@FXML TextField txtNombre_Usuario, txtContrasenia,txtId,txtNombre, txtApaterno, txtAmaterno ,
		txtCalle, txtAvenida, txtNumero, txtColonia, txtCodigo, txtTelefono, txtCorreo, txtBuscador,txtCiudad,txtTipoUsuario;
	@FXML Label lblMensaje;
	@FXML Button guardar, btnRestaurar, btnAbrirarchivo, tnAbrirexcel;
	@FXML CheckBox ckbInactivos;
	@FXML ToggleButton tbtnCambiar;
	
	
	private int filasXPagina, registros;
	private ObservableList<Usuario1> datos;
	private FilteredList<Usuario1> datosBusqueda;
	@FXML Pagination paginador;
	
	
	
	public ControladorUsuarios() {
		// TODO Auto-generated constructor stub
		ce= new ControladorErrores();
		u = new Usuario1();
		filasXPagina=10;
		datos = FXCollections.observableArrayList();
	}
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
		tcNombre_Usuario.setCellValueFactory(new PropertyValueFactory<Usuario1, String>("nombre_usuario"));
		tcTipoUsuario.setCellValueFactory(new PropertyValueFactory<Usuario1, String>("tipo"));
		tcNombre.setCellValueFactory(new PropertyValueFactory<Usuario1, String>("nombre"));
		tcApaterno.setCellValueFactory(new PropertyValueFactory<Usuario1, String>("apellido_paterno"));
		tcAmaterno.setCellValueFactory(new PropertyValueFactory<Usuario1, String>("apellido_materno"));
		tcCiudad.setCellValueFactory(new PropertyValueFactory<Usuario1, String>("ciudad"));
		tcTelefono.setCellValueFactory(new PropertyValueFactory<Usuario1, String>("telefono"));
		tcCorreo.setCellValueFactory(new PropertyValueFactory<Usuario1, String>("email"));
		//tvFilm.setItems(f.getFilms());
		
		// llenar table view
		
		datos= u.getUsuarios1(false);
		datosBusqueda =new FilteredList<>(datos);
		paginador.setPageCount(datosBusqueda.size()/filasXPagina);
		paginador.setPageFactory((Integer pagina)->createPage(pagina));
		registros = datosBusqueda.size();
		lblMensaje.setText( registros + " Registros encontrados");
		
		llenarTableView(true);
		
	} catch (SQLException ex) {
		ce.printlog(ex.getMessage(), this.getClass().toString());
	};
}
	

	// #regionmetodos paginacion
	
	private Node createPage(int pageIndex){
		int fromIndex = pageIndex * filasXPagina;
		int toIndex = Math.min(fromIndex + filasXPagina, datosBusqueda.size());
		tvUsuario.setItems(FXCollections.observableArrayList(
				datosBusqueda.subList(fromIndex, toIndex)));
		return new BorderPane(tvUsuario);
	}

	public void llenarTableView(Boolean estatus){
		try {
			//Refrescar y volver a cargar los datos en el TableView
			datos=u.getUsuarios1(estatus);
			datosBusqueda = new FilteredList<>(datos);
			paginador.setPageCount(datosBusqueda.size()/filasXPagina);
			paginador.setPageFactory((Integer pagina) -> createPage(pagina));
			lblMensaje.setText(datos.size() + " registros encontrados.");
		} catch (Exception ex) {
			ce.printlog(ex.getMessage(), this.getClass().toString());
			lblMensaje.setText("Se ha producido un error al recuperar los datos.");
		}
			
	}
	
	@FXML public void click_TableView(){
		
		if(tvUsuario.getSelectionModel().getSelectedItem()!=null){
			u = tvUsuario.getSelectionModel().getSelectedItem();
			
			txtId.setText(u.getId_usuario().toString());
			txtNombre_Usuario.setText(u.getNombre_Usuario().toString());
			txtContrasenia.setText(u.getContrasenia().toString());
			txtNombre.setText(u.getNombre().toString());
			txtApaterno.setText(u.getApellido_paterno().toString());
			txtAmaterno.setText(u.getApellido_materno().toString());
			txtCalle.setText(u.getCalle().toString());
			txtAvenida.setText(u.getAvenida().toString());
			txtNumero.setText(u.getNumero().toString());
			txtCodigo.setText(u.getCodigo_postal().toString());
			txtCiudad.setText(u.getCiudad().toString());
			txtColonia.setText(u.getColonia().toString());
			txtTelefono.setText(u.getTelefono().toString());
			txtCorreo.setText(u.getEmail().toString());
			txtTipoUsuario.setText(u.getTipo().toString());
			
			txtId.setDisable(true);
		}
		
	}
	
	
	@FXML public void click_insertar(){
		System.out.println("****************************");
		try{
			if(txtNombre_Usuario.getText().trim().isEmpty()||
					txtContrasenia.getText().trim().isEmpty()||
					txtNombre.getText().trim().isEmpty() ||
					txtApaterno.getText().trim().isEmpty()||
					txtAmaterno.getText().trim().isEmpty()||
					txtCalle.getText().trim().isEmpty()||
					txtAvenida.getText().trim().isEmpty()||
					txtNumero.getText().trim().isEmpty()||
					txtColonia.getText().trim().isEmpty()||
					txtCodigo.getText().trim().isEmpty()||
					txtTelefono.getText().trim().isEmpty()||
					txtCorreo.getText().trim().isEmpty()||
				    txtCiudad.getText().trim().isEmpty()||
				    txtTipoUsuario.getText().trim().isEmpty()){
				lblMensaje.setText("Faltan datos por ingresar.");
				System.out.println("****************************");
			}
			else{
				u = new Usuario1();
				u.setNombre_Usuario(new SimpleStringProperty(txtNombre_Usuario.getText()));
				u.setContrasenia(new SimpleStringProperty(txtContrasenia.getText()));
				u.setNombre(new SimpleStringProperty(txtNombre.getText()));
				u.setApellido_paterno(new SimpleStringProperty(txtApaterno.getText()));
				u.setApellido_materno(new SimpleStringProperty(txtAmaterno.getText()));
				u.setCalle(new SimpleStringProperty(txtCalle.getText()));
				u.setAvenida(new SimpleStringProperty(txtAvenida.getText()));
				u.setNumero(new SimpleStringProperty(txtNumero.getText()));
				u.setColonia(new SimpleStringProperty(txtColonia.getText()));
				u.setCodigo_postal(new SimpleIntegerProperty(Integer.valueOf(txtCodigo.getText())));
				u.setCiudad(new SimpleStringProperty(txtCiudad.getText()));
				u.setTelefono(new SimpleStringProperty(txtTelefono.getText()));
				u.setEmail(new SimpleStringProperty(txtCorreo.getText()));
				u.setTipo(new SimpleStringProperty(txtTipoUsuario.getText()));
				boolean yes = u.insertar();
				if(yes){
					lblMensaje.setText("Datos insertados satisfactoriamente.");
					llenarTableView(true);
					limpiar();
				}
				else{
					lblMensaje.setText("!!!Se ha producido un error en el servidor¡¡¡");
				}
			}
		}catch(Exception ex){
			ce.printlog(ex.getMessage(), this.getClass().toString());
			lblMensaje.setText("!!!Se ha producido un error en el servidor¡¡¡");
		}
	}
	
	
	@FXML public void click_eliminar(){
		try {
			if(txtId.getText().isEmpty())
				lblMensaje.setText("Debe seleccionar el cliente a dar de baja");
			else
				u = new Usuario1();
				u.setId_usuario(new SimpleIntegerProperty(Integer.valueOf(txtId.getText())));
				if(u.eliminar()){
					llenarTableView(true);
					limpiar();
					lblMensaje.setText("Registro dado de baja satisfactoriamente.");
				}
				else{
					lblMensaje.setText("Se ha presentado un error con el servidor");
				}
					
		} catch (Exception ex) {
			ce.printlog(ex.getMessage(), this.getClass().toString());
		}
	}
	
	
	public void limpiar(){
		txtId.clear();
		txtNombre_Usuario.clear();
		txtContrasenia.clear();
		txtNombre.clear();
		txtApaterno.clear();
		txtAmaterno.clear();
		txtCalle.clear();
		txtAvenida.clear();
		txtNumero.clear();
		txtColonia.clear();
		txtCodigo.clear();
		txtCiudad.clear();
		txtTelefono.clear();
		txtCorreo.clear();
		txtTipoUsuario.clear();
	}
	
	
	//Método para actualizar un registro en el TableView
		@FXML public void click_actualizar(){
			try {
				if(txtId.getText().isEmpty() ||
						txtNombre_Usuario.getText().isEmpty() ||
						txtContrasenia.getText().isEmpty() ||
						txtNombre.getText().trim().isEmpty() ||
						txtApaterno.getText().trim().isEmpty() ||
						txtAmaterno.getText().trim().isEmpty() ||
						txtCalle.getText().trim().isEmpty() ||
						txtAvenida.getText().trim().isEmpty() ||
						txtNumero.getText().trim().isEmpty() ||
						txtColonia.getText().trim().isEmpty() ||
						txtCodigo.getText().trim().isEmpty()||
						txtCiudad.getText().trim().isEmpty()||
						txtTelefono.getText().trim().isEmpty()||
						txtCorreo.getText().trim().isEmpty()||
						txtTipoUsuario.getText().isEmpty()){
					lblMensaje.setText("Faltan datos por llenar");
				}
				else{
					u = new Usuario1();
					u.setId_usuario(new SimpleIntegerProperty(Integer.valueOf(txtId.getText())));
					u.setNombre_Usuario(new SimpleStringProperty(txtNombre_Usuario.getText()));
					u.setContrasenia(new SimpleStringProperty(txtContrasenia.getText()));
					u.setNombre(new SimpleStringProperty(txtNombre.getText()));
					u.setApellido_paterno(new SimpleStringProperty(txtApaterno.getText()));
					u.setApellido_materno(new SimpleStringProperty(txtAmaterno.getText()));
					u.setCalle(new SimpleStringProperty(txtCalle.getText()));
					u.setAvenida(new SimpleStringProperty(txtAvenida.getText()));
					u.setNumero(new SimpleStringProperty(txtNumero.getText()));
					u.setColonia(new SimpleStringProperty(txtColonia.getText()));
					u.setCodigo_postal(new SimpleIntegerProperty(Integer.valueOf(txtCodigo.getText())));
					u.setCiudad(new SimpleStringProperty(txtCiudad.getText()));
					u.setTelefono(new SimpleStringProperty(txtTelefono.getText()));
					u.setEmail(new SimpleStringProperty(txtCorreo.getText()));
					u.setTipo(new SimpleStringProperty(txtTipoUsuario.getText()));
					boolean yes = u.actualizar();
					if(yes){
						lblMensaje.setText("Datos actualizados satisfactoriamente.");
						llenarTableView(true);
						limpiar();
					}
					else{
						lblMensaje.setText("Se ha producido un error en el servidor");
					}
				}
			}catch(Exception ex){
				ce.printlog(ex.getMessage(), this.getClass().toString());
				lblMensaje.setText("Se ha producido un error en el servidor");
			}
		}
		
		
		public void ClientTabla(){
			try {
				// llenar table view
				datos= u.getUsuarios1(false);
				datosBusqueda =new FilteredList<>(datos);
				paginador.setPageCount(datosBusqueda.size()/filasXPagina);
				paginador.setPageFactory((Integer pagina)->createPage(pagina));
				registros = datosBusqueda.size();
				lblMensaje.setText( registros + " Registros encontrados");
				txtId.setDisable(false);
			} catch (Exception ex) {
				ce.printlog(ex.getMessage(), this.getClass().toString());
				lblMensaje.setText("Se ha producido un error al recuperar los datos.");
			}
		}
		
		
	/*	
		@FXML public void cambiarClientes(){
			if(tbtnCambiar.isSelected()){
				
				try {
					datos= cl.getClientesDeleted();
					btnRestaurar.setDisable(false);
				} catch (SQLException ex) {
					// TODO Auto-generated catch block
					ce.printlog(ex.getMessage(), this.getClass().toString());
				}
			}
			else{
				try {
					btnRestaurar.setDisable(true);
					datos= cl.getClientes(false);
				} catch (SQLException ex) {
					// TODO Auto-generated catch block
					ce.printlog(ex.getMessage(), this.getClass().toString());
				}
			}
			try {
				// llenar table view
				datosBusqueda =new FilteredList<>(datos);
				paginador.setPageCount(datosBusqueda.size()/filasXPagina);
				paginador.setPageFactory((Integer pagina)->createPage(pagina));
				registros = datosBusqueda.size();
				lblMensaje.setText( registros + " Registros encontrados");
				txtId.setDisable(false);
			} catch (Exception ex) {
				ce.printlog(ex.getMessage(), this.getClass().toString());
				lblMensaje.setText("Se ha producido un error al recuperar los datos.");
			}
		}
		
		
		@FXML public void restaurar(){
			if(cl != null){
				if(cl.restaurar()){
					cambiarClientes();
					limpiar();
					lblMensaje.setText("Registro restaurado.");
				}
				else{
					lblMensaje.setText("Se ha producido un error al recuperar los datos.");
				}
			}
			else{
				lblMensaje.setText("No se ha seleccionado un registro.");
			}
		}
	
*/
		
		//Método para mostrar los registros dados de baja
		@FXML public void click_inactivos(){
			if(ckbInactivos.isSelected())
				llenarTableView(false);
			else
				llenarTableView(true);
		}
	 
		//Método para incorporar otro lenguaje.
		//@FXML public void clickLanguage(){
		//	try {
		//		ventanas.modal("../view/fxml/Language.fxml","Lenguaje");
		//		cbLanguage.setItems(l.getLanguage());
		//	} catch (SQLException ex) {
		//		// TODO Auto-generated catch block
		//		ce.printlog(ex.getMessage(), this.getClass().toString());
		//	}
		//}
		
		
		// #region Buscador
	 	@FXML public void buscarTexto(){
	 		if(txtBuscador.getText().trim().isEmpty()){
	 			//Llenar TableView
	 			datosBusqueda= new FilteredList<>(datos);
	 			filasXPagina=10;
				paginador.setPageCount(datosBusqueda.size()/filasXPagina);
				paginador.setPageFactory((Integer pagina) -> createPage(pagina));
				lblMensaje.setText(datosBusqueda.size() + " registros encontrados en la Base de Datos.");
	 		}
	 		else{
	 			try{
		 			datosBusqueda.setPredicate(Usuario1->Usuario1.getNombre().toLowerCase().
		 					contains(txtBuscador.getText().toLowerCase()));
		 			if(datosBusqueda.size()<10)
		 				filasXPagina= datosBusqueda.size();
		 			else
		 				filasXPagina=10;
		 			paginador.setPageCount(datosBusqueda.size()/filasXPagina);
					paginador.setPageFactory((Integer pagina) -> createPage(pagina));
					lblMensaje.setText("Se encontraron " + datosBusqueda.size() + " coincidencias.");
	 			}
	 			catch(Exception ex){
	 				//Enviar mensaje
	 				ce.printlog(ex.getMessage(), this.getClass().toString());
	 				lblMensaje.setText("No se encontraron resultados");
	 				filasXPagina=0;
	 				paginador.setPageCount(filasXPagina); 				
					paginador.setPageFactory((Integer pagina) -> createPage(pagina));
	 			}
	 		}
	 		
	 	}
	 	
	 	
	 	/*
		 * Abrir un archivo y ejecutarlo
		 */
	 	
	 	@FXML                                             
	 		public void Abrirarchivo() {
	 			  //ruta del archivo en el pc
	 		     String File = new String("src/archivos/log.txt"); 
	 			   
	 			 try{ 
	 			   //definiendo la ruta en la propiedad file
	 			   Runtime.getRuntime().exec("cmd /c start "+File);
	 			     
	 			   }catch(IOException ex){
	 				  ce.printlog(ex.getMessage(), this.getClass().toString());
	 			   } 
	 	

		} 
	 	
	 	/*
	 	
	 	@FXML
	 	public void Abrirexcel() {
	        new ExportExcelBaseClientes().WriteExcel();
	        String File = new String("src/archivos/Clientes.xls"); 
			   
			 try{ 
			   //definiendo la ruta en la propiedad file
			   Runtime.getRuntime().exec("cmd /c start "+File);
			     
			   }catch(IOException ex){
				  ce.printlog(ex.getMessage(), this.getClass().toString());
			   } 
			 */
	    }
	 	

	 	// #endregion
	 	
	
