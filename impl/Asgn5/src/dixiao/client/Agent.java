package dixiao.client;

import java.io.ObjectOutputStream;
import java.net.Socket;

import dixiao.common.Constants;
import dixiao.common.Requests;


public class Agent{

	    public Agent() {
	    }
	    
	    public void sumitOrder(Requests req) {
	    	try{
		        Socket clientSocket = new Socket(Constants.serverIP,Constants.portNum);
		        System.out.println("Connecting to the server");
		        ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream( )); 
		        System.out.println("Client: "+"The client is submitting the order: Name"+ req.getItem().name+
		        		","+ "Action:" +req.getAction());
		        oos.writeObject(req); 
		        oos.flush();
		        oos.close();
		        clientSocket.close();
	    	}catch(Exception e){
	    		e.printStackTrace();
	    	}
	    } 
}