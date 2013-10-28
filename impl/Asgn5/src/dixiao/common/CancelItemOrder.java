package dixiao.common;

import java.io.Serializable;

public class CancelItemOrder  implements Requests, Serializable {
	ItemActions actions;
	/**
	 * 
	 */
	private static final long serialVersionUID = -2145691925535509915L;

	
	public CancelItemOrder(ItemActions actions) {
		this.actions = actions;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		actions.cancel();
	}
	
	@Override
	public Item getItem() {
		// TODO Auto-generated method stub
		return actions.item;
	}
	
	@Override
	public String getAction() {
		// TODO Auto-generated method stub
		return "Cancel";
	}
	

}
