package vista;

import javax.swing.*;


public class SplashWindow extends javax.swing.JWindow{
	
	public SplashWindow(java.awt.Frame f, int waitTime){
		super(f);
		JLabel l = new JLabel(new ImageIcon("../vista/imagenes/pcmax.jpg"));
		getContentPane().add(l,java.awt.BorderLayout.CENTER);
		pack();
		java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		java.awt.Dimension labelSize = l.getPreferredSize();
		setLocation(screenSize.width/2 -(labelSize.width/2),screenSize.height/2 -(labelSize.width/2));
		setVisible(true);


		final int pause = waitTime;
		final Runnable closeRunner = new Runnable(){
			
			public void run(){
				setVisible(false);
				dispose();
			}
		};

		final Runnable waitRunner = new Runnable(){
			public void run(){
				try{
					Thread.sleep(pause);
					SwingUtilities.invokeAndWait(closeRunner);
				}
				catch(Exception e){
					e.printStackTrace();
				}
 
			}
        };
           setVisible(true);
             Thread splashThread = new Thread(waitRunner,"SplashThresad");
              splashThread.start();
   }

}

