package controlador;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import modelo.Categoria;
import modelo.Marca;
import modelo.Producto;
import javafx.beans.property.SimpleFloatProperty;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import controlador.ControladorErrores;


public class ControladorProducto implements Initializable{
	private ControladorVentanas ventanas;
	private Marca m;
	private Categoria c;
	private Producto p;
	private ControladorErrores ce;
	

	private FilteredList<Producto> datosBusquedas;
	@FXML private ComboBox<Marca> cbMarca;
	@FXML private ComboBox<Categoria> cbCategoria;
	@FXML TableColumn<Producto, String> tcDescripcion, tcMarca, tcCategoria, tcNumParte;
	@FXML TableColumn<Producto, Integer> tcCodigo;
	@FXML TableView<Producto> tvProductos;
	@FXML TextField txtID, txtExistencias, txtPrecio1, txtPrecio2, txtPrecio3, txtNumParte, txtStockMax, txtStockMin, txtBuscador;
	@FXML TextArea txtDescripcion;
	@FXML CheckBox ckbRegistrosInactivos; 
	@FXML Label lblMensaje, lblMensaje2;
	@FXML Button btnEliminar;
	
	//region variables paginacion
		private int filasXPagina;
		private ObservableList<Producto> datos;
		private FilteredList<Producto> datosBusqueda;
		@FXML Pagination paginador;
	
