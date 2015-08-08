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


public class Servicio {
	private ControladorErrores ce;
	private IntegerProperty id_servicio, dias_de_garantia;
	private FloatProperty precio_1, precio_2, precio_3;
	private StringProperty tipo_de_servicio;
	private Conexion con;
	private ObservableList<Servicio> elementos;
	

	public Servicio(){
		ce= new ControladorErrores();
		con = Conexion.getInstance();
		id_servicio = dias_de_garantia = new SimpleIntegerProperty();
		precio_1 = precio_2 = precio_3 = new SimpleFloatProperty();
		tipo_de_servicio = new SimpleStringProperty();
	}

	public Integer getId_servicio() {
		return id_servicio.getValue();
	}

	public void setId_servicio(IntegerProperty id_servicio) {
		this.id_servicio = id_servicio;
	}

	public Integer getDias_de_garantia() {
		return dias_de_garantia.getValue();
	}

	public void setDias_de_garantia(IntegerProperty dias_de_garantia) {
		this.dias_de_garantia = dias_de_garantia;
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

	public String getTipo_de_servicio() {
		return tipo_de_servicio.getValue();
	}

	public void setTipo_de_servicio(StringProperty tipo_de_servicio) {
		this.tipo_de_servicio = tipo_de_servicio;
	}
	
	
	public ObservableList<Servicio> getServicios() throws SQLException{
		ResultSet rs=null;
		try{
			String sql="select * from servicio where estatus = 's' order by id_servicio";
			con.conectar();
			PreparedStatement comando = con.getConexion().prepareStatement(sql);
			rs= comando.executeQuery();
			elementos = FXCollections.observableArrayList();
		while(rs.next()){
			Servicio s = new Servicio();
			s.id_servicio = new SimpleIntegerProperty(rs.getInt("id_servicio"));
			s.tipo_de_servicio = new SimpleStringProperty(rs.getString("tipo_de_servicio"));
			s.precio_1 = new SimpleFloatProperty(rs.getFloat("precio_1"));
			s.precio_2 = new SimpleFloatProperty(rs.getFloat("precio_2"));
			s.precio_3 = new SimpleFloatProperty(rs.getFloat("precio_3"));
			s.dias_de_garantia = new SimpleIntegerProperty(rs.getInt("dias_de_garantia"));
			elementos.add(s);
		}
		}catch (Exception ex){
			ce.printlog(ex.getMessage(), this.getClass().toString());
		}
		finally{
			con.desconectar(); 
			rs.close();
		}
		return elementos;
	}
	
	
	public ObservableList<Servicio> getServicios2() throws SQLException{
		ResultSet rs=null;
		try{
			String sql="select * from servicio where estatus = 's' order by id_servicio";
			con.conectar();
			PreparedStatement comando = con.getConexion().prepareStatement(sql);
			rs= comando.executeQuery();
			elementos = FXCollections.observableArrayList();
		while(rs.next()){
			Servicio s = new Servicio();
			s.id_servicio = new SimpleIntegerProperty(rs.getInt("id_servicio"));
			s.tipo_de_servicio = new SimpleStringProperty(rs.getString("tipo_de_servicio"));
			s.precio_1 = new SimpleFloatProperty(rs.getFloat("precio_1"));
			s.precio_2 = new SimpleFloatProperty(rs.getFloat("precio_2"));
			s.precio_3 = new SimpleFloatProperty(rs.getFloat("precio_3"));
			s.dias_de_garantia = new SimpleIntegerProperty(rs.getInt("dias_de_garantia"));
			elementos.add(s);
		}
		}catch (Exception ex){
			ce.printlog(ex.getMessage(), this.getClass().toString());
		}
		finally{
			con.desconectar(); 
			rs.close();
		}
		return elementos;
	}
	
	
	public boolean insertar(){
		try {
			String sql = "select fn_agregarservicio (?,?,?,?,?)";
			con.conectar();
			PreparedStatement comando = con.getConexion().prepareStatement(sql);
			comando.setString(1, this.getTipo_de_servicio());
			comando.setFloat(2, this.getPrecio_1());
			comando.setFloat(3, this.getPrecio_2());
			comando.setFloat(4, this.getPrecio_3());
			comando.setInt(5,this.getDias_de_garantia());
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
			String sql = "select fn_eliminarservicio (?)";
			con.conectar();
			PreparedStatement comando = con.getConexion().prepareStatement(sql);
			comando.setInt(1, this.getId_servicio());
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
			String sql = "select fn_actualizarservicio (?,?,?,?,?,?)";
			con.conectar();
			PreparedStatement comando = con.getConexion().prepareStatement(sql);
			comando.setInt(1,this.getId_servicio());
			comando.setString(2, this.getTipo_de_servicio());
			comando.setFloat(3, this.getPrecio_1());
			comando.setFloat(4, this.getPrecio_2());
			comando.setFloat(5, this.getPrecio_3());
			comando.setInt(6,this.getDias_de_garantia());
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
	
}
