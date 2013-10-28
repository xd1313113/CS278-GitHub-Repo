package dixiao.common;

import java.io.Serializable;

public class ReturnItemOrder implements Requests, Serializable {
	ItemActions actions;
	/**
	 * 
	 */
	private static final long serialVersionUID = -2901822962225940127L;

	public ReturnItemOrder(ItemActions actions) {
		this.actions = actions;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		actions.returnitem();
	}

	@Override
	public Item getItem() {
		// TODO Auto-generated method stub
		return actions.item;
	}
	
	@Override
	public String getAction() {
		// TODO Auto-generated method stub
		return "Return";
	}

}
