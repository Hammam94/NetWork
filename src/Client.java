import java.io.*;
import java.net.*;


public class Client {
	@SuppressWarnings("resource")
	public static void main(String[] args) throws UnknownHostException, IOException {   
		String name = args[0];
		Socket socket = new Socket("localhost", Server.TCP_port);
        //Read form the server
		BufferedReader Servermessage = new BufferedReader(new InputStreamReader(socket.getInputStream())); 
		
        //write to the Server
        PrintWriter printWriter =new PrintWriter(socket.getOutputStream(),true);
        printWriter.println(name);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in)); 
        while(true){
        	String ReaderInput = bufferedReader.readLine();
            printWriter.println(ReaderInput);
            System.out.println(Servermessage.readLine());
        }
	}
}
