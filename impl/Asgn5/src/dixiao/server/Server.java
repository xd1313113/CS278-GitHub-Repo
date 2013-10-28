package dixiao.server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import dixiao.common.Constants;
import dixiao.common.Requests;

public class Server {
	public static void main(String[] args) {
		List<Requests> reqsQ = Collections.synchronizedList(new LinkedList<Requests>());
		RequestProcessorThread processorThread = new RequestProcessorThread(reqsQ);
		processorThread.start();
		// TODO Auto-generated method stub
		try{
				ServerSocket connectionSocket = new ServerSocket(Constants.portNum); 
				while (true) {
					Socket dataSocket = connectionSocket.accept( ); 
					System.out.println("Connected to the server");
					RequestCollectorThread collectorThread = new RequestCollectorThread(dataSocket, reqsQ); 
					collectorThread.start( ); 
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
		}
				
	}

}
