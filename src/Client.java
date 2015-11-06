import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

import javax.swing.JScrollPane;


public class Client {
	//public static GUI Server_GUI = new GUI("TCP-Server");
	public static TextArea msgr = new TextArea();
	public static TextField sender = new TextField();
	public static JScrollPane scrollPane;
	public static String message = null , Send_msg = null;
	private static boolean sender_accept = false;
	
	private static boolean Runnable = true;
	@SuppressWarnings("resource")
	public static void main(String[] args) throws UnknownHostException, IOException { 
		String name = args[0];
		Socket socket = new Socket("localhost", Server.TCP_port);
		//Read form the server
		BufferedReader Servermessage = new BufferedReader(new InputStreamReader(socket.getInputStream())); 
		
        //write to the Server
        PrintWriter printWriter =new PrintWriter(socket.getOutputStream(), true);
        printWriter.println(name);
		
		
		//GUI
		GUI Server_GUI = new GUI("TCP-Client " + name);
		scrollPane = new JScrollPane(msgr, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		msgr.setEditable(false);
		msgr.setBackground(Color.WHITE);
		
		scrollPane.setSize(300, 270);
		scrollPane.setVisible(true);
		scrollPane.setLocation(20, 20);
				
		sender.setLocation(20, 300);
		sender.setSize(300, 20);
		sender.setVisible(true);
		
		Button Send = new Button("Send");
		Send.setSize(85,30);
		Send.setLocation(110,330);
		Send.setVisible(true);
		Send.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(socket.isConnected());
				if(!socket.isInputShutdown()){
					String ReaderInput = sender.getText();
		            printWriter.println(ReaderInput);
		            try {
		            	//System.out.println(Servermessage.readLine());
						message += Servermessage.readLine() + "\n";
						msgr.setText(message);
	
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		            sender.setText("");	 
				} else {
					message += "the Server is disconnected.\n";
					msgr.setText(message);
				}
			}
				
        });
		
		
		
		Server_GUI.addComponent(scrollPane);
		Server_GUI.addComponent(sender);
		Server_GUI.addComponent(Send);
		message = "Client is Started.\n";
		msgr.setText(message);
		/*while(sender_accept){
        	String ReaderInput = Send_msg;
            printWriter.println(ReaderInput);
            message += Servermessage.readLine();
            msgr.setText(message);
            System.out.println(sender_accept);
            //sender_accept = false;
        }*/
	}
}
