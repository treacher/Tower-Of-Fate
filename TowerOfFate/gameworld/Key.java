package gameworld;

import dataStorage.XMLFormatter;
import dataStorage.XMLObject;

/**
 * This class represents a key object which is used to open locked doors.
 * 
 * @author Damian Kaye
 *
 */

public class Key extends Item{

	public Key(String name, String description) {
		
		super(name, description, true);
		
	}
	

	public boolean equals(Object other){
		
		if(other == null){
			return false;
		}
		if(other.getClass() != Key.class){
			return false;
		}
		
		Key k = (Key) other;
		return this.getName().equals(k.getName());
		
	}
	public String toXML(){
		XMLFormatter x = new XMLFormatter();
		
		x.appendTag("gameworld.Key");
		
		x.appendTag("name");
		x.append(super.getName());
		x.closeTag();
		
		x.appendTag("description");
		x.append(super.getDescription());
		x.closeTag();
		
		x.closeTag();
		
		return x.getXML();
	}
	
	public static Key fromXML(XMLObject o){
		String name = o.getNextData();
		String description = o.getNextData();
		
		return new Key(name, description);
		
	}
	
}
