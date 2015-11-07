package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import controlador.ControladorErrores;



public class Producto {
	private ControladorErrores ce;
	private StringProperty numero_de_parte, descripcion;
	private IntegerProperty id_producto, existencias, stock_maximo, stock_minimo;
	private FloatProperty precio_1, precio_2, precio_3;
	private Marca m;
	private Categoria c;
	private Conexion con;
	private ObservableList<Producto> elementos;
	
	
	public Producto(){
	con = Conexion.getInstance();
	numero_de_parte = descripcion = new SimpleStringProperty();
	id_producto = existencias = stock_maximo = stock_minimo = new SimpleIntegerProperty();
	precio_1 = precio_2 = precio_3 = new SimpleFloatProperty();
	ce= new ControladorErrores();
	m = new Marca();
	c = new Categoria();
	}
	
	/*  Sección Setter and Getter*/
	public Integer getId_producto() {
		return id_producto.getValue();
	}
	public void setId_producto(IntegerProperty id_producto) {
		this.id_producto = id_producto;
	}
	public String getNumero_de_parte() {
		return numero_de_parte.getValue();
	}
	public void setNumero_de_parte(StringProperty numero_de_parte) {
		this.numero_de_parte = numero_de_parte;
	}
	public String getDescripcion() {
		return descripcion.getValue();
	}
	public void setDescripcion(StringProperty descripcion) {
		this.descripcion = descripcion;
	}
	public Integer getExistencias() {
		return existencias.getValue();
	}
	public void setExistencias(IntegerProperty existencias) {
		this.existencias = existencias;
	}
	public Integer getStock_maximo() {
		return stock_maximo.getValue();
	}
	public void setStock_maximo(IntegerProperty stock_maximo) {
		this.stock_maximo = stock_maximo;
	}
	public Integer getStock_minimo() {
		return stock_minimo.getValue();
	}
	public void setStock_minimo(IntegerProperty stock_minimo) {
		this.stock_minimo = stock_minimo;
	}
	public Float getPrecio_1() {
		return precio_1.getValue();
	}
	public void setPrecio_1(FloatProperty precio_1) {
		this.precio_1 = precio_1;
	}
	public Float getPrecio_2() {
		return precio_2.getValue();
	}
	public void setPrecio_2(FloatProperty precio_2) {
		this.precio_2 = precio_2;
	}
	public Float getPrecio_3() {
		return precio_3.getValue();
	}
	public void setPrecio_3(FloatProperty precio_3) {
		this.precio_3 = precio_3;
	}
	
	public Marca getM() {
		return m;
	}
	public void setM(Marca m) {
		this.m = m;
	}
	
	public Categoria getC() {
		return c;
	}
	public void setC(Categoria c) {
		this.c = c;
	}
	
