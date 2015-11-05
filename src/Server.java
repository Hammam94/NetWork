import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;

import javax.swing.*;



public class Server {
	public static final int TCP_port = 44380;
	public static GUI Server_GUI = new GUI("TCP-Server");
	public static TextArea textArea = new TextArea();
	public static JScrollPane scrollPane;
	public static String message = "Ahmed";
	static ServerSocket serverSocket ;
	public static void main(String[] args) throws IOException {
	    scrollPane = new JScrollPane(textArea);
		textArea.setEditable(false);
		textArea.setBackground(Color.WHITE);
		scrollPane.setSize(240,170);
		scrollPane.setVisible(true);
		scrollPane.setLocation(30, 30);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	        
		Button Server_End = new Button("Server end");
		Server_End.setSize(80,30);
		Server_End.setLocation(210,220);
		Server_End.setVisible(true);
		Server_End.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	try {
					serverSocket.close();
					message = "connection port: " + TCP_port + "\n";
					//textArea.setText(message);
					
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
        });
		
		Button Server_Start = new Button("Server Start");
		Server_Start.setSize(85,30);
		Server_Start.setLocation(10,220);
		Server_Start.setVisible(true);
		Server_Start.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
            	try {
					serverSocket = new ServerSocket(TCP_port);
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
        });
		
		Server_GUI.addComponent(scrollPane);
		Server_GUI.addComponent(Server_Start);
		Server_GUI.addComponent(Server_End);
		textArea.setText("Ahmed");
    }
}
