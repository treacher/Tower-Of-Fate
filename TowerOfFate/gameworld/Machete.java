package gameworld;

import dataStorage.XMLFormatter;
import dataStorage.XMLObject;

/**
 * Class representing the machete vendor item, used to cut thin fibres and to fight.
 * @author Damian Kaye
 *
 */
public class Machete extends VendorItem {

	private static final int GOLD_PRICE = 1000;
	
	public Machete(){
		
		super("Machete" , "A sharp blade used for cutting and fighting.");
		
	}
	
	public int getGoldPrice(){
		return GOLD_PRICE;
	}

	public boolean equals(Object other){
		
		if(other == null){
			return false;
		}
		if(other.getClass() != Machete.class){
			return false;
		}
		
		Machete m = (Machete) other;
		return this.getName().equals(m.getName());
		
	}
	public String toXML(){
		XMLFormatter x = new XMLFormatter();
		
		x.appendTag("gameworld.Machete");

		x.closeTag();
		
		return x.getXML();
	}
	
	public static Machete fromXML(XMLObject o){
		
		return new Machete();
		
	}
}
