package dixiao.common;

import java.io.Serializable;

public class BuyItemOrder implements Requests, Serializable {
	ItemActions actions;
	/**
	 * 
	 */
	private static final long serialVersionUID = 6171873345407235935L;

	public BuyItemOrder(ItemActions action) {
		this.actions = action;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		actions.buy();

	}
	
	@Override
	public Item getItem() {
		// TODO Auto-generated method stub
		return actions.item;
	}

	@Override
	public String getAction() {
		// TODO Auto-generated method stub
		return "Buy";
	}

}
