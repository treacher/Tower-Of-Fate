package network;


import gameworld.Backpack;
import gameworld.GameObject;
import gameworld.GridLocation;
import gameworld.Item;
import gameworld.Keyring;
import gameworld.Monster;
import gameworld.Player;
import gameworld.Room;
import gameworld.Player.Direction;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.List;

import javax.swing.JOptionPane;

import renderer.LoadingScreen;
import renderer.RoomRenderer3D;
import server.Protocol;
import userInterface.MainFrame;

/**
 * This class represents the Client side of the connection.
 * @author kayecalv id: 300155433
 *
 */
public class ClientConnection extends Thread implements KeyListener {
	
	private Socket socket;
	private DataOutputStream output;
	private ObjectInputStream input;
	private String name;
	private MainFrame mainFrame;
	private Room currentRoom;
	private Player player;
	private RoomRenderer3D renderer;
	
	/**
	 * Construct a new ClientConnection object.
	 * @param mainFrame the game frame of the client.
	 * @param addr the address of the server to connect to.
	 * @param name the name of the client's player.
	 */
	public ClientConnection(MainFrame mainFrame, String addr, String name){
		
		this.name = name;
		this.mainFrame = mainFrame;
		
		int port = 6112;
		
		try{
			socket = new Socket(addr,port);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(mainFrame, "There was a problem connecting to the server.", "Can not connect to Server", JOptionPane.INFORMATION_MESSAGE);
			
		}
		
	}
	
