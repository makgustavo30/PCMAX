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


public class Proveedor {
	private ControladorErrores ce;
	private StringProperty nombre_empresa, rfc, calle, avenida, numero, colonia, ciudad, telefono, email;
	private IntegerProperty id_proveedor, codigo_postal;
	private Conexion con;
	private ObservableList<Proveedor> elementos;
	
	public Proveedor(){
		ce= new ControladorErrores();
		con = Conexion.getInstance();
		nombre_empresa = rfc = calle = avenida = numero = colonia = ciudad = telefono = email = new SimpleStringProperty();
		id_proveedor = codigo_postal = new SimpleIntegerProperty();
	}

	

	public String getNombre_empresa() {
		return nombre_empresa.getValue();
	}

	public void setNombre_empresa(StringProperty nombre_empresa) {
		this.nombre_empresa = nombre_empresa;
	}

	public String getRfc() {
		return rfc.getValue();
	}

	public void setRfc(StringProperty rfc) {
		this.rfc = rfc;
	}

	public String getCalle() {
		return calle.getValue();
	}

	public void setCalle(StringProperty calle) {
		this.calle = calle;
	}

	public String getAvenida() {
		return avenida.getValue();
	}

	public void setAvenida(StringProperty avenida) {
		this.avenida = avenida;
	}

	public String getNumero() {
		return numero.getValue();
	}

	public void setNumero(StringProperty numero) {
		this.numero = numero;
	}

	public String getColonia() {
		return colonia.getValue();
	}

	public void setColonia(StringProperty colonia) {
		this.colonia = colonia;
	}

	public String getCiudad() {
		return ciudad.getValue();
	}

	public void setCiudad(StringProperty ciudad) {
		this.ciudad = ciudad;
	}

	public String getTelefono() {
		return telefono.getValue();
	}

	public void setTelefono(StringProperty telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email.getValue();
	}

	public void setEmail(StringProperty email) {
		this.email = email;
	}

	public Integer getId_proveedor() {
		return id_proveedor.getValue();
	}

	public void setId_proveedor(IntegerProperty id_proveedor) {
		this.id_proveedor = id_proveedor;
	}

	public Integer getCodigo_postal() {
		return codigo_postal.getValue();
	}

	public void setCodigo_postal(IntegerProperty codigo_postal) {
		this.codigo_postal = codigo_postal;
	}

	
	public ObservableList<Proveedor> getProveedores() throws SQLException{
		ResultSet rs=null;
		try{
			String sql="Select * from proveedor";
			con.conectar();
			PreparedStatement comando = con.getConexion().prepareStatement(sql);
			rs= comando.executeQuery();
			elementos = FXCollections.observableArrayList();
			while(rs.next()){
				Proveedor pr = new Proveedor();
				pr.id_proveedor =  new SimpleIntegerProperty(rs.getInt("id_proveedor"));
				pr.avenida = new SimpleStringProperty(rs.getString("avenida"));
				pr.calle = new SimpleStringProperty(rs.getString("calle"));
				pr.ciudad = new SimpleStringProperty(rs.getString("ciudad"));
				pr.colonia = new SimpleStringProperty(rs.getString("colonia"));
				pr.email = new SimpleStringProperty(rs.getString("email"));
				pr.nombre_empresa = new SimpleStringProperty(rs.getString("nombre_empresa"));
				pr.rfc = new SimpleStringProperty(rs.getString("rfc"));
				pr.telefono = new SimpleStringProperty(rs.getString("telefono"));
				pr.codigo_postal = new SimpleIntegerProperty(rs.getInt("codigo_postal"));
				
				elementos.add(pr);
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
	

	
	/* Tableview para campos inactivos
	public ObservableList<Proveedor> getProveedores2() throws SQLException{
		ResultSet rs=null;
		try{
			String sql="Select * from proveedor where estatus = 'n' order by nombre_empresa";
			con.conectar();
			PreparedStatement comando = con.getConexion().prepareStatement(sql);
			rs= comando.executeQuery();
			elementos = FXCollections.observableArrayList();
			while(rs.next()){
				Proveedor pr = new Proveedor();
				pr.apellido_materno = new SimpleStringProperty(rs.getString("apellido_materno"));
				pr.apellido_paterno = new SimpleStringProperty(rs.getString("apellido_paterno"));
				pr.avenida = new SimpleStringProperty(rs.getString("avenida"));
				pr.calle = new SimpleStringProperty(rs.getString("calle"));
				pr.celular = new SimpleStringProperty(rs.getString("celular"));
				pr.ciudad = new SimpleStringProperty(rs.getString("ciudad"));
				pr.colonia = new SimpleStringProperty(rs.getString("colonia"));
				pr.email = new SimpleStringProperty(rs.getString("email"));
				pr.email_contacto = new SimpleStringProperty(rs.getString("email_contacto"));
				pr.nombre = new SimpleStringProperty(rs.getString("nombre"));
				pr.nombre_empresa = new SimpleStringProperty(rs.getString("nombre_empresa"));
				pr.rfc = new SimpleStringProperty(rs.getString("rfc"));
				pr.telefono = new SimpleStringProperty(rs.getString("telefono"));
				pr.codigo_postal = new SimpleIntegerProperty(rs.getInt("codigo_postal"));
				
				elementos.add(pr);
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
			String sql = "select fn_agregarproveedor (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			con.conectar();
			PreparedStatement comando = con.getConexion().prepareStatement(sql);
			comando.setString(1, this.getNombre_empresa());
			comando.setString(2, this.getRfc());
			comando.setString(3, this.getCalle());
			comando.setString(4, this.getAvenida());
			comando.setString(5, this.getNumero());
			comando.setString(6, this.getColonia());
			comando.setInt(7, this.getCodigo_postal());
			comando.setString(8, this.getCiudad());
			comando.setString(9, this.getTelefono());
			comando.setString(10, this.getEmail());
			comando.setString(11, this.getNombre());
			comando.setString(12, this.getApellido_paterno());
			comando.setString(13, this.getApellido_materno());
			comando.setString(14, this.getCelular());
			comando.setString(15, this.getEmail_contacto());
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
		return false;
		
	}
	
	public boolean actualizar(){
		return false;
		
	}
	*/
	
	
	public String toString(){
		return nombre_empresa.getValue();
		
	}
	
}
