package gameworld;

import java.util.ArrayList;
import java.util.List;

import dataStorage.XMLFormatter;
import dataStorage.XMLObject;
/**
 * This class represents a ContainerObject. An abstract concept that represents all container objects.
 * @author Calvin Kaye
 *
 */
public abstract class ContainerObject extends InanimateObject {

	private List<Item> container = new ArrayList<Item>();
	private int slots;

	/**
	 * Build the state of a ContainerObject.
	 * @param slots the number of slots this container has.
	 * @param name the name of the container object
	 * @param description the description of the container object
	 * @param gl the grid location this container object is on.
	 */
	public ContainerObject(int slots , String name , String description){

		super(name , description);
		this.slots = slots;

	}

	/**
	 * Get the container of this container object.
	 * @return the list of items.
	 */
	public List<Item> getContainer(){
		return container;
	}

	/**
	 * will set the current container to the list provided
	 * 
	 */
	public void setContainer(List<Item> container){

		this.container = container;
	}


	/**
	 * Get the number of slots in this container object.
	 * @return the number of slots.
	 */
	public int getSlotSize(){
		return slots;
	}
	public String toXML2(){
		return "<object class=\"gameworld.ContainerObject\"> " +
		// TODO Add xml method to go through container array
		"\n</object>";
	}

	public String toXML(){
		XMLFormatter x = new XMLFormatter();

		x.appendTag("gameworld.ContainerObject");

		x.appendTag("name");
		x.append(super.getName());
		x.closeTag();

		//append the list of items
		x.appendTag("Listgameworld.GameObjects");

		int count = 0;
		for(Item i : container){
			x.appendTag("index" + count);

			//this  new line is very important!
			x.append("\n");

			x.append(i.toXML());
			x.closeTag();

			System.out.println("                                           " + i.toString());
		}

		x.closeTag();
		x.closeTag();

		return x.getXML();
	}

	public static ContainerObject fromXML(XMLObject o) {
		String name = o.getNextData();
		
		ArrayList<Item> items = o.getNextItemList();
		
		return null;
	}
	
	public static List<Item> makeContainerList(XMLObject o){
		String name = o.getNextData();
		
		ArrayList<Item> items = o.getNextItemList();
		
		return items;
		
	}

	/**
	 * Checks to see if the container is full.
	 * @return boolean.
	 */
	public boolean isFull(){
		return slots == container.size();
	}



}
