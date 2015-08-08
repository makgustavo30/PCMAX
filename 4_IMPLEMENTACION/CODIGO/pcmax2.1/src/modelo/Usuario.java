package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import controlador.ControladorErrores;


public class Usuario {
	/*
	 * Atributos
	 */
	
	private String nombre, contrasenia, privilegio;
	private Boolean estatus;
	private Conexion con;
	private ControladorErrores ce;
	//para ejecutar una instruccion SQL
	private PreparedStatement comando;
	
	/*
	 * Constructor
	 */
	
	public Usuario() {
		ce= new ControladorErrores();
		this.nombre="";
		this.contrasenia="";
		this.privilegio="";
		this.estatus=false;
	}
	
	/*
	 * Getter y Setter
	 */

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public String getPrivilegio() {
		return privilegio;
	}

	public void setPrivilegio(String privilegio) {
		this.privilegio = privilegio;
	}

	public Boolean getEstatus() {
		return estatus;
	}

	public void setEstatus(Boolean estatus) {
		this.estatus = estatus;
	}
	
	/*
	 * metodo para verificar que existe la base de datos
	 */
	
	public boolean Existe(){
		con = Conexion.getInstance();
		boolean bandera = false;
		try{
			String sql = "select * from usuario where nombre_usuario = ? and"
					+ " contrasenia = ?";
			//Abrir conexion
			con.conectar();
			//Se recupera la conexion
			//miconexion = con
			//Asociamos el parametro con la conexion
			comando = con.getConexion().prepareStatement(sql);
			//parametros
			comando.setString(1, this.nombre);
			comando.setString(2, this.contrasenia);
			//Se ejecuta
			//resultset es una tabla temporal
			//System.out.println(comando); imprime la consulta
			ResultSet rs = comando.executeQuery();
			//Validar los datos
			//Si existen datos en el resulset
			while(rs.next()){
				this.privilegio=(rs.getString(15));
				bandera=true;
			}	
			rs.close();
		}catch (Exception ex){
			ce.printlog(ex.getMessage(), this.getClass().toString());
			System.out.println("************ERROR***********");
			
		}
		finally{
			//El bloque finally se ejecuta exista o no un error.
			con.desconectar();
		}
		return bandera;
	}

	
}
