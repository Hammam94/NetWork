import java.awt.EventQueue;
import java.io.*;
import java.net.*;
import java.util.*;


public class ServerThreads extends Thread {
	Socket socket;
	List<String> ClientNumbers;
	static Server server;
	boolean flag_not_one_number = false, flag_many_dot_in_one_number = false, error = false;
	ServerThreads(Socket socket){
		this.socket = socket;
	}
	
	@SuppressWarnings("static-access")
	public void run(){
		try {
			String message  = null;
			PrintWriter printWriter = new PrintWriter(socket.getOutputStream(),true);
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String ClientName = bufferedReader.readLine();
			server.message += ClientName + " is connected\n";
			server.textArea.setText(server.message);
			while(!( message = bufferedReader.readLine()).equals("") ){
				System.out.println(ClientName +" values: " + message);
				server.message += ClientName +" values: " + message +"\n";
				server.textArea.setText(server.message);
				//Splite by spaces
				ClientNumbers =  Arrays.asList(message.split("\\s+"));
				//get sum
				double Sum = Sumtionof(ClientNumbers);
				//check for errors
				if(flag_many_dot_in_one_number || flag_not_one_number || error){
					flag_many_dot_in_one_number = false;
					flag_not_one_number = false;
					error = false;
					printWriter.println(ClientName + " You have errors in your inupts so i cant sum it ");
				}else{
					printWriter.println(ClientName + " Your result: " + String.valueOf(Sum));
				}								
			}
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	private double Sumtionof(List<String> clientNumber) {
		if(clientNumber.size() >=2){
			double sum = 0;
			for (int i = 0 ; i < clientNumber.size() ; i++){
				//check if client enter numbers digits and . for double numbers
				if(clientNumber.get(i).matches("^[0-9]*\\.?[0-9]*$")){
					if(clientNumber.get(i).toCharArray()[clientNumber.get(i).length()-1] == '.'){
						flag_many_dot_in_one_number =true; break;
					}
					if(!flag_many_dot_in_one_number){
						sum += Double.valueOf(clientNumber.get(i));
				    } else {
				    	flag_many_dot_in_one_number = true;
				    	break;
				    }	
				} else {
					 error = true; break;
				}
			}
			if(flag_many_dot_in_one_number || error)return 0;
			flag_not_one_number = false;
			return sum;
		}
		flag_not_one_number = true;
		return 0;
	}
}
