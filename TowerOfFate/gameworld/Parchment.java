package gameworld;

import dataStorage.XMLFormatter;
import dataStorage.XMLObject;

/**
 * This class represents the parchment objects found in the game that
 * hold key information to completing the game quicker.
 * 
 * @author Damian Kaye
 *
 */

public class Parchment extends Item {

	public Parchment(String name, String description) {
		
		super(name, description,true);
		
	}

	

	public boolean equals(Object other){
		
		if(other == null){
			return false;
		}
		if(other.getClass() != Parchment.class){
			return false;
		}
		
		Parchment p = (Parchment) other;
		return this.getName().equals(p.getName());
		
	}
	public String toXML(){
		XMLFormatter x = new XMLFormatter();
		
		x.appendTag("gameworld.Parchment");
		
		x.appendTag("name");
		x.append(super.getName());
		x.closeTag();
		
		x.appendTag("description");
		x.append(super.getDescription());
		x.closeTag();
		
		x.closeTag();
		
		return x.getXML();
	}
	
	public static Parchment fromXML(XMLObject o){
		String name = o.getNextData();
		String description = o.getNextData();
		
		return new Parchment(name, description);
		
	}
}
