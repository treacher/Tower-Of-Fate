package gameworld;

import dataStorage.XMLFormatter;
import dataStorage.XMLObject;

/**
 * One of the three key fragments needed to create the secret key to unlock a special level door
 * 
 * @author Calvin Kaye
 *
 */

public class BottomKeyFragment extends Item{

	public BottomKeyFragment() {
		
		super("Bottom Key Fragment" , "Combine this with the Top and Middle Key Fragments to produce an ancient key.", true);
		
	}
	

	public boolean equals(Object other){
		
		if(other == null){
			return false;
		}
		if(other.getClass() != BottomKeyFragment.class){
			return false;
		}
		
		BottomKeyFragment bkf = (BottomKeyFragment) other;
		return this.getName().equals(bkf.getName());
		
	}
	
	public String toXML(){
		XMLFormatter x = new XMLFormatter();
		
		x.appendTag("gameworld.BottomKeyFragment");

		x.closeTag();
		
		return x.getXML();
	}
	
	public static BottomKeyFragment fromXML(XMLObject o){
		
		return new BottomKeyFragment();
		
	}
	
}
