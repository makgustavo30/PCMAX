package modelo;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Marca {
	private StringProperty nombre_marca;
	private IntegerProperty id_marca;
	private Conexion con;
	private ObservableList<Marca> elementos;
	
	public Marca(){
		con = Conexion.getInstance();
		id_marca = new SimpleIntegerProperty();
		nombre_marca = new SimpleStringProperty();
		//setNombre_marca(new SimpleStringProperty());
		//setId_marca(new SimpleIntegerProperty());
		}
	
		/*Setter and getters*/
	
	public String getNombre_marca() {
		return nombre_marca.getValue();
	}
	public void setNombre_marca(StringProperty nombre_marca) {
		this.nombre_marca = nombre_marca;
	}
	public Integer getId_marca() {
		return id_marca.getValue();
	}
	public void setId_marca(IntegerProperty id_marca) {
		this.id_marca = id_marca;
	}
	
	public ObservableList<Marca> getMarca() throws SQLException{
		ResultSet rs=null;
		try{
			String sql="select id_marca, nombre_marca from marcas where estatus = 's' order by id_marca";
			con.conectar();
			PreparedStatement comando = con.getConexion().prepareStatement(sql);
			rs= comando.executeQuery();
			elementos = FXCollections.observableArrayList();
		while(rs.next()){
			Marca m = new Marca();
			m.nombre_marca.set(rs.getString("nombre_marca"));
			m.id_marca.set(rs.getInt("id_marca"));
			elementos.add(m);
		}
		}catch (Exception e){
			e.printStackTrace();
		}
		finally{
			con.desconectar(); 
			rs.close();
		}
		return elementos;
	}
	public String toString(){
		return nombre_marca.getValue();
	}
	
	
	public boolean insertar(){
		try{
			String sql = "select fn_agregarmarca (?)";
			con.conectar();
			PreparedStatement comando = con.getConexion().prepareStatement(sql);
			comando.setString(1, this.getNombre_marca());
			comando.execute();
			return true;
		}catch (Exception e){
			e.printStackTrace();
			return false;
		}
		finally{
		con.desconectar();
		}
	}
	
	public boolean eliminar(){
		try{
			String sql = "select fn_eliminarmarca (?)";
			con.conectar();
			PreparedStatement comando = con.getConexion().prepareStatement(sql);
			comando.setInt(1, this.getId_marca());
			comando.execute();
			return true;
		}catch (Exception e){
			e.printStackTrace();
			return false;
		}
		finally{
		con.desconectar();
		}
	}
	
	public boolean actualizar(){
		try {
			String sql = "select fn_actualizarmarca (?,?)";
			con.conectar();
			PreparedStatement comando = con.getConexion().prepareStatement(sql);
			comando.setInt(1, this.getId_marca());
			comando.setString(2, this.getNombre_marca());
			comando.execute();
			return true;
		
		}catch (Exception e){
			e.printStackTrace();
			return false;
		}
		finally{
		con.desconectar();
		}
	}
}
