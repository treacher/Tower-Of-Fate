package network;

import gameworld.GridLocation;
import gameworld.Player;
import gameworld.TowerOfFate;
import gameworld.Player.Direction;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import dataStorage.SaveToXML;

/**
 * This class represents a GameServer which manages all server connections.
 * @author kayecalv id: 300155433
 *
 */
public class GameServer extends Thread {
	
	public static List<ServerConnection> connections = Collections.synchronizedList(new ArrayList<ServerConnection>());
	public final int NUM_OF_PLAYERS;
	private TowerOfFate game;
	public static int numOfPlayers;
	public boolean gameStarted;
	public static List<Player> disconnectedPlayers = new ArrayList<Player>(); // list of disconnected players.
	private static boolean serverRunning = true;
	
	/**
	 * Create a new game with the specified number of players.
	 * @param playerAmount
	 */
	public GameServer(int playerAmount){
		NUM_OF_PLAYERS = playerAmount;
	}
	
	public void run(){
		
		game = new TowerOfFate();
		
		int port = 6112;
		
		int uid = 0;
		try{
			ServerSocket ss = new ServerSocket(port);
			while (1 == 1) {
					// 	Wait for a socket
					Socket s = ss.accept();
					System.out.println("ACCEPTED CONNECTION FROM: " + s.getInetAddress());		
					ServerConnection sc = new ServerConnection(uid , game , s , this);
					connections.add(sc);
					sc.start();
					uid++;
					// update clients on current amount of players in game.
					GameServer.updateClients(new Packet(NetworkProtocol.PLAYERS_JOINED, new Integer(uid)) , null , true);
					if(uid == NUM_OF_PLAYERS){
						try {
							sleep(500);
							for(int i = 5 ; i > 0 ; i--){
								// send out the game count down.
								GameServer.updateClients(new Packet(NetworkProtocol.STARTING_COUNTDOWN , "Game Starting in " + i + "..."), null , true);
								sleep(1000);
							}
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						
						// game started.
						GameServer.updateClients(new Packet(NetworkProtocol.START_GAME , null), null , false);
						break;
						
					}
			}
			
			try { // Let the clients catch up before broadcasting NPC movement.
				sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			for(int i = 0 ; i < TowerOfFate.NPCS.size(); i++){
				TowerOfFate.NPCS.get(i).thread.start();
			}
			
			numOfPlayers = NUM_OF_PLAYERS;
			gameStarted = true;
			
			while(serverRunning){ // accept further connections.
				Socket s = ss.accept();
				System.out.println("ACCEPTED CONNECTION FROM: " + s.getInetAddress());		
				ServerConnection sc = new ServerConnection(uid , game , s , this);
				sc.start();
			}
			
			ss.close();
			
		}
		catch(IOException e) {
			System.err.println("I/O error: " + e.getMessage());
		} 
	}
	
	/**
	 * Checks to see if the server is full.
	 * @return boolean server full.
	 */
	public boolean serverFull(){
		return numOfPlayers == NUM_OF_PLAYERS;
	}
	
	/**
	 * A player disconnects from the game.
	 * @param sc the server connection of the player that has disconnected.
	 */
	public static void disconnectPlayer(ServerConnection sc){
		
		System.out.println("Player " + sc.getUID() + " has disconnected");
		Player p = sc.getPlayer();
		p.getGridLocation().removeGameObject(p); // remove the player from his current location.
		connections.remove(sc.getUID());
		numOfPlayers--;
		disconnectedPlayers.add(sc.getPlayer()); // add the player to the disconnected list.
		// Update clients that the player has been removed from the game world.
		GameServer.updateClients(new Packet(NetworkProtocol.GRIDLOCATION_UPDATE , p.getGridLocation()), sc , false);
		GameServer.updateClients(new Packet(NetworkProtocol.TEXT_PACKET , p.getName() + " has disconnected.") , sc , false);
		
	}
	
	/**
	 * Check the list of disconnected players for a player with the specified name.
	 * @param name the name of the player.
	 * @return Player object if found, or null if player not found.
	 */
	public Player findPlayer(String name){
		for(Player p : disconnectedPlayers){
			if(name.equals(p.getName())){
				return p;
			}
		}
	
		
		return null;
		
	}
	
	
	
	public void saveGame(String fname){
		//disconnect all players from the game
		for (ServerConnection s : connections)
			disconnectPlayer(s);
			
		
		SaveToXML saver = new SaveToXML();
		
		saver.save(game, fname);
		
	}
	
	public void loadGame(String fname){
		//disconnect all players from the game
		for (ServerConnection s : connections)
			disconnectPlayer(s);
		
		SaveToXML saver = new SaveToXML();	
		
		serverRunning = true;
		game = saver.load(fname);
		
		//reset the players in the disconnected array
		disconnectedPlayers.clear();
		
		for (Player p : game.getPlayers())
			disconnectedPlayers.add(p);
			
		
	}
	
	/**
	 * The game has been finished.
	 * @param sender the player who has won the game.
	 */
	public static void gameOver(ServerConnection sender){
		
		sender.addPacket(new Packet(NetworkProtocol.DISPLAY_DIALOG , "You have secured your freedom as you teleport out of the tower. You win."));
		sender.addPacket(new Packet(NetworkProtocol.GAME_OVER , null));
		for(ServerConnection serverC : connections){
			if( sender.getUID() != serverC.getUID()){
				serverC.addPacket(new Packet(NetworkProtocol.TEXT_PACKET , sender.getPlayer().getName() + " is victorious."));
				serverC.addPacket(new Packet(NetworkProtocol.DISPLAY_DIALOG , "You die as the tower around you collapses. You lose."));
				serverC.addPacket(new Packet(NetworkProtocol.GAME_OVER , null));
				serverRunning = false;
			}
		}
	}
	
	/**
	 * Moves the player from their current location to the starting location of the game.
	 * @param serverC the server connection of the player.
	 * @param diedInBattle did the player die in battle.
	 * @param oldGrid the grid location the player is currently in. 
	 */
	public void portPlayer(ServerConnection serverC, boolean diedInBattle, GridLocation oldGrid){
		
		Player p = serverC.getPlayer();
		if(diedInBattle){
			p.setCurrentRoom(game.STARTING_ROOM);
			p.getGridLocation().removeGameObject(p);
			p.setGridLocation(game.STARTING_LOCATION);
			game.STARTING_ROOM.roomGrid[game.STARTING_LOCATION.getRowIndex()][game.STARTING_LOCATION.getColIndex()].getObjects().add(p);
			p.setDirection(Direction.NORTH);
			GameServer.updateClients(new Packet(NetworkProtocol.DIED_IN_BATTLE , p.getCurrentRoom()), serverC, false);
			GameServer.updateClients(new Packet(NetworkProtocol.GRIDLOCATION_UPDATE , oldGrid), serverC , false);
			GameServer.updateClients(new Packet(NetworkProtocol.GRIDLOCATION_UPDATE , p.getGridLocation()), serverC , false);
		}
		else{
			
		}
	}
	
	/**
	 * Update the clients.
	 * @param packet the packet to send to the clients.
	 * @param sender the server connection that initiated the server message.
	 * @param sendToAll whether or not to send to all clients or to send to everyone but the sender.
	 */
	public static void updateClients(Packet packet , ServerConnection sender , boolean sendToAll){
		
		
		if( packet.PROTOCOL == NetworkProtocol.STARTING_COUNTDOWN || packet.PROTOCOL == NetworkProtocol.DISPLAY_DIALOG || packet.PROTOCOL == NetworkProtocol.PLAYERS_JOINED
			|| packet.PROTOCOL == NetworkProtocol.START_GAME){
			
			for (ServerConnection serverC : connections){ // send to all clients
				serverC.addPacket(packet);
			}
			return;
		}
		
		if(packet.PROTOCOL == NetworkProtocol.BATTLE_OVER || packet.PROTOCOL == NetworkProtocol.GAME_PORT
		|| packet.PROTOCOL == NetworkProtocol.DIED_IN_BATTLE){
			sender.addPacket(packet);
			return;
		}
		
		if(!sendToAll){
			for (ServerConnection serverC : connections){
				if( sender.getUID() != serverC.getUID()){
					if(packet.PROTOCOL == NetworkProtocol.TEXT_PACKET){
						serverC.addPacket(packet);
					}
					else if(packet.PROTOCOL == NetworkProtocol.GRIDLOCATION_ROOM_UPDATE){
						GridLocation grid = (GridLocation) packet.object;
						if(grid.getRoom().equals(serverC.getPlayer().getCurrentRoom()) && !serverC.getPlayer().inBattle() && !serverC.getPlayer().inTrade()){
							serverC.addPacket(packet);
						}
					}
					else if(sender.getPlayer().getCurrentRoom().equals(serverC.getPlayer().getCurrentRoom()) && !serverC.getPlayer().inBattle() && !serverC.getPlayer().inTrade()){
						serverC.addPacket(packet);
					}
				}
			}
		}
		else{
			for (ServerConnection serverC : connections){
				if(packet.PROTOCOL ==NetworkProtocol.NPC_GRIDLOCATION_UPDATE || packet.PROTOCOL == NetworkProtocol.NPC_GRIDLOCATION_NO_UPDATE){
					GridLocation grid = (GridLocation) packet.object;
					if(grid.getRoom().equals(serverC.getPlayer().getCurrentRoom()) && !serverC.getPlayer().inBattle() && !serverC.getPlayer().inTrade()){
						serverC.addPacket(packet);
					}
				}
				else if(sender.getPlayer().getCurrentRoom().equals(serverC.getPlayer().getCurrentRoom()) && !serverC.getPlayer().inBattle() && !serverC.getPlayer().inTrade()){
					serverC.addPacket(packet);
				}
			}
		}
	}
	
}