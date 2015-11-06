import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Label;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*; 
import java.net.*; 
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
class UDP_Client {    
	public static TextArea msgr = new TextArea();
	//public static TextField sender = new TextField();
	public static JLabel num1 = new JLabel("number 1"),
			            num2 = new JLabel("number 2") ;
	public static JScrollPane scrollPane;
	public static String message = null , Send_msg = null;
	private static Button Send;
    private static byte[] sendData = new byte[1024], receiveData = new byte[1024];
    
    //main
	public static void main(String args[]) throws Exception {     
		String name = args[0];     
		@SuppressWarnings("resource")
		DatagramSocket clientSocket = new DatagramSocket();       
		InetAddress IPAddress = InetAddress.getByName("localhost");   
		
	    sendData = name.getBytes();
	    DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, UDP_Server.UDP_port);
	    try {
			clientSocket.send(sendPacket);
		} catch (IOException e) {
			e.printStackTrace();
		}  
	    
		GUI Server_GUI = new GUI("UDP-Client " + name);
		scrollPane = new JScrollPane(msgr, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		msgr.setEditable(false);
		msgr.setBackground(Color.WHITE);
		
		scrollPane.setSize(300, 270);
		scrollPane.setVisible(true);
		scrollPane.setLocation(20, 20);
		
		num1.setLocation(20, 300);
		num1.setSize(100, 20);
		num1.setFont(new Font("Courier New", Font.ITALIC, 12));
		num1.setForeground(Color.WHITE);
		num1.setVisible(true);
		
		num2.setLocation(200, 300);
		num2.setSize(100, 20);
		num2.setFont(new Font("Courier New", Font.ITALIC, 12));
		num2.setForeground(Color.WHITE);
		num2.setVisible(true);
		
		Send = new Button("Send");
		Send.setSize(85,30);
		Send.setLocation(110,330);
		Send.setVisible(true);
		Send.addActionListener(new ActionListener() {				
			@Override
			public void actionPerformed(ActionEvent e) {	
				double First_Number = ThreadLocalRandom.current().nextDouble(-5000, 5000);
				double Second_Number = ThreadLocalRandom.current().nextDouble(-5000, 5000);
				num1.setText(String.valueOf(First_Number));
				num2.setText(String.valueOf(Second_Number));
				
				sendData = (num1.getText() + " " + num2.getText()).getBytes();
				DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, UDP_Server.UDP_port);       
				try {
					clientSocket.send(sendPacket);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}       
				DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);       
				try {
					clientSocket.receive(receivePacket);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}       
				String modifiedSentence = new String(receivePacket.getData()); 
				message += "From Server: " +modifiedSentence.trim() + "\n";
				System.out.println("FROM SERVER:" + modifiedSentence.trim());
				msgr.setText(message);
				
			}	
        });
		
		
		
		Server_GUI.addComponent(scrollPane);
		Server_GUI.addComponent(num1);
		Server_GUI.addComponent(num2);
		Server_GUI.addComponent(Send);
		message = "Client is Started.\n";
		msgr.setText(message);
	} 
}