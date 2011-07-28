package gameworld;

import java.io.Serializable;

import dataStorage.XMLFormatter;
import dataStorage.XMLObject;

/**
 * This class represents a Level. A level is a floor of rooms.
 * 
 * @author Calvin Kaye
 *
 */
public class Level implements Serializable{
	
	private String name;
	private int levelNumber;
	private static TowerOfFate tof;
	private String description;
	
	/**
	 * Construct a new Level object.
	 * @param name the name of the level.
	 * @param description the description of the level.
	 * @param levelNumber the level number of the level.
	  */
	public Level(String name , String description , int levelNumber){
		
		this.name = name; 
		this.levelNumber = levelNumber;
		this.description = description;
		
	}

	
	
	/**
	 * Gets the name of this level.
	 * @return
	 */
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public void setDescription(String description){
		this.name = name;
	}
	
	public void setLevelNumber(int levelNumber){
		this.levelNumber = levelNumber;
	}
	/**
	 * Get the description of this level.
	 * @return
	 */
	public String getDescription(){
		return description;
	}
	
	/**
	 * Gets the level number of this level.
	 * @return
	 */
	public int getLevelNumber(){
		return levelNumber;
	}

	/**
	 * Set the tower the level is on.
	 * @param tower
	 */
	public static void setTower(TowerOfFate tower){
		tof = tower;
	}
	
	/**
	 * Get the tower the level is on.
	 * @return TowerOfFate
	 */
	public static TowerOfFate getTower(){
		return tof;
	}
	
	public String toXML(){
		XMLFormatter x = new XMLFormatter();
		
		x.appendTag("gameworld.Level");
		
		x.appendTag("name");
		x.append(name);
		x.closeTag();
		
		x.appendTag("description");
		x.append(description);
		x.closeTag();
		
		x.appendTag("levelNumber");
		x.append("" + levelNumber);
		x.closeTag();
		
		x.closeTag();
		
		return x.getXML();
	}



	public static Level fromXML(XMLObject o) {
		//public Level(String name , String description , int levelNumber){
		System.out.println(o);
		
		String name = o.getNextData();
		String description = o.getNextData();
		int levelNumber = new Integer(o.getNextData());
		
		return new Level(name, description, levelNumber);
	}
}
