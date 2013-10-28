package dixiao.client;

import java.util.ArrayList;
import java.util.List;

import dixiao.common.BuyItemOrder;
import dixiao.common.CancelItemOrder;
import dixiao.common.Item;
import dixiao.common.ItemActions;
import dixiao.common.Requests;
import dixiao.common.ReturnItemOrder;

public class Client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Requests> orderlist = generateOrders();
		Agent agent = new Agent();
		for (Requests req: orderlist){
			agent.sumitOrder(req);
		}
	}
	
	
	public static List<Requests> generateOrders(){
		List<Requests> ordersList = new ArrayList<Requests>();
		Item chola = new Item("chocolate");
		ItemActions buyc = new ItemActions(chola);
		ItemActions cancelc = new ItemActions(chola);
		ItemActions returnc = new ItemActions(chola);
		
		
		BuyItemOrder buychola = new BuyItemOrder(buyc);
		CancelItemOrder cancelchola = new CancelItemOrder(cancelc);
		ReturnItemOrder returnchola = new ReturnItemOrder(returnc);
		
		ordersList.add(buychola);
		ordersList.add(cancelchola);
		ordersList.add(returnchola);
		System.out.println("Orders Generated");
		
		return ordersList;
		
	}

}
