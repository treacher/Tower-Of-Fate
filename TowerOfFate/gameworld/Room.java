package gameworld;

import gameworld.Player.Direction;

import java.io.Serializable;
import java.util.ArrayList;

import dataStorage.XMLFormatter;
import dataStorage.XMLObject;

/**
 * This class represents a Room (an enclosed area with 4 walls).
 * @author Damian Kaye
 *
 */
public class Room implements Serializable{

	private String name;
	private String description;
	private transient Level level;
	public static final int ROWS = 8;
	public static final int COLS = 8;
	public GridLocation[][] roomGrid = initialiseRoom();
	private static int idCount = 0;
	private int id;

	/**
	 * Construct a new Room object.
	 * @param name the name of the room.
	 * @param description the description of the room.
	 * @param level the level the room is on.
	 */
	public Room(String name, String description, Level level){
		this.name = name;
		this.description = description;
		this.level = level;
		id = idCount++;
		
	}
	/**
	 * alternate constructor for creating from XML
	 * 
	 * @param name
	 * @param description
	 * @param level
	 * @param id
	 */
	public Room(String name, String description, Level level, int id){
		this.name = name;
		this.description = description;
		this.level = level;
		this.id = id;
		
	}
	


	/**
	 * Creates an empty room, used by slave to be populated
	 * by an incoming byte stream.
	 * 
	 */
	public Room(int id, String name){

		this.name = name;
		this.id = id;
	}

	/**
	 * Sets the room up with a basic initialisation.
	 * @return GridLocation double array representing the room
	 */
	public GridLocation[][] initialiseRoom(){

		GridLocation[][] rGrid = new GridLocation[ROWS][COLS];

		for(int i = 0; i < ROWS; i++){
			for(int j = 0 ; j < COLS ; j++){
				GridLocation grid;
				if(i == 0 || j == 0 ||i == ROWS - 1 || j == COLS - 1){
					
					grid = new GridLocation(this,i,j);
					grid.getObjects().add(new Wall());				
				}

				else {
					
					grid = new GridLocation(this,i,j);					
				}

				rGrid[i][j] = grid;								
			}
			
		}
		return rGrid;
	}

	/**
	 * Get the grid locations of this room
	 * 
	 * @return roomGrid
	 */
	public GridLocation[][] getRoomGrid(){
		return roomGrid;
	}
	
	
	public void setRoomGrid(GridLocation[][] grid){
		roomGrid = grid;
	}

	/**
	 * Get the name of this room.
	 * @return
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * Set the name of this room
	 * 
	 * @param name
	 */
	public void setName(String name){
		this.name = name;
	}	
	
	/**
	 * Get the description of this room.
	 * @return String
	 */
	public String getDescription(){
		return description;
	}
	
	/**
	 * Sets the description of the room
	 * 		
	 * @param description
	 */
	public void setDescription(String d){
		description = d;
	}
	                                     
	/**
	 * Sets the level of the room
	 * 
	 * @param level
	 */
	public void setLevel(Level l){
		level = l;
	}

	/**
	 * Get the level that this room is on.
	 * @return Level
	 */
	public Level getLevel(){
		return level;
	}

	/**
	 * Returns the next grid location based on the player's current grid location.
	 * The next grid location is one grid location away from the player's current grid location.
	 * @param player the player to find the next grid location for.
	 * @return GridLocation : the next grid location.
	 */
	public GridLocation moveNextGridLocation(AnimateObject object){
		Direction dir = null;
		int row = 0;
		int col = 0;
		
		if(object instanceof Player){
			Player player = (Player)object;
			dir = player.getDirectionFacing();
			row = player.getGridLocation().getRowIndex();
			col = player.getGridLocation().getColIndex();
		}
		else if(object instanceof NPC){
			NPC npc = (NPC)object;
			dir = npc.getDirectionFacing();
			row = npc.getGridLocation().getRowIndex();
			col = npc.getGridLocation().getColIndex();
		}

		GridLocation nextLocation = null;

		switch (dir){
		case WEST : 
			nextLocation = roomGrid[row-1][col];
			
			break;
		case EAST : 
			nextLocation = roomGrid[row+1][col];
			
			break;
		case SOUTH : 
			nextLocation = roomGrid[row][col+1];
			
			break;
		case NORTH : 
			nextLocation = roomGrid[row][col-1];
			
			break;
		}
		
		
		for(GameObject gO : nextLocation.getObjects()){
				if(gO instanceof BoundaryObject){
					// return the grid location the player is currently on 
					// because a player cannot move onto a grid location containing a BoundaryObject.
					return object.getGridLocation(); 
				}
			}
	

		return nextLocation;

	}
	
