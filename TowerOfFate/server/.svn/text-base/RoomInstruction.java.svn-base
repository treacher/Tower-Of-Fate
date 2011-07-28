package server;

import gameworld.GridLocation;
import gameworld.Room;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;



public class RoomInstruction implements Instruction {

	private final byte PROTOCOL = 3;
	private Room room;

	public RoomInstruction(Room room){
		this.room = room;

	}


	public void writeToOutput(DataOutputStream output) throws IOException {

		output.writeByte(PROTOCOL);
		output.writeByte((byte)room.getName().length());
		output.writeChars(room.getName());


		for (GridLocation[] row : room.roomGrid){
			for (GridLocation cell : row){
				Instruction c = new GridLocationInstruction(room, cell.getRowIndex(), cell.getColIndex());

				c.writeToOutput(output);

				
			}	
		}
	}

	public static Room parseRoom(DataInputStream input) throws IOException{

		byte length = input.readByte();

		String name = "";
		for(int i = 0; i < length ; i++){
			char c = input.readChar();
			name += c;

		}

		System.out.println(name);
		Room room = new Room(0, name);


		for (int i = 0; i < Room.ROWS ; i++){
			for (int j = 0; j < Room.COLS ; j++){
				input.readByte(); //clear leading protocol, we already know what it is
				room.roomGrid[i][j] = GridLocationInstruction.parseGridLocation(input, room);

			}
		}

		return room;

	}

}

