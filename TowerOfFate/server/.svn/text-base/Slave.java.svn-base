package server;
import gameworld.GameObject;
import gameworld.GridLocation;
import gameworld.Player;
import gameworld.Room;

import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.ArrayList;

import javax.swing.JFrame;

import renderer.RoomRenderer3D;

import userInterface.MainFrame;



/**
 * A slave connection receives information about the current state of the board
 * and relays that into the local copy of the board. The slave connection also
 * notifies the master connection of key presses by the player.
 */
public final class Slave extends Thread implements KeyListener {



	private Socket socket;
	private Room room;	
	private DataOutputStream output;
	private DataInputStream input;
	private int uid;
	private int totalSent;
	private Player player;
	private String name;
	private RoomRenderer3D renderer;

	private MainFrame mainFrame;

	/**
	 * Construct a slave connection from a socket. A slave connection does no
	 * local computation, other than to display the current state of the board;
	 * instead, board logic is controlled entirely by the server, and the slave
	 * display is only refreshed when data is received from the master
	 * connection.
	 * 
	 * @param socket
	 * @param dumbTerminal
	 */
	public Slave(MainFrame mainFrame, String addr, String name) {				
		int port = 6112;
		this.name = name;

		try {
			socket = new Socket(addr,port);
			this.mainFrame = mainFrame;

			mainFrame.addKeyListener((this));
			mainFrame.requestFocusInWindow();

			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {	
			e.printStackTrace();
		}
	}

	


	public void run() {
		try {			
			output = new DataOutputStream(socket.getOutputStream());
			input = new DataInputStream(socket.getInputStream());

			output.writeByte((byte)name.length());
			output.writeChars(name);

			//get playres uid
			int uid = input.readInt();

			byte protocol = input.readByte();		
			room = RoomInstruction.parseRoom(input);

			protocol = input.readByte();
			player = PlayerInstruction.createNewPlayer(room, input, uid);


			boolean exit=false;
			long totalRec = 0;
			
			renderer = new RoomRenderer3D(player, room);
			mainFrame.setRenderer(renderer);
			mainFrame.add(renderer);
			renderer.addKeyListener(this);
			renderer.requestFocusInWindow();
			mainFrame.updatePlayer(player);
			renderer.setVisible(true);
			

			System.out.println("Slave - start of loop");
			while(!exit) {
				// read event							

				if(input.available() != 0) {

					
					protocol = input.readByte();
					
					System.out.println("Slave - read protocol = " + protocol);

					switch(protocol){

					case Protocol.GRID_LOCATION:
						GridLocation cell = GridLocationInstruction.parseGridLocation(input, room);
					//	mainFrame.setCell(cell);
						break;

					case Protocol.PLAYER:
						PlayerInstruction.modifyPlayer(this.player, input);
						mainFrame.updatePlayer(this.player);					
						break;
					case Protocol.CHAT_MESSAGE:
						break;

					case Protocol.ROOM_BYTE_ARRAY :
						System.out.println("Adding room startCase");
						room = RoomInstruction.parseRoom(input);
						mainFrame.setRenderRoom(room);
						System.out.println("Slave: " + uid + "completed loading room");
						break;
					
					
					case Protocol.PLAYER_DIRECTION:
		
						
						mainFrame.updatePlayer(PlayerInstruction.modifyPlayer(player, input));
						
						System.out.println("slave player faceing : " + player.getDirectionFacing());
						break;
					}
				} 

				//display.repaint();
				// print out some useful information about the amount of data
				// sent and received		
			}
			socket.close(); // release socket ... v.important!
		} catch(IOException e) {
			System.err.println("I/O Error: " + e.getMessage());
			e.printStackTrace(System.err);
		}
	}


	// The following intercept keyboard events from the user.

	public void keyPressed(KeyEvent e) {		
		try {
			int code = e.getKeyCode();
			if(code == KeyEvent.VK_RIGHT || code == KeyEvent.VK_KP_RIGHT) {	
				output.writeByte(Protocol.USER_ACTION);
				output.writeInt(3);
				totalSent += 5;


			} else if(code == KeyEvent.VK_LEFT || code == KeyEvent.VK_KP_LEFT) {
				output.writeByte(Protocol.USER_ACTION);
				output.writeInt(2);
				totalSent += 5;

				System.out.println("Left");
			} else if(code == KeyEvent.VK_UP) {	
				output.writeByte(Protocol.USER_ACTION);
				output.writeInt(1);
				totalSent += 5;

				System.out.println("Up");
			} else if(code == KeyEvent.VK_DOWN) {
				output.writeByte(Protocol.USER_ACTION);
				output.writeInt(4);
				totalSent += 5;

				System.out.println("Down");
			} else if(code == KeyEvent.VK_SPACE) {	
				output.writeByte(Protocol.USER_ACTION);
				output.writeInt(5);
				totalSent += 5;

				System.out.println("Space");
				
				//TESTING STATEMENTS
				System.out.println("----------------------SLAVE " + uid  + "------------------------------");
				for (GridLocation[] row : room.roomGrid){
					for (GridLocation g : row){
						for (GameObject o : g.getObjects()){
							if (o instanceof Player){
								System.out.println("Player: " +  ((Player)o).UID + " Location: " + g.getColIndex() + " / " + g.getRowIndex() + " DIR: " + ((Player)o).getDirectionFacing());			
						
							
							}
							
						}
					}

				}
				System.out.println("----------------------ENDSLAVE-----------------------------------");
				
				//TESTING STATEMENTS
				
				
				
			}
			output.flush();
		} catch(IOException ioe) {
			// something went wrong trying to communicate the key press to the
			// server.  So, we just ignore it.
		}
	}

	public Player getPlayer(){
		return player;
	}

	public Room getRoom(){
		return room;
	}

	public void keyReleased(KeyEvent e) {		
	}

	public void keyTyped(KeyEvent e) {

	}		
}
