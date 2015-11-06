import java.io.*;
import java.net.*;
import java.util.Arrays;
import java.util.List;

public class UDP_ServerThreads extends Thread {
	
	DatagramSocket serverSocket = null;
	boolean listen = true;
	byte[] receiveData = new byte[10000];
	byte[] sendData    = new byte[10000];
	List<String> ClientNumbers;
	static UDP_Server server;
	String sentence;
	//constructor
		
	
	UDP_ServerThreads(DatagramSocket serverSocket) throws SocketException {	
		this.serverSocket = serverSocket;
	}

	@SuppressWarnings("static-access")
	public void run(){	
		while(true){
		try{			
			DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);                   
			serverSocket.receive(receivePacket);                 
			sentence = new String( receivePacket.getData(), "UTF8");
			server.message += "RECEVIED: " + sentence.trim() + "\n";
			server.textArea.setText(server.message);
			InetAddress IPAddress = receivePacket.getAddress();                   
			int port = receivePacket.getPort();
			ClientNumbers =  Arrays.asList(sentence.trim().split("\\s+"));
			double Sum = Get_Sumation(ClientNumbers);
			String capitalizedSentence =  String.valueOf(Sum);                   
			sendData = capitalizedSentence.getBytes();                   
			DatagramPacket sendPacket =new DatagramPacket(sendData, sendData.length, IPAddress, port);                   
			serverSocket.send(sendPacket);
		} catch (Exception e){
			e.printStackTrace();
			System.out.println("error Found ..." + e);
		}
		}
		
	}

	private double Get_Sumation(List<String> clientNumbers) {
		return Double.valueOf(clientNumbers.get(0)) + Double.valueOf(clientNumbers.get(1));
	}
			

}
