package network;

import gameworld.*;
import gameworld.Player.Direction;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * This class represents the Server side of the connection.
 * @author kayecalv id: 300155433
 *
 */
public class ServerConnection extends Thread{
	
	private TowerOfFate game;
	private Socket socket;
	private int uid;
	private Player player;
	private DataInputStream input;
	private ObjectOutputStream output;
	private ArrayBlockingQueue<Packet> packets = new ArrayBlockingQueue<Packet>(10000); // packet queuing system.
	private GameServer server;
	
	/**
	 * Construct a new ServerConnection object.
	 * @param uid the uid that will be given to the player.
	 * @param game the game this server connection is working on.
	 * @param socket the socket that represents the server end of the connection.
	 * @param server the server this ServerConnection is part of.
	 */
	public ServerConnection(int uid , TowerOfFate game , Socket socket, GameServer server){
		
		this.uid = uid;
		this.game = game;
		this.socket = socket;
		this.server = server;
		
	}
	
	public void run(){
		
		try {
			input = new DataInputStream(socket.getInputStream()); // reads primitives.
			output = new ObjectOutputStream(socket.getOutputStream()); // writes objects.
			
			output.writeBoolean(server.gameStarted); // Tell the client whether the game has started or not.
			output.flush();
			
			if(!server.gameStarted){// if the game has not started.
				// Tell the client how many players are in this game.
				output.writeObject(new Packet(NetworkProtocol.NUMBER_OF_PLAYERS , new Integer(server.NUM_OF_PLAYERS)));
				
				Packet p1; 
				while(1 == 1){ // game count down. 5 ... 4 ... 3 ... 2 ... 1 ...
					if(!packets.isEmpty()){
						p1 = packets.poll();
						if(p1.PROTOCOL == NetworkProtocol.START_GAME){ 
							output.writeObject(p1);
							break;
						}
						else{
							output.writeObject(p1);
						}
					}
				}
			}
			
			//get players name
			int length = input.readInt();
			String name = "";
			for(int i = 0 ; i< length ; i++){
				name += input.readChar();
			}
			
			if(!server.gameStarted){ // Create a new player for the game.
				player = new Player(game.STARTING_LOCATION , name, "Hi I'm 5",game.STARTING_ROOM, uid);
				TowerOfFate.players.add(player);
				game.STARTING_ROOM.roomGrid[game.STARTING_LOCATION.getRowIndex()][game.STARTING_LOCATION.getColIndex()].getObjects().add(player);
				output.writeBoolean(true);
				output.flush();
			}
			else{
				if(server.serverFull()){ // Reject the player because the server is full.
					output.writeBoolean(false);
					output.writeObject(new Packet(NetworkProtocol.DISPLAY_DIALOG , "Server is full"));
					output.flush();
					return;
				}
				else if(server.findPlayer(name) == null){ // Invalid player tried to reconnect.
					output.writeBoolean(false);
					output.writeObject(new Packet(NetworkProtocol.DISPLAY_DIALOG , "Failed to reconnect, enter correct username."));
					output.flush();
					return;
				}
				else if(server.findPlayer(name) != null){ // The correct player has reconnected.
					player = server.findPlayer(name);
					uid = player.UID;
					GameServer.numOfPlayers++;
					GameServer.disconnectedPlayers.remove(player);
					player.getCurrentRoom().roomGrid[player.getGridLocation().getRowIndex()][player.getGridLocation().getColIndex()].getObjects().add(player);
					GameServer.updateClients(new Packet(NetworkProtocol.GRIDLOCATION_UPDATE , player.getGridLocation()) , this, false);
					GameServer.updateClients(new Packet(NetworkProtocol.TEXT_PACKET , player.getName() + " has reconnected."), this , false);
					output.writeBoolean(true);
					output.flush();
					try {
						sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					GameServer.connections.add(this);
				}
			}
			
			output.writeObject(player.getCurrentRoom()); // send the client a room to render
			output.writeObject(player); // send the client the player.
			output.reset(); // need to reset the state of the ObjectOutputStream, because the state of the objects written will change.
			
			boolean exit = false;
			while(!exit) {	// infinite loop to handle client messages and to broadcast server messages to client.
				
				if(!packets.isEmpty()){ // update client.
					Packet p = packets.poll(); 
					if(p.object.getClass() == GridLocation.class){
						synchronized (p.object){
							output.writeObject(p);
							output.reset();
						}
					}
					else{
						output.writeObject(p);
						output.reset();
					}
				}
				
				if(!(input.available() == 0)){ // read input from client.
					int command = input.readInt();
					
					if(command == NetworkProtocol.TEXT_PACKET){ // Chat message
						String message = "";
						int length1 = input.readInt();
						for(int i = 0 ; i< length1 ; i++){
							message += input.readChar();
						}
						GameServer.updateClients(new Packet(NetworkProtocol.TEXT_PACKET , message)  , this, false);
					}
					else if(command == NetworkProtocol.PLAYER_ATTACKED){ // Player has been attacked by monster
						
						int currentHealth = input.readInt();
						player.setCurrentHealth(currentHealth);
						output.writeObject(new Packet(NetworkProtocol.PLAYER_ATTACKED , new Integer(currentHealth)));
						
					}
					// Monster has died so update the client's renderer to the world view again.
					else if(command == NetworkProtocol.MONSTER_DIED){ 
						int xp = input.readInt();
						int goldLoot = input.readInt();
						boolean dracoKilled = input.readBoolean();
						if(dracoKilled){
							player.killDraco();
						}
						player.loot(xp , goldLoot); // The rewards of killing the monster.
						player.battleOver();
						output.writeObject(new Packet(NetworkProtocol.MONSTER_DIED , player));
						output.writeObject(new Packet(NetworkProtocol.BATTLE_OVER, player.getCurrentRoom()));
						output.reset();
						
					}
					// Monster has died so revive the player and port him back to game starting location.
					else if(command == NetworkProtocol.PLAYER_DIED){ 
						player.battleOver();
						player.setCurrentHealth(player.getMaxHealth());
						output.writeObject(new Packet(NetworkProtocol.PLAYER_REVIVED , new Integer(player.getCurrentHealth())));
						output.writeObject(new Packet(NetworkProtocol.PLAYER_LEVEL_UPDATE , new Integer(1)));
						server.portPlayer(this,true, player.getGridLocation());
						
					}
					// Trading an item.
					else if(command ==NetworkProtocol.BUY_ITEM){
						String message1 = "";
						int length1 = input.readInt();
						for(int i = 0 ; i< length1 ; i++){
							message1 += input.readChar();
						}
						
						int oldGold = player.getGoldAmount();
						VendorItem vi = null;
						if(message1.equals("Machete")){
							vi = new Machete();
						}
						else if(message1.equals("Scroll of Stamina")){
							vi = new ScrollOfStamina();
						}
						else if(message1.equals("Scroll of Strength")){
							vi = new ScrollOfStrength();
						}
						else if(message1.equals("Health Potion")){
							vi = new HealthPotion();
						}
						
						String result = GeneralSuppliesVendor.trade(player, vi);
						output.writeObject(new Packet(NetworkProtocol.TEXT_PACKET , result));
						
						if(oldGold != player.getGoldAmount()){ // The player has bought the item.
							vi.setContainer(player.getBackpack());
							output.writeObject(new Packet(NetworkProtocol.BUY_ITEM , new Integer(vi.getGoldPrice())));
							output.writeObject(new Packet(NetworkProtocol.PICKUPITEM, player.getBackpack().getContainer()));
							output.reset();
						}
					}
					// Trade is over so update the client with the world view renderer.
					else if(command == NetworkProtocol.TRADE_OVER){
						player.tradeOver();
						output.writeObject(new Packet(NetworkProtocol.TRADE_OVER , player.getCurrentRoom()));
						output.reset();
					}
					
					switch (command){
						
						// The player has initiated a move up command.
						case NetworkProtocol.MOVE_UP :
							
							GridLocation oldGL = player.getGridLocation();
							Monster mon = player.move();
							GridLocation newGL = player.getGridLocation();
							if(mon != null && !player.hasKilledDraco()){
								player.fightingBattle();
								output.writeObject(new Packet(NetworkProtocol.MONSTER , mon));
							}
							GameServer.updateClients(new Packet(NetworkProtocol.GRIDLOCATION_NO_UPDATE , oldGL)  , this, true);
							GameServer.updateClients(new Packet(NetworkProtocol.GRIDLOCATION_UPDATE , newGL) , this , true);
							break;
						// The player has initiated a turn left command.
						case NetworkProtocol.TURN_LEFT :
							player.turnLeft();
							if(!player.inBattle() && !player.inTrade()){
								output.writeObject(new Packet(NetworkProtocol.TURN_LEFT , player.getDirectionFacing()));
							}
							GameServer.updateClients(new Packet(NetworkProtocol.GRIDLOCATION_UPDATE ,player.getGridLocation()) , this, false);
							break;
						// The player has initiated a turn right command.
						case NetworkProtocol.TURN_RIGHT :
							player.turnRight();
							if(!player.inBattle() && !player.inTrade()){
								output.writeObject(new Packet(NetworkProtocol.TURN_RIGHT , player.getDirectionFacing()));
							}
							GameServer.updateClients(new Packet(NetworkProtocol.GRIDLOCATION_UPDATE ,player.getGridLocation()) , this , false);
							break;
						// The player has initiated a pick up command.
						case NetworkProtocol.PICKUP :
							Item i = player.canPickUp();
							
							if(i != null){	
									if(i instanceof Key){
										Key k = (Key) i;
										String str = player.pickUp(k);
										output.writeObject(new Packet(NetworkProtocol.TEXT_PACKET , str));
										output.writeObject(new Packet(NetworkProtocol.PICKUPKEY , player.getKeyring().getContainer()));
										output.reset();
									}
									else{
										String str = player.pickUp(i);
										output.writeObject(new Packet(NetworkProtocol.TEXT_PACKET , str));
										output.writeObject(new Packet(NetworkProtocol.PICKUPITEM , player.getBackpack().getContainer()));
										output.reset();
										GameServer.updateClients(new Packet(NetworkProtocol.GRIDLOCATION_UPDATE ,player.getGridLocation()) , this, true);
									}
								}
							break;
						// The player has initiated an interact command. A player can only interact with interactive objects in the game world.	
						case NetworkProtocol.INTERACT:
							
							GridLocation oldGrid = player.getGridLocation();
							int oldGold = player.getGoldAmount();
							Room r1 = player.getCurrentRoom();
							InteractiveObject in = player.canInteract();
							
								if(in != null){
									String s = player.interact(in);
									output.writeObject(new Packet(NetworkProtocol.TEXT_PACKET , s));		
									if(in instanceof Sarcophagus){
										Sarcophagus sarco = (Sarcophagus) in;
										if(!sarco.getContainer().isEmpty()){
											if(sarco.getContainer().get(0) instanceof Key){
												output.writeObject(new Packet(NetworkProtocol.PICKUPKEY, player.getKeyring().getContainer()));
												output.reset();
											}
											else if(sarco.getContainer().get(0).isUnique()){
												output.writeObject(new Packet(NetworkProtocol.PICKUPITEM, player.getBackpack().getContainer()));
												output.reset();
											}
											else{
												sarco.getContainer().remove(0);
												output.writeObject(new Packet(NetworkProtocol.PICKUPITEM, player.getBackpack().getContainer()));
												output.reset();
											}
										}
									}
									else if(in instanceof Orb){
										
										GameServer.gameOver(this);
										
									}
									else if(in instanceof NPC){
										if(in.getClass() == KeySmith.class){
											output.writeObject(new Packet(NetworkProtocol.PICKUPKEY, player.getKeyring().getContainer()));
											output.writeObject(new Packet(NetworkProtocol.PICKUPITEM, player.getBackpack().getContainer()));
											if( oldGold != player.getGoldAmount()){
												output.writeObject(new Packet(NetworkProtocol.BUY_ITEM , new Integer(KeySmith.FEE)));
											}
											output.reset();
										}
										else if(in.getClass() == Engineer.class){
											output.writeObject(new Packet(NetworkProtocol.PICKUPITEM, player.getBackpack().getContainer()));
											if( oldGold != player.getGoldAmount()){
												output.writeObject(new Packet(NetworkProtocol.BUY_ITEM , new Integer(Engineer.FEE)));
											}
											output.reset();
										}
										else if(in.getClass() == GeneralSuppliesVendor.class){
											player.trading();
											output.writeObject( new Packet(NetworkProtocol.GENERAL_TRADE , null));
										}
									}
									else if( in instanceof Bookcase){
										Bookcase bk = (Bookcase) in;
										if(!bk.getContainer().isEmpty()){
											if(bk.getContainer().get(0) instanceof Key){
												output.writeObject(new Packet(NetworkProtocol.PICKUPKEY, player.getKeyring().getContainer()));
												output.reset();
											}
											else if(bk.getContainer().get(0).isUnique()){
												output.writeObject(new Packet(NetworkProtocol.PICKUPITEM, player.getBackpack().getContainer()));
												output.reset();
											}
											else{
												bk.getContainer().remove(0);
												output.writeObject(new Packet(NetworkProtocol.PICKUPITEM, player.getBackpack().getContainer()));
												output.reset();
											}
										}
											
									}										
									else if(in instanceof Skeleton){
										Skeleton skele = (Skeleton) in;
										if(!skele.getContainer().isEmpty()){
											if(skele.getContainer().get(0) instanceof Key){
												output.writeObject(new Packet(NetworkProtocol.PICKUPKEY, player.getKeyring().getContainer()));
												output.reset();
											}
											else if(skele.getContainer().get(0).isUnique()){
												output.writeObject(new Packet(NetworkProtocol.PICKUPITEM, player.getBackpack().getContainer()));
												output.reset();
											}
											else{
												skele.getContainer().remove(0);
												output.writeObject(new Packet(NetworkProtocol.PICKUPITEM, player.getBackpack().getContainer()));
												output.reset();
											}
										}
									}
									else if(in.getClass() == Door.class){
										output.writeObject(new Packet(NetworkProtocol.ROOM_OBJECT , player.getCurrentRoom()));
										output.reset();
										GameServer.updateClients(new Packet(NetworkProtocol.GRIDLOCATION_ROOM_UPDATE , oldGrid) , this, false);
										GameServer.updateClients(new Packet(NetworkProtocol.GRIDLOCATION_UPDATE , player.getGridLocation()) , this, false);
									}
									else if(in instanceof LockedDoor){
										if(!r1.equals(player.getCurrentRoom())){
											output.writeObject(new Packet(NetworkProtocol.ROOM_OBJECT , player.getCurrentRoom()));
											output.reset();
											GameServer.updateClients(new Packet(NetworkProtocol.GRIDLOCATION_ROOM_UPDATE , oldGrid) , this, false);
											GameServer.updateClients(new Packet(NetworkProtocol.GRIDLOCATION_UPDATE , player.getGridLocation()) , this, false);
											if(in instanceof LevelDoor){
												output.writeObject(new Packet(NetworkProtocol.PLAYER_LEVEL_UPDATE , new Integer(player.getLevel())));
											}
										}
									}
								}
							break;
						// The player has initiated an inspect command.	
						case NetworkProtocol.INSPECT:
							GameObject g = player.canInspect();
							if(g != null){
									String st = player.inspect(g);
									output.writeObject(new Packet(NetworkProtocol.TEXT_PACKET, st));
							}
							break;
							
						// The player has initiated a drop item command. Can only drop Vendor items in your backpack.
						case NetworkProtocol.DROP:
							int index1 = input.readInt();
							String s4 = player.drop(index1);
							output.writeObject(new Packet(NetworkProtocol.TEXT_PACKET, s4));
							output.writeObject(new Packet(NetworkProtocol.DROP , player.getBackpack().getContainer()));
							output.reset();
							GameServer.updateClients(new Packet(NetworkProtocol.GRIDLOCATION_UPDATE , player.getGridLocation()), this, true);
							
							break;
						
						// The player has initiated an inspect command. Inspecting items in your backpack or key ring.
						case NetworkProtocol.CONTAINER_INSPECT:
							int index2 = input.readInt();
							boolean isKey1 = input.readBoolean();
							Item item2;
							String s1;
							if(!isKey1){
								 item2 = player.getBackpack().getContainer().get(index2);
								 s1 = item2.getDescription();
								 output.writeObject(new Packet(NetworkProtocol.TEXT_PACKET,s1));
							}
							else{
								item2 = player.getKeyring().getContainer().get(index2);
								 s1 = item2.getDescription();
								 output.writeObject(new Packet(NetworkProtocol.TEXT_PACKET,s1));	
							}
																																									
							break;
						// The player has initiated a destroy item command. Can only destroy items in your backpack and key ring.	
						case NetworkProtocol.DESTROY:
							int index3 = input.readInt();														
							String s2;
							boolean isKey2 = input.readBoolean();
							if(!isKey2){								
								s2 = player.destroy(index3, true);
								output.writeObject(new Packet(NetworkProtocol.TEXT_PACKET,s2));	
								output.writeObject(new Packet(NetworkProtocol.DESTROY_ITEM , player.getBackpack().getContainer()));
								output.reset();
							}
							else{								
								s2 = player.destroy(index3, false);
								output.writeObject(new Packet(NetworkProtocol.TEXT_PACKET,s2));	
								output.writeObject(new Packet(NetworkProtocol.DESTROY_KEY , player.getKeyring().getContainer()));
								output.reset();
							}
							
							break;
						// The player has initiated a use item command.You can only use items that are usable in your backpack or key ring.	
						case NetworkProtocol.USE:
							try{
							int index4 = input.readInt();
							UsableItem item4 = (UsableItem) player.getBackpack().getContainer().get(index4);
							String s3 = item4.useEffect(index4);
							output.writeObject(new Packet(NetworkProtocol.TEXT_PACKET,s3));			
							output.writeObject(new Packet(NetworkProtocol.USE_ITEM , player.getBackpack().getContainer()));	
							output.writeObject(new Packet(NetworkProtocol.PLAYER_OBJECT, player));
							output.reset();
							}catch(IndexOutOfBoundsException e){
								// some thing went wrong in communication so just ignore it!
							}
							break;
							
						default :
							break;
						}
				}
			}
			
			
		}catch(IOException e) {
			System.err.println("PLAYER " + uid + " DISCONNECTED");
			GameServer.disconnectPlayer(this); // disconnect the player.
		} 
		finally {
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			} 
		}
		
	}
	
	/**
	 * Add a packet to the packet queuing system.
	 * @param packet 
	 */
	public void addPacket(Packet packet){
		packets.add(packet);
	}
	
	/**
	 * Get the uid of the player of this server connection.
	 * @return
	 */
	public int getUID(){
		return uid;
	}
	
	/**
	 * Get the player of this server connection.
	 * @return
	 */
	public Player getPlayer(){
		return player;
	}
	
}
