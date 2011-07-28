package gameworld;

import java.util.List;

/**
 * Class representing the engineer NPC that makes explosives if given the right items.
 * @author Damian Kaye
 *
 */

public class Engineer extends NPC {

	
	public static final int FEE = 50;
	
	public Engineer( GridLocation gl) {
		
		super( "Fizzlespark ", gl, "Constructs explosives for a price");
		
	}

	


	public boolean checkRequirements(Player player) {
		
		List<Item> bp = player.getBackpack().getContainer();
		
		int count = 0;
		for(Item i : bp){
			if(i.getClass() == GunpowderPouch.class || i.getClass() == Fuse.class ){
				count++;
			}
		}
		if(count == 2){
			return true;
		}
		else{
			return false;
		}
	
	}

	
	/**
	 * Makes a trade with a player if the player has the right items. (gunpowder + fuse)
	 * An explosive will be created if player has all items.
	 * @param The player to trade with.
	 * @return A string representing details about the trade
	 */
	public String trade(Player player) {
		
			List<Item> bp = player.getBackpack().getContainer();
		
			Explosive exp = new Explosive();
			if(checkRequirements(player)){
				if(!player.checkFullBackpack()){
					if(player.canPay(FEE)){
						player.changeGoldAmount(FEE);
						bp.add(exp);
						exp.setContainer(player.getBackpack());
						
						int i = 0;
						while(i < bp.size()){		
							Item it = bp.get(i);
							if(it.getClass() == Fuse.class || it.getClass() == GunpowderPouch.class){
								bp.remove(i);
								it.setContainer(null);								
							}		
							else{
								i++;
							}
						}
						
						return "You receive loot " + exp.getName();
					}
					else{
						return "You need more gold for the crafting of the explosive";
					}
				}
				else{
					return "You are over burdened";
				}
			}
			else{
				return "You do not have the right requirements for this trade";
			}
	}

	public String interactEffect(Player p) {
		
		return trade(p);
	}
}
