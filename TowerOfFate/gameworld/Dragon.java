package gameworld;

/**
 * Class representing the special dragon boss monster
 * @author Damian Kaye
 *
 */

public class Dragon extends Monster {

	public Dragon(GridLocation gl, int health, int strength, double dodgeChance, int goldLoot) {
		
		super(gl, "Draco the Orb Keeper" , "The mastermind of the Tower of Fate", health, strength, dodgeChance, goldLoot);
		
	}


	public String attack(Player player) {
		
		if(!dodge()){
			if(criticalStrike()){
				player.setCurrentHealth(player.getCurrentHealth() - ((getStrength()*2)+ 20));
				return  getName() + " critically hit " + player.getName() + " for " + getStrength() * 2 + " also dealing an additional 20 special damage";
			}
			else{
				player.setCurrentHealth(player.getCurrentHealth() - (getStrength() + 20));
				return getName() + " hit " + player.getName() + " for " + getStrength() + " also dealing an additional 20 special damage";
			}
		}
		else{
			return player.getName() + "dodges the attack by " + getName();
		}
	}
	
	public String toXML(){
		return "<object class=\"gameworld.Dragon\"> " +
				"\n\t<int>"+getMaxHealth()+"</int>" +
						"\n\t<int>"+getStrength()+"</int>" +
								"\n\t<double>"+getDodgeChance()+"</double>" +
										"\n\t<int>"+getGoldLoot()+"</int>" +
												"\n</object>";
	}

	
}
