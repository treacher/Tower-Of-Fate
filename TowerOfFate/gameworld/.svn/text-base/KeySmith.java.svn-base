package gameworld;

import java.util.List;

/**
 * Class representing the keysmith NPC that makes special keys out of broken down key fragments.
 * @author Damian Kaye
 *
 */
public class KeySmith extends NPC{

	public static final int FEE = 50;
	
	public KeySmith(GridLocation gl) {
		
		super("KeySmith Grinder" , gl, "Repairs broken keys for a price.");
	
	}


	public boolean checkRequirements(Player player) {
		
		List<Item> bp = player.getBackpack().getContainer();
		
		int count = 0;
		for(Item i : bp){
			if(i.getClass() == MiddleKeyFragment.class || i.getClass() == BottomKeyFragment.class || i.getClass() == TopKeyFragment.class){
				count++;
			}
		}
		if(count == 3){
			return true;
		}
		else{ 
			return false;
		}
	}


	/**
	 * Makes a trade with a player if the play has the right items. (all three key fragments)
	 * A special forged key will be created if he has all the items.
	 * @param player, The player to trade with
	 * @return a string representing details about the trade
	 */
	public String trade(Player player){
		
		if(checkRequirements(player)){
			if(!player.getKeyring().isFull()){
					if(player.canPay(FEE)){
						player.changeGoldAmount(FEE);
						Key k = new Key("Forged Key" , "A key that opens the door to level 3.");
						List<Item> kr = player.getKeyring().getContainer();
						kr.add(k);
						k.setContainer(player.getKeyring());
						List<Item> bp = player.getBackpack().getContainer();
						int i = 0;
						while(i < bp.size()){		
							Item it = bp.get(i);
							if(it.getClass() == BottomKeyFragment.class || it.getClass() == TopKeyFragment.class || it.getClass() == MiddleKeyFragment.class){
								bp.remove(i);
								it.setContainer(null);								
							}
							else{
								i++;
							}
						}					
						return "You recieve loot " + k.getName();
					}
					else{
						return "You need more money to craft that key";
					}
				}
				else{
					return "you are over burdened";
				}
		}
		else{
			return "you do not have the right requirements for this trade";
		}
	}

	/*
	 * (non-Javadoc)
	 * @see gameworld.InteractiveObject#interactEffect(gameworld.Player)
	 */
	public String interactEffect(Player p) {
		
		return trade(p);
	
	}
	
}
