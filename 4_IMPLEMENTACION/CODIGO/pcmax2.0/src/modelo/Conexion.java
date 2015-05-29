package modelo;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
	
	private String bd; 
	private String usuario;
	private String contrasenia;
	private String puerto;
	private String direccionip;
	private String servidor;
	private static Conexion instancia;
	private Connection con;
	
	/*
	 * Constructor sin parametros que permitira definir un dato por default de la clase
	 */
	
	private Conexion(){
		bd = "pcmax";
		usuario = "postgres";
		contrasenia = "maki3001";
		puerto = "5432";
		direccionip = "127.0.0.1";
		servidor = "jdbc:postgresql://";
		con= null;
	}
	
	/*
	 * Constructor con parametros que permita inicializar con valores personalizados
	 */
	
	private Conexion(String bd, String usuario, String contrasenia, String puerto, String direccionip){
		this.bd = bd;
		this.usuario = usuario;
		this.contrasenia = contrasenia;
		this.puerto = puerto;
		this.direccionip = direccionip;
		this.servidor = "jdbc:postgresql://";
		con= null;
	}
	
	/*
	 * Metodo para recuperar la instancia de la clase de conexion
	 */
	
	public static Conexion getInstance(){
		if(instancia == null)
			{
				instancia = new Conexion();
			}
		return instancia;
	}
	
	/*
	 * M�todo para conectar al servidor Postgresql
	 */
	
	public String conectar(){
		try {
			//verifica que este el driver en el proyecto
			Class.forName("org.postgresql.Driver"); 
			//Establecemos conexion
			servidor = "jdbc:postgresql://";
			con = DriverManager.getConnection(servidor+direccionip+":"+puerto+"/"+bd, usuario, contrasenia);
			System.out.println("Se hizo la conexi�n");
			return "Conexion �xitosa";
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "No se establecio la conexi�n, Consulta a su administrador.";
		}
	}
	
	/*
	 * M�todo para desconectar del servidor de Postgresql
	 */
	
	public String desconectar(){
		try {
			//Cerrar la conexi�n
			con.close();
			System.out.println("Se ha desconectado del servidor");
			return "Se ha desconectado del servidor";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "La conexi�n est� siendo ocupada. No se puede desconectar.";
		}
	}
	
	/*
	 * metodo para recuperar la conexion
	 */
	
	public Connection getConexion(){
		return con;
	}
	
	/*
	 * Getter y Setter
	 */

	public String getBd() {
		return bd;
	}

	public void setBd(String bd) {
		this.bd = bd;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public String getPuerto() {
		return puerto;
	}

	public void setPuerto(String puerto) {
		this.puerto = puerto;
	}

	public String getDireccionip() {
		return direccionip;
	}

	public void setDireccionip(String direccionip) {
		this.direccionip = direccionip;
	}
}
