package controlador;



import java.net.URL;
import java.util.ResourceBundle;

import vista.Principal;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Accordion;
import javafx.scene.control.Dialogs;
import javafx.scene.control.Dialogs.DialogOptions;
import javafx.scene.control.Dialogs.DialogResponse;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import modelo.Conexion;
import modelo.Usuario;

public class ControladorConexion implements Initializable {
	/*
	 * Atributos de la clase
	 */
	private Conexion con;
	private Usuario modeloUsuario;
	private ControladorVentanas ventanas;
	private GridPane grid;
	private String usernameResult, passwordResult;
	private TextField username = new TextField();
	private PasswordField password = new PasswordField();
	private Callback myCallback;
	private Stage stage;
	
	
	
	/*
	 * Controles de la aplicacion
	 */
	@FXML TextField txtBasededatos, txtUsuario, txtContraseña, txtPuerto, txtDireccionIp;
	@FXML TextField txtUser;
	@FXML PasswordField txtCon;
	@FXML Label lblMensaje;
	@FXML TitledPane tpServidor, tpLogin;
	@FXML Accordion acord;
	
	/*
	 * Método asociado al boton para establecer conexión
	 */
	@FXML 
	public void click_btnConectar(){
		con=Conexion.getInstance();
		
		if(txtBasededatos.getText().isEmpty()==false &
				txtUsuario.getText().isEmpty()==false &
				txtContraseña.getText().isEmpty()==false&
				txtPuerto.getText().isEmpty()==false &
				txtDireccionIp.getText().isEmpty()==false){
			//se asigna datos a los atributos
			con.setUsuario(txtUsuario.getText().trim());
			con.setBd(txtBasededatos.getText().trim());
			con.setContrasenia(txtContraseña.getText().trim());
			con.setPuerto(txtPuerto.getText().trim());
			con.setDireccionip(txtDireccionIp.getText().trim());;
		}
		String mensaje=con.conectar();
		lblMensaje.setText(mensaje);
	}
	
	/*
	 * Método asociado a la pestaña conexion para autentificarse y cambiar la conexion
	 */
	@FXML
	public void click_titledServidor(){
		stage=Principal.getPrimaryStage();
		dialogAcceso();
		DialogResponse resp = Dialogs.showCustomDialog(stage, grid, "¿Eres Administrador?", "Confirmar Identidad", DialogOptions.OK_CANCEL, myCallback);
				
			if(resp==DialogResponse.OK){
				if(usernameResult.equals("Gustavo") & passwordResult.equals("300185")){
					tpServidor.setExpanded(true);
				}
				else{
					tpServidor.setExpanded(false);
					lblMensaje.setText("Acceso Denegado");
				}
			}
			
	}
	
	@FXML
	public void click_Acceso(){
		if(txtUser.getText().isEmpty() | txtCon.getText().isEmpty()){
			lblMensaje.setText("Faltan datos por ingresar");
		}
		else{
			//Validar si existe el usuario
			if(modeloUsuario==null){
				modeloUsuario = new Usuario();
			}
			modeloUsuario.setNombre(txtUser.getText());
			modeloUsuario.setContrasenia(txtCon.getText());
			boolean resultado= modeloUsuario.Existe();
			if(resultado){
				ventanas = ControladorVentanas.getInstancia();
				ventanas.setPrimaryStage(Principal.getPrimaryStage());
				ventanas.asignarEscena("../vista/fxml/Menu.fxml","MENU Principal",modeloUsuario.getPrivilegio());
				System.out.println("Existe el usuario es: " + modeloUsuario.getPrivilegio());
			}
			else
				System.out.println("Usuario no valido");
		}
		
	}
	

	public void dialogAcceso(){
		//Construye el cuadro de dialogo
		grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 150, 10, 10));
		username.setPromptText("Username");
		password.setPromptText("Password");
		
		grid.add(new Label("Username:"), 0, 0);
		grid.add(username, 1, 0);
		grid.add(new Label("Password:"), 0, 1);
		grid.add(password, 1, 1);
		

		
		myCallback = new Callback() {
			@Override
			public Object call(Object arg0){
				usernameResult = username.getText();
				passwordResult = password.getText();
				return null;
			}
			};
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		acord.setExpandedPane(tpLogin);
		
	}
	
}
