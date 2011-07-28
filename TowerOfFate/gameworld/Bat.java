package gameworld;

/**
 * Class representing the bat monster
 * @author Damian Kaye
 *
 */

public class Bat extends Monster{
	
	public Bat(GridLocation gl, int health, int strength, double dodgeChance, int goldLoot) {
		
		super(gl, "Vicious Bat" , "A large wild bat", health, strength, dodgeChance, goldLoot);
		
	}
	
	public String attack(Player player) {
		
		if(!dodge()){
			if(criticalStrike()){
				player.setCurrentHealth(player.getCurrentHealth() - getStrength()*2);
				return  getName() + " critically hit " + player.getName() + " for " + getStrength() * 2;
			}
			else{
				player.setCurrentHealth(player.getCurrentHealth() - getStrength());
				return getName() + " hit " + player.getName() + " for " + getStrength();
			}
		}
		else{
			return  player.getName() + " dodges the attack by " + getName();
		}
	
	}


}
