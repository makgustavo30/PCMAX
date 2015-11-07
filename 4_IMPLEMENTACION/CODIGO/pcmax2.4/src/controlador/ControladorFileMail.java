package controlador;
import java.io.File;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;

import vista.Mail;

public class ControladorFileMail {
   public  static FileDialog fileChooser;
   /********************ENCRIPTAR*******************/
   public  static String [] nombres_filtros = { "blog de notas (.txt)","Documentos de Word(.docx)" ,  "Documentos de Execel (.xls)" , "Archivos Acrobat (.pdf)" ,"Power Point (.PPS)","Power Point(.PPT)","WinZip(.rar)","WinZip(.zip)"} ; 
   public  static String [] extencion = { "*.txt","*.docx" , "*.xls" , "*.pdf","*.PPS","*.PPT","*.rar","*.zip" } ; 
   /****************************************/
   /********************DECRIPTAR**********************/
   public  static String [] nombres_filtros2 = { "FAST-PROTE (.han)"} ; 
   public  static String [] extencion2 = { "*.han", } ; 
   /*********************************************/
   public  static File dir_archi;
   public  static Display display = new Display () ; 
   public  static Shell shell = new Shell ( display ) ; 
   public  static String result =new String("");
   public  static String dir =new String("");
   public  static String nomb =new String("");
   public static boolean cancel=true;
   public static Mail mail;
	public static void browser(){
   	   
	    fileChooser = new FileDialog(shell,SWT.OPEN);
	    fileChooser.setFilterNames ( nombres_filtros ); 
	    fileChooser.setFilterExtensions ( extencion ); 
	    fileChooser.setText("ESCOJE EL ARCHIVO A ENVIAR");
	    result = fileChooser.open(); 
	    //si se presiono el boton abrir
	    if ( result !=null ){ 
	    	String fil=fileChooser.getFileName();
	    	dir_archi=new File(result);
	    	 System.out.println("Enviando");
	        } 
	        else
	        {
	    	 mail.señal_dialo=false;
	        }  	
    	 }
    
    		
    		
    		   	      
	    
	  
    
}
