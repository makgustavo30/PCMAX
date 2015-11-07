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


public class CheckEquipo {
	private ControladorErrores ce;
	private StringProperty tipo_equipo,sistema_equipo, sistema_operativo, falla_detectada,
	danios_presentados,diagnostico;
	private IntegerProperty id_check_equipo ;
	private ObservableList<CheckEquipo> elementos;
	private Conexion con;
	
	
	public CheckEquipo(){
		ce= new ControladorErrores();
		tipo_equipo= sistema_equipo= sistema_operativo= falla_detectada= diagnostico=new SimpleStringProperty();
		id_check_equipo = new SimpleIntegerProperty();
		con = Conexion.getInstance();
	}

	// #region Getters and Setters
		
	public String getTipo_equipo() {
		return tipo_equipo.get();
	}


	public void setTipo_equipo(StringProperty tipo_equipo) {
		this.tipo_equipo= tipo_equipo;
	}


	public String getSistema_equipo() {
		return sistema_equipo.get();
	}


	public void setSistema_equipo(StringProperty sistema_equipo) {
		this.sistema_equipo = sistema_equipo;
	}


	public Integer getId_check_equipo() {
		return id_check_equipo.get();
	}

	public void setId_check_equipo(IntegerProperty id_check_equipo) {
		this.id_check_equipo = id_check_equipo;
	}


	public String getSistema_operativo() {
		return sistema_operativo.get();
	}


	public void setSistema_operativo(StringProperty sistema_operativo) {
		this.sistema_operativo = sistema_operativo;
	}
    
	public String getFalla_detectada() {
		return falla_detectada.get();
	}
	
	public void setFalla_detectada(StringProperty falla_detectada) {
		this.falla_detectada = falla_detectada;
	}
	
	public String getDanios_presentados() {
		return danios_presentados.get();
	}
	
	public void setDanios_presentados(StringProperty danios_presentados) {
		this.danios_presentados= danios_presentados;
	}
	
	public String getDiagnostico() {
		return diagnostico.get();
	}
	
	public void setDiagnostico(StringProperty diagnostico) {
		this.diagnostico = diagnostico;
	}
	

	
	// #endregion
	
		public ObservableList<CheckEquipo> getCheckEquipos() throws SQLException{
			ResultSet rs = null;
			try {
				String sql  ="select * from vwCheck";
				con.conectar();
				PreparedStatement comando = con.getConexion().prepareStatement(sql);
				rs= comando.executeQuery();
				elementos = FXCollections.observableArrayList();
			while(rs.next()){
				CheckEquipo ch = new CheckEquipo();
				ch.id_check_equipo = new SimpleIntegerProperty(rs.getInt("id_check_equipo"));
				ch.tipo_equipo = new SimpleStringProperty(rs.getString("tipo_equipo"));
				ch.sistema_equipo = new SimpleStringProperty(rs.getString("sistema_equipo"));
				ch.sistema_operativo = new SimpleStringProperty(rs.getString("sistema_operativo"));
				ch.falla_detectada = new SimpleStringProperty(rs.getString("falla_detectada"));
				ch.danios_presentados = new SimpleStringProperty(rs.getString("danios_presentados"));
				ch.diagnostico = new SimpleStringProperty(rs.getString("diagnostico"));
				elementos.add(ch);
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
		} catch (Exception ex) {
			ce.printlog(ex.getMessage(), this.getClass().toString());
		}
		finally{
			rs.close();
			con.desconectar();			
		}
		return elementos;
		
	}
	*/
	// #endregion
	
	public boolean insertar(){
		try{
			String sql = "select fn_agregarcheckequipo(?, ?, ?, ?, ?, ?)";
			con.conectar();
			PreparedStatement comando = con.getConexion().prepareStatement(sql);
			comando.setString(1, this.getTipo_equipo());
			comando.setString(2, this.getSistema_equipo());
			comando.setString(3, this.getSistema_operativo());
			comando.setString(4, this.getFalla_detectada());
			comando.setString(5, this.getDanios_presentados());
			comando.setString(6, this.getDiagnostico());
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
			String sql = "select fn_modificarcheckequipo(?, ?, ?, ?, ?, ?, ?)";
			con.conectar();
			PreparedStatement comando = con.getConexion().prepareStatement(sql);
			comando.setInt(1, this.getId_check_equipo());
			comando.setString(2, this.getTipo_equipo());
			comando.setString(3, this.getSistema_equipo());
			comando.setString(4, this.getSistema_operativo());
			comando.setString(5, this.getFalla_detectada());
			comando.setString(6, this.getDanios_presentados());
			comando.setString(7, this.getDiagnostico());
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
			String sql = "select fnrestaurarcheckequipo(?)";
			con.conectar();
			PreparedStatement comando = con.getConexion().prepareStatement(sql);
			comando.setInt(1, this.getId_check_equipo());
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



