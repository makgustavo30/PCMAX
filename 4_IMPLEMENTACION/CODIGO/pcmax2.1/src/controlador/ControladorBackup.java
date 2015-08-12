package controlador;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ResourceBundle;

import modelo.Backup;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class ControladorBackup implements Initializable {

	@FXML Label lblMensaje;
	@FXML TextField txtRuta, txtIp, txtPuerto, txtUsuario, txtBasedeDatos;
	@FXML PasswordField pwdContrasenia;
	@FXML Button btnRespaldo, btnRestaurar, btnBuscar;
	@FXML
	static TextArea txtprogreso;



	@FXML public void respaldar() throws Exception{
		try {
			if(txtIp.getText().isEmpty() || txtPuerto.getText().isEmpty() || txtUsuario.getText().isEmpty() || 
					pwdContrasenia.getText().isEmpty() || txtBasedeDatos.getText().isEmpty()){
				lblMensaje.setText("Debes Ingresar los datos");
			}else{	
			        ProcessBuilder pbuilder;
			        //Realiza la construccion del comando
			        pbuilder = new ProcessBuilder( "C:/Program Files/PostgreSQL/9.4/bin\\pg_dump.exe", "-h", txtIp.getText(), "-p", txtPuerto.getText(), "-U", txtUsuario.getText(), "-C",  "-d", txtBasedeDatos.getText(), "-f","PcMax.sql");
			        //Se ingresa el valor del password a la variable de entorno de postgres
			        pbuilder.environment().put( "PGPASSWORD", pwdContrasenia.getText() );
			        pbuilder.redirectErrorStream( true );
			        //Ejecuta el proceso
			        
			        Process p = pbuilder.start();
			        p.waitFor();
			        
		 
			        lblMensaje.setText("Respaldo Exitoso");
			        
			        txtRuta.setText( "PcMax.sql");
				    new Backup("DES/ECB/PKCS5Padding",  "PcMax.sql").encriptar();
			        
				      
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@FXML public void restaurar(){
		try {
			if(txtRuta.getText().isEmpty() || txtIp.getText().isEmpty() || txtPuerto.getText().isEmpty() || txtUsuario.getText().isEmpty() || 
					pwdContrasenia.getText().isEmpty()){
				lblMensaje.setText("Debes Ingresar los datos");
			}else{
					new Backup("DES/ECB/PKCS5Padding",txtRuta.getText()).desencriptar();
			        ProcessBuilder pbuilder;
			        //Realiza la construccion del comando
			        pbuilder = new ProcessBuilder( "C:/Program Files/PostgreSQL/9.4/bin\\pg_restore.exe", "-h", txtIp.getText(), "-p", txtPuerto.getText(), "-U", txtUsuario.getText(),  "-f", txtRuta.getText()+ ".sql");
			        //Se ingresa el valor del password a la variable de entorno de postgres
			        pbuilder.environment().put( "PGPASSWORD", pwdContrasenia.getText() );
			        pbuilder.redirectErrorStream( true );
			        //Ejecuta el proceso y con Process espera a termine de hacer el respaldo.
			        Process p =pbuilder.start();
			        lblMensaje.setText("Restaurando base de datos espere un momento por favor...");
			        p.waitFor();
			        new Backup("DES/ECB/PKCS5Padding", txtRuta.getText()+ ".sql").encriptar();
			        lblMensaje.setText("Restauración Exitosa");

			}
		} catch (Exception e) {
		}
	}
	
	@FXML public void buscar(){
		FileChooser fileChooser = new FileChooser();
		 fileChooser.setTitle("Buscar archivo");
		 fileChooser.getExtensionFilters().addAll(
		         new ExtensionFilter("Text Files", "*.*"));
		 File selectedFile = fileChooser.showOpenDialog(null);
		if (selectedFile != null) {
			txtRuta.setText(selectedFile.getAbsolutePath());
		}
	}
	
	static void escribirProcess(Process process) throws Exception{
		BufferedInputStream bufferIs = new BufferedInputStream(process.getInputStream());
        InputStreamReader isReader = new InputStreamReader( bufferIs );
        BufferedReader reader = new BufferedReader(isReader);
        String line = ""; txtprogreso.setText(line);
        while (true){
        	line = reader.readLine();
            if (line == null) break;
            txtprogreso.setText(txtprogreso.getText()+"\n"+line);}  
        }
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		txtIp.setText("localhost");
		txtPuerto.setText("5432");
		txtUsuario.setText("postgres");
		txtBasedeDatos.setText("pcmax2.1");
	}
	
}
