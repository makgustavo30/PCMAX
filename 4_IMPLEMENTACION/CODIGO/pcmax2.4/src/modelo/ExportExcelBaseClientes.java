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


public class ExportExcelBaseClientes {

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
	  File file = new File("src/archivos/Clientes.xls");
	  
	  private ControladorErrores ce;

	    /**
	 * Constructor de clase
	 */
	    public ExportExcelBaseClientes (){
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
	            workbook.createSheet( "cliente", 0 );
	            excelSheet = workbook.getSheet(0);
	            System.out.println(  "creando hoja excel.....Listo"  );            
	        } catch (IOException ex) {
	        	ce.printlog(ex.getMessage(), this.getClass().toString());
	        }

	        //Consulta SQL 
	        
	         try{
	        	 String sql = "SELECT id_cliente , rfc , nombre , apellido_paterno, apellido_materno , calle , avenida , numero , colonia , codigo_postal , ciudad , telefono , email , estatus FROM cliente";
	 			PreparedStatement pstm = conn.prepareStatement(sql);
	 			ResultSet res = pstm.executeQuery();
	             System.out.println(  "obteniendo registros.....Listo"  );
	              while(res.next())
	              {
	                  Number id_cliente    = new Number( 0 , row, res.getInt( "id_cliente" ) , cf );
	                  Label rfc    = new Label( 1 , row, res.getString( "rfc" ) , cf );
	                  Label nombre  = new Label( 2 , row, res.getString( "nombre" ) , cf );                  
	                  Label apellido_paterno= new Label( 3 , row, res.getString( "apellido_paterno" ) , cf );                  
	                  Label apellido_materno= new Label( 4 , row, res.getString( "apellido_materno" ) , cf );
	                  Label calle= new Label( 5 , row, res.getString( "calle" ) , cf );
	                  Label avenida= new Label( 6 , row, res.getString( "avenida" ) , cf );
	                  Label numero    = new Label( 7 , row, res.getString( "numero" ) , cf );
	                  Label colonia= new Label( 8 , row, res.getString( "colonia" ) , cf );
	                  Label codigo_postal= new Label( 9 , row, res.getString( "codigo_postal" ) , cf );
	                  Label ciudad= new Label( 10 , row, res.getString( "ciudad" ) , cf );
	                  Label telefono= new Label( 11 , row, res.getString( "telefono" ) , cf );
	                  Label email= new Label( 12 , row, res.getString( "email" ) , cf );  
	                  Label estatus= new Label( 13 , row, res.getString( "estatus" ) , cf );   
	                  row ++;                  
	                 try {
	                     excelSheet.addCell( id_cliente );
	                     excelSheet.addCell( rfc );
	                     excelSheet.addCell( nombre );
	                     excelSheet.addCell( apellido_paterno );
	                     excelSheet.addCell( apellido_materno );
	                     excelSheet.addCell( calle );
	                     excelSheet.addCell( avenida );
	                     excelSheet.addCell( numero );
	                     excelSheet.addCell( colonia );
	                     excelSheet.addCell( codigo_postal );
	                     excelSheet.addCell( ciudad );
	                     excelSheet.addCell( telefono );
	                     excelSheet.addCell( email );
	                     excelSheet.addCell( estatus );
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