package gameworld;

import java.util.ArrayList;

import dataStorage.XMLFormatter;
import dataStorage.XMLObject;

/**
 * This class represents a Sarcophagus. A Sarcophagus is a special tomb that can hold items in it.
 * @author Calvin Kaye
 *
 */
public class Sarcophagus extends ContainerObject implements InteractiveObject, BoundaryObject {
	
	/**
	 * Construct a new Sarcophagus object.
	 * @param slots the number of slots on this Sarcophagus.
	 * @param name the name of this Sarcophagus.
	 * @param description the description of this Sarcophagus
	  */
	public Sarcophagus(int slots , String name , String description){
		
		super(slots , name , description);
		
	}

	
	/*
	 * (non-Javadoc)
	 * @see gameworld.InteractiveObject#interactEffect(gameworld.Player)
	 */
	public String interactEffect(Player p) {
		
		String ans = "";
		//give the player each item the sarcophagus owns.
		
		if(!getContainer().isEmpty()){
			Item i = getContainer().get(0);
			ans += "You see a glimmering item deep inside the sarcophagus, ";
			ans += p.interactivePickup(i);
		}
		
		if(ans.equals("")){
			ans = "You interact with " + getName() + ", nothing happens.";
		}
		
		return ans;
		
	}
	/**
	 * Put this object into an XML format
	 * included contained objects
	 */
	public String toXML(){
		XMLFormatter x = new XMLFormatter();

		x.appendTag("gameworld.Sarcophagus");
		
		x.appendTag("slots");
		x.append("" + getSlotSize());
		x.closeTag();
		
		x.appendTag("name");
		x.append("" + getName());
		x.closeTag();

		x.appendTag("description");
		x.append("" + getDescription());
		x.closeTag();

		x.appendTag("Listgameworld.Item");

		int count = 0;

		for (GameObject g : getContainer()){
			x.appendTag("index" + count);

			//this  new line is very important!
			x.append("\n");
			
			x.append(g.toXML());
			x.closeTag();

			count++;
		}

		x.closeTag();
		x.closeTag();

		return x.getXML();
	}

	/**
	 * Parses in and creates a Sarcophagus object from xmlOBject input
	 * @param XMLObject o
	 * @return Sarcophagus object g
	 */
	public static Sarcophagus fromXML(XMLObject o){
		//xml[0] = class name
		//xml[1] = slots
		//xml[2] = name
		//xml[3] = description
		//xml[4] = itemlist
		int slots = new Integer(o.getNextData());
		String name = new String(o.getNextData());
		String description = new String(o.getNextData());
		
		XMLObject gameObjectList = o.getNextObject();
		ArrayList<Item> items = gameObjectList.getNextItemList();
		
		Sarcophagus g = new Sarcophagus(slots, name, description);
		for (int i = 0; i<items.size();i++){
		g.getContainer().set(i, items.get(i));
		}
		return g;
	}
}


