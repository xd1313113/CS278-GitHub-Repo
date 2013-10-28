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
		final String TAG = "Server: ";
		List<Requests> reqsQ = Collections.synchronizedList(new LinkedList<Requests>());
		RequestProcessorThread processorThread = new RequestProcessorThread(reqsQ);
		processorThread.start();
		System.out.println(TAG+"The server is ready to reciceve order");
		// TODO Auto-generated method stub
		try{
				ServerSocket connectionSocket = new ServerSocket(Constants.portNum); 
				System.out.println(TAG+"Connected to the server");
				while (true) {
					Socket dataSocket = connectionSocket.accept( ); 
					RequestCollectorThread collectorThread = new RequestCollectorThread(dataSocket, reqsQ); 
					collectorThread.start( ); 
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
		}
				
	}

}
