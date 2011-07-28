package gameworld;

import dataStorage.XMLFormatter;
import dataStorage.XMLObject;

/**
 * 
 * Represents the fuse object that is essential when creating a explosive
 * 
 * @author Calvin Kaye
 *
 */
public class Fuse extends Item{

	public Fuse() {
		
		super("Fuse" , "A piece of flammable rope.",true);
		
	}


	public boolean equals(Object other){
		
		if(other == null){
			return false;
		}
		if(other.getClass() != Fuse.class){
			return false;
		}
		
		Fuse f = (Fuse) other;
		return this.getName().equals(f.getName());
		
	}
	public String toXML(){
		XMLFormatter x = new XMLFormatter();
		
		x.appendTag("gameworld.Fuse");

		x.closeTag();
		
		return x.getXML();
	}
	
	public static Fuse fromXML(XMLObject o){
		
		return new Fuse();
		
	}
}
