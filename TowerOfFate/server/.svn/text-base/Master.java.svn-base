package server;

import gameworld.GameObject;
import gameworld.GridLocation;
import gameworld.Player;
import gameworld.Room;
import gameworld.TowerOfFate;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;


public final class Master extends Thread {

	//private final ArrayList<Room> rooms;

	private final byte PLAYER_PROTOCOL = 1;
	private final byte CHAT_MESSAGE = 2;
	private final byte ROOM_BYTE_ARRAY = 3;
	private final byte GRID_LOCATION = 4;

	//player sub protocols
	private final static byte PLAYER_NAME_ = 5;
	private final static byte PLAYER_ADD_GOLD = 6;

	public final int uid;
	private final Socket socket;

	private Room room;

	private Player player;
	private int broadcastClock = 1;

	private ArrayBlockingQueue<Instruction> instructions = new ArrayBlockingQueue<Instruction>(300);

	private TowerOfFate game;

	public Master(Socket socket, int uid, Room room, TowerOfFate game) {
		this.room = room;	

		this.socket = socket;
		this.uid = uid;
		this.game = game;

		Server.add(this);
		
	}



	public void run() {		
		try {
			System.out.println("Master - start");

			DataInputStream input = new DataInputStream(socket.getInputStream());
			DataOutputStream output = new DataOutputStream(socket.getOutputStream());

			
			//get players name
			int length = input.readByte();
			String name = "";
			for(int i = 0 ; i< length ; i++)
				name += input.readChar();
				
			player = new Player(game.STARTING_LOCATION, name, "Lee", game.STARTING_ROOM, uid);
				
			System.out.println(name);
			
			game.addPlayer(player);
			

			
			RoomInstruction r = new RoomInstruction(room);
			PlayerInstruction p = new PlayerInstruction(player, Protocol.PLAYER);
			
			output.writeInt(uid);
			r.writeToOutput(output);
			p.writeToOutput(output);
			
			output.flush();
			
			//send slave their player class
		

			System.out.println("Master - start of loop");
			System.out.println("Master - instructions SIZE: " + instructions.size());
			boolean exit = false;
			while(!exit) {		
			
				
				try {

					while (!instructions.isEmpty()){
						//send each instruction
						instructions.poll().writeToOutput(output);
						output.flush();


					}

					readUserInput(input);

					Thread.sleep(broadcastClock);
				} catch (InterruptedException e) {
					//error
					e.printStackTrace();
				}
			}
			socket.close(); // release socket ... v.important!
			System.err.println("PLAYER " + uid + " DISCONNECTED");
			Server.disconnectPlayer(uid);
		} catch(IOException e) {
			System.err.println("PLAYER " + uid + " DISCONNECTED");
			Server.disconnectPlayer(uid);
		}
		
			
	}





	private void readUserInput(DataInputStream input) throws IOException {
		if(input.available() != 0) {

			byte protocol = input.readByte();

			switch(protocol){

			case Protocol.USER_ACTION:
				System.out.println("USRER_ACTION RECIEVED!!");
				doPlayerAction(input.readInt());

				break;

			case Protocol.PLAYER:

				break;

			case Protocol.CHAT_MESSAGE:
				break;

			}

		}
	}



	/**
	 * method used to que up instructions to be sent
	 * 
	 * @param i
	 */
	public void addInstruction(Instruction i){
		instructions.add(i);
	}


	/**
	 * Used to evaluate and execute a action command
	 * 
	 * @param action
	 */
	private void doPlayerAction(int action){
		// read direction event from client.

		System.out.println("Action pressed: " + action);

		switch(action) {
		case 1:
			player.move();
			System.out.println("up");
			
			instructions.offer(new PlayerInstruction(player, Protocol.PLAYER_DIRECTION));
			Server.broadCastInstruction(new GridLocationInstruction(player.getGridLocation()));
			break;
		case 2:
			player.turnLeft();
			System.out.println("Left");
			
			instructions.offer(new PlayerInstruction(player, Protocol.PLAYER_DIRECTION));
			break;
		case 3:
			player.turnRight();
			System.out.println("Right");
			
			instructions.offer(new PlayerInstruction(player, Protocol.PLAYER_DIRECTION));
			break;						

		case 4:
			//down key
			break;
		case 5:
			//space bar
			
			
			
			
			//TESTING STATEMENTS
			System.out.println("-------------------MASTERSTART " + uid + "-----------------------------");
			for (GridLocation[] row : room.roomGrid){
				for (GridLocation g : row){
					for (GameObject o : g.getObjects()){
						if (o instanceof Player){
							Player p1 =(Player)o;
							
							System.out.println(g.getColIndex() + " / " + g.getRowIndex() + " Player[" + p1.UID + "]" + "Direction: "+ ((Player)o).getDirectionFacing());
						}
						
					}
				}

			}
			System.out.println("-------------------MASTEREND-----------------------------");
			
			//TESTING STATEMENTS
			
			
			break;
		}

	}
}


