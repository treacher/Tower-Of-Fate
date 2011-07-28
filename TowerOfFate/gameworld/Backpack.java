package gameworld;

import dataStorage.XMLFormatter;
import dataStorage.XMLObject;



/**
 * This class represents a Backpack. A backpack is a container that a player
 * uses to hold his/her items.
 * @author Damian Kaye
 *
 */
public class Backpack extends ContainerObject{

	public static final int MAX_ITEMS = 16; // the max amount of items a backpack can hold.
	private transient Player owner;
	
	/**
	 * Construct a new Backpack object.
	 * @param name the name of the Backpack.
	 */
	public Backpack(String name){
		
		super(MAX_ITEMS , name , "A Backpack is used to hold items.");
		
	}

	/**
	 * Set the owner of this Backpack.
	 * @param p the owner of the Backpack.
	 */
	public void setOwner(Player p){
		owner = p;
	}
	
	/**
	 * Get the owner of this Backpack.
	 * @return the player who owns this Backpack.
	 */
	public Player getOwner(){
		return owner;
	}
	
	
	
	/**
	 * Checks to see if there is an item at a particular slot in this backpack.
	 * @param slot the slot to check
	 * @return true if there is an item, false if there is no item.
	 */
	public boolean checkSlot(int slot){
		
		if(slot < getContainer().size()){
			return true;
		}
		else{
			return false;
		}
	}
	
	/**
	 * Returns the possible options available for a particular item in this backpack.
	 * @param slot the slot of this backpack.
	 * @return the String[] of options. Guarantees no null strings in the array.
	 */
	public String[] itemOptions(int slot){
		
		Item item = getContainer().get(slot);
		
		String dropOption = null;
		String usableOption = null;
		
		if(item instanceof VendorItem){
			dropOption = "Drop " + item.getName();
		}
		
		if(item instanceof UsableItem){
			usableOption = "Use " + item.getName();
		}
		
		String destroyOption = "Destroy " + item.getName();
		String inspectOption = "Inspect " + item.getName();
		
		String[] options;
		
		if(dropOption == null && usableOption != null){
			
			options = new String[3];
			options[0] = destroyOption;
			options[1] = inspectOption;
			options[2] = usableOption;
			return options;
			
		}
		else if(dropOption != null && usableOption == null){
			
			options = new String[3];
			options[0] = destroyOption;
			options[1] = inspectOption;
			options[2] = dropOption;
			return options;
			
		}
		
		else if(dropOption != null && usableOption != null){
			
			options = new String[4];
			
			options[0] = destroyOption;
			options[1] = inspectOption;
			options[2] = dropOption;
			options[3] = usableOption;
			return options;
			
		}
		else{
		
			options = new String[2];			
			options[0] = destroyOption;
			options[1] = inspectOption;
			return options;
		
		}
		
	}
	
	public String toXML(){
		XMLFormatter x = new XMLFormatter();
		
		x.appendTag("gameworld.Backpack");
		
		x.append(super.toXML());
			
		x.closeTag();
		
		return x.getXML();
	}
	
	public static Backpack fromXML(XMLObject o ){
		
		XMLObject container = o.getNextObject();	
		ContainerObject c = ContainerObject.fromXML(container);
		
		Backpack backpack = new Backpack(c.getName());
		backpack.setContainer(ContainerObject.makeContainerList(container));
		
		return backpack;
	}
	
}
