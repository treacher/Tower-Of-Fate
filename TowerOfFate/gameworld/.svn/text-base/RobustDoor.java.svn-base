package gameworld;

import java.util.ArrayList;
import java.util.List;

import dataStorage.Element;
import dataStorage.XMLFormatter;
import dataStorage.XMLObject;

/**
 * This class represents a RobustDoor which is a special type of level door.
 * @author Calvin Kaye
 *
 */
public class RobustDoor extends LevelDoor{

	/**
	 * Construct a new RobustDoor.
	 * @param name the name of this robust door.
	 * @param description the description of this robust door.
	 * @param gl the grid location of this robust door.
	 * @param password the password to this robust door.
	 * @param levelTo the level this door connects to.
	 * @param connectsTo the door this robust door connects to.
	 */
	public RobustDoor(String name, String description, GridLocation gl, Level levelTo, Door connectsTo) {
		super(name, description, gl, "Explosive and Flint", levelTo, connectsTo);
	}
	


	/*
	 * (non-Javadoc)
	 * @see gameworld.LevelDoor#canUnlock(gameworld.Player)
	 */
	public boolean canUnlock(Player player) {
		
		List<Item> bp = player.getBackpack().getContainer();
		boolean passwordPart1 = false;
		boolean passwordPart2 = false;
		
		for(Item i : bp){
			if(i.getName().equals("Explosive")){
				passwordPart1 = true;
			}
			if(i.getName().equals("Flint")){
				passwordPart2 = true;
			}
		}
		
		if(passwordPart1 && passwordPart2){
			return true;
		}
		else{
			return false;
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see gameworld.LevelDoor#interactEffect(gameworld.Player)
	 */
	public String interactEffect(Player p){
		
		if(getPlayersUnlocked().contains(p)){
			p.moveThrough(this);
			Room newRoom = p.getCurrentRoom();
			return "You walk into " + newRoom.getName() + "\n ,You are now on Level " +
			getLevelTo().getLevelNumber() +	"\n see the tablet for level objectives.\n" +
			newRoom.getName() + " Description: " + newRoom.getDescription();
		}
		
		
		if(canUnlock(p)){
			p.moveThrough(this);
			getPlayersUnlocked().add(p);
			Room newRoom = p.getCurrentRoom();
			return "You walk into " + newRoom.getName() + "\nYou are now on Level " +
			getLevelTo().getLevelNumber() +	"\n see the tablet for level objectives./n" +
			newRoom.getName() + " Description: " + newRoom.getDescription();
		}
		
		else{
			return "You need Explosive and Flint to blow down this door.";
		}
		
		
	}
	

}
