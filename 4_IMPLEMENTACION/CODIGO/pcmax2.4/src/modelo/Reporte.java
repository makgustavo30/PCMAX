package modelo;

import java.io.File;
import java.util.HashMap;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

public class Reporte {
private JasperDesign disenio;
private JasperPrint impreso;
private JasperReport reporte;

Conexion con;

public Reporte(){
	disenio = new JasperDesign();
	impreso = new JasperPrint();
	con = Conexion.getInstance();
}

public void cargarReporte (String ruta){
	
	try {
		File f =  new File(ruta);
		disenio = JRXmlLoader.load(f.getAbsolutePath());
		reporte = JasperCompileManager.compileReport(disenio);
		con.conectar();
		impreso = JasperFillManager.fillReport(reporte, new HashMap(), con.getConexion());
		con.desconectar();
	} catch (JRException ex) {
		// TODO Auto-generated catch block
		ex.printStackTrace();
	}
}
public void mostrarReporte(){
	JasperViewer.viewReport(impreso,false);
}
}
