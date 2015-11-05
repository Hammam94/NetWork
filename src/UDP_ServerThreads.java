import java.io.*;
import java.net.*;

public class UDP_ServerThreads extends Thread {
	
	DatagramSocket serverSocket = null;
	boolean listen = true;
	byte[] receiveData = new byte[10000];
	byte[] sendData    = new byte[10000];
	//constructor
		
	
	UDP_ServerThreads(DatagramSocket serverSocket) throws SocketException {	
		this.serverSocket = serverSocket;
	}

	public void run(){	
		while(true){
		try{
			System.out.println(serverSocket.isConnected());
			
			DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);                   
			serverSocket.receive(receivePacket);                 
			String sentence = new String( receivePacket.getData(), "UTF8");
			
			System.out.println("RECEIVED: " + sentence.trim());                   
			InetAddress IPAddress = receivePacket.getAddress();                   
			int port = receivePacket.getPort();            
			//serverSocket.connect(IPAddress, port);
			String capitalizedSentence = sentence.toUpperCase();                   
			sendData = capitalizedSentence.getBytes();                   
			DatagramPacket sendPacket =new DatagramPacket(sendData, sendData.length, IPAddress, port);                   
			serverSocket.send(sendPacket);
		//System.out.println("Server started.");
		} catch (Exception e){
			e.printStackTrace();
			System.out.println("error Found ..." + e);
		}
		}
		
	}
			

}
