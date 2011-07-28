package gameworld;

import dataStorage.XMLFormatter;
import dataStorage.XMLObject;


/**
 * A created item crafted by the engineer which blows down robust doors.
 * @author Calvin Kaye
 *
 */
public class Explosive extends Item{

	public Explosive() {
		
		super("Explosive" , "A potent Gunpowder Explosive", true);
		
	}

	public boolean equals(Object other){
		
		if(other == null){
			return false;
		}
		if(other.getClass() != Explosive.class){
			return false;
		}
		
		Explosive e = (Explosive) other;
		return this.getName().equals(e.getName());
		
	}
	
	public String toXML(){
		XMLFormatter x = new XMLFormatter();
		
		x.appendTag("gameworld.Explosive");

		x.closeTag();
		
		return x.getXML();
	}
	
	public static Explosive fromXML(XMLObject o){
		
		return new Explosive();
		
	}
}
