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
import controlador.ControladorErrores;

public class Usuario1 {
	private StringProperty nombre_usuario,contrasenia,nombre, apellido_paterno, apellido_materno,
	calle, avenida, numero, colonia, ciudad, telefono, email,tipo;
	private IntegerProperty id_usuario, codigo_postal ;
	private ObservableList<Usuario1> elementos;
	private Conexion con;
	private ControladorErrores ce;
	
	
	public Usuario1(){
		nombre_usuario= contrasenia =nombre= apellido_paterno= apellido_materno= calle= avenida= numero= colonia= ciudad= telefono = email= tipo =new SimpleStringProperty();
		codigo_postal = new SimpleIntegerProperty();
		con = Conexion.getInstance();
		ce= new ControladorErrores();
	}

	// #region Getters and Setters
	
	public String getNombre_Usuario() {
		return nombre_usuario.get();
	}


	public void setNombre_Usuario(StringProperty nombre_usuario) {
		this.nombre_usuario = nombre_usuario;
	}
		
	public String getContrasenia() {
		return contrasenia.get();
	}


	public void setContrasenia(StringProperty contrasenia) {
		this.contrasenia = contrasenia;
	}
	
	public String getNombre() {
		return nombre.get();
	}


	public void setNombre(StringProperty nombre) {
		this.nombre = nombre;
	}

	public Integer getId_usuario() {
		return id_usuario.get();
	}

	public void setId_usuario(IntegerProperty id_usuario) {
		this.id_usuario = id_usuario;
	}


	public String getApellido_paterno() {
		return apellido_paterno.get();
	}


	public void setApellido_paterno(StringProperty apaterno) {
		this.apellido_paterno = apaterno;
	}
    
	public String getApellido_materno() {
		return apellido_materno.get();
	}
	
	public void setApellido_materno(StringProperty amaterno) {
		this.apellido_materno = amaterno;
	}
	
	public String getCalle() {
		return calle.get();
	}
	
	public void setCalle(StringProperty calle) {
		this.calle = calle;
	}
    
	public String getAvenida() {
		return avenida.get();
	}
	
	public void setAvenida(StringProperty avenida) {
		this.avenida = avenida;
	}
	
	public String getNumero() {
		return numero.get();
	}
	
	public void setNumero(StringProperty numero) {
		this.numero = numero;
	}
	
	public String getColonia() {
		return colonia.get();
	}
	
	public void setColonia(StringProperty colonia) {
		this.colonia = colonia;
	}
	
	public Integer getCodigo_postal() {
		return codigo_postal.get();
	}


	public void setCodigo_postal(IntegerProperty codigo_postal) {
		this.codigo_postal = codigo_postal;
	}
	
	
	public String getCiudad() {
	    return ciudad.get();
	}

	public void setCiudad(StringProperty ciudad) {
		this.ciudad = ciudad;
		
	}
	
	
	public String getTelefono() {
		return telefono.get();
	}


	public void setTelefono(SimpleStringProperty telefono) {
		this.telefono = telefono;
	}


	public String getEmail() {
		return email.get();
	}


	public void setEmail(SimpleStringProperty email) {
		this.email = email;
	}


	public String getTipo() {
		return tipo.get();
	}


	public void setTipo(StringProperty tipo) {
		this.tipo = tipo;
	}

	

	
	// #endregion
	
	public ObservableList<Usuario1> getUsuarios1(boolean estatus) throws SQLException{
		ResultSet rs = null;
		try {
			String sql="";
			if(estatus)
				sql  ="select * from vwusuario where status='s'";
			else
				sql="select * from vwusuario where status='n'";
			con.conectar();
			PreparedStatement comando = con.getConexion().prepareStatement(sql);
			rs= comando.executeQuery();
			elementos = FXCollections.observableArrayList();
			while(rs.next()){
				Usuario1 u = new Usuario1();
				u.id_usuario = new SimpleIntegerProperty(rs.getInt("id_usuario"));
				u.nombre_usuario = new SimpleStringProperty(rs.getString("nombre_usuario"));
				u.contrasenia = new SimpleStringProperty(rs.getString("contrasenia"));
				u.nombre = new SimpleStringProperty(rs.getString("nombre"));
				u.apellido_paterno = new SimpleStringProperty(rs.getString("apellido_paterno"));
				u.apellido_materno = new SimpleStringProperty(rs.getString("apellido_materno"));
				u.calle= new SimpleStringProperty(rs.getString("calle"));
				u.avenida= new SimpleStringProperty(rs.getString("avenida"));			
				u.numero= new SimpleStringProperty(rs.getString("numero"));		
				u.colonia= new SimpleStringProperty(rs.getString("colonia"));	
				u.codigo_postal= new SimpleIntegerProperty(rs.getInt("codigo_postal"));
				u.ciudad= new SimpleStringProperty(rs.getString("ciudad"));	
				u.telefono= new SimpleStringProperty(rs.getString("telefono"));	
				u.email= new SimpleStringProperty(rs.getString("email"));	
				u.tipo = new SimpleStringProperty(rs.getString("tipo"));
				elementos.add(u);
			}
		} catch (Exception ex) {
			ce.printlog(ex.getMessage(), this.getClass().toString());
		}
		finally{
			rs.close();
			con.desconectar();			
		}
		return elementos;
		
	}
	
