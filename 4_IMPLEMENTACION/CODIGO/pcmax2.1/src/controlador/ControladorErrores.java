package controlador;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import controlador.ControladorErrores;

public class ControladorErrores {

	private DateFormat dateFormat;
	private Date date;
	
	public ControladorErrores() {
		dateFormat = new SimpleDateFormat(" yyyy/MM/dd HH:mm:ss");
		date = new Date();
	}
	
	public void printlog(String mensaje, String clase){
		FileWriter pw = null;
		BufferedWriter bw = null;
		try{
			File archivo = new File("src/archivos/log.txt");
			pw = new FileWriter(archivo, true);
			bw = new BufferedWriter(pw);
			bw.write(clase);
			bw.newLine();
			bw.write(mensaje + "" + "" +"" +dateFormat.format(date));
			bw.newLine();
			bw.write("*************************************************");
			bw.newLine();
			bw.close();
		}catch (IOException e) {
			e.printStackTrace();
		}
}
}