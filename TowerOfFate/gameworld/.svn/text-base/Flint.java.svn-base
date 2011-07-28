package gameworld;

import dataStorage.XMLFormatter;
import dataStorage.XMLObject;


/**
 * 
 * Represents the flint object which is essential when starting the ignition of the explosive
 * @author Calvin Kaye
 *
 */
public class Flint extends Item{

	public Flint() {
		
		super("Flint" , "Hard rock that if struck together will create fire.",true);
		
	}


	public boolean equals(Object other){
		
		if(other == null){
			return false;
		}
		if(other.getClass() != Flint.class){
			return false;
		}
		
		Flint f = (Flint) other;
		return this.getName().equals(f.getName());
		
	}
	
	public String toXML(){
		XMLFormatter x = new XMLFormatter();
		
		x.appendTag("gameworld.Flint");

		x.closeTag();
		
		return x.getXML();
	}
	
	public static Flint fromXML(XMLObject o){
		
		return new Flint();
		
	}
}