	/**
	 * Examines the grid location in front of the player for a game object.
	 * @param player
	 * @return
	 */
	public GridLocation examineGridLocation(Player player){

		Direction dir = player.getDirectionFacing();
		int row = player.getGridLocation().getRowIndex();
		int col = player.getGridLocation().getColIndex();

		GridLocation nextLocation = null;

		switch (dir){
		case WEST : // NORTH
			nextLocation = roomGrid[row-1][col];
			
			break;
		case EAST : // SOUTH
			nextLocation = roomGrid[row+1][col];
			
			break;
		case SOUTH : // EAST
			nextLocation = roomGrid[row][col+1];
			
			break;
		case NORTH : //WEST
			nextLocation = roomGrid[row][col-1];
			
			break;
		}

		return nextLocation;

	}
	

	/**
	 * Returns the view of the room based on a certain direction.
	 * @param directionFacing the direction your facing.
	 * @return a new 2D grid location array with the direction of view.
	 */
	public GridLocation[][] getRoomView(Direction directionFacing){

		GridLocation[][] newGrid = new GridLocation[ROWS][COLS];
		int newGridRow = 0;
		int newGridCol = 0;

		switch (directionFacing){

		case NORTH :
			
			return roomGrid; //default layout of room.
			
		case WEST :
			
			newGridRow = 0;
			newGridCol = 0;
			for(int i = COLS - 1 ; i >= 0 ; i--){
				for(int j = 0 ; j < ROWS ; j++){
					newGrid[newGridRow][newGridCol] = roomGrid[j][i];
					newGridCol++;
				}
				newGridCol = 0;
				newGridRow++;

			}
			break;
		case EAST:
			
			newGridRow = 0;
			newGridCol = 0;
			for(int i = 0 ; i < COLS ; i++){
				for(int j = ROWS - 1 ; j >= 0 ; j--){
					newGrid[newGridRow][newGridCol] = roomGrid[j][i];
					newGridCol++;
				}
				newGridCol = 0;
				newGridRow++;

			}
			break;		
		case SOUTH :
			newGridRow = 0;
			newGridCol = 0;
			for(int i = ROWS - 1 ; i >= 0 ; i--){
				for(int j = COLS - 1 ; j >= 0 ; j--){
					newGrid[newGridRow][newGridCol] = roomGrid[i][j];
					newGridCol++;
				}
				newGridCol = 0;
				newGridRow++;

			}
			break;		

		}

		return newGrid;
	}


	/**
	 * Get the Id of this room
	 * 
	 * @return id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Sets the Id of this room
	 * @param id
	 */
	public void setId(int id){
		this.id = id;
	}
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object other){
		
		if(other == null){
			return false;
		}
		
		if(other.getClass() != Room.class){
			return false;
		}
		
		Room room = (Room) other;
		return id == room.id;
		
	}




	public String toXML() {
		XMLFormatter x = new XMLFormatter();
		
		x.appendTag("gameworld.Room");

		x.appendTag("name");
		x.append(name);
		x.closeTag();

		x.appendTag("description");
		x.append(description);
		x.closeTag();
		
		x.appendTag("id");
		x.append("" + id);
		x.closeTag();
		
		x.append(level.toXML());
		
		//append every GridLocation
		for(GridLocation[] row : roomGrid){
			for (GridLocation g : row){
				if (!g.isEmpty())
					x.append(g.toXML());
			}
		}
		
		x.closeTag();
		
		return x.getXML();
	}

	public static Room fromXML(XMLObject o) {
		//public Room(String name, String description, Level level){
		
		String name = o.getNextData();
		String description = o.getNextData();
		Integer id = new Integer(o.getNextData());
		
		XMLObject levelXML = o.getNextObject();
		Level level = Level.fromXML(levelXML);
		
		Room room = new Room(name, description, level, id);
		
		System.out.println("GRIDLOCATION  == " + o.peek().getTag());
		
		while(o.peek().getTag().equals("gameworld.GridLocation")){
			
			System.out.println("TURE");
			XMLObject cell = o.getNextObject();
			GridLocation g = (GridLocation) cell.getObject();
			
			
			
		}
		
		return room;
	}


}
