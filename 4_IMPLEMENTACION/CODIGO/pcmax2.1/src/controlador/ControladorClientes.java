package controlador;

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
import modelo.Cliente;
import controlador.ControladorErrores;


public class ControladorClientes implements Initializable {
	
	private Cliente cl;

	@FXML TableColumn<Cliente, String> tcNombre, tcApaterno, tcAmaterno, tcTelefono, tcCorreo, tcCiudad;
	@FXML TableColumn<Cliente, Integer> tcCodigo;
	@FXML TableView<Cliente> tvCliente;
	@FXML TextField txtRfc,txtId,txtNombre, txtApaterno, txtAmaterno ,
		txtCalle, txtAvenida, txtNumero, txtColonia, txtCodigo, txtTelefono, txtCorreo, txtBuscador,txtCiudad;
	@FXML Label lblMensaje;
	@FXML Button guardar, btnRestaurar,btnEliminar;
	@FXML CheckBox ckbInactivos;
	@FXML ToggleButton tbtnCambiar;
	
	private int filasXPagina, registros;
	private ObservableList<Cliente> datos;
	private FilteredList<Cliente> datosBusqueda;
	private ControladorErrores ce;
	@FXML Pagination paginador;
	
	
	public ControladorClientes() {
		// TODO Auto-generated constructor stub
		ce= new ControladorErrores();
		cl = new Cliente();
		filasXPagina=10;
		datos = FXCollections.observableArrayList();
	}
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
		tcNombre.setCellValueFactory(new PropertyValueFactory<Cliente, String>("nombre"));
		tcApaterno.setCellValueFactory(new PropertyValueFactory<Cliente, String>("apellido_paterno"));
		tcAmaterno.setCellValueFactory(new PropertyValueFactory<Cliente, String>("apellido_materno"));
		tcCiudad.setCellValueFactory(new PropertyValueFactory<Cliente, String>("ciudad"));
		tcTelefono.setCellValueFactory(new PropertyValueFactory<Cliente, String>("telefono"));
		tcCorreo.setCellValueFactory(new PropertyValueFactory<Cliente, String>("email"));
		//tvFilm.setItems(f.getFilms());
		
		// llenar table view
		
		datos= cl.getClientes(false);
		datosBusqueda =new FilteredList<>(datos);
		paginador.setPageCount(datosBusqueda.size()/filasXPagina);
		paginador.setPageFactory((Integer pagina)->createPage(pagina));
		registros = datosBusqueda.size();
		lblMensaje.setText( registros + " Registros encontrados");
		
		llenarTableView(true);
		
