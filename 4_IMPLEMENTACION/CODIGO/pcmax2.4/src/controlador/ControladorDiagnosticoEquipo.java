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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import modelo.CheckEquipo;
import modelo.ExportExcelBaseDiagnosticos;
import controlador.ControladorErrores;


public class ControladorDiagnosticoEquipo implements Initializable {
	
	private CheckEquipo ch;

	@FXML TableColumn<CheckEquipo, String> tcEquipo, tcSistemaE, tcSistemaO, tcDanios, tcFallas,tcDiagnostico;
	@FXML TextArea txtDanios, txtFallas, txtDiagnostico;
	@FXML TableView<CheckEquipo> tvDiagnostico;
	@FXML TextField txtTipoEquipo,txtId,txtSistemaEquipo, txtSistemaOpe, txtBuscador;
	@FXML Label lblMensaje;
	@FXML Button guardar, btnRestaurar, btnAbrirexcel;
	@FXML CheckBox ckbInactivos;
	@FXML ToggleButton tbtnCambiar;
	
	
	private int filasXPagina, registros;
	private ControladorErrores ce;
	private ObservableList<CheckEquipo> datos;
	private FilteredList<CheckEquipo> datosBusqueda;
	
	@FXML Pagination paginador;
	
	
	public ControladorDiagnosticoEquipo() {
		// TODO Auto-generated constructor stub
		ch = new CheckEquipo();
		filasXPagina=10;
		datos = FXCollections.observableArrayList();
	}
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		tcEquipo.setCellValueFactory(new PropertyValueFactory<CheckEquipo, String>("tipo_equipo"));
	    tcSistemaE.setCellValueFactory(new PropertyValueFactory<CheckEquipo, String>("sistema_equipo"));
	    tcSistemaO.setCellValueFactory(new PropertyValueFactory<CheckEquipo, String>("sistema_operativo"));
	    tcDanios.setCellValueFactory(new PropertyValueFactory<CheckEquipo, String>("danios_presentados"));
	    tcFallas.setCellValueFactory(new PropertyValueFactory<CheckEquipo, String>("falla_detectada"));
	    tcDiagnostico.setCellValueFactory(new PropertyValueFactory<CheckEquipo, String>("diagnostico"));
		//tvFilm.setItems(f.getFilms());
		
		
		// llenar table view
		
