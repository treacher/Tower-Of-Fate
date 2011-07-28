package gameworld;

/**
 * Special types of items that can be used for an effect to help
 * the player in the game
 * @author Damian Kaye
 *
 */

public interface UsableItem {
	
	/**
	 * 
	 * Use this item for a beneficial effect to the player
	 * 
	 * @param int the index of the usable item in the players back pack
	 * @return String representing details about the use effect
	 */
	public String useEffect(int backpackIndex);

}
