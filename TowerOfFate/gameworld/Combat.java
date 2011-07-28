package gameworld;

public class Combat {
	
	/**
	 * A fight to the death between a player and a monster. 
	 * 
	 * @param player the player who's fighting the monster.
	 * @param monster the monster who's fighting the player.
	 * @return the CombatObject that wins the fight.
	 */
	public static CombatObject Fight(Player player , Monster monster){
		
		while(true){
			
			player.attack(monster);
			monster.attack(player);
			
			if(player.isDead()){
				return monster;
			}
			
			if(monster.isDead()){
				return player;
			}
			
		}
		
		
	}
	
}
