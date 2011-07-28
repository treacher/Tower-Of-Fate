package gameworld;

/**
 * This class represents an InanimateObject. 
 * An abstract concept that represents all inanimate type objects.
 * 
 * @author Calvin Kaye
 *
 */
public abstract class InanimateObject extends GameObject {
	
	/**
	 * Build the state of an InanimateObject.
	 * @param name the name of the InanimateObject.
	 * @param description the description of the InanimateObject.
	 */
	public InanimateObject(String name , String description){
		
		super(name, description);
		
	}
	
}