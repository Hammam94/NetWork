import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

import javax.swing.*;



public abstract class Server {
	public static final int TCP_port = 44380;
	public static GUI Server_GUI = new GUI("TCP-Server");
	public static TextArea textArea = new TextArea();
	public static JScrollPane scrollPane;
	public static String message = null;	
	private static boolean Runnable = true;
	public static boolean Connected = true;
	
	public static void main(String[] args) throws IOException  {
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
		    			    ServerSocket serverSocket = new ServerSocket(TCP_port);
	    					message = "connection port: " + TCP_port + "\n";
	    					textArea.setText(message);
	    					
	    					while(true) {
	    						//accept the connection form clients
	    						Socket socket = serverSocket.accept();
	    						
	    						//Start Thread to handle many requests
	    						new ServerThreads(socket).start();
	    					}	    		    
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