	/* Tableview para campos activos*/
	public ObservableList<Producto> getProductos() throws SQLException {
		ResultSet rs=null;
		try{
			String sql="Select * from fn_producto()";
			con.conectar();
			PreparedStatement comando = con.getConexion().prepareStatement(sql);
			rs= comando.executeQuery();
			elementos = FXCollections.observableArrayList();
			while(rs.next()){
				Producto p = new Producto();
				p.id_producto = new SimpleIntegerProperty(rs.getInt("idproducto"));
				p.numero_de_parte = new SimpleStringProperty(rs.getString("numeroparte"));
				p.descripcion = new SimpleStringProperty(rs.getString("descripciones"));
				p.existencias = new SimpleIntegerProperty(rs.getInt("existencia"));
				p.c.setId_categoria(new SimpleIntegerProperty(rs.getInt("idcategoria")));
				p.c.setNombre_categoria(new SimpleStringProperty(rs.getString("nombrecategoria")));
				p.m.setId_marca(new SimpleIntegerProperty(rs.getInt("idmarca")));
				p.m.setNombre_marca(new SimpleStringProperty(rs.getString("nombremarca")));
				p.stock_maximo = new SimpleIntegerProperty(rs.getInt("stockmaximo"));
				p.stock_minimo = new SimpleIntegerProperty(rs.getInt("stockminimo"));
				p.precio_1 = new SimpleFloatProperty(rs.getFloat("precio1"));
				p.precio_2 = new SimpleFloatProperty(rs.getFloat("precio2"));
				p.precio_3 = new SimpleFloatProperty(rs.getFloat("precio3"));
				
				elementos.add(p);
			}
			}catch (Exception ex){
				ce.printlog(ex.getMessage(), this.getClass().toString());
			}
			finally{
				rs.close();
				con.desconectar();
			}
			return elementos;
		}
	
	
	/* Tableview para campos inactivos*/
	public ObservableList<Producto> getProductos2() throws SQLException {
		ResultSet rs=null;
		try{
			String sql="Select * from fn_productoinactivo()";
			con.conectar();
			PreparedStatement comando = con.getConexion().prepareStatement(sql);
			rs= comando.executeQuery();
			elementos = FXCollections.observableArrayList();
			while(rs.next()){
				Producto p = new Producto();
				p.id_producto = new SimpleIntegerProperty(rs.getInt("idproducto"));
				p.numero_de_parte = new SimpleStringProperty(rs.getString("numeroparte"));
				p.descripcion = new SimpleStringProperty(rs.getString("descripciones"));
				p.existencias = new SimpleIntegerProperty(rs.getInt("existencia"));
				p.c.setId_categoria(new SimpleIntegerProperty(rs.getInt("idcategoria")));
				p.c.setNombre_categoria(new SimpleStringProperty(rs.getString("nombrecategoria")));
				p.m.setId_marca(new SimpleIntegerProperty(rs.getInt("idmarca")));
				p.m.setNombre_marca(new SimpleStringProperty(rs.getString("nombremarca")));
				p.stock_maximo = new SimpleIntegerProperty(rs.getInt("stockmaximo"));
				p.stock_minimo = new SimpleIntegerProperty(rs.getInt("stockminimo"));
				p.precio_1 = new SimpleFloatProperty(rs.getFloat("precio1"));
				p.precio_2 = new SimpleFloatProperty(rs.getFloat("precio2"));
				p.precio_3 = new SimpleFloatProperty(rs.getFloat("precio3"));
				
				elementos.add(p);
			}
			}catch (Exception ex){
				ce.printlog(ex.getMessage(), this.getClass().toString());
			}
			finally{
				rs.close();
				con.desconectar();
			}
			return elementos;
		}
	
	
	public boolean insertar(){
		try {
			String sql = "select fn_agregarproducto (?,?,?,?,?,?,?,?,?,?)";
			con.conectar();
			PreparedStatement comando = con.getConexion().prepareStatement(sql);
			comando.setInt(1, this.getM().getId_marca());
			comando.setInt(2, this.getC().getId_categoria());
			comando.setInt(3, this.getId_producto());
			comando.setString(4, this.getNumero_de_parte());
			comando.setFloat(5, this.getPrecio_1());
			comando.setFloat(6, this.getPrecio_2());
			comando.setFloat(7, this.getPrecio_3());
			comando.setString(8, this.getDescripcion());
			comando.setInt(9, this.getStock_maximo());
			comando.setInt(10, this.getStock_minimo());
			comando.execute();
			return true;
		
		}catch (Exception ex){
			ce.printlog(ex.getMessage(), this.getClass().toString());
			return false;
		}
		finally{
		con.desconectar();
		}
	}
	
	
	public boolean eliminar(){
		try{
			String sql = "select fn_eliminarproducto (?)";
			con.conectar();
			PreparedStatement comando = con.getConexion().prepareStatement(sql);
			comando.setInt(1, this.getId_producto());
			comando.execute();
			return true;
		}catch (Exception ex){
			ce.printlog(ex.getMessage(), this.getClass().toString());
			return false;
		}
		finally{
		con.desconectar();
		}
	}
	
	
	public boolean actualizar(){
		try {
			String sql = "select fn_actualizarproductos (?,?,?,?,?,?,?,?,?,?)";
			String aux= this.getM().getId_marca() + ",";
			aux += this.getC().getId_categoria() + ",";
			aux += this.getId_producto() + ",";
			aux += this.getNumero_de_parte() + ",";
			aux += this.getPrecio_1() + ",";
			aux += this.getPrecio_2() + ",";
			aux += this.getPrecio_3() + ",";
			aux += this.getDescripcion() + ",";
			aux += this.getStock_maximo() + ",";
			aux += this.getStock_minimo() + ",";
			System.out.println(sql);
			System.out.println(aux);
			
			con.conectar();
			PreparedStatement comando = con.getConexion().prepareStatement(sql);
			comando.setInt(1, this.getM().getId_marca());
			comando.setInt(2, this.getC().getId_categoria());
			comando.setInt(3, this.getId_producto());
			comando.setString(4, this.getNumero_de_parte());
			comando.setFloat(5, this.getPrecio_1());
			comando.setFloat(6, this.getPrecio_2());
			comando.setFloat(7, this.getPrecio_3());
			comando.setString(8, this.getDescripcion());
			comando.setInt(9, this.getStock_maximo());
			comando.setInt(10, this.getStock_minimo());
			comando.execute();
			return true;
		
		}catch (Exception ex){
			ce.printlog(ex.getMessage(), this.getClass().toString());
			return false;
		}
		finally{
		con.desconectar();
		}
	}
	
	public String toString(){
		return descripcion.getValue();
	}
	
}
