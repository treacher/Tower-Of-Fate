package gameworld;

import dataStorage.XMLFormatter;
import dataStorage.XMLObject;

/**
 * This class represents a Tablet. A Tablet is used by players for instructions
 * on how to escape a particular level.
 * @author Damian Kaye
 *
 */
public class Tablet extends InanimateObject implements BoundaryObject {
	
	/**
	 * Construct a new Tablet Object.
	 * @param name the name of this Tablet.
	 * @param description the description of this Tablet.
	 */
	public Tablet(String name , String description){
		
		super(name, description);
		
	}
	
	public String toXML(){
		XMLFormatter x = new XMLFormatter();
		
		x.appendTag("gameworld.Tablet");
		
		x.appendTag("name");
		x.append(super.getName());
		x.closeTag();
		
		x.appendTag("description");
		x.append(super.getDescription());
		x.closeTag();
		
		x.closeTag();
		
		return x.getXML();
	}
	
	public static Tablet fromXML(XMLObject o){
		String name = o.getNextData();
		String description = o.getNextData();
		
		return new Tablet(name, description);
		
	}

	
	

}
