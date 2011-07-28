package gameworld;

import dataStorage.XMLFormatter;
import dataStorage.XMLObject;

/**
 * One of the three key fragments needed to create the secret key to unlock a special level door
 * 
 * @author Calvin Kaye
 *
 */

public class TopKeyFragment extends Item {

	public TopKeyFragment() {
		
		super("Top Key Fragment" , "Combine this with the Bottom and Middle Key Fragments to produce an ancient key.",true);
		
	}


	/*
	 * (non-Javadoc)
	 * @see gameworld.GameObject#equals(java.lang.Object)
	 */
	public boolean equals(Object other){
		
		if(other == null){
			return false;
		}
		if(other.getClass() != TopKeyFragment.class){
			return false;
		}
		
		TopKeyFragment tkf = (TopKeyFragment) other;
		return this.getName().equals(tkf.getName());
		
	}
	
	public String toXML(){
		XMLFormatter x = new XMLFormatter();
		
		x.appendTag("gameworld.TopKeyFragment");

		x.closeTag();
		
		return x.getXML();
	}
	
	public static TopKeyFragment fromXML(XMLObject o){
		
		return new TopKeyFragment();
		
	}
}
