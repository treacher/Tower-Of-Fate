package gameworld;

import java.util.ArrayList;

import dataStorage.Element;
import dataStorage.XMLFormatter;
import dataStorage.XMLObject;

/**
 * This class represents a BoulderDoor which is a special type of level door.
 * @author Calvin Kaye
 *
 */
public class BoulderDoor extends LevelDoor {
	
	/**
	 * Construct a new BoulderDoor.
	 * @param name the name of this boulder door.
	 * @param description the description of this boulder door.
	 * @param gl the grid location of this boulder door.
	 * @param password the password to this boulder door.
	 * @param levelTo the level this door connects to.
	 * @param connectsTo the door this boulder door connects to.
	 */
	public BoulderDoor(String name , String description, GridLocation gl, Level levelTo, Door connectsTo){
		
		super(name , description, gl , "30" , levelTo , connectsTo);
		
	}
		
	
	public boolean canUnlock(Player player) {
		
		if(player.getStrength() >= 30){
			return true;
		}
		else{
			return false;
		}
	}
	
	public String interactEffect(Player p){
		
		if(getPlayersUnlocked().contains(p)){
			p.moveThrough(this);
			Room newRoom = p.getCurrentRoom();
			return "You walk into " + newRoom.getName() + "You are now on Level " +
			getLevelTo().getLevelNumber() +	" see the tablet for level objectives." +
			newRoom.getName();
		}
		
		if(canUnlock(p)){
			p.moveThrough(this);
			getPlayersUnlocked().add(p);
			Room newRoom = p.getCurrentRoom();
			return "You walk into " + newRoom.getName() + "You are now on Level " +
			getLevelTo().getLevelNumber() +	" see the tablet for level objectives." +
			newRoom.getName();
		}
		
		else{
			return "You are not strong enough yet, you need 30 strength, you only have " + p.getStrength();
		}
		
		
	}
	

	

}
