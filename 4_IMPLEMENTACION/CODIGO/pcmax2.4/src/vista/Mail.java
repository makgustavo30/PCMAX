package vista;

import modelo.EnviarMail;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.BevelBorder;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import org.jvnet.substance.SubstanceLookAndFeel;

import vista.Mail;
import vista.Mail.AlinearTexto;
import controlador.ControladorFileMail;

public class Mail extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JPanel panel_opciones = null;
	public static JRadioButton radio_hotmail = null;
	public static JRadioButton radio_gmail = null;
	public ButtonGroup grupo=new ButtonGroup();  //  @jve:decl-index=0:
	private JPanel panel_secion = null;
	private JLabel eti_corre = null;
	private static JTextField txt_correo = null;
	private JLabel eti_contra = null;
	private JToolBar herra_botos = null;
	private JButton bto_alaizquier = null;
	private JButton bto_centrar = null;
	private JButton bto_aladere = null;
	private JButton bto_abjunto = null;
	private JPanel panel_enviar = null;
	private JButton bto_enviar = null;
	public static JPanel panel_text = null;
	private JTextPane text_mensaje = null;
	private JLabel eti_ima = null;
	public static JLabel eti_aviso = null;
	private JLabel eti_disti = null;
	private static JTextField txt_destino = null;
	private static JPasswordField txt_contra = null;
	public static boolean señal_dialo=false;
	/**
	 * This method initializes panel_opciones	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPanel_opciones() {
		if (panel_opciones == null) {
			panel_opciones = new JPanel();
			panel_opciones.setLayout(null);
			panel_opciones.setBounds(new Rectangle(0, 48, 377, 48));
			panel_opciones.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
			panel_opciones.add(getRadio_hotmail(), null);
			panel_opciones.add(getRadio_gmail(), null);
		}
		return panel_opciones;
	}

	/**
	 * This method initializes radio_hotmail	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getRadio_hotmail() {
		if (radio_hotmail == null) {
			radio_hotmail = new JRadioButton();
			radio_hotmail.setBounds(new Rectangle(76, 17, 78, 25));
			radio_hotmail.setText("Hotmail");
			grupo.add(radio_hotmail);
			radio_hotmail.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(e.getSource()==radio_hotmail)
					{
						activa(true);
					}
					
				}
			});
		}
		return radio_hotmail;
	}

	/**
	 * This method initializes radio_gmail	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getRadio_gmail() {
		if (radio_gmail == null) {
			radio_gmail = new JRadioButton();
			radio_gmail.setBounds(new Rectangle(238, 17, 78, 25));
			radio_gmail.setText("Gmail");
			grupo.add(radio_gmail);
			radio_gmail.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(e.getSource()==radio_gmail)
					{
						activa(true);
					}
				}
			});
		}
		return radio_gmail;
	}

	/**
	 * This method initializes panel_secion	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPanel_secion() {
		if (panel_secion == null) {
			eti_disti = new JLabel();
			eti_disti.setBounds(new Rectangle(1, 67, 82, 25));
			eti_disti.setFont(new Font("Dialog", Font.PLAIN, 14));
			eti_disti.setText("Destinatario:");
			eti_aviso = new JLabel();
			eti_aviso.setBounds(new Rectangle(51, 95, 284, 25));
			eti_aviso.setFont(new Font("Dialog", Font.BOLD, 14));
			eti_aviso.setHorizontalAlignment(SwingConstants.CENTER);
			eti_contra = new JLabel();
			eti_contra.setBounds(new Rectangle(1, 37, 82, 26));
			eti_contra.setFont(new Font("Dialog", Font.PLAIN, 14));
			eti_contra.setText("Contraseña:");
			eti_corre = new JLabel();
			eti_corre.setBounds(new Rectangle(1, 3, 55, 26));
			eti_corre.setFont(new Font("Dialog", Font.PLAIN, 14));
			eti_corre.setText("Correo:");
			panel_secion = new JPanel();
			panel_secion.setLayout(null);
			panel_secion.setBounds(new Rectangle(0, 101, 378, 122));
			panel_secion.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
			
			panel_secion.add(eti_corre, null);
			panel_secion.add(getTxt_correo(), null);
			panel_secion.add(eti_contra, null);
			panel_secion.add(eti_aviso, null);
			panel_secion.add(eti_disti, null);
			panel_secion.add(getTxt_destino(), null);
			panel_secion.add(getTxt_contra(), null);
		}
		return panel_secion;
	}

	/**
	 * This method initializes txt_correo	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxt_correo() {
		if (txt_correo == null) {
			txt_correo = new JTextField();
			txt_correo.setBounds(new Rectangle(55, 3, 320, 26));
			txt_correo.setForeground(new Color(0, 153, 255));
			txt_correo.setFont(new Font("Dialog", Font.PLAIN, 14));
		}
		return txt_correo;
	}

	/**
	 * This method initializes herra_botos	
	 * 	
	 * @return javax.swing.JToolBar	
	 */
	private JToolBar getHerra_botos() {
		if (herra_botos == null) {
			herra_botos = new JToolBar();
			herra_botos.setBounds(new Rectangle(0, 224, 376, 46));
			herra_botos.setFloatable(false);
			herra_botos.addSeparator();
			herra_botos.addSeparator();
			herra_botos.add(getBto_alaizquier());
			herra_botos.add(getBto_centrar());
			herra_botos.add(getBto_aladere());
			herra_botos.add(getBto_abjunto());
			herra_botos.add(getPanel_enviar());
		}
		return herra_botos;
	}

	/**
	 * This method initializes bto_alaizquier	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBto_alaizquier() {
		if (bto_alaizquier == null) {
			bto_alaizquier = new JButton();
			bto_alaizquier.setIcon(new ImageIcon(getClass().getResource("../vista/imagenes/text_align_left.png")));
			bto_alaizquier.setToolTipText("a linear a la Izquierda");
			/*************ALINEA EL TEXTO A AL IZQUIERDA************************/
			AlinearTexto b1=new AlinearTexto(1);
			bto_alaizquier.addActionListener(b1);
			/*******************************************/
			bto_alaizquier.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					
				}
			});
		}
		return bto_alaizquier;
	}

	/**
	 * This method initializes bto_centrar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBto_centrar() {
		if (bto_centrar == null) {
			bto_centrar = new JButton();
			bto_centrar.setIcon(new ImageIcon(getClass().getResource("../vista/imagenes/text_align_center.png")));
			bto_centrar.setToolTipText("Centrar");
			/*************CENTRA EL TEXTO************************/
			AlinearTexto b2=new AlinearTexto(2);
			bto_centrar.addActionListener(b2);
			/**********************************************/
			bto_centrar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					
				}
			});
		}
		return bto_centrar;
	}

	/**
	 * This method initializes bto_aladere	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBto_aladere() {
		if (bto_aladere == null) {
			bto_aladere = new JButton();
			bto_aladere.setIcon(new ImageIcon(getClass().getResource("../vista/imagenes/text_align_right.png")));
			bto_aladere.setToolTipText("a linear a la Derecha");
			/****************ALINEA EL TEXTO A LA DERECHA********************************/
			AlinearTexto b3=new AlinearTexto(3);
			bto_aladere.addActionListener(b3);
			/**********************************************/
			bto_aladere.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					
				}
			});
		}
		return bto_aladere;
	}

	/**
	 * This method initializes bto_abjunto	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBto_abjunto() {
		if (bto_abjunto == null) {
			bto_abjunto = new JButton();
			bto_abjunto.setToolTipText("Enviar Abjunto");
			bto_abjunto.setIcon(new ImageIcon(getClass().getResource("../vista/imagenes/epiphany-download.png")));
			bto_abjunto.addActionListener(new java.awt.event.ActionListener() {
				@SuppressWarnings("static-access")
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(e.getSource()==bto_abjunto)
					{
						ControladorFileMail dialo=new ControladorFileMail();
						dialo.browser();
						señal_dialo=true;
						
					
						
					}
				}
			});
		}
		return bto_abjunto;
	}

	/**
	 * This method initializes panel_enviar	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPanel_enviar() {
		if (panel_enviar == null) {
			panel_enviar = new JPanel();
			panel_enviar.setLayout(new BorderLayout());
			panel_enviar.add(getBto_enviar(), BorderLayout.CENTER);
		}
		return panel_enviar;
	}

	/**
	 * This method initializes bto_enviar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBto_enviar() {
		if (bto_enviar == null) {
			bto_enviar = new JButton();
			bto_enviar.setText("Enviar");
			bto_enviar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(e.getSource()==bto_enviar)
					{
							EnviarMail envia=new EnviarMail(text_mensaje.getText());
							envia.start();	
						
					}
				}
			});
		}
		return bto_enviar;
	}

	/**
	 * This method initializes panel_text	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPanel_text() {
		if (panel_text == null) {
			panel_text = new JPanel();
			panel_text.setLayout(new BorderLayout());
			panel_text.setBounds(new Rectangle(0, 271, 377, 284));
			panel_text.add(getText_mensaje(), BorderLayout.CENTER);
		}
		return panel_text;
	}

	/**
	 * This method initializes text_mensaje	
	 * 	
	 * @return javax.swing.JTextPane	
	 */
	private JTextPane getText_mensaje() {
		if (text_mensaje == null) {
			text_mensaje = new JTextPane();
			text_mensaje.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
			text_mensaje.setForeground(new Color(0, 204, 255));
			text_mensaje.setFont(new Font("Dialog", Font.PLAIN, 14));
			text_mensaje.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
				public void propertyChange(java.beans.PropertyChangeEvent e) {
					if ((e.getPropertyName().equals("lineWrap"))) {
						
						text_mensaje.setCaretPosition(text_mensaje.getText().length()); 
					}
				}
			});
		}
		return text_mensaje;
	}

	/**
	 * This method initializes txt_destino	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxt_destino() {
		if (txt_destino == null) {
			txt_destino = new JTextField();
			txt_destino.setBounds(new Rectangle(83, 67, 291, 26));
			txt_destino.setForeground(new Color(0, 153, 255));
			txt_destino.setFont(new Font("Dialog", Font.PLAIN, 14));
		}
		return txt_destino;
	}

	/**
	 * This method initializes txt_contra	
	 * 	
	 * @return javax.swing.JPasswordField	
	 */
	private JPasswordField getTxt_contra() {
		if (txt_contra == null) {
			txt_contra = new JPasswordField();
			txt_contra.setBounds(new Rectangle(82, 37, 291, 26));
		}
		return txt_contra;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try{
	                JFrame.setDefaultLookAndFeelDecorated(true);
	                SubstanceLookAndFeel.setSkin("org.jvnet.substance.skin.CremeSkin"); 
	       
	            }              
	            catch(Exception e){
	            } 
				Mail thisClass = new Mail();
				thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				thisClass.setVisible(true);
			}
		});
	}

	/**
	 * This is the default constructor
	 */
	public Mail() {
		super();
		initialize();
		activa(false);
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(395, 592);
		this.setLocationRelativeTo(null);
		this.setContentPane(getJContentPane());
		this.setTitle("PcMax Mail");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			eti_ima = new JLabel();
			eti_ima.setBounds(new Rectangle(2, 2, 375, 45));
			eti_ima.setIcon(new ImageIcon(getClass().getResource("../vista/imagenes/logo_blue_orange3.png")));
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getPanel_opciones(), null);
			jContentPane.add(getPanel_secion(), null);
			jContentPane.add(getHerra_botos(), null);
			jContentPane.add(getPanel_text(), null);
			jContentPane.add(eti_ima, null);
		}
		return jContentPane;
	}
	/********************************METODOS Y FUNCIONES********************************************/
	public class AlinearTexto implements ActionListener 
    {
    	private int origen=0;
    	
    	public AlinearTexto(int op) 
    	{	
    		origen=op;
    		
    	}
    	public void actionPerformed(ActionEvent ev) 
    	{
    		
    	  if(origen==1)
    	  {
              alineacion(StyleConstants.ALIGN_LEFT);
    	  }
    	  else if(origen==2)
    	  {
    		  		 
              alineacion(StyleConstants.ALIGN_CENTER);
    	  }
    	  
    	  else
    	  {
 
              alineacion(StyleConstants.ALIGN_RIGHT);
    	  }
    	  
    	}
    	private void alineacion(int stc)
  	    {
  		  StyledDocument sd=text_mensaje.getStyledDocument();
  		  SimpleAttributeSet sas = new SimpleAttributeSet();  
          StyleConstants.setAlignment(sas, stc);  
          sd.setParagraphAttributes(0,text_mensaje.getText().length(), sas, false);  
          text_mensaje.updateUI();
  	    }

    }
	/*****************************************************/
	public void activa(boolean señal)
	{
		txt_correo.setEnabled(señal);	
		txt_contra.setEnabled(señal);
		txt_destino.setEnabled(señal);
		if(señal==true)
		{panel_secion.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		 panel_opciones.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		}		
	}
	/************************************************/
	public static String damecorreo1()
	{
		return txt_correo.getText();
	}
	public static String damecontra()
	{
		return txt_contra.getText();
	}
	public static String damecorreo2()
	{
		return txt_destino.getText();
	}

}  //  @jve:decl-index=0:visual-constraint="271,-13"
