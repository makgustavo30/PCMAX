package modelo;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Backup extends JFrame {

	Process p; ProcessBuilder pb;	JFileChooser seleccion = new JFileChooser();
	JButton btn;	JComboBox cBFormato;	static JTextArea progreso;
	JTextField rutaArchivo, usuario, clave, bDatos, host, puerto;
	
	public Backup() {
	
		colocarSkin();
		
		setLayout(new BorderLayout());
		
		JPanel backcup = new JPanel();
		backcup.setLayout(new GridLayout(8, 2));
		
		seleccion.setFileFilter(new FileNameExtensionFilter("Text files (*.txt)","txt"));
		
		backcup.add(new JLabel("Nombre Archivo ")); 
		JPanel aux = new JPanel();
		aux.add(rutaArchivo = new JTextField(15));
		rutaArchivo.setText("RespadaldoBdPcMax.txt");
		
		btn = new JButton(". . .");
		btn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(seleccion.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					rutaArchivo.setText(seleccion.getSelectedFile().getAbsolutePath());
				}
			}		
		}); aux.add(btn); 
		backcup.add(aux);
		
		backcup.add(new JLabel("Formato "));
		cBFormato = new JComboBox(new String[]{"","Custom","Tar","Plain", "Directoy"});	
		cBFormato.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(cBFormato.getSelectedIndex() == 0) {
					seleccion.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
					seleccion.setFileFilter(new FileNameExtensionFilter("Text files (*.txt)","txt"));
				} else { 
				if(cBFormato.getSelectedIndex() == 1) {
					seleccion.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
					seleccion.setFileFilter(new FileNameExtensionFilter("Backup files (*.backup)","backup"));
				} else {
				if(cBFormato.getSelectedIndex() == 2) {
					seleccion.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
					seleccion.setFileFilter(new FileNameExtensionFilter("Backup files (*.backup)","backup"));
				} else {
				if(cBFormato.getSelectedIndex() == 3) {
					seleccion.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
					seleccion.setFileFilter(new FileNameExtensionFilter("Query files (*.sql)","sql"));
				} else {
				if(cBFormato.getSelectedIndex() == 4) {
					seleccion.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
					seleccion.setFileFilter(new FileNameExtensionFilter("Directorys ", "txt", "backup", "sql"));
				}
				}
				}
				}
				}
			}}); backcup.add(cBFormato);
		
			backcup.add(new JLabel("Host ")); 
			backcup.add(host = new JTextField(15));
			host.setText("localhost");
		
			backcup.add(new JLabel("Puerto ")); 
			backcup.add(puerto = new JTextField(15));
			puerto.setText("5432");
		
			backcup.add(new JLabel("Usuario ")); 
			backcup.add(usuario = new JTextField(15));
			usuario.setText("postgres");
		
			backcup.add(new JLabel("contraseña ")); 
			backcup.add(clave = new JPasswordField(15));
			clave.setText("maki3001");
		
			backcup.add(new JLabel("Base de datos")); 
			backcup.add(bDatos = new JTextField(15));
			bDatos.setText("pcmax2.1");
		
		btn = new JButton("Respaldar");
		btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pgBackUp(host.getText(), puerto.getText(), usuario.getText(), clave.getText(),
						bDatos.getText(), cBFormato.getSelectedItem().toString(), rutaArchivo.getText());
			}		
		}); 
		backcup.add(btn);

		btn = new JButton("Restaurar");
		btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(cBFormato.getSelectedIndex() != 3 && cBFormato.getSelectedIndex() != 0) {
					pgRestore(host.getText(), puerto.getText(), bDatos.getText(), rutaArchivo.getText());
				} else {
					progreso.setText("-- no se puede restaurar el archivo --");
				}
			}		
		}); 
		backcup.add(btn);
		
		progreso = new JTextArea(20,40);
		JScrollPane scroll = new JScrollPane(progreso);
		scroll.setPreferredSize(new Dimension(400,200));
		add(scroll, java.awt.BorderLayout.SOUTH);
		add(backcup);
	}

	public void colocarSkin() {
		try {
			 UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
			} catch (ClassNotFoundException e) {
			 e.printStackTrace();
			} catch (InstantiationException e) {
			 e.printStackTrace();
			} catch (IllegalAccessException e) {
			 e.printStackTrace();
			} catch (UnsupportedLookAndFeelException e) {
			 e.printStackTrace();
			}
	}

	public void pgBackUp(String host, String puerto, String usuario, String clave, String bDatos, String format, String path) {
		try { /*
	         C:/Program Files/PostgreSQL/9.1/bin\pg_dump.exe 
	         --host localhost --port 5432 --username "postgres" --role "postgres"  
	         --format custom --blobs --oids --inserts --column-inserts --no-privileges --no-tablespaces 
	         --use-set-session-authorization --disable-dollar-quoting --verbose --quote-all-identifiers 
	         --no-unlogged-table-data --file "path" "postgres" */
			if(!format.equalsIgnoreCase("")) {
				pb = new ProcessBuilder("C:/Program Files/PostgreSQL/9.4/bin\\pg_dump.exe", "--verbose", "--format", format, "-f", path);
			} else {
				pb = new ProcessBuilder("C:/Program Files/PostgreSQL/9.4/bin\\pg_dump.exe", "--verbose", "--inserts", "--column-inserts", "-f", path);
			}
	        pb.environment().put("PGHOST", host);
	        pb.environment().put("PGPORT", puerto);
	        pb.environment().put("PGUSER", usuario);
	        pb.environment().put("PGPASSWORD", clave);
	        pb.environment().put("PGDATABASE", bDatos);
	        pb.redirectErrorStream(true);
	        p = pb.start();
	        
	        escribirProcess(p);
	        System.out.print("terminado backup "+path+"\n");
	    } catch (Exception e) {
	    	System.out.print("backup \n"+e.getMessage()+"\n");
	    }
	}
	
	public void pgRestore(String host, String puerto, String bDatos, String path) {
		try {/*
	         C:/Program Files/PostgreSQL/9.1/bin\pg_restore.exe 
	         --host localhost --port 5432 --username "postgres" --dbname "postgres" --role "postgres" 
	         --no-password  --data-only --disable-triggers --create --clean --single-transaction 
	         --no-data-for-failed-tables --use-set-session-authorization  
	         --verbose "path" */
	        pb = new ProcessBuilder("C:/Program Files/PostgreSQL/9.4/bin\\pg_restore.exe", "--exit-on-error", "--verbose", "-l", path);
	        pb.environment().put("PGHOST", host);
	        pb.environment().put("PGPORT", puerto);
	        pb.environment().put("PGDATABASE", bDatos);
	        pb.redirectErrorStream(true);
	        p = pb.start();        
	        
	        escribirProcess(p);
        	System.out.print("terminado restore \n");
	    } catch (Exception e) {
	    	System.out.print("restore \n"+e.getMessage()+"\n");
	    }
	}

	static void escribirProcess(Process process) throws Exception{
		BufferedInputStream bufferIs = new BufferedInputStream(process.getInputStream());
        InputStreamReader isReader = new InputStreamReader( bufferIs );
        BufferedReader reader = new BufferedReader(isReader);
        String line = ""; progreso.setText(line);
        while (true){
        	line = reader.readLine();
            if (line == null) break;
            progreso.setText(progreso.getText()+"\n"+line);  
        }		
    }
	
}