package gameworld;

import java.util.ArrayList;

import dataStorage.XMLFormatter;
import dataStorage.XMLObject;

/**
 * This class represents a Skeleton. A skeleton can hold items in it.
 * @author Damian Kaye
 *
 */
public class Skeleton extends ContainerObject implements InteractiveObject, BoundaryObject{
	
	/**
	 * Construct a new Skeleton object.
	 * @param slots the number of slots on this Skeleton.
	 * @param name the name of the Skeleton.
	 * @param description the description of the Skeleton.
	 */
	public Skeleton(int slots, String name , String description){
		
		super(slots, name , description);
	
	}

	
	/*
	 * (non-Javadoc)
	 * @see gameworld.InteractiveObject#interactEffect(gameworld.Player)
	 */
	public String interactEffect(Player p) {
		
		String ans = "";
		//give the player each item the skeleton owns
		if(!getContainer().isEmpty()){
			Item i = getContainer().get(0);
			ans += "You see a shiny in the skeletons chest, ";
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

		x.appendTag("gameworld.Skeleton");
		
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
	 * Parses in and creates a Skeleton object from xmlOBject input
	 * @param XMLObject o
	 * @return Skeleton object g
	 */
	public static Skeleton fromXML(XMLObject o){
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
		
		Skeleton g = new Skeleton(slots, name, description);
		for (int i = 0; i<items.size();i++){
		g.getContainer().set(i, items.get(i));
		}
		return g;
	}
	
}
