package server;

import gameworld.Room;
import gameworld.TowerOfFate;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Connection extends Thread{

	int port;
	TowerOfFate game;

	public Connection(int port, TowerOfFate game){
		this.port = port;
		this.game = game;
	}

	public void run(){
		
		int uid = 0;
		try{
			ServerSocket ss = new ServerSocket(port);
			
			
			while (1 == 1) {
								 		
					// 	Wait for a socket
					Socket s = ss.accept();
					System.out.println("ACCEPTED CONNECTION FROM: " + s.getInetAddress());		
					Room startingRoom = game.STARTING_ROOM;

					Master m = new Master(s,uid,startingRoom, game);
					
					m.start();			

					uid++;
					
				
			}
		}
		catch(IOException e) {
			System.err.println("I/O error: " + e.getMessage());
		} 
	}

}