package gameworld;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import dataStorage.Element;
import dataStorage.XMLFormatter;
import dataStorage.XMLObject;

/**
 * This class represents a GridLocation. A GridLocation is a part of a grid.
 * @author Calvin Kaye
 *
 */
public class GridLocation implements Serializable {

	private List<GameObject> gameObjects = Collections.synchronizedList(new ArrayList<GameObject>());  // the list of objects the grid location contains.
	private transient Room room; // the room the grid location is part of.
	private int row; // used for indexing the room grid of room.
	private int col; // used for indexing the room grid of room.


	public GridLocation(Room room, int row , int col){

		this.room = room;
		this.row = row;
		this.col = col;

	}

	/**
	 * Constructs a new GridLocation object.
	 * @param room the room the grid location is part of.
	 * @param x the x coordinate of the grid location.
	 * @param y the y coordinate of the grid location.
	 */



	/**
	 * Get the room that this grid location is part of.
	 * @return
	 */
	public Room getRoom(){
		return room;
	}

	/**
	 * Set the room that this grid location is part of.
	 * @return
	 */
	public void setRoom(Room room){
		this.room = room;
	}


	/**
	 * Get the row index of this grid location in association to the Room 2D array
	 * that this grid location is part of.
	 * @return the row number.
	 */
	public int getRowIndex(){
		return row;
	}

	/**
	 * Set the row index of this grid location in association to the Room 2D array
	 * that this grid location is part of.
	 * @return the row number.
	 */
	public void setRow(int row){
		this.row = row;
	}

	/**
	 * Get the column index of this grid location in association to the Room 2D array
	 * that this grid location is part of.
	 * @return the column number.
	 */
	public int getColIndex(){
		return col;
	}


	/**
	 * Set the column index of this grid location in association to the Room 2D array
	 * that this grid location is part of.
	 * @return the column number.
	 */
	public void setCol(int col){
		this.col = col;
	}


	/**
	 * Get the game objects that are in this grid location.
	 * @return the list of game objects.
	 */
	public List<GameObject> getObjects(){
		return gameObjects;
	}

	public void setObjects(List<GameObject> gameObjects){
		this.gameObjects = Collections.synchronizedList(gameObjects);
	}

	public List<GameObject> getGameObjects(){
		return gameObjects;
	}

	/**
	 * Adds game object to current list of game objects
	 * 
	 * @param g
	 */
	public void addGameObject(GameObject g){
		synchronized (this){
			gameObjects.add(0,g);
		}
	}

	/**
	 * Removes game object from current list of game objects.
	 * @param g
	 */
	public void removeGameObject(GameObject g){
		synchronized(this){
			gameObjects.remove(g);
		}
	}

	/**
	 * Generates a random monster for this grid location when a player moves on to it. 
	 * 20% chance to generate a monster. 
	 * @return the monster that is generated.
	 * Returns null if no monster generated.
	 */

	public Monster generateMonster(){
		Random random = new Random();

		if(room.getLevel().getLevelNumber() == 5 && !this.equals(new GridLocation(null,6,4))){	
			return new Dragon(this,1500,200,0.15,1000);			
		}
		else{
			double chanceMonster = random.nextDouble();
			if(chanceMonster < 0.10){ // 10% chance to generate a monster.

				int rand = random.nextInt(3) + 1; // 3 monsters to choose from.

				if(rand == 1){
					Spider spid = new Spider(this, 60*room.getLevel().getLevelNumber(),5 * room.getLevel().getLevelNumber(), 0.10, 30* room.getLevel().getLevelNumber());
					return spid;
				}
				else if(rand == 2){
					Bat bat = new Bat(this, 50*room.getLevel().getLevelNumber(), 10 * room.getLevel().getLevelNumber(), 0.10, 35* room.getLevel().getLevelNumber());
					return bat;
				}

				else{
					Adder add = new Adder(this,70*room.getLevel().getLevelNumber(),5 * room.getLevel().getLevelNumber(), 0.20 ,40* room.getLevel().getLevelNumber());
					return add;
				}

			}
		}

		return null;


	}

	public boolean equals(Object other){

		if(other == null){
			return false;
		}

		if(GridLocation.class == other.getClass()){			
			GridLocation grid = (GridLocation) other;

			return this.row == grid.row && this.col == grid.col;
		}

		return false;
	}

	
	public boolean isEmpty(){
		//if it only contains a wall, we can assume the cell is empty
		if (gameObjects.contains(new Wall()))
			return true;
	
		return gameObjects.isEmpty();
	}

	public String toXML(){
		XMLFormatter x = new XMLFormatter();

		x.appendTag("gameworld.GridLocation");

		x.appendTag("row");
		x.append("" + row);
		x.closeTag();

		x.appendTag("col");
		x.append("" + col);
		x.closeTag();

		x.appendTag("Listgameworld.GameObjects");

		int count = 0;

		for (GameObject g : gameObjects){
			x.appendTag("index" + count);

			//this  new line is very important!
			x.append("\n");
			
			x.append(g.toXML());
			x.closeTag();

		}

		x.closeTag();
		x.closeTag();

		return x.getXML();
	}


	public static GridLocation fromXML(XMLObject o){

		
		int row = new Integer(o.getNextData());
		int col = new Integer(o.getNextData());
		
		XMLObject gameObjectList = o.getNextObject();
		
		ArrayList<GameObject> gameObjects = gameObjectList.getNextGameObjectList();
		
		GridLocation g = new GridLocation(null, row, col);
		
		g.setObjects(gameObjects);
		return g;
	}

	
}
