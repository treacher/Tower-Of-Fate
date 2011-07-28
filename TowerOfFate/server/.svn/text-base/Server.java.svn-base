package server;

import gameworld.TowerOfFate;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;


/**
 * A Thread to start a Server aswell as a Connection for players to
 * connect to the game
 * 
 * 
 * @author Sean
 *
 */
public class Server extends Thread{
	
	private static TowerOfFate game;
	private int port;
	
	public Server(int port){
		game = new TowerOfFate();
		this.port = port;
	}
	
	public void run(){
		System.out.println("TOWER OF FATE SERVER LISTENING ON PORT " + port);
		Connection listener = new Connection(port, game);
		listener.start(); 
	}

	private static ArrayList<Master> masters = new ArrayList<Master>();
	
	public static void add(Master m){
		System.out.println("Player " + m.uid + " connected");
		masters.add(m);
	}
	
	public static void disconnectPlayer(int uid){
		System.out.println("Player " + uid + " has disconnected");
		masters.remove(uid);
	}
	
	public static Master get(int uid){
		return masters.get(uid);
	}
	
	public static synchronized void broadCastInstruction(Instruction i){
		
		for (Master m : masters){
			m.addInstruction(i);
		}
	}
	
}
