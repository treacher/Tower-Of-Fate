package gameworld;

import java.util.ArrayList;
import java.util.List;

import dataStorage.Element;
import dataStorage.XMLFormatter;
import dataStorage.XMLObject;

/**
 * This class represents a SpiderWebDoor which is a special type of locked door.
 * @author Calvin Kaye
 *
 */
public class SpiderwebDoor extends LockedDoor {
	
	/**
	 * Construct a new SpiderwebDoor
	 * @param name the name of this spiderweb door
	 * @param description the description of this spiderweb door
	 * @param gl the grid location this spiderweb door is on.
	 * @param connectsTo the door this spiderweb door connects to.
	 */
	public SpiderwebDoor(String name , String description, GridLocation gl, Door connectsTo){
		
		super(name , description, gl, "Machete", connectsTo);
		
	}

	/*
	 * (non-Javadoc)
	 * @see gameworld.LockedDoor#canUnlock(gameworld.Player)
	 */
	public boolean canUnlock(Player player) {
		
		List<Item> bp = player.getBackpack().getContainer();
		
		for(Item i : bp){
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
			return "You walk into " + newRoom.getName() + 
			"\n" + newRoom.getName() + " Description: " + newRoom.getDescription();
			
		}
		
		if(canUnlock(p)){
			
			p.moveThrough(this);
			getPlayersUnlocked().add(p);
			Room newRoom = p.getCurrentRoom();
			return "You slash a hole through the spider webs to get into " + newRoom.getName() + 
			"\n" + newRoom.getName() + " Description: " + newRoom.getDescription();
			
		}
		
		else{
			return "You need a " + getPassword() + " for this webbed door";
		}
	}
	
	
}
