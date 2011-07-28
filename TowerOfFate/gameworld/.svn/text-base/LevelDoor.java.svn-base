package gameworld;

import java.util.ArrayList;
import java.util.List;

import dataStorage.Element;
import dataStorage.XMLFormatter;
import dataStorage.XMLObject;

/**
 * This class represents a LevelDoor. A LevelDoor is a locked door that needs to be opened to advance levels.
 * @author Calvin Kaye
 *
 */
public class LevelDoor extends LockedDoor {
	
	private Level levelTo;
	//used for Xml loading
	protected int levelToId;
	/**
	 * Construct a new LevelDoor.
	 * @param name the name of this level door.
	 * @param description the description of this level door.
	 * @param gl the grid location of this level door.
	 * @param password the password to this level door.
	 * @param levelTo the level this door connects to.
	 * @param connectsTo the door this level door connects to.
	 */
	public LevelDoor(String name , String description, GridLocation gl, String password, Level levelTo, Door connectsTo){
		
		super(name , description, gl, password, connectsTo);
		this.levelTo = levelTo;
		
	}
	

	/**
	 * Get the level this level door connects to.
	 * @return Level: the level this level door connects to.
	 */
	public Level getLevelTo(){
		return levelTo;
	}

	
	public boolean canUnlock(Player player) {
		List<Item> kr = player.getKeyring().getContainer();
		
		for(Item i : kr){
			if(i.getName().equals(getPassword())){
				return true;
			}
		}
		return false;
	}
	
	/*
	 * (non-Javadoc)
	 * @see gameworld.Door#interactEffect(gameworld.Player)
	 */
	public String interactEffect(Player p){
		
		if(getPlayersUnlocked().contains(p)){
			p.moveThrough(this);
			Room newRoom = p.getCurrentRoom();
			return "You walk into " + newRoom.getName() + "\nYou are now on Level " +
			levelTo.getLevelNumber() +	"\n see the tablet for level objectives.\n" +
			newRoom.getName();
		}
		
		if(canUnlock(p)){
			p.moveThrough(this);
			getPlayersUnlocked().add(p);
			Room newRoom = p.getCurrentRoom();
			return "You walk into " + newRoom.getName() + "\nYou are now on Level " +
			levelTo.getLevelNumber() +	"\n see the tablet for level objectives.\n" +
			newRoom.getName();
		}
		else{
			return "You need " + getPassword() + " for this door";
		}
		
	}

		


}
