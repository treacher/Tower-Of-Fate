package gameworld;

import dataStorage.XMLFormatter;
import dataStorage.XMLObject;

/**
 * Represents the gun powder object that is essential when creating a explosive
 * 
 * @author Calvin Kaye
 *
 */

public class GunpowderPouch extends Item{

	public GunpowderPouch() {
		
		super("Gunpowder Pouch" , "A leathery pouch containing gunpowder.",true);
	
	}


	public boolean equals(Object other){
		
		if(other == null){
			return false;
		}
		if(other.getClass() != GunpowderPouch.class){
			return false;
		}
		
		GunpowderPouch gp = (GunpowderPouch) other;
		return this.getName().equals(gp.getName());
		
	}
	
	public String toXML(){
		XMLFormatter x = new XMLFormatter();
		
		x.appendTag("gameworld.GunpowderPouch");

		x.closeTag();
		
		return x.getXML();
	}
	
	public static GunpowderPouch fromXML(XMLObject o){
		
		return new GunpowderPouch();
		
	}
}
