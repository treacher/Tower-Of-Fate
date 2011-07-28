package gameworld;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing the general supplies vendor NPC
 * 
 * @author Damian Kaye
 *
 */

public class GeneralSuppliesVendor extends NPC{

	List<VendorItem> supplies = new ArrayList<VendorItem>();
	
	public GeneralSuppliesVendor(GridLocation gl) {
		
		super("Mawgal The Lost One (TLO)" , gl , "Sells your general supplies" );
	
	}


	public boolean checkRequirements(Player player) {
		return true;
	}
	
	
	
	/**
	 * Trades the player the vendor item given for a price.
	 * @param The player making the trade
	 * @param The item to be traded
	 * @return A string representing the details about the trade made.
	 */
	
	public static String trade(Player player, VendorItem item) {
		
		List<Item> bp = player.getBackpack().getContainer();
	
			int price =	item.getGoldPrice();
			if(!player.checkFullBackpack()){
				if(player.canPay(price)){
					player.changeGoldAmount(-price);
					bp.add(item);
					return "You recieve loot " + item.getName();
				}
				else{
					return "You need more gold for that item";
				}
			}
			else{
				return "You are over burdened";
			}
		
	}

	
	
	public String interactEffect(Player p) {
		
		return "Trading with " + getName(); // itemSelected from trade window.
	}
}
