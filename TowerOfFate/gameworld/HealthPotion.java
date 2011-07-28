package gameworld;

import dataStorage.XMLFormatter;
import dataStorage.XMLObject;

/**
 * Class representing a health potion, when used it provides
 * health regeneration.
 * @author Damian Kaye
 *
 */


public class HealthPotion extends VendorItem implements UsableItem {

	private static final int HEAL_AMOUNT = 50;
	private static final int GOLD_PRICE = 100;
	
	public HealthPotion() {
		
		super("Health Potion", "Heals you for " + HEAL_AMOUNT + "health.");
		
	}
	
	public int getGoldPrice(){
		return GOLD_PRICE;
	}
	
	
	/**
	 * 
	 * Gives the player health back 
	 * 
	 * @param The index of the players bag where the health potion is
	 * @return String, representing details about what happened when used
	 */
	public String useEffect(int backpackIndex) {
		
		Backpack bp = (Backpack) this.getContainer();
		Player owner = bp.getOwner();
		owner.setCurrentHealth(owner.getCurrentHealth() + HEAL_AMOUNT);
		owner.destroy(backpackIndex, true);
		return owner.getName() + " gained " + HEAL_AMOUNT + " health from " + this.getName();
		
	}

	public boolean equals(Object other){
		
		if(other == null){
			return false;
		}
		if(other.getClass() != HealthPotion.class){
			return false;
		}
		
		HealthPotion hp = (HealthPotion) other;
		return this.getName().equals(hp.getName());
		
	}
	
	public String toXML(){
		XMLFormatter x = new XMLFormatter();
		
		x.appendTag("gameworld.HealthPotion");
		x.append("HealthPotion");
		x.closeTag();
		
		return x.getXML();
	}

	
	public static HealthPotion fromXML(XMLObject o) {
		
		return new HealthPotion();
	}


	

}