		if(ControladorMenu.nivel.equals("usuario")){
			btnEliminar.setDisable(true);}
		
	} catch (SQLException ex) {
		ce.printlog(ex.getMessage(), this.getClass().toString());
	};
}
	

	// #regionmetodos paginacion
	
	private Node createPage(int pageIndex){
		int fromIndex = pageIndex * filasXPagina;
		int toIndex = Math.min(fromIndex + filasXPagina, datosBusqueda.size());
		tvCliente.setItems(FXCollections.observableArrayList(
				datosBusqueda.subList(fromIndex, toIndex)));
		return new BorderPane(tvCliente);
	}

	public void llenarTableView(Boolean estatus){
		try {
			//Refrescar y volver a cargar los datos en el TableView
			datos=cl.getClientes(estatus);
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
		
		if(tvCliente.getSelectionModel().getSelectedItem()!=null){
			cl = tvCliente.getSelectionModel().getSelectedItem();
			
			txtId.setText(cl.getId_cliente().toString());
			txtRfc.setText(cl.getRfc().toString());
			txtNombre.setText(cl.getNombre().toString());
			txtApaterno.setText(cl.getApellido_paterno().toString());
			txtAmaterno.setText(cl.getApellido_materno().toString());
			txtCalle.setText(cl.getCalle().toString());
			txtAvenida.setText(cl.getAvenida().toString());
			txtNumero.setText(cl.getNumero().toString());
			txtCodigo.setText(cl.getCodigo_postal().toString());
			txtCiudad.setText(cl.getCiudad().toString());
			txtColonia.setText(cl.getColonia().toString());
			txtTelefono.setText(cl.getTelefono().toString());
			txtCorreo.setText(cl.getEmail().toString());
			
			txtId.setDisable(true);
		}
		
	}
	
	
	@FXML public void click_insertar(){
		System.out.println("****************************");
		try{
			if(txtNombre.getText().trim().isEmpty() ||
					txtRfc.getText().trim().isEmpty()||
					txtApaterno.getText().trim().isEmpty()||
					txtAmaterno.getText().trim().isEmpty()||
					txtCalle.getText().trim().isEmpty()||
					txtAvenida.getText().trim().isEmpty()||
					txtNumero.getText().trim().isEmpty()||
					txtColonia.getText().trim().isEmpty()||
					txtCodigo.getText().trim().isEmpty()||
					txtTelefono.getText().trim().isEmpty()||
					txtCorreo.getText().trim().isEmpty()||
				    txtCiudad.getText().trim().isEmpty()){
				lblMensaje.setText("Faltan datos por ingresar.");
				System.out.println("****************************");
			}
			else{
				cl = new Cliente();
				cl.setNombre(new SimpleStringProperty(txtNombre.getText()));
				cl.setRfc(new SimpleStringProperty(txtRfc.getText()));
				cl.setApellido_paterno(new SimpleStringProperty(txtApaterno.getText()));
				cl.setApellido_materno(new SimpleStringProperty(txtAmaterno.getText()));
				cl.setCalle(new SimpleStringProperty(txtCalle.getText()));
				cl.setAvenida(new SimpleStringProperty(txtAvenida.getText()));
				cl.setNumero(new SimpleStringProperty(txtNumero.getText()));
				cl.setColonia(new SimpleStringProperty(txtNombre.getText()));
				cl.setCodigo_postal(new SimpleIntegerProperty(Integer.valueOf(txtCodigo.getText())));
				cl.setCiudad(new SimpleStringProperty(txtCiudad.getText()));
				cl.setTelefono(new SimpleStringProperty(txtTelefono.getText()));
				cl.setEmail(new SimpleStringProperty(txtCorreo.getText()));
				boolean yes = cl.insertar();
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
				cl = new Cliente();
				cl.setId_cliente(new SimpleIntegerProperty(Integer.valueOf(txtId.getText())));
				if(cl.eliminar()){
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
		txtNombre.clear();
		txtRfc.clear();
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
	}
	
	//Método para actualizar un registro en el TableView
		@FXML public void click_actualizar(){
			try {
				if(txtId.getText().isEmpty() ||
						txtRfc.getText().isEmpty() ||
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
						txtCorreo.getText().trim().isEmpty()){
					lblMensaje.setText("Faltan datos por llenar");
				}
				else{
					cl = new Cliente();
					cl.setId_cliente(new SimpleIntegerProperty(Integer.valueOf(txtId.getText())));
					cl.setRfc(new SimpleStringProperty(txtRfc.getText()));
					cl.setNombre(new SimpleStringProperty(txtNombre.getText()));
					cl.setApellido_paterno(new SimpleStringProperty(txtApaterno.getText()));
					cl.setApellido_materno(new SimpleStringProperty(txtAmaterno.getText()));
					cl.setCalle(new SimpleStringProperty(txtCalle.getText()));
					cl.setAvenida(new SimpleStringProperty(txtAvenida.getText()));
					cl.setNumero(new SimpleStringProperty(txtNumero.getText()));
					cl.setColonia(new SimpleStringProperty(txtColonia.getText()));
					cl.setCodigo_postal(new SimpleIntegerProperty(Integer.valueOf(txtCodigo.getText())));
					cl.setCiudad(new SimpleStringProperty(txtCiudad.getText()));
					cl.setTelefono(new SimpleStringProperty(txtTelefono.getText()));
					cl.setEmail(new SimpleStringProperty(txtCorreo.getText()));
					boolean yes = cl.actualizar();
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
				datos= cl.getClientes(false);
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
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else{
				try {
					btnRestaurar.setDisable(true);
					datos= cl.getClientes(false);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
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
			} catch (Exception e) {
				e.printStackTrace();
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
		//	} catch (SQLException e) {
		//		// TODO Auto-generated catch block
		//		e.printStackTrace();
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
		 			datosBusqueda.setPredicate(Cliente->Cliente.getNombre().toLowerCase().
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
	 				ce.printlog(ex.getMessage(), this.getClass().toString());
	 				//Enviar mensaje
	 				lblMensaje.setText("No se encontraron resultados");
	 				filasXPagina=0;
	 				paginador.setPageCount(filasXPagina); 				
					paginador.setPageFactory((Integer pagina) -> createPage(pagina));
	 			}
	 		}
	 		
	 	}
	 	
	 	}
	 	
	 	// #endregion
	 	
	
