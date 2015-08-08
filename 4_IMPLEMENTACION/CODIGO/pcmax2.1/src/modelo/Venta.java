package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import modelo.Cliente;
import modelo.Conexion;
import modelo.Detalle;
import modelo.Usuario;

public class Venta {
	private Integer cantidad, id_venta, id_detalle_venta;
	private float total;
	private Usuario usuario;
	private Producto producto;
	private Cliente cliente;
	private ObservableList<Detalle> listaDetalle;
	private Conexion con;
	
	public Venta() {
		setCantidad(setId_venta(setId_detalle_venta(0)));
		setUsuario(new Usuario());
		setCliente(new Cliente());
		setProducto(new Producto());
		listaDetalle = FXCollections.observableArrayList();
		con = Conexion.getInstance();
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Integer getId_detalle_venta() {
		return id_detalle_venta;
	}

	public Integer setId_detalle_venta(Integer id_detalle_venta) {
		this.id_detalle_venta = id_detalle_venta;
		return id_detalle_venta;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Integer getId_venta() {
		return id_venta;
	}

	public Integer setId_venta(Integer id_venta) {
		this.id_venta = id_venta;
		return id_venta;
	}
	
	public boolean agregarDetalle(){
		boolean existe=false;
		try {
			if(listaDetalle.isEmpty()==false){
				for(int i=0; i<listaDetalle.size();i++){
					if(listaDetalle.get(i).getId_producto()== producto.getId_producto()){
						//El producto existe
						Detalle d = listaDetalle.get(i);
						int nuevacantidad = d.getCantidad() 
								+ cantidad;
						d.setCantidad(new SimpleIntegerProperty(nuevacantidad));
						Float nuevoSubtotal = d.getPrecio() 
								* nuevacantidad; 
						d.setSubtotal(new SimpleFloatProperty(nuevoSubtotal));
						listaDetalle.set(i,d);
						existe=true;
					}
				}
			}			
			if(listaDetalle.isEmpty() || existe ==false){
				Detalle productos = new Detalle();
				productos.setId_producto(new SimpleIntegerProperty(producto.getId_producto()));
				productos.setCantidad(new SimpleIntegerProperty(cantidad));
				productos.setPrecio(new SimpleFloatProperty(producto.getPrecio_1()));
				productos.setSubtotal(new SimpleFloatProperty(cantidad*producto.getPrecio_1()));
				productos.setDescripcion(new SimpleStringProperty(producto.getDescripcion()));			
				listaDetalle.add(productos);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public float getTotal(){
		total=0;
		for(Detalle d: listaDetalle){
			total+=d.getSubtotal();
		}
		return total;
	}
	
	public ObservableList<Detalle> obtenerDetalle(){
		return listaDetalle;
	}
	
	public boolean guardar(){
		try {
			int id_venta=0;
			String sql ="insert into venta values(1,1,now(),1,12.5,'30 dias',1,1)";
			String sql2;
			con.conectar();	
			con.getConexion().setAutoCommit(false);
			PreparedStatement comando1 = con.getConexion().prepareStatement(sql);
			PreparedStatement comando2;
			comando1.execute();
			//Recuperar id de la ultima venta insertada
			sql="select max(id_venta) from venta";
			comando2= con.getConexion().prepareStatement(sql);
			ResultSet rs = comando2.executeQuery();
			if(rs.next()){
				id_venta= rs.getInt(1);
				System.out.println(id_venta);
			}
			//Insertar detalle
			for(Detalle d: listaDetalle){
				sql="insert into detalleventa values (1, ?, ?, ?,?,?,?)";
				comando2 = con.getConexion().prepareStatement(sql);
				comando2.setInt(1, id_venta);
				comando2.setInt(2, d.getId_producto());
				comando2.setFloat(3, d.getPrecio());
				comando2.setInt(4, d.getCantidad());
				comando2.executeUpdate();
			}
			con.getConexion().commit();
			con.getConexion().setAutoCommit(true);
			return true;
		} catch (Exception e) {
		
			e.printStackTrace();
			return false;
		}
		finally{
			con.desconectar();
		}
	}
	
	
}
