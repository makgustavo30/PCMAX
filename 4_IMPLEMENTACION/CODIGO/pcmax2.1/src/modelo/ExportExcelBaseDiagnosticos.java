package modelo;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;

import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import controlador.ControladorErrores;

public class ExportExcelBaseDiagnosticos {

	  /* DATOS PARA LA CONEXION */
	  /** nombre de la base de datos*/
	  private String db = "pcmax2.1";
	  /** usuario */
	  private String user = "postgres";
	  /** contraseña de Posgresql*/
	  private String password = "alex88mtz";
	  /** Cadena de conexion */
	  private String url = "jdbc:postgresql://localhost/"+db;
	  /** variable para trabajar con la conexion a la base de datos */
	  private Connection conn = null;  
	  /** ruta y archivo de destino */
	  File file = new File("src/archivos/Diagnosticos.xls");
	  
	  private ControladorErrores ce;

	    /**
	 * Constructor de clase
	 */
	    public ExportExcelBaseDiagnosticos (){
	    	ce= new ControladorErrores();
	      this.url = "jdbc:postgresql://localhost/"+this.db;
	       try{
	    	 //verifica que este el driver en el proyecto
				Class.forName("org.postgresql.Driver"); 
	         //obtenemos la conexión
	         conn = DriverManager.getConnection( this.url, this.user , this.password );
	         if ( conn!=null ){
	            System.out.println("Conexión a la base de datos "+this.db+"...... Listo ");
	         }
	      }catch(SQLException ex){
	    	  ce.printlog(ex.getMessage(), this.getClass().toString());
	      }catch(ClassNotFoundException ex){
	    	  ce.printlog(ex.getMessage(), this.getClass().toString());
	      }
	    }


	    /**
	 * Metodo para obtener los registros de la base de datos y crear el archivo excel
	 */
	    public void WriteExcel()
	    {
	        int row=0;
	        //formato fuente para el contenido contenido
	        WritableFont wf = new WritableFont( WritableFont.ARIAL, 12, WritableFont.NO_BOLD );
	        WritableCellFormat cf = new WritableCellFormat(wf);    

	        //Interfaz para una hoja de cálculo
	        WritableSheet excelSheet = null;
	        WritableWorkbook workbook = null;

	        //Establece la configuración regional para generar la hoja de cálculo
	        WorkbookSettings wbSettings = new WorkbookSettings();
	        wbSettings.setLocale(new Locale("es", "ES"));
	        try {
	            workbook = Workbook.createWorkbook( file, wbSettings );
	            //hoja con nombre de la tabla
	            workbook.createSheet( "diagnosticos", 0 );
	            excelSheet = workbook.getSheet(0);
	            System.out.println(  "creando hoja excel.....Listo"  );            
	        } catch (IOException ex) {
	        	ce.printlog(ex.getMessage(), this.getClass().toString());
	        }

	        //Consulta SQL 
	        
	         try{
	        	 String sql = "SELECT id_check_equipo , tipo_equipo , sistema_equipo , sistema_operativo, danios_presentados , falla_detectada ,diagnostico FROM check_equipo";
	 			PreparedStatement pstm = conn.prepareStatement(sql);
	 			ResultSet res = pstm.executeQuery();
	             System.out.println(  "obteniendo registros.....Listo"  );
	              while(res.next())
	              {
	                  Number id_check_equipo    = new Number( 0 , row, res.getInt( "id_check_equipo" ) , cf );
	                  Label tipo_equipo   = new Label( 1 , row, res.getString( "tipo_equipo" ) , cf );
	                  Label sistema_equipo  = new Label( 2 , row, res.getString( "sistema_equipo" ) , cf );                  
	                  Label sistema_operativo = new Label( 3 , row, res.getString( "sistema_operativo" ) , cf );                  
	                  Label danios_presentados = new Label( 4 , row, res.getString( "danios_presentados" ) , cf );
	                  Label falla_detectada = new Label( 5 , row, res.getString( "falla_detectada" ) , cf );
	                  Label diagnostico = new Label( 6 , row, res.getString( "diagnostico" ) , cf );  
	                  row ++;                  
	                 try {
	                     excelSheet.addCell( id_check_equipo );
	                     excelSheet.addCell( tipo_equipo );
	                     excelSheet.addCell( sistema_equipo );
	                     excelSheet.addCell( sistema_operativo );
	                     excelSheet.addCell( danios_presentados );
	                     excelSheet.addCell( falla_detectada );
	                     excelSheet.addCell( diagnostico );
	                 } catch (WriteException ex) {
	                	 ce.printlog(ex.getMessage(), this.getClass().toString());
	                 } 
	              }
	             res.close();         
	         }catch( SQLException ex ){
	        	 ce.printlog(ex.getMessage(), this.getClass().toString());
	        }

	        //Escribe el archivo excel en disco
	        try {
	            workbook.write();
	            workbook.close();
	            System.out.println(  "Escribiendo en disco....Listo"  );         
	        } catch (IOException ex) {
	        	ce.printlog(ex.getMessage(), this.getClass().toString());
	        }
	        catch (WriteException ex) {
	        	ce.printlog(ex.getMessage(), this.getClass().toString());
	        }

	        System.out.println(  "Proceso completado...."  );
	    }

	}//--> fin clase

