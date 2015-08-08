package controlador;

import java.net.URL;
import java.util.ResourceBundle;




import modelo.Detalle;
import modelo.Producto;
import modelo.Venta;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;

public class ControladorVentas implements Initializable {
	@FXML ListView<Producto> lvProductos;
	@FXML TextField txtBuscador, txtProducto, txtPrecio, txtExistencia, 
	txtCantidad, txtTotal;
	@FXML TableView<modelo.Detalle> tvDetalle;
	@FXML TableColumn<modelo.Detalle, String> tcProducto;
	@FXML TableColumn<modelo.Detalle, Integer>  tcCantidad;
	@FXML TableColumn<modelo.Detalle, Float> tcPrecio, tcSubtotal;
	@FXML TableColumn tcEliminar;
	
	private ObservableList<Producto> elementos;
	private FilteredList<Producto> elementosBusqueda;
	private Producto p;
	private Venta v;
	
	public ControladorVentas() {
		elementos = FXCollections.observableArrayList();
		p = new Producto();
		v = new Venta();
		System.out.println(getClass().getResource("/vista/imagenes/delete.png").getPath());
		
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			elementos = p.getProductos();
			insertarBoton();
			lvProductos.setItems(elementos);
			tcProducto.setCellValueFactory(
					new PropertyValueFactory<modelo.Detalle, String>("descripcion"));
			tcCantidad.setCellValueFactory(
					new PropertyValueFactory<modelo.Detalle, Integer>("cantidad"));
			tcPrecio.setCellValueFactory(
					new PropertyValueFactory<modelo.Detalle, Float>("precio"));
			tcSubtotal.setCellValueFactory(
					new PropertyValueFactory<modelo.Detalle, Float>("subtotal"));
			
		} catch (Exception e) {
			e.printStackTrace(); 
		}
	}
	
	@FXML public void guardar(){
		try {
			v.guardar();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	
	@FXML public void buscador(){
		try {
			if(!txtBuscador.getText().trim().isEmpty()){
				elementosBusqueda = new FilteredList<Producto>(elementos);
				elementosBusqueda.setPredicate(Producto->Producto.getDescripcion().toLowerCase().
		 					contains(txtBuscador.getText().toLowerCase()));
				lvProductos.setItems(elementosBusqueda);
			}
			else{
				lvProductos.setItems(elementos);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@FXML public void seleccionar(){ 
		try {
			Producto p = lvProductos.getSelectionModel().getSelectedItem();
			if(p!=null){
				txtProducto.setText(p.getDescripcion());
				txtExistencia.setText(p.getExistencias().toString());
				txtPrecio.setText(p.getPrecio_1().toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@FXML public void agregarProducto(){
		try {
			int cantidad = Integer.parseInt(txtCantidad.getText());
			int existencias = Integer.parseInt(txtExistencia.getText());
			
			if(cantidad > 0 && cantidad <= existencias){
				v.setProducto(lvProductos.getSelectionModel().getSelectedItem());
				v.setCantidad(Integer.parseInt(txtCantidad.getText()));
				if(v.agregarDetalle()==true){
					System.out.println("Se agrego el producto.");
					actualizarDetalle();
					
				}
				else
					System.out.println("Ha ocurrido un error.");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	
	@FXML public void eliminar(){
		
	}
	
	public void actualizarDetalle(){
		try {
			
			tvDetalle.setItems(v.obtenerDetalle());
			//Actualizar el total
			///???????
			txtTotal.setText(String.valueOf(v.getTotal()));
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void insertarBoton(){
		try{
		
        tcEliminar.setSortable(false);         
        tcEliminar.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Detalle, Boolean>, 
                ObservableValue<Boolean>>() {
 
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Detalle, Boolean> p) {
                return new SimpleBooleanProperty(p.getValue() != null);
            }
        });
 
        tcEliminar.setCellFactory(
                new Callback<TableColumn<Detalle, Boolean>, TableCell<Detalle, Boolean>>() {
 
            @Override
            public TableCell<Detalle, Boolean> call(TableColumn<Detalle, Boolean> p) {
                return new ButtonCell();
            }
         
        });
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	
	// #region Clase privada
	private class ButtonCell extends TableCell<Detalle, Boolean> {
       
        Image eliminarImagen;
        ImageView iv;
        final Button cellButton;
        
        ButtonCell(){
        	eliminarImagen = new Image(getClass().getResourceAsStream("/vista/imagenes/delete.png"),10,10,false,false);
        	iv = new ImageView(eliminarImagen);
        	cellButton = new Button("", new ImageView(eliminarImagen));
      	
            cellButton.setOnAction(new EventHandler<ActionEvent>(){
 
				@Override
				public void handle(ActionEvent arg0) {
					// TODO Auto-generated method stub
					System.out.println("gola");
					
				}
            });
        }
 

        @Override
        protected void updateItem(Boolean t, boolean empty) {
            super.updateItem(t, empty);
            if(!empty){
                setGraphic(cellButton);
            }
        }
    }
     
     // #endregionS
	
}