		public ControladorProducto(){
			p = new Producto();
			m = new Marca();
			c = new Categoria();
			filasXPagina=10;
			ventanas =  ControladorVentanas.getInstancia();
			datos = FXCollections.observableArrayList();
		}
		

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		try{
			cbCategoria.setItems(c.getCategoria());
			cbMarca.setItems(m.getMarca());
			tcCodigo.setCellValueFactory(new PropertyValueFactory<Producto, Integer>("id_producto"));
			tcDescripcion.setCellValueFactory(new PropertyValueFactory<Producto, String>("descripcion"));
			tcMarca.setCellValueFactory(new PropertyValueFactory<Producto, String>("m"));
			tcCategoria.setCellValueFactory(new PropertyValueFactory<Producto, String>("c"));
			tcNumParte.setCellValueFactory(new PropertyValueFactory<Producto, String>("numero_de_parte"));
			
			//llenar table view
			llenarTableView();
			
			if(ControladorMenu.nivel.equals("usuario")){
				btnEliminar.setDisable(true);}
			
			}catch (SQLException ex){
				ce.printlog(ex.getMessage(), this.getClass().toString());
			};
	}
	
	
	//region metodos de paginacion
	
		public void llenarTableView(){
			try{
				datos=p.getProductos();
				datosBusqueda = new FilteredList<>(datos);
				paginador.setPageCount((int)(Math.ceil((double)datosBusqueda.size()/filasXPagina)));//redondea las paginas
				//System.out.println(Math.ceil((double)datosBusqueda.size()/filasXPagina));
				paginador.setPageFactory((Integer pagina) -> createPage(pagina));
				lblMensaje2.setText(datos.size() + " registros encontrados");
			}catch (Exception ex){
				ce.printlog(ex.getMessage(), this.getClass().toString());
				lblMensaje.setText("Se ha producido un error al recuperar los datos");
			}
		}

		
		public void llenarTableView2(){
			try{
				datos=p.getProductos2();
				datosBusqueda = new FilteredList<>(datos);
				paginador.setPageCount((int)(Math.ceil((double)datosBusqueda.size()/filasXPagina)));//redondea las paginas
				System.out.println(Math.round(datosBusqueda.size()/filasXPagina));
				paginador.setPageFactory((Integer pagina) -> createPage(pagina));
				lblMensaje2.setText(datos.size() + " registros inactivos encontrados");
			}catch (Exception ex){
				ce.printlog(ex.getMessage(), this.getClass().toString());
				lblMensaje.setText("Se ha producido un error al recuperar los datos");
			}
		}
		
	
		private Node createPage(int pageIndex){
			if(filasXPagina>0){
				int fromIndex = pageIndex * filasXPagina;
				int toIndex = Math.min(fromIndex + filasXPagina, datosBusqueda.size());
				tvProductos.setItems(FXCollections.observableArrayList(
					datosBusqueda.subList(fromIndex, toIndex)));
			}else{
				tvProductos.setItems(null);
				paginador.setPageCount(0);
			}
			return new BorderPane(tvProductos);
		}
		
		
	//metodo para subir los datos del tableview al los textfield
	@FXML public void click_TableView(){
		if(tvProductos.getSelectionModel().getSelectedItem()!=null){
			p = tvProductos.getSelectionModel().getSelectedItem();
			txtID.setText(p.getId_producto().toString());
			txtExistencias.setText(p.getExistencias().toString());
			txtDescripcion.setText(p.getDescripcion());
			txtNumParte.setText(p.getNumero_de_parte());
			txtPrecio1.setText(p.getPrecio_1().toString());
			txtPrecio2.setText(p.getPrecio_2().toString());
			txtPrecio3.setText(p.getPrecio_3().toString());
			txtStockMax.setText(p.getStock_maximo().toString());
			txtStockMin.setText(p.getStock_minimo().toString());
			//combo Box
			cbCategoria.getSelectionModel().select(p.getC());
			cbMarca.getSelectionModel().select(p.getM());
			
		}
	}
	
	
	@FXML public void buscarTexto(){
		
		try {
			if(txtBuscador.getText().trim().isEmpty()){
				//Llenar table view
				datosBusqueda=new FilteredList<>(datos);
				paginador.setPageCount(datosBusqueda.size()/filasXPagina);
				paginador.setPageFactory((Integer pagina) -> createPage(pagina));
				lblMensaje2.setText(datosBusqueda.size() + " registros encontrados en la Base de Datos.");	
			}
			else{
				datosBusqueda.setPredicate(Producto->Producto.getDescripcion().toLowerCase().contains(txtBuscador.getText().toLowerCase()));
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
			
		}catch (Exception ex) {
			//Enviar mensaje
			ce.printlog(ex.getMessage(), this.getClass().toString());
				lblMensaje2.setText("No se encontraron resultados");
				filasXPagina=0;
				paginador.setPageCount(filasXPagina); 				
			paginador.setPageFactory((Integer pagina) -> createPage(pagina));
			
		}
		
	}
	
	
	public void limpiar(){
		txtID.clear();
		txtExistencias.clear();
		txtDescripcion.clear();
		txtNumParte.clear();
		txtPrecio1.clear();
		txtPrecio2.clear();
		txtPrecio3.clear();
		txtStockMax.clear();
		txtStockMin.clear();
		//combo Box
		cbCategoria.getSelectionModel().select(-1);
		cbMarca.getSelectionModel().select(-1);
		
	}
	
	
	@FXML public void click_insertar(){ 
		try{
			if(
					txtID.getText().trim().isEmpty()||
					txtExistencias.getText().trim().isEmpty()||
					txtDescripcion.getText().trim().isEmpty()||
					txtNumParte.getText().trim().isEmpty()||
					txtPrecio1.getText().trim().isEmpty()||
					txtPrecio2.getText().trim().isEmpty()||
					txtPrecio3.getText().trim().isEmpty()||
					txtStockMax.getText().trim().isEmpty()||
					txtStockMin.getText().trim().isEmpty()||
					cbCategoria.getSelectionModel().getSelectedItem()==null||
					cbMarca.getSelectionModel().getSelectedItem()==null){
					lblMensaje.setText("faltan datos por ingresar");
			}else{
				p=new Producto();
				p.setId_producto(new SimpleIntegerProperty(Integer.valueOf(txtID.getText())));
				p.setExistencias(new SimpleIntegerProperty(Integer.valueOf(txtExistencias.getText())));
				p.setDescripcion(new SimpleStringProperty(txtDescripcion.getText()));
				p.setNumero_de_parte(new SimpleStringProperty(txtNumParte.getText()));
				p.setPrecio_1(new SimpleFloatProperty(Float.valueOf(txtPrecio1.getText())));
				p.setPrecio_2(new SimpleFloatProperty(Float.valueOf(txtPrecio2.getText())));
				p.setPrecio_3(new SimpleFloatProperty(Float.valueOf(txtPrecio3.getText())));
				p.setStock_maximo(new SimpleIntegerProperty(Integer.valueOf(txtStockMax.getText())));
				p.setStock_minimo(new SimpleIntegerProperty(Integer.valueOf(txtStockMin.getText())));
				p.setM(cbMarca.getSelectionModel().getSelectedItem());
				p.setC(cbCategoria.getSelectionModel().getSelectedItem());
				
				boolean res = p.insertar();
				if(res){
					limpiar();
					llenarTableView();
					lblMensaje.setText("Datos insertados");
				}else
					lblMensaje.setText("Se ha producido un error en el servidor");
				}
			}catch (Exception ex){
				ce.printlog(ex.getMessage(), this.getClass().toString());
				lblMensaje.setText("Se ha producido un error en el servidor");

		}
	}
	
	
	@FXML public void click_actualizar(){ 
		try{
			if(
					txtID.getText().trim().isEmpty()||
					txtExistencias.getText().trim().isEmpty()||
					txtDescripcion.getText().trim().isEmpty()||
					txtNumParte.getText().trim().isEmpty()||
					txtPrecio1.getText().trim().isEmpty()||
					txtPrecio2.getText().trim().isEmpty()||
					txtPrecio3.getText().trim().isEmpty()||
					txtStockMax.getText().trim().isEmpty()||
					txtStockMin.getText().trim().isEmpty()||
					cbCategoria.getSelectionModel().getSelectedItem()==null||
					cbMarca.getSelectionModel().getSelectedItem()==null){
					lblMensaje.setText("faltan datos por ingresar");
			}else{
				p=new Producto();
				p.setId_producto(new SimpleIntegerProperty(Integer.valueOf(txtID.getText())));
				p.setExistencias(new SimpleIntegerProperty(Integer.valueOf(txtExistencias.getText())));
				p.setDescripcion(new SimpleStringProperty(txtDescripcion.getText()));
				p.setNumero_de_parte(new SimpleStringProperty(txtNumParte.getText()));
				p.setPrecio_1(new SimpleFloatProperty(Float.valueOf(txtPrecio1.getText())));
				p.setPrecio_2(new SimpleFloatProperty(Float.valueOf(txtPrecio2.getText())));
				p.setPrecio_3(new SimpleFloatProperty(Float.valueOf(txtPrecio3.getText())));
				p.setStock_maximo(new SimpleIntegerProperty(Integer.valueOf(txtStockMax.getText())));
				p.setStock_minimo(new SimpleIntegerProperty(Integer.valueOf(txtStockMin.getText())));
				p.setM(cbMarca.getSelectionModel().getSelectedItem());
				p.setC(cbCategoria.getSelectionModel().getSelectedItem());
				
				boolean res = p.actualizar();
				if(res){
					limpiar();
					llenarTableView();
					lblMensaje.setText("Datos insertados");
				}else
					lblMensaje.setText("Se ha producido un error en el servidor");
				}
			}catch (Exception ex){
				ce.printlog(ex.getMessage(), this.getClass().toString());
				lblMensaje.setText("Se ha producido un error en el servidor");

		}
	}
	
	
	@FXML public void click_eliminar(){
		if(txtID.getText().isEmpty()){
			lblMensaje.setText("Debe seleccionar un registro");
		}else{
			p=new Producto();
			p.setId_producto(new SimpleIntegerProperty(Integer.valueOf(txtID.getText())));
			if(p.eliminar()==true){
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
	
	
	//Método para incorporar otra Categoria.
		@FXML public void clickCategoria(){
			try {
				ventanas.modal2("../vista/fxml/CategoriaEmergente.fxml","Categoria");
				cbCategoria.setItems(c.getCategoria());
			} catch (SQLException ex) {
				// TODO Auto-generated catch block
				ce.printlog(ex.getMessage(), this.getClass().toString());
			}
		}
		
		
		//Método para incorporar otra marca
				@FXML public void clickMarca(){
					try {
						ventanas.modal("../vista/fxml/MarcasEmergente.fxml","Marca");
						cbCategoria.setItems(c.getCategoria());
					} catch (SQLException ex) {
						// TODO Auto-generated catch block
						ce.printlog(ex.getMessage(), this.getClass().toString());
					}
				}
		
}
