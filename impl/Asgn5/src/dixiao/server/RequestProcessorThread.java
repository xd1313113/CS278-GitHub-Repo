package dixiao.server;

import java.util.List;

import dixiao.common.Requests;

public class RequestProcessorThread extends Thread{
	List<Requests> queue;
	RequestProcessorThread(List<Requests> reqsQ){ 
		queue = reqsQ;
	} 
	public void run(){
		while(true){
			synchronized (queue) {
				if(!queue.isEmpty()){
					queue.remove(queue.size()-1).execute();
					System.out.println("Processor: "+"One req is processed by the server");
				}
			}
		}
	}
}