		llenarTableView(true);;
}
	

	// #regionmetodos paginacion
	
	private Node createPage(int pageIndex){
		int fromIndex = pageIndex * filasXPagina;
		int toIndex = Math.min(fromIndex + filasXPagina, datosBusqueda.size());
		tvDiagnostico.setItems(FXCollections.observableArrayList(
				datosBusqueda.subList(fromIndex, toIndex)));
		return new BorderPane(tvDiagnostico);
	}

	public void llenarTableView(Boolean estatus){
		try {
			//Refrescar y volver a cargar los datos en el TableView
			datos=ch.getCheckEquipos();
			datosBusqueda = new FilteredList<>(datos);
			paginador.setPageCount((int)(Math.ceil((double)datosBusqueda.size()/filasXPagina)));
			paginador.setPageFactory((Integer pagina) -> createPage(pagina));
			lblMensaje.setText(datos.size() + " registros encontrados.");
		} catch (Exception ex) {
			ce.printlog(ex.getMessage(), this.getClass().toString());
			lblMensaje.setText("Se ha producido un error al recuperar los datos.");
		}
			
	}
	
	@FXML public void click_TableView(){
		
		if(tvDiagnostico.getSelectionModel().getSelectedItem()!=null){
			ch = tvDiagnostico.getSelectionModel().getSelectedItem();
			
			txtId.setText(ch.getId_check_equipo().toString());
			txtTipoEquipo.setText(ch.getTipo_equipo().toString());
			txtSistemaEquipo.setText(ch.getSistema_equipo().toString());
			txtSistemaOpe.setText(ch.getSistema_operativo().toString());
			txtDanios.setText(ch.getDanios_presentados().toString());
			txtFallas.setText(ch.getFalla_detectada().toString());
			txtDiagnostico.setText(ch.getDiagnostico().toString());
			
			txtId.setDisable(true);
		}
		
	}
	
	@FXML public void click_insertar(){
		System.out.println("****************************");
		try{
			if(txtTipoEquipo.getText().trim().isEmpty() ||
					txtSistemaEquipo.getText().trim().isEmpty()||
					txtSistemaOpe.getText().trim().isEmpty()||
					txtDanios.getText().trim().isEmpty()||
					txtFallas.getText().trim().isEmpty()||
					txtDiagnostico.getText().trim().isEmpty()){
				lblMensaje.setText("Faltan datos por ingresar.");
				System.out.println("****************************");
			}
			else{
				ch = new CheckEquipo();
				ch.setTipo_equipo(new SimpleStringProperty(txtTipoEquipo.getText()));
				ch.setSistema_equipo(new SimpleStringProperty(txtSistemaEquipo.getText()));
				ch.setSistema_operativo(new SimpleStringProperty(txtSistemaOpe.getText()));
				ch.setDanios_presentados(new SimpleStringProperty(txtDanios.getText()));
				ch.setFalla_detectada(new SimpleStringProperty(txtFallas.getText()));
				ch.setDiagnostico(new SimpleStringProperty(txtDiagnostico.getText()));
				boolean yes = ch.insertar();
				if(yes){
					lblMensaje.setText("Datos insertados satisfactoriamente.");
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
	
	public void limpiar(){
		txtId.clear();
		txtTipoEquipo.clear();
		txtSistemaEquipo.clear();
		txtSistemaOpe.clear();
		txtDanios.clear();
		txtFallas.clear();
		txtDiagnostico.clear();

	}
	
	//Método para actualizar un registro en el TableView
		@FXML public void click_actualizar(){
			try {
				if(txtId.getText().isEmpty() ||
						txtTipoEquipo.getText().isEmpty() ||
						txtSistemaEquipo.getText().trim().isEmpty() ||
						txtSistemaOpe.getText().trim().isEmpty() ||
						txtDanios.getText().trim().isEmpty() ||
						txtFallas.getText().trim().isEmpty() ||
						txtDiagnostico.getText().trim().isEmpty()){
					lblMensaje.setText("Faltan datos por llenar");
				}
				else{
					ch = new CheckEquipo ();
					ch.setId_check_equipo(new SimpleIntegerProperty(Integer.valueOf(txtId.getText())));
					ch.setTipo_equipo(new SimpleStringProperty(txtTipoEquipo.getText()));
					ch.setSistema_equipo(new SimpleStringProperty(txtSistemaEquipo.getText()));
					ch.setSistema_operativo(new SimpleStringProperty(txtSistemaOpe.getText()));
					ch.setDanios_presentados(new SimpleStringProperty(txtDanios.getText()));
					ch.setFalla_detectada(new SimpleStringProperty(txtFallas.getText()));
					ch.setDiagnostico(new SimpleStringProperty(txtDiagnostico.getText()));
					boolean yes = ch.actualizar();
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
 /*	
		public void CheckTabla(){
			try {
				// llenar table view
				datos= ch.getCheckEquipos(false);
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
		
		
		@FXML
	 	public void Abrirexcel() {
	        new ExportExcelBaseDiagnosticos().WriteExcel();
	        String File = new String("src/archivos/Diagnosticos.xls"); 
			   
			 try{ 
			   //definiendo la ruta en la propiedad file
			   Runtime.getRuntime().exec("cmd /c start "+File);
			     
			   }catch(IOException ex){
				  ce.printlog(ex.getMessage(), this.getClass().toString());
			   } 
		}
		
		
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
		 			datosBusqueda.setPredicate(CheckEquipo->CheckEquipo.getTipo_equipo().toLowerCase().
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
	 	// #endregion
	 	
	}
