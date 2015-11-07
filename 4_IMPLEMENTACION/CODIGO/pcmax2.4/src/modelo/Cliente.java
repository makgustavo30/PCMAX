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

public class Cliente {
	private StringProperty nombre,rfc, apellido_paterno, apellido_materno,
	calle, avenida, numero, colonia, ciudad, telefono, email;
	private IntegerProperty id_cliente, codigo_postal ;
	private ObservableList<Cliente> elementos;
	private Conexion con;
	private ControladorErrores ce;
	
	
	public Cliente(){
		nombre= rfc= apellido_paterno= apellido_materno= calle= avenida= numero= colonia= ciudad= telefono = email =new SimpleStringProperty();
		codigo_postal = new SimpleIntegerProperty();
		con = Conexion.getInstance();
		ce= new ControladorErrores();
	}

	// #region Getters and Setters
		
	public String getNombre() {
		return nombre.get();
	}


	public void setNombre(StringProperty nombre) {
		this.nombre = nombre;
	}


	public String getRfc() {
		return rfc.get();
	}


	public void setRfc(StringProperty rfc) {
		this.rfc = rfc;
	}


	public Integer getId_cliente() {
		return id_cliente.get();
	}

	public void setId_cliente(IntegerProperty id_cliente) {
		this.id_cliente = id_cliente;
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




	

	
	// #endregion
	
	public ObservableList<Cliente> getClientes(boolean estatus) throws SQLException{
		ResultSet rs = null;
		try {
			String sql="";
			if(estatus)
				sql  ="select * from vwCliente where status='s'";
			else
				sql="select * from vwCliente where status='n'";
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
			
			String sql="select fn_eliminarcliente (?)";
			con.conectar();
			PreparedStatement comando = con.getConexion().prepareStatement(sql);
			comando.setInt(1, this.getId_cliente());
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
			String sql = "select fn_agregarcliente(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			con.conectar();
			PreparedStatement comando = con.getConexion().prepareStatement(sql);
			comando.setString(1, this.getRfc());
			comando.setString(2, this.getNombre());
			comando.setString(3, this.getApellido_paterno());
			comando.setString(4, this.getApellido_materno());
			comando.setString(5, this.getCalle());
			comando.setString(6, this.getAvenida());
			comando.setString(7, this.getNumero());
			comando.setString(8, this.getColonia());
			comando.setInt(9 , this.getCodigo_postal());
			comando.setString(10, this.getCiudad());
			comando.setString(11, this.getTelefono());
			comando.setString(12, this.getEmail());
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
			String sql = "select fn_modificarcliente(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			con.conectar();
			PreparedStatement comando = con.getConexion().prepareStatement(sql);
			comando.setInt(1, this.getId_cliente());
			comando.setString(2, this.getRfc());
			comando.setString(3, this.getNombre());
			comando.setString(4, this.getApellido_paterno());
			comando.setString(5, this.getApellido_paterno());
			comando.setString(6, this.getCalle());
			comando.setString(7, this.getAvenida());
			comando.setString(8, this.getNumero());
			comando.setString(9, this.getColonia());
			comando.setInt(10, this.getCodigo_postal());
			comando.setString(11, this.getCiudad());
			comando.setString(12 , this.getTelefono());
			comando.setString(13, this.getEmail());
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
			String sql = "select fnrestaurarcliente(?)";
			con.conectar();
			PreparedStatement comando = con.getConexion().prepareStatement(sql);
			comando.setInt(1, this.getId_cliente());
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




	


