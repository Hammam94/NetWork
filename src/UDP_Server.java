import java.awt.Button;
import java.awt.Color;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.*; 

import javax.swing.JScrollPane;

class UDP_Server extends Thread {
	
	public static final int UDP_port = 9876;
	public static GUI Server_GUI = new GUI("UDP-Server");
	public static TextArea textArea = new TextArea();
	public static JScrollPane scrollPane;
	public static String message = null;
	
	public static void main(String args[]) throws Exception       {  
		DatagramSocket serverSocket = new DatagramSocket(UDP_Server.UDP_port);
		byte[] receiveData = new byte[10000];
		byte[] sendData    = new byte[10000];
		scrollPane = new JScrollPane(textArea);
		textArea.setEditable(false);
		textArea.setBackground(Color.WHITE);
		scrollPane.setSize(300,300);
		scrollPane.setVisible(true);
		scrollPane.setLocation(20, 20);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	       
		Button Server_Start = new Button("Server Start");
		Server_Start.setSize(85,30);
		Server_Start.setLocation(110,330);
		Server_Start.setVisible(true);
		Server_Start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Thread queryThread = new Thread() {
			      public void run() {
			    	  try {    		  
	    					message = "connection port: " + UDP_port + "\n";
	    					textArea.setText(message);
	    					new UDP_ServerThreads(serverSocket).start();
	    				} catch (IOException e1) {
	    					e1.printStackTrace();
	    				}
			      }
			    };
			    queryThread.start();
			   
			  } 
        });
		
		Server_GUI.addComponent(scrollPane);
		Server_GUI.addComponent(Server_Start);
	} 
} 