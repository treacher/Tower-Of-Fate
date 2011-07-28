package gameworld;

/**
 * This class represents an Item. An abstract concept that represents all items.
 * @author Calvin Kaye
 *
 */
public abstract class Item extends InanimateObject {

	private boolean unique;
	private ContainerObject container;
	
	/**
	 * Build the state of an Item.
	 * @param name the name of the item.
	 * @param description the description of the item.
	 * @param unique is the item unique or not. Can you have more than one of these.
	 * If an item is unique you can only have one of them.
	 */
	public Item(String name , String description, boolean unique){
		
		super(name , description);
		this.unique = unique;
	
	}
	
	
	
	/**
	 * Returns if the item is unique or not.
	 * @return boolean 
	 */
	public boolean isUnique(){
		return unique;
	}
	
	/**
	 * Get the container this item is in.
	 * @return the container the item is in.
	 * Returns null if the item is not in a container.
	 */
	public ContainerObject getContainer(){
		return container;
		// return null if item is not in container.
	}
	
	/**
	 * Set the item's container.
	 * @param container the container to put the item in or
	 * if container is null , it means the item is now not in a container.
	 */
	public void setContainer(ContainerObject container){
		this.container = container;
	}
	
	
}
