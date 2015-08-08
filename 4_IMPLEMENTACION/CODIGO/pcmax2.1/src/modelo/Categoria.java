package modelo;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
//import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import controlador.ControladorErrores;


public class Categoria {
	private ControladorErrores ce;
	private IntegerProperty id_categoria;
	private StringProperty nombre_categoria;
	private Conexion con;
	private ObservableList<Categoria> elementos;
	
	public Categoria(){
	ce= new ControladorErrores();
	con = Conexion.getInstance();
	id_categoria = new SimpleIntegerProperty();
	nombre_categoria = new SimpleStringProperty();
	//setNombre_categoria(new SimpleStringProperty());
	//setId_categoria(new SimpleIntegerProperty());
	}	
	
	
	public Integer getId_categoria() {
		return id_categoria.getValue();
	}
	public void setId_categoria(IntegerProperty id_categoria) {
		this.id_categoria = id_categoria;
	}
	public String getNombre_categoria() {
		return nombre_categoria.getValue();
	}
	public void setNombre_categoria(StringProperty nombre_categoria) {
		this.nombre_categoria = nombre_categoria;
	}
	
	public ObservableList<Categoria> getCategoria() throws SQLException{
		ResultSet rs=null;
		try{
			String sql="select id_categoria, nombre_categoria from categoria where estatus = 's' order by id_categoria";
			con.conectar();
			PreparedStatement comando = con.getConexion().prepareStatement(sql);
			rs= comando.executeQuery();
			elementos = FXCollections.observableArrayList();
		while(rs.next()){
			Categoria c = new Categoria();
			c.nombre_categoria.set(rs.getString("nombre_categoria"));
			c.id_categoria.set(rs.getInt("id_categoria"));
			elementos.add(c);
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
	public String toString(){
		return nombre_categoria.getValue();
	}
	
	public boolean insertar(){
		try{
			String sql = "select fn_agregarcategoria (?)";
			con.conectar();
			PreparedStatement comando = con.getConexion().prepareStatement(sql);
			comando.setString(1, this.getNombre_categoria());
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
			String sql = "select fn_eliminarcategoria (?)";
			con.conectar();
			PreparedStatement comando = con.getConexion().prepareStatement(sql);
			comando.setInt(1, this.getId_categoria());
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
			String sql = "select fn_actualizarcategoria (?,?)";
			con.conectar();
			PreparedStatement comando = con.getConexion().prepareStatement(sql);
			comando.setInt(1, this.getId_categoria());
			comando.setString(2, this.getNombre_categoria());
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