	public void run(){
		
		try {			
			output = new DataOutputStream(socket.getOutputStream()); // write primitives.
			input = new ObjectInputStream(socket.getInputStream()); // read objects.
			
			boolean gameStarted = input.readBoolean(); // has the game started or not.
			
			if(!gameStarted){

				Packet p = (Packet) input.readObject();
				Integer i1 = (Integer) p.object;
				int numOfPlayers = i1.intValue();
				
				// Show the loading screen to the client if the game has not started.
				LoadingScreen ls = new LoadingScreen(numOfPlayers); 
				mainFrame.setLoadingScreen(ls);
				mainFrame.add(ls);

				boolean start = false;
				while(!start){
					p = (Packet) input.readObject();
					if(p.PROTOCOL == NetworkProtocol.PLAYERS_JOINED){
						i1 = (Integer) p.object;
						ls.setPlayerCount(i1.intValue());
					}
					else if(p.PROTOCOL == NetworkProtocol.STARTING_COUNTDOWN){
						String string1 = (String) p.object;
						mainFrame.getLogPanel().getLogTextArea().appendLogText(string1);
					}
					else if(p.PROTOCOL == NetworkProtocol.START_GAME){
						break;
					}
				}

			}
			
			// write the name of the player to the server.
			output.writeInt(name.length());
			output.writeChars(name);
			
			boolean validClient = input.readBoolean();
			if(!validClient){ // client was invalid.
				Packet p = (Packet) input.readObject();
				String reason = (String) p.object;
				mainFrame.showDialog(reason);
				mainFrame.showSplashScreen(); // show the splash screen to the user.
				return;
			}
		
			currentRoom = (Room) input.readObject(); // room to display in the renderer.
			player = (Player) input.readObject(); // the player for this client.
			
			renderer = new RoomRenderer3D(player,currentRoom); 
			mainFrame.setRenderer(renderer);
		
			renderer.addKeyListener(this);
			renderer.requestFocusInWindow();
			mainFrame.updatePositionRoomField(currentRoom);
			mainFrame.updatePlayer(player);
			renderer.repaint();
			renderer.setVisible(true);
			mainFrame.getLogPanel().enableChatBox();
			
			boolean exit = false;
			while(!exit) { // infinite loop to update client from messages sent from server.
				
				Packet packet = (Packet)input.readObject(); // the unit of data.
				int protocol = packet.PROTOCOL; // read the protocol.
				GridLocation grid;
					switch (protocol){
						// update the client's player.
						case NetworkProtocol.PLAYER_OBJECT :  
							player = (Player) packet.object;
							mainFrame.updatePlayer(player);
							if(player.inBattle()){
								mainFrame.updatePlayerInBattle(player); // update the player if an item is used.
							}
							break;
						// update the client but do not repaint the room.
						case NetworkProtocol.GRIDLOCATION_NO_UPDATE : 
							if(!player.inBattle() && !player.inTrade()){
									grid = (GridLocation) packet.object;
									mainFrame.setCell(grid,false);
							}
							break;
						// update the client and repaint the room
						case NetworkProtocol.GRIDLOCATION_UPDATE :
							if(!player.inBattle() && !player.inTrade()){
								grid = (GridLocation) packet.object;
								mainFrame.setCell(grid,true);
							}
							break;
						
						// Update the client and repaint the room as another player has left the room.
						case NetworkProtocol.GRIDLOCATION_ROOM_UPDATE : 
							if(!player.inBattle() && !player.inTrade()){
								grid = (GridLocation) packet.object;
								mainFrame.setCell(grid, true);
							}
							break;
						
						// Update the client and repaint the room with the room object passed.
						case NetworkProtocol.ROOM_OBJECT :
							if(!player.inBattle() && !player.inTrade()){
								currentRoom = (Room) packet.object;
								mainFrame.setRenderRoom(currentRoom);
								mainFrame.updatePositionRoomField(currentRoom);
							}
							break;
						
						// Update the client as the client's player has turned left.
						case NetworkProtocol.TURN_LEFT :
							if(!player.inBattle() && !player.inTrade()){
								player.turnLeft();
								mainFrame.setRenderRoomDirection(player);
								mainFrame.updatePlayer(player);
							}
							break;
						// Update the client as the client's player has turned right.
						case NetworkProtocol.TURN_RIGHT :
							if(!player.inBattle() && !player.inTrade()){
								player.turnRight();
								mainFrame.setRenderRoomDirection(player);
								mainFrame.updatePlayer(player);
							}
							break;
						// Append a piece of text to the log.
						case NetworkProtocol.TEXT_PACKET :
							String string = (String) packet.object;
							mainFrame.getLogPanel().getLogTextArea().appendLogText(string);
							break;
						// Update the client as the client's player has picked up an item.	
						case NetworkProtocol.PICKUPITEM :
							List<Item> items = (List<Item>) packet.object;
							mainFrame.getSidePanel().getPlayerInventoryPanel().getItemPanel().setItems(items);
							break;
						// Update the client as the client's player has picked up a key.
						case NetworkProtocol.PICKUPKEY :
							List<Item> keyItems = (List<Item>) packet.object;
							mainFrame.getSidePanel().getKeyRingPanel().getKeyPanel().setItems(keyItems);
							break;
						// Update the client as the client's player has dropped an item.
						case NetworkProtocol.DROP :
							List<Item> items2 = (List<Item>) packet.object;
							mainFrame.getSidePanel().getPlayerInventoryPanel().getItemPanel().setItems(items2);
							break;
						// Update the client as the client's player has destroyed an item from the backpack.
						case NetworkProtocol.DESTROY_ITEM :
							List<Item> items3 = (List<Item>) packet.object;
							for(Item i2 : items3){
								System.out.println(i2.getName());
							}
							mainFrame.getSidePanel().getPlayerInventoryPanel().getItemPanel().setItems(items3);
							break;
						// Update the client as the client's player has destroyed a key from the key ring.
						case NetworkProtocol.DESTROY_KEY :
							List<Item> keyItems2 = (List<Item>) packet.object;
							mainFrame.getSidePanel().getKeyRingPanel().getKeyPanel().setItems(keyItems2);
							break;
						// Update the client as the client's player has used an item.
						case NetworkProtocol.USE_ITEM :
							List<Item> items4 = (List<Item>) packet.object;
							for(Item i2 : items4){
								System.out.println(i2.getName());
							}
							mainFrame.getSidePanel().getPlayerInventoryPanel().getItemPanel().setItems(items4);
							mainFrame.updatePlayer(player);
							break;
						// Update the client as an NPC has moved in the world and repaint the room.
						case NetworkProtocol.NPC_GRIDLOCATION_UPDATE :
							if(!player.inBattle() && !player.inTrade()){
								grid = (GridLocation) packet.object;
								mainFrame.setCell(grid,true);
							}
							break;
						// Update the client as an NPC has moved in the world but do not repaint the room.
						case NetworkProtocol.NPC_GRIDLOCATION_NO_UPDATE :
							if(!player.inBattle() && !player.inTrade()){
								grid = (GridLocation) packet.object;
								mainFrame.setCell(grid,false);
							}
							break;
						// Update the client as the client's player has ported to the starting location.
						case NetworkProtocol.GAME_PORT :
							player.setDirection(Direction.NORTH);
							mainFrame.setRenderRoomDirection(player);
							currentRoom = (Room) packet.object;
							mainFrame.setRenderRoom(currentRoom);
							mainFrame.updatePositionRoomField(currentRoom);
							break;
						// Display a dialog to the client.	
						case NetworkProtocol.DISPLAY_DIALOG :
							String string2 = (String) packet.object;
							mainFrame.showDialog(string2);
							break;
						// Update the client as the player's level has changed.	
						case NetworkProtocol.PLAYER_LEVEL_UPDATE :
							Integer i = (Integer) packet.object;
							player.setLevel(i.intValue());
							mainFrame.updatePlayer(player);
							break;
						// Update the client with a battle scene as a monster battle has started.	
						case NetworkProtocol.MONSTER :
							Monster mon = (Monster) packet.object;
							player.fightingBattle();
							mainFrame.monsterAttacks(mon, player);
							break;
						// Update the client as the client's player has been attacked (Loss of health).	
						case NetworkProtocol.PLAYER_ATTACKED :
							Integer i2 = (Integer) packet.object;
							player.setCurrentHealth(i2.intValue());
							mainFrame.updatePlayer(player);
							break;
						// Update the client as the client's player has killed a monster and receives rewards.
						case NetworkProtocol.MONSTER_DIED :
							player = (Player) packet.object;
							mainFrame.updatePlayer(player);
							break;
						// Update the client with the game world renderer because the battle with a monster is over.
						case NetworkProtocol.BATTLE_OVER :
							currentRoom = (Room) packet.object;
							mainFrame.setRenderer(renderer);
							mainFrame.setRenderRoom(currentRoom);
							mainFrame.updatePositionRoomField(currentRoom);
							player.battleOver();
							renderer.setVisible(true);
							break;
						// The client's player has died so revive him to full health.	
						case NetworkProtocol.PLAYER_REVIVED :
							Integer i3 = (Integer) packet.object;
							player.setCurrentHealth(i3.intValue());
							mainFrame.updatePlayer(player);
							break;
						// Update the client with the game world renderer because the player has revived and
						// is at the starting location.
						case NetworkProtocol.DIED_IN_BATTLE :
							player.setDirection(Direction.NORTH);
							currentRoom = (Room) packet.object;
							mainFrame.setRenderer(renderer);
							mainFrame.setRenderRoom(currentRoom);
							mainFrame.setRenderRoomDirection(player);
							mainFrame.updatePositionRoomField(currentRoom);
							player.battleOver();
							renderer.setVisible(true);
							break;
						// Update the client with the trade window.
						case NetworkProtocol.GENERAL_TRADE :
							player.trading();
							mainFrame.playerTrade();
							break;
						// Update the client as the client's player has bought an item (loss of gold).
						case NetworkProtocol.BUY_ITEM :
							Integer i4 = (Integer) packet.object;
							player.changeGoldAmount(-i4.intValue());
							mainFrame.setPlayersGold(player.getGoldAmount());
							break;
						// Update the client with the game world renderer because the player has exited the trade.	
						case NetworkProtocol.TRADE_OVER :
							currentRoom = (Room) packet.object;
							mainFrame.setRenderer(renderer);
							mainFrame.setRenderRoom(currentRoom);
							mainFrame.updatePositionRoomField(currentRoom);
							player.tradeOver();
							renderer.setVisible(true);
							break;
						// Show the splash screen as the game is over.
						case NetworkProtocol.GAME_OVER :
							mainFrame.showSplashScreen();
							mainFrame.getLogPanel().getLogTextArea().setText("");
							mainFrame.resetPlayer();
							return;
							
						default : 
							break;
						}
			}
				
		
			
		}catch(IOException e) {
			// means you got disconnected from the server.
			JOptionPane.showMessageDialog(mainFrame, "You have been disconnected", "Can not connect to Server", JOptionPane.INFORMATION_MESSAGE);
			mainFrame.showSplashScreen();
			mainFrame.getLogPanel().getLogTextArea().setText("");
			mainFrame.resetPlayer();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		catch(NullPointerException e){
			// means there was a problem connecting to server!
			mainFrame.getLogPanel().disableChatBox();
			mainFrame.showSplashScreen();
			mainFrame.getLogPanel().getLogTextArea().setText("");

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
	 * Sends a chat message to all the clients and updates your chat box locally
	 * @param message - Message sent by the client
	 */
	public void sendChatMessage(String message){
		message = player.getName() +": "+ message;
		mainFrame.getLogPanel().getLogTextArea().appendLogText(message);
		try {
			output.writeInt(NetworkProtocol.TEXT_PACKET);
			output.writeInt(message.length());
			output.writeChars(message);
		} catch (IOException e) {
			// Should not be able to send messages when disconnected should be disabled.
		}
		
		
	}
	/**
	 * The client sending the server the option they chose on a item in their inventory.		
	 * @param command the command name.
	 * @param index the index of the item in the backpack or key ring.
	 * @param isKey is the item a key or not.
	 */
	public void sendOption(String command ,int index, boolean isKey){
		try{
			if(command.equals("Drop")){
				output.writeInt(NetworkProtocol.DROP);
				output.writeInt(index);	
			}
		
			else if(command.equals("Inspect")){
				output.writeInt(NetworkProtocol.CONTAINER_INSPECT);
				output.writeInt(index);	
				output.writeBoolean(isKey);
			}
		
			else if(command.equals("Destroy")){
				output.writeInt(NetworkProtocol.DESTROY);
				output.writeInt(index);	
				output.writeBoolean(isKey);
			}
		
			else if(command.equals("Use")){
				output.writeInt(NetworkProtocol.USE);
				output.writeInt(index);	
			
			}
		
			
		} catch(IOException ioe) {
		// something went wrong trying to communicate the key press to the
		// server.  So, we just ignore it.
		}
		
	}
	
	
	
	// The following intercept keyboard events from the user.
	public void keyPressed(KeyEvent e) {	
		try {
			int code = e.getKeyCode();
			if(code == KeyEvent.VK_RIGHT || code == KeyEvent.VK_KP_RIGHT) {	
				output.writeInt(NetworkProtocol.TURN_RIGHT);
			} else if(code == KeyEvent.VK_LEFT || code == KeyEvent.VK_KP_LEFT) {
				output.writeInt(NetworkProtocol.TURN_LEFT);
			} else if(code == KeyEvent.VK_UP) {	
				output.writeInt(NetworkProtocol.MOVE_UP);
			} else if(code == KeyEvent.VK_SPACE) {	
				output.writeInt(NetworkProtocol.INTERACT);
			} else if(code == KeyEvent.VK_P){				
				output.writeInt(NetworkProtocol.PICKUP);
			} else if(code == KeyEvent.VK_I){				
				output.writeInt(NetworkProtocol.INSPECT);
			}else if(code == KeyEvent.VK_ENTER){
				mainFrame.getLogPanel().setChatBoxFocus();
			}	
		} catch(IOException ioe) {
			// something went wrong trying to communicate the key press to the
			// server.  So, we just ignore it.
		}
		
	}
	
	public void keyReleased(KeyEvent e) {		
	
	}

	public void keyTyped(KeyEvent e) {
	
	}	
	
	/**
	 * Get the player
	 * @return player.
	 */
	public Player getPlayer(){
		return player;
	}
	
	/**
	 * Get the room the player is in.
	 * @return current room.
	 */
	public Room getRoom(){
		return currentRoom;
	}
	
	/**
	 * Send to the server the results of the player being attacked by a monster.
	 * @param currentHealth of the player.
	 */
	public void attacked(int currentHealth){
		
		try {
			output.writeInt(NetworkProtocol.PLAYER_ATTACKED);
			output.writeInt(currentHealth);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Send to the server the results of the player killing a monster.
	 * @param xp the xp gained from killing the monster.
	 * @param goldLoot the gold received from killing the monster.
	 * @param draco is the monster Draco or not (last boss).
	 */
	public void killMonster(int xp , int goldLoot, boolean draco){
		
		try {
			output.writeInt(NetworkProtocol.MONSTER_DIED);
			output.writeInt(xp);
			output.writeInt(goldLoot);
			output.writeBoolean(draco);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Send the server a message that this player has died.
	 */
	public void die(){
		
		try{
			output.writeInt(NetworkProtocol.PLAYER_DIED);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Send to the server the name of the item the player wants to buy.
	 * @param name of the item.
	 */
	public void buyVendorItem(String name){
		
		try {
			output.writeInt(NetworkProtocol.BUY_ITEM);
			output.writeInt(name.length());
			output.writeChars(name);
			output.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Send a message to the server that this player's trade is over.
	 */
	public void tradeOver(){
		
		try {
			output.writeInt(NetworkProtocol.TRADE_OVER);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
}
