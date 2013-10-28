package dixiao.common;

import java.io.Serializable;

import dixiao.common.Constants.ItemStatus;

public class Item implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5405982534063992639L;
	public String name="";
	ItemStatus status;
	
	public Item(String name){
		this.name = name;
		status = ItemStatus.InStock;
	}
	
}
