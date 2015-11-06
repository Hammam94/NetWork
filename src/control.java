import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;


public class control {

	public static void main(String[] args) {
		GUI Control_GUI = new GUI("Direction");
		
		JLabel TCP_Header = new JLabel("TCP Connection:");
		TCP_Header.setLocation(10, 10);
		TCP_Header.setVisible(true);
		TCP_Header.setSize(100, 20);
		TCP_Header.setFont(new Font("Courier New", Font.ITALIC, 12));
		TCP_Header.setForeground(Color.WHITE);
		
		JLabel UDP_Header = new JLabel("UDP Connection:");
		UDP_Header.setLocation(10, 180);
		UDP_Header.setVisible(true);
		UDP_Header.setSize(100, 20);
		UDP_Header.setFont(new Font("Courier New", Font.ITALIC, 12));
		UDP_Header.setForeground(Color.WHITE);
		
		Button TCP_Server = new Button("TCP Server");
		TCP_Server.setSize(85,30);
		TCP_Server.setLocation(30, 70 );
		TCP_Server.setVisible(true);
		TCP_Server.addActionListener(new ActionListener() {				
			@Override
			public void actionPerformed(ActionEvent e) {
				Thread queryThread = new Thread() {
				      public void run() {
				    	  try {
								String[] args = {};
								Server.main(args);
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
				      }
				};
				queryThread.start();	 
			}	
        });
		
		Button TCP_Client = new Button("TCP Client");
		TCP_Client.setSize(85,30);
		TCP_Client.setLocation(220, 70 );
		TCP_Client.setVisible(true);
		TCP_Client.addActionListener(new ActionListener() {				
			@Override
			public void actionPerformed(ActionEvent e) {
				Thread queryThread = new Thread() {
				      public void run() {
				    	  try {    		  
				    		  String[] args = {JOptionPane.showInputDialog("Clinet Name:" , "Write Client Name Here")};
							  Client.main(args);
				    	  } catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
						}
				      }
				};
				queryThread.start();
			}	
        });
		
		
		Button UDP_server = new Button("UDP Server");
		UDP_server.setSize(85,30);
		UDP_server.setLocation(30, 240 );
		UDP_server.setVisible(true);
		UDP_server.addActionListener(new ActionListener() {				
			@Override
			public void actionPerformed(ActionEvent e) {
				Thread queryThread = new Thread() {
				      public void run() {
				    	  try {
								String[] args = {};
								UDP_Server.main(args);
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
				      }
				};
				queryThread.start();	 
			}	
        });
		
		Button UDP_client = new Button("UDP Client");
		UDP_client.setSize(85,30);
		UDP_client.setLocation(220, 240 );
		UDP_client.setVisible(true);
		UDP_client.addActionListener(new ActionListener() {				
			@Override
			public void actionPerformed(ActionEvent e) {
				Thread queryThread = new Thread() {
				      public void run() {
				    	  try {    		  
				    		  String[] args = {JOptionPane.showInputDialog("Clinet Name:" , "Write Client Name Here")};
							  UDP_Client.main(args);
				    	  } catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
						}
				      }
				};
				queryThread.start();
			}	
        });
		Control_GUI.addComponent(TCP_Header);
		Control_GUI.addComponent(UDP_Header);
		Control_GUI.addComponent(TCP_Server);
		Control_GUI.addComponent(TCP_Client);
		Control_GUI.addComponent(UDP_server);
		Control_GUI.addComponent(UDP_client);
	}

}
