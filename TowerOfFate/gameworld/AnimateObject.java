package gameworld;

import java.io.Serializable;

public class AnimateObject extends GameObject {
	
	private transient GridLocation gridLocation;
	
	
	public AnimateObject(GridLocation gridLocation, String name , String description){
		super(name , description);
		this.gridLocation = gridLocation;
	}
	
	
	/**
	 * Get the grid location this game object is in.
	 * @return the grid location this object is in.
	 */
	public GridLocation getGridLocation(){
		
		return gridLocation;
		
	}
	
	/**
	 * Set the grid location of this game object.
	 * @param gl the new grid location of this object.
	 */
	public void setGridLocation(GridLocation gl){
		
		this.gridLocation = gl;
		
	}


	
	public String toXML() {		
		return null;
	}
	

}
