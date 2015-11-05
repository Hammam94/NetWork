import java.io.IOException;
import java.net.*; 

class UDP_Server extends Thread {
	
	public static final int Port = 9876;
	
	public static void main(String args[]) throws Exception       {  
		DatagramSocket serverSocket = new DatagramSocket(UDP_Server.Port);
		byte[] receiveData = new byte[10000];
		byte[] sendData    = new byte[10000];
		//while(true){
			new UDP_ServerThreads(serverSocket).start();
		//}
	} 
} 