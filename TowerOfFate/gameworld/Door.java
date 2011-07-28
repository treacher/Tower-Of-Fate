package gameworld;

import dataStorage.XMLFormatter;
import dataStorage.XMLObject;

/**
 * THis class represents a Door. A Door is an exit to a room and connects a room to another room.
 * @author Calvin Kaye
 *
 */
public class Door extends InanimateObject implements InteractiveObject, BoundaryObject {
	
	private Door connectingDoor; // The door this door connects to.
	private boolean isOpen;
	private GridLocation gridLocation;
	private int conDoorCol, conDoorRow, conDoorRoomID;
	
	
	/**
	 * Construct a new Door object.
	 * @param name the name of this door.
	 * @param description the description of this door.
	 * @param gl the grid location this door is on.
	 * @param isOpen is the door open or not.
	 * @param connectsTo the door this door connects to. A means of connecting rooms.
	 */
	public Door(String name , String description, GridLocation gl, boolean isOpen, Door connectsTo){
		
		super(name , description);
		this.gridLocation = gl;
		this.isOpen = isOpen;
		this.connectingDoor = connectsTo;
		
	}
	
	/**
	 * Returns the gridLocation the door is on
	 * @return GridLocation the door is on
	 */
	public GridLocation getGridLocation(){
		return gridLocation;
	}
	
	/**
	 * Sets the doors gridLocation
	 * @param grid the GridLocation to be set
	 */
	public void setGridLocation(GridLocation grid){
		gridLocation = grid;
	}
	
	
	/**
	 * Returns if the door is open or not.
	 * @return boolean.
	 */
	public boolean getIsOpen(){
		return isOpen;
	}
	
	/**
	 * sets the isOpen boolean for this door
	 * @param open
	 */
	public void setIsOpen(boolean open){
		isOpen = open;
	}
	
	/**
	 * Sets the door open.
	 */
	public void setOpen(){
		isOpen = true;
	}
	
	/**
	 * Get the door this door connects to. The connecting door is part of another room,
	 * so this is how this door connects two rooms together.
	 * @return
	 */
	public Door getConnectingDoor(){
		return connectingDoor;
	}
	
	public String interactEffect(Player p) {
		
			p.moveThrough(this);
			Room newRoom = p.getCurrentRoom();
			return "You walk into " +
			newRoom.getName();
	
	}
	
	/**
	 * Sets the door that this door connects to
	 * @param d , the door this door connects to
	 */
	public void setConnectingDoor(Door d) {
		connectingDoor = d;
		
	}

	@Override
	public String toXML() {
		// TODO Auto-generated method stub
		return null;
	}


	

	
}
