package gameworld;

import dataStorage.XMLFormatter;
import dataStorage.XMLObject;



/**
 * This class represents a Keyring. A Keyring is a container that a player uses to hold
 * his/her keys.
 * @author Calvin Kaye
 *
 */
public class Keyring extends ContainerObject{

	public static final int MAX_KEYS = 4; // the max amount of keys a keyring can hold.
	private transient Player owner;

	/**
	 * Construct a new Keyring object.
	 * @param name the name of the Keyring.
	 */
	public Keyring(String name){

		// Pass null for the grid location because it can never be on a grid location. 
		// It is always on the player.
		super(MAX_KEYS, name , "A Keyring is used to hold keys.");

	}

	/**
	 * Set the owner of this Keyring.
	 * @param p the owner of the Keyring.
	 */
	public void setOwner(Player p){
		owner = p;
	}

	/**
	 * Get the owner of this Keyring.
	 * @return the player who owns this Keyring.
	 */
	public Player getOwner(){
		return owner;
	}

	/**
	 * Checks to see if there is a key at a particular slot in this keyring.
	 * @param slot the slot to check
	 * @return true if there is a key, false if there is no key.
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
	 * Returns the possible options available for a particular key in this backpack.
	 * @param slot the slot of this keyring.
	 * @return the String[] of options. Guarantees no null strings in the array.
	 */
	public String[] keyOptions(int slot){

		Key key = (Key) getContainer().get(slot);

		String[] options = new String[2];
		String destroyOption = "Destroy " + key.getName();
		String inspectOption = "Inspect " + key.getName();

		options[0] = destroyOption;
		options[1] = inspectOption;

		return options;
	}

	public String toXML(){

		XMLFormatter x = new XMLFormatter();

		x.appendTag("gameworld.Keyring");

		x.append(super.toXML());

		x.closeTag();

		return x.getXML();

	}
	

	public static Keyring fromXML(XMLObject o){

		XMLObject container = o.getNextObject();	
		ContainerObject c = ContainerObject.fromXML(container);

		Keyring keyring = new Keyring(c.getName());
		keyring.setContainer(ContainerObject.makeContainerList(container));

		return keyring;

	}
}
