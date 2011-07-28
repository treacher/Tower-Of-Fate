package gameworld;

/**
 * Class representing the spider monster
 * @author Damian Kaye
 *
 */

public class Spider extends Monster{

	public Spider(GridLocation gl, int health, int strength, double dodgeChance, int goldLoot) {
	
		super(gl, "Large Spider", "A large venomous spider", health, strength, dodgeChance, goldLoot);
		
	}
	
	

	public String attack(Player player) {
		
		if(!dodge()){
			if(criticalStrike()){
				player.setCurrentHealth(player.getCurrentHealth() - ((getStrength()*2)+5));
				return getName() + " critically hit " + player.getName() + " for " + getStrength() * 2  + " also dealing an additional 5 poison damage";
			}
			else{
				player.setCurrentHealth(player.getCurrentHealth() - (getStrength() + 5));
				return getName() + " hit " + player.getName() + " for " + getStrength() + " also dealing an additional 5 poison damage";
			}
		}
		else{
			return  player.getName() + " dodges the attack by " + getName();
		}
		
	}
		
	public String toXML(){
		return "<object class=\"gameworld.Spider\"> " +
				"\n\t<int>"+getMaxHealth()+"</int>" +
						"\n\t<int>"+getStrength()+"</int>" +
								"\n\t<double>"+getDodgeChance()+"</double>" +
										"\n\t<int>"+getGoldLoot()+"</int>" +
												"\n</object>";
	}

}
