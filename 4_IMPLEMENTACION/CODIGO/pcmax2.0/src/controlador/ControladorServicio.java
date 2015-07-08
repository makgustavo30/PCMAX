package controlador;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import modelo.Servicio;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;


public class ControladorServicio implements Initializable{

	private Servicio s;
	private FilteredList<Servicio> datosBusquedas;
	@FXML TableColumn<Servicio, String> tcTipoServicio;
	@FXML TableColumn<Servicio, Integer> tcID, tcDiasGarantia;
	@FXML TableColumn<Servicio, Float> tcPrecio1, tcPrecio2,tcPrecio3;
	@FXML TableView<Servicio> tvServicios;
	@FXML Label lblMensaje, lblMensaje2;
	@FXML TextField txtID, txtTipoServicio, txtDiasGarantia, txtBuscador, txtPrecio1, txtPrecio2, txtPrecio3;
	
	//region variables paginacion
		private int filasXPagina;
		private ObservableList<Servicio> datos;
		private FilteredList<Servicio> datosBusqueda;
		@FXML Pagination paginador;
	
	public ControladorServicio() {
		s = new Servicio();
		filasXPagina=10;
		datos = FXCollections.observableArrayList();
	}
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		try{
			tcDiasGarantia.setCellValueFactory(new PropertyValueFactory<Servicio, Integer>("dias_de_garantia"));
			tcID.setCellValueFactory(new PropertyValueFactory<Servicio, Integer>("id_servicio"));
			tcPrecio1.setCellValueFactory(new PropertyValueFactory<Servicio, Float>("precio_1"));
			tcPrecio2.setCellValueFactory(new PropertyValueFactory<Servicio, Float>("precio_2"));
			tcPrecio3.setCellValueFactory(new PropertyValueFactory<Servicio, Float>("precio_3"));
			tcTipoServicio.setCellValueFactory(new PropertyValueFactory<Servicio, String>("tipo_de_servicio"));
			
			
			datos=s.getServicios();
			datosBusqueda = new FilteredList<>(datos);
			paginador.setPageCount(datosBusqueda.size()/filasXPagina);
			paginador.setPageFactory((Integer pagina) -> createPage(pagina));
		
			}catch (SQLException e){
				e.printStackTrace();
			};			
	}

	public void llenarTableView(){
		try{
			datos=s.getServicios();
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
			datos=s.getServicios2();
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
		tvServicios.setItems(FXCollections.observableArrayList(
				datosBusqueda.subList(fromIndex, toIndex)));
		return new BorderPane(tvServicios);
	}
	
	
	@FXML public void click_TableView(){
		if(tvServicios.getSelectionModel().getSelectedItem()!=null){
			s = tvServicios.getSelectionModel().getSelectedItem();
			txtDiasGarantia.setText(s.getDias_de_garantia().toString());
			txtID.setText(s.getId_servicio().toString());
			txtPrecio1.setText(s.getPrecio_1().toString());
			txtPrecio2.setText(s.getPrecio_2().toString());
			txtPrecio3.setText(s.getPrecio_3().toString());
			txtTipoServicio.setText(s.getTipo_de_servicio());
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
				datosBusqueda.setPredicate(Film->Film.getTipo_de_servicio().toLowerCase().contains(txtBuscador.getText().toLowerCase()));
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
		txtDiasGarantia.clear();
		txtID.clear();
		txtPrecio1.clear();
		txtPrecio2.clear();
		txtPrecio3.clear();
		txtTipoServicio.clear();
	}
	
	@FXML public void click_insertar(){ 
		try{
			if(
					txtDiasGarantia.getText().trim().isEmpty()||
					txtID.getText().trim().isEmpty()||
					txtPrecio1.getText().trim().isEmpty()||
					txtPrecio2.getText().trim().isEmpty()||
					txtPrecio3.getText().trim().isEmpty()||					
					txtTipoServicio.getText().trim().isEmpty()){
				lblMensaje.setText("faltan datos por ingresar");
			}else{
				s=new Servicio();
				s.setDias_de_garantia(new SimpleIntegerProperty(Integer.valueOf(txtDiasGarantia.getText())));
				s.setId_servicio(new SimpleIntegerProperty(Integer.valueOf(txtID.getText())));
				s.setPrecio_1(new SimpleFloatProperty(Float.valueOf(txtPrecio1.getText())));
				s.setPrecio_2(new SimpleFloatProperty(Float.valueOf(txtPrecio2.getText())));
				s.setPrecio_3(new SimpleFloatProperty(Float.valueOf(txtPrecio3.getText())));
				s.setTipo_de_servicio(new SimpleStringProperty(txtTipoServicio.getText()));
				boolean res = s.insertar();
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
	
	
	@FXML public void click_actualizar(){ 
		try{
			if(
					txtDiasGarantia.getText().trim().isEmpty()||
					txtID.getText().trim().isEmpty()||
					txtPrecio1.getText().trim().isEmpty()||
					txtPrecio2.getText().trim().isEmpty()||
					txtPrecio3.getText().trim().isEmpty()||					
					txtTipoServicio.getText().trim().isEmpty()){
				lblMensaje.setText("faltan datos por ingresar");
			}else{
				s=new Servicio();
				s.setDias_de_garantia(new SimpleIntegerProperty(Integer.valueOf(txtDiasGarantia.getText())));
				s.setId_servicio(new SimpleIntegerProperty(Integer.valueOf(txtID.getText())));
				s.setPrecio_1(new SimpleFloatProperty(Float.valueOf(txtPrecio1.getText())));
				s.setPrecio_2(new SimpleFloatProperty(Float.valueOf(txtPrecio2.getText())));
				s.setPrecio_3(new SimpleFloatProperty(Float.valueOf(txtPrecio3.getText())));
				s.setTipo_de_servicio(new SimpleStringProperty(txtTipoServicio.getText()));
				boolean res = s.actualizar();
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
			s=new Servicio();
			s.setId_servicio(new SimpleIntegerProperty(Integer.valueOf(txtID.getText())));
			if(s.eliminar()==true){
				//limpiar 
				limpiar();
				llenarTableView();
	
				lblMensaje.setText("Se elimino el registro satisfactoriamente");
				
			}else{
				lblMensaje.setText("Se ha producido un error en el servidor");
			}
		}
	}	
	
}