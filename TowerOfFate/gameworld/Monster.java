package gameworld;

/**
 * Class representing all monsters in the game
 * 
 * @author Damian Kaye
 *
 */

public abstract class Monster extends CombatObject {
	
	private int goldLoot;
	
	private int experience = 0;
	
	
	
	public Monster(GridLocation gl, String name, String description, int health, int strength, double dodgeChance, int goldLoot) {
		
		super(gl, name, description, health, strength, dodgeChance);
		this.goldLoot = goldLoot;
		experience = 50 * getGridLocation().getRoom().getLevel().getLevelNumber();
	}
	
	
	/**
	 * Gets the experience dropped by the monster
	 * @return experience dropped
	 */
	
	public int getExperience(){
		return experience;
	}

	/**
	 * An attack that deals damage to a player
	 * 
	 * @param Player the player to attack
	 * @return String, details about the attack made
	 */
	public abstract String attack(Player player);
	
	
	/**
	 * Get the gold this monster holds.
	 * @return int : gold amount.
	 */
	public int getGoldLoot(){
		return goldLoot;
	}
	
	/*
	 * (non-Javadoc)
	 * @see gameworld.CombatObject#isDead()
	 */
	public boolean isDead() {
		
		if(getCurrentHealth() <= 0){
			return true;
		}
		return false;
	}
		
}
