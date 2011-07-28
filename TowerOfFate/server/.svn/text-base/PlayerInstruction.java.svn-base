package server;

import gameworld.GridLocation;
import gameworld.Player;
import gameworld.Room;
import gameworld.Player.Direction;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class PlayerInstruction implements Instruction{



	Player player;
	private byte SUB_PROTOCOL;

	public PlayerInstruction(Player p, byte sub_instruction){

		player = p;
		SUB_PROTOCOL = sub_instruction;
	}


	//byte PLAYER_PROTOCOL
	//byte nameLength
	//char[nameLength] name

	public void writeToOutput(DataOutputStream output) throws IOException {
		output.writeByte(Protocol.PLAYER);

		switch (SUB_PROTOCOL){
		case Protocol.PLAYER:

			output.writeByte(Protocol.PLAYER);
			output.writeByte((byte)player.getName().length());
			output.writeChars(player.getName());

			output.writeByte((int)player.getGridLocation().getRowIndex());
			output.writeByte((int)player.getGridLocation().getColIndex());

			break;
		case Protocol.PLAYER_DIRECTION:
			Direction dir = player.getDirectionFacing();

			output.writeByte(Protocol.PLAYER);
			output.writeByte(Protocol.PLAYER_DIRECTION);
			
			System.out.print(player.UID);
			output.writeByte((byte)dir.ordinal());

			break;

		case Protocol.PLAYER_MOVE:
			//stuff
			break;

		}

	}


	//byte nameLength
	//char[nameaLength] name
	public static Player createNewPlayer(Room currentRoom, DataInputStream input, int uid) throws IOException{

		input.readByte(); // ignore protocol, we know what it's Protocol.PLAYER

		byte length = input.readByte();	
		char[] name = new char[length];




		//loop till we get the whole string
		for (int i = 0 ; i < length ; i++)
			name[i] = input.readChar();

		byte gridRowIndex = input.readByte();
		byte gridColIndex = input.readByte();

		GridLocation gl = new GridLocation(currentRoom, gridRowIndex, gridColIndex);

		return new Player(gl,new String(name),"description", currentRoom, uid);


	}

	public static Player modifyPlayer(Player p, DataInputStream input) throws IOException{
		System.out.println("In method modifyPlayer(p,stream)");
		


		//resolve tailing bit
		input.readByte(); //head bit
		byte tail = input.readByte();
		System.out.println("Tail byte == " + tail);
		switch(tail){

		case Protocol.PLAYER_DIRECTION :

			System.out.println("-------------------------------------");
			System.out.println("North = " + Direction.NORTH.ordinal());
			System.out.println("South = " + Direction.SOUTH.ordinal());
			System.out.println("East = " + Direction.EAST.ordinal());
			System.out.println("west = " + Direction.WEST.ordinal());
			System.out.println("-------------------------------------");
			
			
			byte dir = input.readByte();

			System.out.println("Dir byte == " + dir);
			
			switch(dir){
			case 0:
				p.setDirection(Direction.NORTH);
				break;
			case 1:
				p.setDirection(Direction.EAST);
				break;
			case 2:	
				p.setDirection(Direction.SOUTH);
				break;
			case 3 :
				p.setDirection(Direction.WEST);
				break;

			default :
				break;
			}

			break;

		case Protocol.PLAYER_NAME :
			break;
		case Protocol.PLAYER_ADD_GOLD :
			p.changeGoldAmount(2);
			break;		
		}

		return p;

	}



}
