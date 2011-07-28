package gameworld;

import java.util.ArrayList;
import java.util.List;

import dataStorage.Element;
import dataStorage.XMLFormatter;
import dataStorage.XMLObject;


/**
 * This class represents the abstract concept of a LockedDoor. A LockedDoor is a Door that requires items to open it.
 * @author Calvin Kaye
 *
 */
public abstract class LockedDoor extends Door {
	
	private List<Player> players = new ArrayList<Player>(); // Players who have unlocked this door before.
	private String password; // the required items to open this door.
	protected int conDoorCol, conDoorRow, conDoorRoomID;
	protected List<Integer> UIDs;

	/**
	 * Construct a new LockedDoor.
	 * @param name the name of this locked door.
	 * @param description the description of this locked door.
	 * @param gl the grid location of this locked door.
	 * @param password the password to open this locked door.
	 * @param connectsTo the door this locked door connects to.
	 */
	public LockedDoor(String name , String description, GridLocation gl, String password, Door connectsTo){
		
		super(name , description, gl , false, connectsTo);
		this.password = password;
		
	}
	
	/**
	 * Checks to see if the player has the requirements to open this locked door.
	 * @param player the player who is trying to unlock this locked door.
	 * @return boolean : can the player open this locked door.
	 */
	public abstract boolean canUnlock(Player player);
	
	/**
	 * Get the players who have previously unlocked this door.
	 * @return List of players who have unlocked this locked door before.
	 */
	public List<Player> getPlayersUnlocked(){
		return players;
	}
	
	/**
	 * Get the password for this door.
	 * @return password.
	 */
	public String getPassword(){
		return password;
	}
}
