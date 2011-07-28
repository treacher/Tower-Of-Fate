package gameworld;

import dataStorage.XMLFormatter;
import dataStorage.XMLObject;

/**
 * One of the three key fragments needed to create the secret key to unlock a special level door
 * 
 * @author Calvin Kaye
 *
 */

public class MiddleKeyFragment extends Item {

	public MiddleKeyFragment() {
		
		super("Middle Key Fragment" , "Combine this with the Bottom and Top Key Fragments to produce an ancient key." , true);
		
	}
	
	public boolean equals(Object other){
		
		if(other == null){
			return false;
		}
		if(other.getClass() != MiddleKeyFragment.class){
			return false;
		}
		
		MiddleKeyFragment mkf = (MiddleKeyFragment) other;
		return this.getName().equals(mkf.getName());
		
	}

	
	public String toXML(){
		XMLFormatter x = new XMLFormatter();
		
		x.appendTag("gameworld.MiddleKeyFragment");

		x.closeTag();
		
		return x.getXML();
	}
	
	public static MiddleKeyFragment fromXML(XMLObject o){
		
		return new MiddleKeyFragment();
		
	}
	
	
}
