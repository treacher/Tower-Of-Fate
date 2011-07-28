package gameworld;

import dataStorage.XMLFormatter;
import dataStorage.XMLObject;

/**
 * This class represents a Wall. A Wall is an object that represents a boundary.
 * @author Damian Kaye
 *
 */
public class Wall extends InanimateObject implements BoundaryObject {

	/**
	 * Construct a new Wall object.
	*/
	public Wall() {
		
		super("Wall", "A boundary you cannot pass.");
		
	}
	
	public boolean equals(Object o){
		if (o == null)
			return false;
		
		return o.getClass() == getClass();
	}
	
	public String toXML(){
		XMLFormatter x = new XMLFormatter();
		
		x.appendTag("gameworld.Wall");

		x.closeTag();
		
		return x.getXML();
	}
	
	public static Wall fromXML(XMLObject o){
		
		return new Wall();
		
	}


}
