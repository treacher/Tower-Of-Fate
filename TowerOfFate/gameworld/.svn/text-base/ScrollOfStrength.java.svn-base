package gameworld;

import dataStorage.XMLFormatter;
import dataStorage.XMLObject;


/**
 * Gives a player a permanent increase to their strength
 * @author Damian Kaye
 *
 */


public class ScrollOfStrength extends VendorItem implements UsableItem{

	private static final int STRENGTH_AMOUNT = 2;
	private static final int GOLD_PRICE = 400;
	
	public ScrollOfStrength() {
		
		super("Scroll of Strength" , "Increases your Strength");
		
	}

	public int getGoldPrice(){
		return GOLD_PRICE;
	}

	/**
	 * Gives the player a permanent increase to their strength amount
	 * 
	 * @param int the index of the scroll in the players backpack
	 * @return String representing the details when scroll is used
	 */
	public String useEffect(int backpackIndex) {
		
		Backpack bp =  (Backpack) this.getContainer();
		Player owner = bp.getOwner();
		owner.changeStrength(STRENGTH_AMOUNT);
		owner.destroy(backpackIndex, true);
		return owner.getName() + "gained " + STRENGTH_AMOUNT + " Strength from " + this.getName();
	}

	public boolean equals(Object other){
		
		if(other == null){
			return false;
		}
		if(other.getClass() != ScrollOfStrength.class){
			return false;
		}
		
		ScrollOfStrength ss = (ScrollOfStrength) other;
		return this.getName().equals(ss.getName());
		
	}
	public String toXML(){
		XMLFormatter x = new XMLFormatter();
		
		x.appendTag("gameworld.ScrollOfStrength");

		x.closeTag();
		
		return x.getXML();
	}
	
	public static ScrollOfStrength fromXML(XMLObject o){
		
		return new ScrollOfStrength();
		
	}
}