	/*public ObservableList<Cliente> getClientesDeleted() throws SQLException{
		ResultSet rs = null;
		try {
			String sql  ="select * from vwclientesdeleted";
			con.conectar();
			PreparedStatement comando = con.getConexion().prepareStatement(sql);
			rs= comando.executeQuery();
			elementos = FXCollections.observableArrayList();
			while(rs.next()){
				Cliente cl = new Cliente();
				cl.id_cliente = new SimpleIntegerProperty(rs.getInt("id_cliente"));
				cl.rfc = new SimpleStringProperty(rs.getString("rfc"));
				cl.nombre = new SimpleStringProperty(rs.getString("nombre"));
				cl.apellido_paterno = new SimpleStringProperty(rs.getString("apellido_paterno"));
				cl.apellido_materno = new SimpleStringProperty(rs.getString("apellido_materno"));
				cl.calle= new SimpleStringProperty(rs.getString("calle"));
				cl.avenida= new SimpleStringProperty(rs.getString("avenida"));			
				cl.numero= new SimpleStringProperty(rs.getString("numero"));		
				cl.colonia= new SimpleStringProperty(rs.getString("colonia"));	
				cl.codigo_postal= new SimpleIntegerProperty(rs.getInt("codigo_postal"));
				cl.ciudad= new SimpleStringProperty(rs.getString("ciudad"));	
				cl.telefono= new SimpleStringProperty(rs.getString("telefono"));	
				cl.email= new SimpleStringProperty(rs.getString("email"));	
				elementos.add(cl);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			rs.close();
			con.desconectar();			
		}
		return elementos;
		
	}
	*/
	// #endregion
	
	// #region Delete
	public boolean eliminar(){
		try {
			
			String sql="select fn_eliminarusuario (?)";
			con.conectar();
			PreparedStatement comando = con.getConexion().prepareStatement(sql);
			comando.setInt(1, this.getId_usuario());
			comando.execute();
			return true;
		} catch (Exception ex) {
			ce.printlog(ex.getMessage(), this.getClass().toString());
			return false;
		}
		finally{
			con.desconectar();
		}
	}
	// #endregion 
	
	public boolean insertar(){
		try{
			String sql = "select fn_agregarusuario(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?)";
			con.conectar();
			PreparedStatement comando = con.getConexion().prepareStatement(sql);
			comando.setString(1, this.getNombre_Usuario());
			comando.setString(2, this.getContrasenia());
			comando.setString(3, this.getNombre());
			comando.setString(4, this.getApellido_paterno());
			comando.setString(5, this.getApellido_materno());
			comando.setString(6, this.getCalle());
			comando.setString(7, this.getAvenida());
			comando.setString(8, this.getNumero());
			comando.setString(9, this.getColonia());
			comando.setInt(10 , this.getCodigo_postal());
			comando.setString(11, this.getCiudad());
			comando.setString(12, this.getTelefono());
			comando.setString(13, this.getEmail());
			comando.setString(14, this.getTipo());
			System.out.println(comando.toString());
			comando.execute();
			return true;
		} catch (Exception ex) {
			// TODO: handle exception
			ce.printlog(ex.getMessage(), this.getClass().toString());
			return false;
		}
		finally{
			con.desconectar();
		}
	}
	// #endregion


	public boolean actualizar(){
		try{
			String sql = "select fn_modificarusuario(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?)";
			con.conectar();
			PreparedStatement comando = con.getConexion().prepareStatement(sql);
			comando.setInt(1, this.getId_usuario());
			comando.setString(2, this.getNombre_Usuario());
			comando.setString(3, this.getContrasenia());
			comando.setString(4, this.getNombre());
			comando.setString(5, this.getApellido_paterno());
			comando.setString(6, this.getApellido_paterno());
			comando.setString(7, this.getCalle());
			comando.setString(8, this.getAvenida());
			comando.setString(9, this.getNumero());
			comando.setString(10, this.getColonia());
			comando.setInt(11, this.getCodigo_postal());
			comando.setString(12, this.getCiudad());
			comando.setString(13 , this.getTelefono());
			comando.setString(14, this.getEmail());
			comando.setString(15, this.getTipo());
			comando.execute();
			return true;
		} catch (Exception ex) {
			ce.printlog(ex.getMessage(), this.getClass().toString());
			return false;
		}
		finally{
			con.desconectar();
		}
	}

	public boolean restaurar(){
		try {
			String sql = "select fnrestaurarusuario(?)";
			con.conectar();
			PreparedStatement comando = con.getConexion().prepareStatement(sql);
			comando.setInt(1, this.getId_usuario());
			comando.execute();
		//	con.respaldo();
			return true;
		} catch (Exception ex) {
			ce.printlog(ex.getMessage(), this.getClass().toString());
		}
		finally{
			con.desconectar();
		}
		return false;
	}

	     
}




	


