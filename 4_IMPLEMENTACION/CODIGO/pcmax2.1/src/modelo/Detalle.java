package modelo;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Detalle {
	private IntegerProperty cantidad, id_producto;
	private FloatProperty precio, subtotal;
	private StringProperty descripcion;
	
	public Detalle() {
		cantidad = id_producto= new SimpleIntegerProperty();
		precio=subtotal = new SimpleFloatProperty();
		descripcion=new SimpleStringProperty();
	}
	
	// #region Getters and Setters
	public int getCantidad() {
		return cantidad.get();
	}
	public void setCantidad(IntegerProperty cantidad) {
		this.cantidad = cantidad;
	}
	public int getId_producto() {
		return id_producto.get();
	}
	public void setId_producto(IntegerProperty id_producto) {
		this.id_producto = id_producto;
	}
	public Float getPrecio() {
		return precio.get();
	}
	public void setPrecio(FloatProperty precio) {
		this.precio = precio;
	}
	public Float getSubtotal() {
		return subtotal.get();
	}
	public void setSubtotal(FloatProperty subtotal) {
		this.subtotal = subtotal;
	}
	public String getDescripcion() {
		return descripcion.get();
	}
	public void setDescripcion(StringProperty descripcion) {
		this.descripcion = descripcion;
	}
	
	// #endregion
	
	
}
