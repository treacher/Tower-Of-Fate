package gameworld;


/**
 * 
 * Class representing the adder monster
 * @author Damian Kaye
 *
 */

public class Adder extends Monster {

	public Adder(GridLocation gl, int health, int strength, double dodgeChance, int goldLoot) {
		
		super(gl, "Death Adder" , "A poisonous adder" , health, strength, dodgeChance, goldLoot);
	
	}

	public String attack(Player player) {
		
		if(!dodge()){
			if(criticalStrike()){
				player.setCurrentHealth(player.getCurrentHealth() - ((getStrength()*2) + 10));
				return  getName() + " critically hit " + player.getName() + " for " + getStrength() * 2 + " also dealing an additional 10 venom damage";
			}
			else{
				player.setCurrentHealth(player.getCurrentHealth() - (getStrength() + 10));
				return getName() + " hit " + player.getName() + " for " + getStrength() + " also dealing an additional 10 venom damage";
			}
		}
		else{
			return  player.getName() + " dodges the attack by " + getName();
		}
		
	}
	public String toXML(){
		return "<object class=\"gameworld.Adder\"> " +
				"\n\t<int>"+getMaxHealth()+"</int>" +
						"\n\t<int>"+getStrength()+"</int>" +
								"\n\t<double>"+getDodgeChance()+"</double>" +
										"\n\t<int>"+getGoldLoot()+"</int>" +
												"\n</object>";
	}

	
}
