package gameworld;

import dataStorage.XMLFormatter;
import dataStorage.XMLObject;

/**
 * This class holds the information about the explosive recipe which is needed to help guide the crafting of an explosive.
 * 
 * @author Damian Kaye
 *
 */
public class ExplosiveRecipe extends Item{

	public ExplosiveRecipe() {
		 
		super("Explosive Recipe" , 
			  "A secret Explosive Recipe: \n" + 
			  "Requirements: \n" +
			  "x1 Gunpowder Pouch \n" +
			  "x1 Fuse \n" , true);
		
	}

	public boolean equals(Object other){
		
		if(other == null){
			return false;
		}
		if(other.getClass() != ExplosiveRecipe.class){
			return false;
		}
		
		ExplosiveRecipe er = (ExplosiveRecipe) other;
		return this.getName().equals(er.getName());
		
	}
	public String toXML(){
		XMLFormatter x = new XMLFormatter();
		
		x.appendTag("gameworld.ExplosiveRecipe");

		x.closeTag();
		
		return x.getXML();
	}
	
	public static ExplosiveRecipe fromXML(XMLObject o){
		
		return new ExplosiveRecipe();
		
	}
}
