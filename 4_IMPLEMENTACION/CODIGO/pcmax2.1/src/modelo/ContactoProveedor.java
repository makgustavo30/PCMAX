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


public class ContactoProveedor {
	private ControladorErrores ce;
	private StringProperty nombrecompleto, nombre, apellido_paterno, apellido_materno, celular, email_contacto, nombre_empresa, ciudad;
	private IntegerProperty id_contacto_proveedor;
	private Proveedor pr;
	private Conexion con;
	private ObservableList<ContactoProveedor> elementos;

	
	public ContactoProveedor(){
		ce= new ControladorErrores();
		con = Conexion.getInstance();
		nombre = apellido_paterno = apellido_materno = celular = email_contacto = new SimpleStringProperty();
		id_contacto_proveedor = new SimpleIntegerProperty();
		pr = new Proveedor();
	}

	
	// #region Getters and Setters
	public String getNombre() {
		return nombre.getValue();
	}

	public void setNombre(StringProperty nombre) {
		this.nombre = nombre;
	}

	public String getApellido_paterno() {
		return apellido_paterno.getValue();
	}

	public void setApellido_paterno(StringProperty apellido_paterno) {
		this.apellido_paterno = apellido_paterno;
	}

	public String getApellido_materno() {
		return apellido_materno.getValue();
	}

	public void setApellido_materno(StringProperty apellido_materno) {
		this.apellido_materno = apellido_materno;
	}

	public String getCelular() {
		return celular.getValue();
	}

	public void setCelular(StringProperty celular) {
		this.celular = celular;
	}

	public String getEmail_contacto() {
		return email_contacto.getValue();
	}

	public void setEmail_contacto(StringProperty email_contacto) {
		this.email_contacto = email_contacto;
	}

	public Integer getId_contacto_proveedor() {
		return id_contacto_proveedor.getValue();
	}

	public void setId_contacto_proveedor(IntegerProperty id_contacto_proveedor) {
		this.id_contacto_proveedor = id_contacto_proveedor;
	}

	public Proveedor getPr() {
		return pr;
	}

	public void setPr(Proveedor pr) {
		this.pr = pr;
	}
	
	public String getNombre_empresa() {
		return pr.getNombre_empresa();
	}

	public String getCiudad() {
		return pr.getCiudad();
	}
	
	public String getNombrecompleto() {
		return nombrecompleto.getValue();
	}


	public void setNombrecompleto(StringProperty nombrecompleto) {
		this.nombrecompleto = nombrecompleto;
	}
	
	// #endregion
	
	
	// #region Select
	public ObservableList<ContactoProveedor> getContProveedor() throws SQLException{
		ResultSet rs=null;
		try{
			String sql="Select * from fn_proveedor()";
			con.conectar();
			PreparedStatement comando = con.getConexion().prepareStatement(sql);
			rs= comando.executeQuery();
			elementos = FXCollections.observableArrayList();
			while(rs.next()){
				ContactoProveedor cp = new ContactoProveedor();
				cp.nombrecompleto = new SimpleStringProperty(rs.getString("nombrecompleto"));
				cp.nombre = new SimpleStringProperty(rs.getString("nombrecontacto"));
				cp.apellido_paterno = new SimpleStringProperty(rs.getString("apaterno"));
				cp.apellido_materno = new SimpleStringProperty(rs.getString("amaterno"));
				cp.celular = new SimpleStringProperty(rs.getString("cel"));
				cp.email_contacto = new SimpleStringProperty(rs.getString("emailcontacto"));
				cp.pr.setId_proveedor(new SimpleIntegerProperty(rs.getInt("idproveedor")));
				cp.pr.setNombre_empresa(new SimpleStringProperty(rs.getString("nombreempresa")));
				cp.pr.setRfc(new SimpleStringProperty(rs.getString("registrofc")));
				cp.pr.setCalle(new SimpleStringProperty(rs.getString("calles")));
				cp.pr.setAvenida(new SimpleStringProperty(rs.getString("avenidas")));
				cp.pr.setNumero(new SimpleStringProperty(rs.getString("num")));
				cp.pr.setColonia(new SimpleStringProperty(rs.getString("col")));
				cp.pr.setCodigo_postal(new SimpleIntegerProperty(rs.getInt("cp")));
				cp.pr.setCiudad(new SimpleStringProperty(rs.getString("ciudad")));
				cp.pr.setTelefono(new SimpleStringProperty(rs.getString("tel")));
				cp.pr.setEmail(new SimpleStringProperty(rs.getString("emailempresa")));

				elementos.add(cp);
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
	
	// #endregion
	
	// #region Select
		public ObservableList<ContactoProveedor> getContProveedor2() throws SQLException{
			ResultSet rs=null;
			try{
				String sql="Select * from fn_proveedorinactivo()";
				con.conectar();
				PreparedStatement comando = con.getConexion().prepareStatement(sql);
				rs= comando.executeQuery();
				elementos = FXCollections.observableArrayList();
				while(rs.next()){
					ContactoProveedor cp = new ContactoProveedor();
					cp.nombrecompleto = new SimpleStringProperty(rs.getString("nombrecompleto"));
					cp.nombre = new SimpleStringProperty(rs.getString("nombrecontacto"));
					cp.apellido_paterno = new SimpleStringProperty(rs.getString("apaterno"));
					cp.apellido_materno = new SimpleStringProperty(rs.getString("amaterno"));
					cp.celular = new SimpleStringProperty(rs.getString("cel"));
					cp.email_contacto = new SimpleStringProperty(rs.getString("emailcontacto"));
					cp.pr.setId_proveedor(new SimpleIntegerProperty(rs.getInt("idproveedor")));
					cp.pr.setNombre_empresa(new SimpleStringProperty(rs.getString("nombreempresa")));
					cp.pr.setRfc(new SimpleStringProperty(rs.getString("registrofc")));
					cp.pr.setCalle(new SimpleStringProperty(rs.getString("calles")));
					cp.pr.setAvenida(new SimpleStringProperty(rs.getString("avenidas")));
					cp.pr.setNumero(new SimpleStringProperty(rs.getString("num")));
					cp.pr.setColonia(new SimpleStringProperty(rs.getString("col")));
					cp.pr.setCodigo_postal(new SimpleIntegerProperty(rs.getInt("cp")));
					cp.pr.setCiudad(new SimpleStringProperty(rs.getString("ciudad")));
					cp.pr.setTelefono(new SimpleStringProperty(rs.getString("tel")));
					cp.pr.setEmail(new SimpleStringProperty(rs.getString("emailempresa")));

					elementos.add(cp);
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
		
		// #endregion


	// #region Insert
	public boolean insertar(){
		try {
			String sql = "select fn_agregarproveedor (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			con.conectar();
			PreparedStatement comando = con.getConexion().prepareStatement(sql);
			comando.setString(1, this.getPr().getNombre_empresa());
			comando.setString(2, this.getPr().getRfc());
			comando.setString(3, this.getPr().getCalle());
			comando.setString(4, this.getPr().getAvenida());
			comando.setString(5, this.getPr().getNumero());
			comando.setString(6, this.getPr().getColonia());
			comando.setInt(7, this.getPr().getCodigo_postal());
			comando.setString(8, this.getPr().getCiudad());
			comando.setString(9, this.getPr().getTelefono());
			comando.setString(10, this.getPr().getEmail());
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
	// #endregion
	
	
	public boolean eliminar(){
		try{
			String sql = "select fn_eliminarcontactoproveedor (?)";
			con.conectar();
			PreparedStatement comando = con.getConexion().prepareStatement(sql);
			comando.setInt(1, this.getId_contacto_proveedor());
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
