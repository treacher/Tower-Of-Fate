package gameworld;

import dataStorage.XMLFormatter;
import dataStorage.XMLObject;

public class Orb extends InanimateObject implements InteractiveObject, BoundaryObject{
	
	public Orb(String name , String description){
		
		super(name , description);
		
	}

	

	public String interactEffect(Player p) {
		
		//first player to touch this wins the game
		
		return "" + p.getName() + " wins the game!!!";
		
	}

	public String toXML(){
		XMLFormatter x = new XMLFormatter();
		
		x.appendTag("gameworld.Orb");
		
		x.appendTag("name");
		x.append(super.getName());
		x.closeTag();
		
		x.appendTag("description");
		x.append(super.getDescription());
		x.closeTag();
		
		x.closeTag();
		
		return x.getXML();
	}
	
	public static Orb fromXML(XMLObject o){
		String name = o.getNextData();
		String description = o.getNextData();
		
		return new Orb(name, description);
		
	}


}
