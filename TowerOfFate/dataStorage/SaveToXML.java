package dataStorage;

import gameworld.GridLocation;
import gameworld.HealthPotion;
import gameworld.Key;
import gameworld.Player;
import gameworld.Room;
import gameworld.TowerOfFate;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
/**
 * Class with methods to save and load TowerOfFate game objects
 * 
 * @author anderssean2
 *
 */
public class SaveToXML {




	/**
	 * saves the TowerOfFate game object to specified file name 
	 * 
	 * @param game
	 * @param fname
	 */
	public void save(TowerOfFate game, String fname){
		try {


			Room room = game.STARTING_ROOM;
			Player p = new Player(null, "Sean", "Player", room, 7);
			p.getBackpack().getContainer().add(new HealthPotion());
			p.getKeyring().getContainer().add(new Key("blue", "blat"));


			game.players.add(p);
			p.setName("BOB");

			game.players.add(p);
			game.players.add(p);


			FileWriter file =  new FileWriter(fname);

			file.write("<?xml version =\"1.0\"?>\n");

			file.write(game.toXML());

			file.close();

		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	/**
	 * loads from the specified filename
	 * 
	 * @param fname
	 * @return
	 */
	public TowerOfFate load(String fname) {
		try {

			FileReader reader = new FileReader(fname);

			BufferedReader read = new BufferedReader(reader);

			//clear first line
			read.readLine();

			ArrayList<String> lines = new ArrayList<String>();

			while(read.ready()){
				String line = read.readLine();
				lines.add(line);

			}

			Element[] e = new Element[lines.size()];



			for (int i = 0 ; i < lines.size(); i++)
				e[i] = new Element(lines.get(i));


			XMLObject game = new XMLObject(e, 0);
			TowerOfFate g = TowerOfFate.fromXML(game); 



			setParentPointers(g);

			return g;
		} catch (Throwable e) {

			e.printStackTrace();
		}
		return null;

	}

	private void setParentPointers(TowerOfFate game) {

		//set the room pointers
		Room[] rooms = game.getRooms();

		for (int i = 0; i < rooms.length ; i++){
			Room r = rooms[i];
			if (r != null){
				for(int j = 0 ; j < Room.ROWS ; j++){
					for (int k = 0; k < Room.COLS ; k++ ){

						GridLocation cell = r.roomGrid[j][k];

						//make a new GridLocation, so superConstrutors are made correctly
						GridLocation newCell = new GridLocation(r, cell.getRowIndex(), cell.getColIndex());

						newCell.setObjects(cell.getGameObjects());

						r.roomGrid[j][k] = newCell; 
					}
				}
			}
		}
		//set player pointers correctly
		for (int i = 0 ; i < game.getPlayers().size() ; i++){

			Player p = game.getPlayers().get(i);
			
			Room currentRoom = rooms[p.roomID];
			
			
			GridLocation g = currentRoom.roomGrid[p.rowID][p.colID];
			
			Player newPlayer = new Player(g, p.getName(), p.getDescription(), currentRoom, p.UID);
			
			game.getPlayers().add(i, newPlayer);

		}





	}
}
