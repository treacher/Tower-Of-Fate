package gameworld;

import java.io.Serializable;

import dataStorage.Element;
import dataStorage.XMLFormatter;

/**
 * This class represents a GameObject. An abstract concept that represents all game objects.
 * @author Calvin Kaye
 *
 */
public abstract class GameObject implements Serializable {
	
	private String name;
	private transient String description;
	
	
	/**
	 * Build the state of a GameObject.
	 * @param name the name of this game object.
	 */
	public GameObject(String name, String description){
		
		this.name = name;
		this.description = description;
		
	}
	

	/**
	 * Get the description of this game object.
	 * @return String description.
	 */
	public String getDescription(){
		return description;
	}
	
	
	/**
	 * Set the description of this game object.
	 * @return String description.
	 */
	public void setDescription(String d){
		description = d;
	}
	
	
	/**
	 * Get the name of this game object.
	 * @return
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * Set the name of this game object.
	 * @return
	 */
	public void setName(String name){
		this.name = name;
	}
	
	public boolean equals(Object o){
		if(o == null) return false;
		
		return (o.getClass() == getClass());
	
			
	}
	public abstract String toXML();
	//public abstract Object fromXML();
	
	public String toString(){
		return getClass().toString();
	}

}
