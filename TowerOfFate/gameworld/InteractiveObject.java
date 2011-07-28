package gameworld;

/**
 * This interface represents all objects that are interactive.
 * @author Calvin Kaye
 *
 */
public interface InteractiveObject {

	/**
	 * By interacting with this object it triggers an interact effect.
	 * @param p the player who is interacting with this object.
	 * @return a string outcome of the interact effect.
	 */
	public String interactEffect(Player p);
	
	
}
