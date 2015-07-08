package controlador;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import modelo.ContactoProveedor;
import modelo.Producto;
import modelo.Proveedor;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

public class ControladorContactoProveedor implements Initializable {
	
	private Proveedor pr;
	private ContactoProveedor cp;
	
	@FXML TableColumn<ContactoProveedor, String> tcContacto, tcEmail, tcTelefono;
	@FXML TableColumn<ContactoProveedor, String> tcEmpresa, tcCiudad;
	@FXML TableView<ContactoProveedor> tvProvedores;
	@FXML Label lblMensaje, lblMensaje2;
	@FXML TextField txtEmpresa, txtRfc, txtCalle, txtAvenida, txtNumero, txtColonia, txtCpostal, txtCiudad, txtTelefono, txtEmailEmpresa, txtNombre, txtApaterno, txtAmaterno, txtCelular, txtEmailContacto, txtBuscador;
	@FXML CheckBox ckbRegistrosInactivos; 
	
	//region variables paginacion
			private int filasXPagina;
			private ObservableList<ContactoProveedor> datos;
			private FilteredList<ContactoProveedor> datosBusqueda;
			@FXML Pagination paginador;

			public ControladorContactoProveedor() {
				cp = new ContactoProveedor();
				pr = new Proveedor();
				filasXPagina=10;
				datos = FXCollections.observableArrayList();
			}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tcEmpresa.setCellValueFactory(new PropertyValueFactory<ContactoProveedor, String>("nombre_empresa"));
		tcCiudad.setCellValueFactory(new PropertyValueFactory<ContactoProveedor, String>("ciudad"));
		tcContacto.setCellValueFactory(new PropertyValueFactory<ContactoProveedor, String>("nombrecompleto"));
		tcEmail.setCellValueFactory(new PropertyValueFactory<ContactoProveedor, String>("email_contacto"));
		tcTelefono.setCellValueFactory(new PropertyValueFactory<ContactoProveedor, String>("celular"));
		txtBuscador.setDisable(false);
		//llenar table view
		llenarTableView();
	}
	
	// #region Metodos_Paginacion
	private Node createPage(int pageIndex){
		if(filasXPagina>0){
		int fromIndex = pageIndex * filasXPagina;
		int toIndex = Math.min(fromIndex + filasXPagina, datosBusqueda.size());
		tvProvedores.setItems(FXCollections.observableArrayList(
				datosBusqueda.subList(fromIndex, toIndex)));
		
	}else{
		tvProvedores.setItems(null);
		paginador.setPageCount(0);
	}
		return new BorderPane(tvProvedores);
	}
	// #endregion
	
	
	//Método para llenar el TableView.
	public void llenarTableView(){
		try{
			//Refrescar y volver a cargar los datos en el TableView
			datos=cp.getContProveedor();
			datosBusqueda = new FilteredList<>(datos);
			paginador.setPageCount((int)(Math.ceil((double)datosBusqueda.size()/filasXPagina)));
			paginador.setPageFactory((Integer pagina) -> createPage(pagina));
			lblMensaje.setText(datos.size() + " registros encontrados");
		}catch (Exception e){
			e.printStackTrace();
			lblMensaje.setText("Se ha producido un error al recuperar los datos");
		}
	}
	
	//Método para llenar el TableView con registros inactivos.
	public void llenarTableView2(){
		try{
			datos=cp.getContProveedor2();
			datosBusqueda = new FilteredList<>(datos);
			paginador.setPageCount((int)(Math.ceil((double)datosBusqueda.size()/filasXPagina)));
			paginador.setPageFactory((Integer pagina) -> createPage(pagina));
			lblMensaje.setText(datos.size() + " registros encontrados");
		}catch (Exception e){
			e.printStackTrace();
			lblMensaje.setText("Se ha producido un error al recuperar los datos");
		}
	}
	
	
	
	//Método para subir los datos del TableView a los TextField
	@FXML public void click_TableView(){
		if(tvProvedores.getSelectionModel().getSelectedItem()!=null){
		cp = tvProvedores.getSelectionModel().getSelectedItem();
		
		txtNombre.setText(cp.getNombre());
		txtAmaterno.setText(cp.getApellido_materno());
		txtApaterno.setText(cp.getApellido_paterno());
		txtCelular.setText(cp.getCelular());
		txtEmailContacto.setText(cp.getEmail_contacto());
		txtCiudad.setText(cp.getPr().getCiudad());
		txtColonia.setText(cp.getPr().getColonia());
		txtCpostal.setText(cp.getPr().getCodigo_postal().toString());
		txtCalle.setText(cp.getPr().getCalle());
		txtAvenida.setText(cp.getPr().getAvenida());
		txtEmailEmpresa.setText(cp.getPr().getEmail());
		txtEmpresa.setText(cp.getPr().getNombre_empresa());
		txtNumero.setText(cp.getPr().getNumero());
		txtRfc.setText(cp.getPr().getRfc());
		txtTelefono.setText(cp.getPr().getTelefono());
		}
	}
		
	
	
	
	
	
	public void limpiar(){
		txtAmaterno.clear();
		txtApaterno.clear();
		txtAvenida.clear();
		txtCalle.clear();
		txtCelular.clear();
		txtCiudad.clear();
		txtColonia.clear();
		txtCpostal.clear();
		txtEmailContacto.clear();
		txtEmailEmpresa.clear();
		txtEmpresa.clear();
		txtNombre.clear();
		txtNumero.clear();
		txtRfc.clear();
		txtTelefono.clear();
	}
	
	//Método para insertar registros.
	@FXML public void click_insertar(){ 
		try{
			if(
					txtAmaterno.getText().trim().isEmpty()||
					txtApaterno.getText().trim().isEmpty()||
					txtAvenida.getText().trim().isEmpty()||
					txtCalle.getText().trim().isEmpty()||
					txtCelular.getText().trim().isEmpty()||
					txtCiudad.getText().trim().isEmpty()||
					txtColonia.getText().trim().isEmpty()||
					txtCpostal.getText().trim().isEmpty()||
					txtEmailContacto.getText().trim().isEmpty()||
					txtEmailEmpresa.getText().trim().isEmpty()||
					txtEmpresa.getText().trim().isEmpty()||
					txtNombre.getText().trim().isEmpty()||
					txtRfc.getText().trim().isEmpty()||
					txtTelefono.getText().trim().isEmpty()
					){
				lblMensaje.setText("faltan datos por ingresar");
			}else{
				cp=new ContactoProveedor();
				cp.setApellido_materno(new SimpleStringProperty(txtAmaterno.getText()));
				cp.setApellido_paterno(new SimpleStringProperty(txtApaterno.getText()));
				cp.getPr().setAvenida(new SimpleStringProperty(txtAvenida.getText()));
				cp.getPr().setCalle(new SimpleStringProperty(txtCalle.getText()));
				cp.setCelular(new SimpleStringProperty(txtCelular.getText()));
				cp.getPr().setCiudad(new SimpleStringProperty(txtCiudad.getText()));
				cp.getPr().setCodigo_postal(new SimpleIntegerProperty(Integer.valueOf(txtCpostal.getText())));
				cp.getPr().setColonia(new SimpleStringProperty(txtColonia.getText()));
				cp.getPr().setEmail(new SimpleStringProperty(txtEmailEmpresa.getText()));
				cp.setEmail_contacto(new SimpleStringProperty(txtEmailContacto.getText()));
				cp.setNombre(new SimpleStringProperty(txtNombre.getText()));
				cp.getPr().setNombre_empresa(new SimpleStringProperty(txtEmpresa.getText()));
				cp.getPr().setNumero(new SimpleStringProperty(txtNumero.getText()));
				cp.getPr().setRfc(new SimpleStringProperty(txtRfc.getText()));
				cp.getPr().setTelefono(new SimpleStringProperty(txtTelefono.getText()));
				boolean res = cp.insertar();
				if(res){
					limpiar();
					llenarTableView();
					lblMensaje.setText("Datos insertados");
				}else
					lblMensaje.setText("Se ha producido un error en el servidor");
				}
			}catch (Exception e){
				e.printStackTrace();
				lblMensaje.setText("Se ha producido un error en el servidor");

			}
		}

	
	
	@FXML public void click_actualizar(){ 
		
	}
	
	
	@FXML public void click_eliminar(){
		if(txtEmpresa.getText().isEmpty()){
			lblMensaje.setText("Debe seleccionar un registro");
		}else{
			cp=new ContactoProveedor();
			cp.setId_contacto_proveedor(new SimpleIntegerProperty());
			if(cp.eliminar()==true){
				//limpiar 
				limpiar();
				llenarTableView();
	
				lblMensaje.setText("Se elimino el registro satisfactoriamente");
				
			}else{
				lblMensaje.setText("Se ha producido un error en el servidor");
			}
		}
	}
	
	
	
	@FXML public void click_inactivos(){
		if(ckbRegistrosInactivos.isSelected()==true){
				llenarTableView2();			
		}else{
				llenarTableView();
		}
	}
	
	
	// #region Buscador
	@FXML public void buscarTexto(){
		
		try {
			if(txtBuscador.getText().trim().isEmpty()){
				//Llenar table view
				datosBusqueda=new FilteredList<>(datos);
				paginador.setPageCount(datosBusqueda.size()/filasXPagina);
				paginador.setPageFactory((Integer pagina) -> createPage(pagina));
				
			}
			else{
				datosBusqueda.setPredicate(ContactoProveedor->ContactoProveedor.getNombre().toLowerCase().contains(txtBuscador.getText().toLowerCase()));
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
	// #endregion
}