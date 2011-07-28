package gameworld;

import dataStorage.XMLFormatter;
import dataStorage.XMLObject;

public class ScrollOfStamina extends VendorItem implements UsableItem {

	/**
	 * Gives the player a permanent increase to their health.
	 * @author Damian Kaye
	 */
	
	private static final int STAMINA_AMOUNT = 2;
	private static final int GOLD_PRICE = 200;
	
	public ScrollOfStamina() {
		
		super("Scroll of Stamina", "Increases your Stamina");
		
	}

	public int getGoldPrice(){
		return GOLD_PRICE;
	}
	
	/**
	 * 
	 * Gives a permanent increase to the players health 
	 * 
	 * @param int the index of where the scroll is in the players backpack
	 * @return String representing the details when scroll is used
	 */
	
	public String useEffect(int slot) {
		
		Backpack bp =  (Backpack) this.getContainer();
		Player owner = bp.getOwner();
		owner.changeMaxHealth(STAMINA_AMOUNT);
		owner.destroy(slot, true);
		
		return owner.getName() + "gained " + STAMINA_AMOUNT + " Stamina from " + this.getName();
	}

	public boolean equals(Object other){
		
		if(other == null){
			return false;
		}
		if(other.getClass() != ScrollOfStamina.class){
			return false;
		}
		
		ScrollOfStamina ss = (ScrollOfStamina) other;
		return this.getName().equals(ss.getName());
		
	}
	public String toXML(){
		XMLFormatter x = new XMLFormatter();
		
		x.appendTag("gameworld.ScrollOfStamina");

		x.closeTag();
		
		return x.getXML();
	}
	
	public static ScrollOfStamina fromXML(XMLObject o){
		
		return new ScrollOfStamina();
		
	}
}
