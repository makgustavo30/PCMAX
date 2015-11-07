package modelo;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.InetAddress;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.imageio.ImageIO;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import controlador.ControladorFileMail;
import vista.Mail;
import java.net.UnknownHostException;
public class EnviarMail extends Thread{
	Mail mail;
	String Texto=new String("");
	public EnviarMail(String texto)
	{
		
		try {
			Texto=texto;				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	@SuppressWarnings("static-access")
	public void run(){	
		try
        {
          // se obtiene el objeto Session. La configuración es para
          // una cuenta de hotmail.
            Properties props = new Properties();
            if(mail.radio_gmail.isSelected()==true)
            {
            	 props.setProperty("mail.smtp.host", "smtp.gmail.com");
            	 props.setProperty("mail.smtp.starttls.enable", "true");
            }
            else
           if(mail.radio_hotmail.isSelected()==true)
            {
        	   props.setProperty("mail.smtp.host", "smtp.live.com");
               props.setProperty("mail.smtp.starttls.enable", "true");
            }     
            props.setProperty("mail.smtp.port", "587");
            props.setProperty("mail.smtp.user", mail.damecorreo1());
            props.setProperty("mail.smtp.auth", "true");    
            Session session = Session.getInstance(props, null);
            /*******************EXTRAIGO DESDE EL ARCHIVO*************************/
            /*********************/  
            // session.setDebug(true);
            // Se compone la parte del texto
            BodyPart texto = new MimeBodyPart();
            String usu = System.getProperty("user.name");
            texto.setText(Texto);
            // Se compone el adjunto con la imagen  
            BodyPart adjunto = new MimeBodyPart();
            if(mail.señal_dialo==true)
            {
            adjunto.setDataHandler( new DataHandler(new FileDataSource(ControladorFileMail.dir_archi)));
            adjunto.setFileName(ControladorFileMail.dir_archi.getName()); 
            }
            // Una MultiParte para agrupar texto e imagen.
            MimeMultipart multiParte = new MimeMultipart();
            multiParte.addBodyPart(texto);
            if(mail.señal_dialo==true)
            {
            multiParte.addBodyPart(adjunto);  
            }
            // contenido.
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(mail.damecorreo1()));
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(mail.damecorreo2()));
            message.setSubject("PcMax Mensaje");
            message.setContent(multiParte);
            System.out.println("Mensaje en curso:");
            // Se envia el correo.          
            Transport t = session.getTransport("smtp"); 
            t.connect(mail.damecorreo1(), mail.damecontra());   
            t.sendMessage(message, message.getAllRecipients());
            t.close();
            System.out.println("Mensaje enviado");
            mail.eti_aviso.setText("Mensaje Enviado!!!");
            mail.eti_aviso.setForeground(Color.BLUE);
         
        }
        catch (Exception e)
        {
        	/**********************SI LA CUENTA HOTMAIL NO ES VERIDICA OSEA SI NO EXISTE****************************/
           // e.printStackTrace();
        	 mail.eti_aviso.setText("Error al enviar");
        	 mail.eti_aviso.setForeground(Color.red);
        	
        }	
			
		
	}
	/****************************************************************************/
	
	
}
