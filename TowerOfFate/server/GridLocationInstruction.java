package server;

import gameworld.GameObject;
import gameworld.GridLocation;
import gameworld.Player;
import gameworld.Room;
import gameworld.TowerOfFate;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class GridLocationInstruction implements Instruction {

	private final int PROTOCOL = 4;

	private	byte roomId;
	private int x;
	private int y;
	private byte[] gameObjects;

	private Room room;

	/**
	 * 
	 * Format
	 * byte room; // the room the grid location is part of.
	 byte row; // used for indexing the room grid of room.
	 byte col; // used for indexing the room grid of room.
	 {byte length, byte[] gameObjects}**
	 * 
	 * @param protocol
	 * @param instruction
	 */
	public GridLocationInstruction(Room room, int x, int y) {

		this.room = room;
		this.x = x;
		this.y = y;
	}

	public GridLocationInstruction(GridLocation cell){
		this.room = cell.getRoom();
		this.x = cell.getRowIndex();
		this.y = cell.getColIndex();
	}


	/**
	 *
	 *format :
	 *
	 *	byte protocol, byte length, byte[] gridLocationByteArray
	 *
	 **/
	public void writeToOutput(DataOutputStream output) throws IOException {	


		//byte[] g = room.roomGrid[x][y].gridLocationToByteArray();

		//protocol
		//length
		//byte[]
		output.writeByte(PROTOCOL);	

		//output.writeByte((byte)g.length);
		//output.write(g);


	}

	/**
	 *Method for parsing a GridLocation, must ensure the correct
	 *protocol has been used.
	 *format :
	 *
	 *	byte length, byte[] gridLocationByteArray
	 *
	 **/
	public static GridLocation parseGridLocation(DataInputStream input, Room room) throws IOException{

		byte length = input.readByte();	
		int lengthInt = length;

		byte[] gridLocation = new byte[lengthInt];

		System.out.println("length = " + gridLocation.length);

		input.read(gridLocation);

		assert (gridLocation != null);
		if (gridLocation == null){
			System.out.println("Gridlocatoin byte array is null");

		}

	

		System.out.println();


		return null;//new GridLocation(gridLocation, room);



	}

}
