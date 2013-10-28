package dixiao.server;

import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.List;

import dixiao.common.Requests;

public class RequestCollectorThread extends Thread{
	Socket dataSocket; 
	List<Requests> queue;
	RequestCollectorThread(Socket cs, List<Requests> reqsQ){ 
		dataSocket = cs;
		queue = reqsQ;
	} 
	public void run(){
		ObjectInputStream ois;
		try {
			ois = new ObjectInputStream(dataSocket.getInputStream( ));
			Requests reqData = (Requests) ois.readObject( ); 
			synchronized (queue) {
				queue.add(reqData);
				System.out.println("One req is added to queue");
			}
			dataSocket.close(); 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

	}
}
