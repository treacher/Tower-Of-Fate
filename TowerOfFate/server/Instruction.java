package server;

import gameworld.GameObject;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public interface Instruction {
	
	final byte PLAYER_ACTION = 0;
	final byte PLAYER = 1;
	final byte CHAT_MESSAGE = 2;
	final byte ROOM_BYTE_ARRAY = 3;
	final byte GRID_LOCATION = 4;

	public void writeToOutput(DataOutputStream output) throws IOException;
	
	

}
