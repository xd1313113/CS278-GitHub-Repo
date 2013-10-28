package dixiao.common;

import java.io.Serializable;

import dixiao.common.Constants.ItemStatus;

public class ItemActions implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1616813408361735666L;
	public Item item;
	
	public ItemActions(Item item){
		this.item = item;
	}
	
	public void cancel(){
		if(item.status == ItemStatus.InTransit||item.status == ItemStatus.OrderPlaced){
			System.out.println("You want to cancel");
			item.status = ItemStatus.InStock;
		}
		else
			System.out.println("Failed. Unable to cancel");
	}
	public void buy(){
		if(item.status == ItemStatus.InStock){
			System.out.println("You want to buy");
			item.status = ItemStatus.OrderPlaced;
		}
		else
			System.out.println("Failed. Unable to puchase");
	}
	public void returnitem(){
		if(item.status == ItemStatus.OrderFinished){
			System.out.println("You want to return");
			item.status = ItemStatus.InStock;
		}
		else
			System.out.println("Failed. Unable to return");
	}
}
