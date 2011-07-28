package gameworld;

import gameworld.Player.Direction;

import java.util.Random;

import network.GameServer;
import network.NetworkProtocol;
import network.Packet;
import dataStorage.XMLFormatter;

/**
 * Non player combat, a mutual person in the game that has services and items
 * that help you with the game when you trade with them.
 * @author Damian Kaye
 *
 */

public abstract class NPC extends AnimateObject implements InteractiveObject {

	
	private Direction directionFacing;
	public transient Thread thread;
	
	public NPC(String name , GridLocation gl, String description) {
		
		super(gl ,name , description);
		directionFacing = Direction.NORTH;
		thread = new WalkerThread();
		
	}

	
	/**
	 * To trade with particular NPCS you need to have requirements so they can fulfil
	 * their role. This method checks to see if you are able to trade with them.
	 * 
	 * @param The player to check if he meets the requirements.
	 * @return Boolean representing if the player did meet the requirements or not
	 */
	public abstract boolean checkRequirements(Player player);
	
	
	/**
	 * Turn the npc right.
	 */
	public void turnRight(){
		
		switch(directionFacing){
		case NORTH :
			directionFacing = Direction.EAST;
			break;
		case EAST :
			directionFacing = Direction.SOUTH;
			break;
		case SOUTH :
			directionFacing = Direction.WEST;
			break;
		case WEST :
			directionFacing = Direction.NORTH;
			break;
		}
		
		
		GameServer.updateClients(new Packet(NetworkProtocol.NPC_GRIDLOCATION_UPDATE , getGridLocation()) , null , true);
		

	}
	/**
	 * Moves the player
	 * @return
	 */
	public synchronized void move(){
		GridLocation oldG = getGridLocation();
		GridLocation nextGridLocation = getGridLocation().getRoom().moveNextGridLocation(this);
		
		if(getGridLocation().equals(nextGridLocation)){ 
		
		}
		else{ 
			getGridLocation().removeGameObject(this);
			nextGridLocation.addGameObject(this);
			setGridLocation(nextGridLocation);
		}
		GridLocation newG = getGridLocation();
		GameServer.updateClients(new Packet(NetworkProtocol.NPC_GRIDLOCATION_NO_UPDATE, oldG) , null , true);
		GameServer.updateClients(new Packet(NetworkProtocol.NPC_GRIDLOCATION_UPDATE , newG), null, true);
	}

	/**
	 * Gets the current direction
	 * @return
	 */
	public Direction getDirectionFacing() {
		return directionFacing;
	}
	/**
	 * THE OL WALKEER THREAD
	 * @author treachmich
	 *
	 */
	class WalkerThread extends Thread{
		/*
		 * (non-Javadoc)
		 * @see java.lang.Thread#run()
		 */
		public void run(){
			
			try{
				while(true){
					Random r = new Random();
					int randomNumber = r.nextInt(4);
					for(int i = 0 ; i< randomNumber ; i++){
						move();
						sleep(5000);
					}
					turnRight();
					sleep(1000);
					turnRight();
					sleep(1000);
				}
			}
			catch(InterruptedException e){
			
			}
		}
	}
	
	
}