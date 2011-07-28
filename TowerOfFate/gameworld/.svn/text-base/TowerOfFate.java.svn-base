package gameworld;

import java.util.ArrayList;
import java.util.List;

import dataStorage.XMLFormatter;
import dataStorage.XMLObject;

/**
 * This class represents the Tower of Fate. It is the main class that manages the game.
 * @author Damian Kaye
 *
 */
public class TowerOfFate {
	private static Room[] rooms = new Room[200];
	private List<Player> playersInGame = new ArrayList<Player>();
	private String name = "The Tower of Fate";
	private static String description = "You awake in a basement where other players around you do not know where they are... " +
	"A voice comes on... \"Welcome to the Tower of Fate, you will be competing against each other to reach the top." +
	"At the top of the tower lies an orb of teleportation that will teleport you back to your hometown and secure your freedom. Once " +
	"this orb has been triggered by a player the rest of you will die, so be the first to reach the top. Your " +
	"fate lies in a series of challenges at each floor to the top of the tower. So be prepared...\"";

	public final Level LEVEL_1 = new Level("Basement of Initiation","Reach 30 stength to be able to unlock the next level."
			+ "Get strength by leveling off monsters or by buying Scrolls of Strength from the General Supplies Vendor." +
			"You must then find a boulder which blocks the stairs to the next level, with your newly acquired strength you will be able to move it." +
			"Good Luck...",1);
	public final Level LEVEL_2 = new Level("The Broken Past","You must collect 3 key fragments, the Bottom , Middle and Top Key Fragments." +
			"Once you have collected these 3 key fragments you must find the secret Keysmith who will forge the three key fragments into a key " +
			"to unlock the level door to the next level. The Keysmith will not do this for free and will cost you 50 gold for the key to be forged." ,2);
	public final Level LEVEL_3 = new Level("The Mysterious Key","Luck or skill? You must find the correct key to unlock the door to the next level." +
			"There are alot to choose from, so choose wisely." ,3);
	public final Level LEVEL_4 = new Level("Did someone say \"Boom\"? ", "The door to the next level has no lock and is jammed tight." +
			"You must find the explosive recipe that lies on this level and follow the instructions from there." , 4);
	public final Level LEVEL_5 = new Level("The Final Reckoning","Your final challenge lies ahead... You must slay Draco the Orb Keeper because he guards the/n" +
			"sacred orb of teleportation. Once touched, this orb will secure your freedom back to your hometown." , 5);

	public final Room STARTING_ROOM = new Room("Starting Room Level 1", "The starting room for the game", LEVEL_1);
	public final GridLocation STARTING_LOCATION = STARTING_ROOM.roomGrid[Room.ROWS-3][Room.COLS-2];

	public final Room LOBBY_ROOM = new Room("Waiting room" , "wait for other players to join", LEVEL_1);
	public final GridLocation SPAWN_LOCATION = LOBBY_ROOM.roomGrid[Room.ROWS-3][Room.COLS-2];

	//npcs in the game

	public final static ArrayList<NPC> NPCS = new ArrayList<NPC>();
	public static ArrayList<Player> players = new ArrayList<Player>();
	//level doors

	private BoulderDoor bd; //level1
	private BoulderDoor bd1;

	private LevelDoor ld2;  //level 2
	private LevelDoor ld21;

	private LevelDoor ld3;  //level3
	private LevelDoor ld31;


	private RobustDoor rb; //level4
	private RobustDoor rb1;

	//rooms in each level

	public final Room[] roomsLevel1 = new Room[4];
	public final Room[] roomsLevel2 = new Room[4];
	public final Room[] roomsLevel3 = new Room[4];
	public final Room[] roomsLevel4 = new Room[4];


	public TowerOfFate(){
		Level.setTower(this);
		createLevel1();
		createLevel2();
		createLevel3();
		createLevel4();
		createLevel5();
	}


	/**
	 * Creates level 1
	 */
	public void createLevel1(){

		//ROOM 1

		Door door1 = null;
		Door door2 = null;
		Door door3 = null;

		STARTING_ROOM.roomGrid[1][1].getObjects().add(new Tablet("Level 1 Tablet ",LEVEL_1.getDescription()));

		//add game objects

		Skeleton skele = new Skeleton(1, "Skeleton", "The remains of a glorified warrior");
		skele.getContainer().add(new Key("rusted key", "rusted key"));	
		STARTING_ROOM.roomGrid[2][6].getObjects().add(skele);

		//add NPCS

		NPC gsv = new GeneralSuppliesVendor(STARTING_ROOM.roomGrid[6][6]);
		STARTING_ROOM.roomGrid[6][6].getObjects().add(gsv);
		NPCS.add(gsv);


		//create the door connections from starting room

		STARTING_ROOM.roomGrid[4][0].getObjects().clear();
		Door d1 = new Door("Door to the chamber of horrors" , "Door", STARTING_ROOM.roomGrid[4][0], false , null);
		STARTING_ROOM.roomGrid[4][0].getObjects().add(d1);


		STARTING_ROOM.roomGrid[0][4].getObjects().clear();
		Door d2 = new Door("Door to the chamber of pain", "Door", STARTING_ROOM.roomGrid[0][4], false , null);
		STARTING_ROOM.roomGrid[0][4].getObjects().add(d2);


		STARTING_ROOM.roomGrid[Room.ROWS-1][4].getObjects().clear();
		Door d3 =  new Door("Door to Boulder hall", "Door", STARTING_ROOM.roomGrid[Room.ROWS-1][4], false , null);
		STARTING_ROOM.roomGrid[Room.ROWS-1][4].getObjects().add(d3);


		Sarcophagus sarcc = new Sarcophagus(1, "Sarcophy","A hollow coffin");
		sarcc.getContainer().add(new Key("Glorius skeleton key", "Glorius skeleton key"));
		STARTING_ROOM.roomGrid[3][4].getObjects().add(sarcc);

		STARTING_ROOM.roomGrid[2][1].getObjects().add(new Bookcase(0,"Mouldy bookcase","Mouldy bookcase"));

		//ROOM 2

		//setting up door connection

		Room room2 = new Room("Boulder Hall", "Boulder Hall", LEVEL_1);
		room2.roomGrid[4][Room.COLS-1].getObjects().clear();
		door1 = new Door("Door to starting room of level 1", "Door" , room2.roomGrid[4][Room.COLS-1], false , d1);
		room2.roomGrid[4][Room.COLS-1].getObjects().add(door1);	
		d1.setConnectingDoor(door1);
		door1.setConnectingDoor(d1);

		//add game objects
		room2.roomGrid[3][3].getObjects().add(new Skeleton(0,"Skeleton", "The remains of a glorified warrior"));
		room2.roomGrid[5][2].getObjects().add(new Skeleton(0,"Skeleton", "The remains of a glorified warrior"));
		room2.roomGrid[4][5].getObjects().add(new Sarcophagus(0,"Sarcophagus", "A rusted sarcophagus"));
		room2.roomGrid[2][1].getObjects().add(new Bookcase(0,"Mouldy bookcase","This bookcase is barely standing."));

		//level door

		room2.roomGrid[4][0].getObjects().clear();		
		bd = new BoulderDoor("Boulder blocking the way to level 2" , "Boulder blocking the way to level 2", room2.roomGrid[4][0], LEVEL_2, null);
		room2.roomGrid[4][0].getObjects().add(bd);

		//ROOM 3


		//setting up door connection
		Room room3 = new Room("Chamber Of Horrors", "Chamber Of Horrors", LEVEL_1);
		room3.roomGrid[Room.ROWS-1][4].getObjects().clear();		
		door2 = new Door("Door to starting room of level 1", "Door" , room3.roomGrid[Room.ROWS-1][4],false,d2);

		room3.roomGrid[Room.ROWS-1][4].addGameObject(door2);
		d2.setConnectingDoor(door2);
		door2.setConnectingDoor(d2);

		//add game objects
		room3.roomGrid[5][5].getObjects().add(new Skeleton(0,"Skeleton", "The remains of a glorified warrior"));
		room3.roomGrid[3][3].getObjects().add(new Sarcophagus(0, "Sarcophagus", "A rusted sarcophagus"));
		room3.roomGrid[2][1].getObjects().add(new Bookcase(0,"Mouldy bookcase","This bookcase is barely standing."));		


		//ROOM 4

		//setting up door connection
		Room room4 = new Room("Chamber Of Pain", "Chamber Of Pain", LEVEL_1);	
		room4.roomGrid[0][4].getObjects().clear();
		door3 = new Door("Door to starting room of level 1", "Door ", room4.roomGrid[0][4], false , d3);
		room4.roomGrid[0][4].addGameObject(door3);
		d3.setConnectingDoor(door3);
		door3.setConnectingDoor(d3);

		//add game objects
		room4.roomGrid[3][3].getObjects().add(new Sarcophagus(0, "Sarcophagus", "A rusted sarcophagus"));
		room4.roomGrid[2][4].getObjects().add(new Sarcophagus(0, "Sarcophagus", "A rusted sarcophagus"));
		room4.roomGrid[3][1].getObjects().add(new Bookcase(0,"Mouldy bookcase","This bookcase is barely standing."));


		//level1

		roomsLevel1[0] = (STARTING_ROOM);
		roomsLevel1[1] = room2;
		roomsLevel1[2] = room3;
		roomsLevel1[3] = room4;


		rooms[0] = (STARTING_ROOM);
		rooms[1] = room2;
		rooms[2] = room3;
		rooms[3] = room4;
	}


	/**
	 * Create level 2
	 */
	public void createLevel2(){

		//starting room

		Room startingRoom = new Room("Starting Room Level 2", "room" , LEVEL_2);
		startingRoom.roomGrid[3][3].getObjects().add(new Skeleton(0,"Skeleton", "The remains of a glorified warrior"));
		startingRoom.roomGrid[6][5].getObjects().add(new HealthPotion());
		startingRoom.roomGrid[1][4].getObjects().add(new ScrollOfStrength());


		startingRoom.roomGrid[4][Room.COLS-1].getObjects().clear();		
		bd1 = new BoulderDoor("Level 2 entrance Door", "Level 2 entrance Door", startingRoom.roomGrid[4][Room.COLS-1] ,LEVEL_1, bd);
		startingRoom.roomGrid[4][Room.COLS-1].getObjects().add(bd1);

		bd.setConnectingDoor(bd1);
		bd1.setConnectingDoor(bd);

		//add the keysmith

		KeySmith ks = new KeySmith(startingRoom.roomGrid[6][6]);
		startingRoom.roomGrid[6][6].getObjects().add(ks);		
		NPCS.add(ks);

		startingRoom.roomGrid[1][1].getObjects().add(new Tablet("Level 2 Tablet", LEVEL_2.getDescription()));


		startingRoom.roomGrid[4][0].getObjects().clear();
		Door door1 = new Door("Door to The Lost Memories", "Door to room 1", startingRoom.roomGrid[4][0],false, null);
		startingRoom.roomGrid[4][0].getObjects().add(door1);


		// first room

		Room room1 = new Room("Lost Memories", "room", LEVEL_2);

		room1.roomGrid[4][Room.COLS-1].getObjects().clear();
		Door d11 = new Door("Door to starting room of level 2", "door starting room", room1.roomGrid[4][Room.COLS-1], false, door1);
		room1.roomGrid[4][Room.COLS-1].getObjects().add(d11);

		d11.setConnectingDoor(door1);
		door1.setConnectingDoor(d11);

		room1.roomGrid[5][2].getObjects().add(new Skeleton(0,"Skele", "The remains of a glorified warrior"));

		room1.roomGrid[6][6].getObjects().add(new HealthPotion());
		room1.roomGrid[1][6].getObjects().add(new HealthPotion());

		Sarcophagus sarcoo = new Sarcophagus(1,"sarcy", "rusted sarcy");
		sarcoo.getContainer().add(new TopKeyFragment());
		room1.roomGrid[2][2].getObjects().add(sarcoo);	

		Door door2 = new Door("Door to the Broken hall", "Door to room2", room1.roomGrid[Room.ROWS-3][0],false, null);
		room1.roomGrid[Room.ROWS-3][0].getObjects().clear();
		room1.roomGrid[Room.ROWS-3][0].getObjects().add(door2);



		//second room

		Room room2 = new Room("Broken Hall","Broken Hall", LEVEL_2);

		room2.roomGrid[Room.ROWS-3][Room.COLS-1].getObjects().clear();		
		Door d2 = new Door("Door to the Lost memories", "door from room2", room2.roomGrid[Room.ROWS-3][Room.COLS-1],false,door2);
		room2.roomGrid[Room.ROWS-3][Room.COLS-1].getObjects().add(d2);

		d2.setConnectingDoor(door2);
		door2.setConnectingDoor(d2);

		Sarcophagus sarco = new Sarcophagus(1,"Sarcy", "Rusted sarcophagus");
		sarco.getContainer().add(new BottomKeyFragment());
		room2.roomGrid[3][3].getObjects().add(sarco);	

		room2.roomGrid[2][1].getObjects().add(new Bookcase(0,"mouldy bookcase","mouldy bookcase"));

		room2.roomGrid[2][5].getObjects().add(new Skeleton(0,"Skeleton", "The remains of a glorified warrior"));


		room2.roomGrid[4][0].getObjects().clear();
		Door door3 = new Door("Door to the Lost warriors", "door to room3", room2.roomGrid[4][0], false,null);
		room2.roomGrid[4][0].getObjects().add(door3);

		//third room

		Room room3 = new Room("Lost Warriors", "Lost Warriors", LEVEL_2);

		room3.roomGrid[4][Room.COLS-1].getObjects().clear();
		Door d3 = new Door("Door to the Broken Hall", "door from room 3", room3.roomGrid[4][Room.COLS-1],false ,door3);
		room3.roomGrid[4][Room.COLS-1].getObjects().add(d3);

		d3.setConnectingDoor(door3);
		door3.setConnectingDoor(d3);

		Sarcophagus sarcoy = new Sarcophagus(1,"Sarcophagus", "Rusted Sarcophagus");
		sarcoy.getContainer().add(new MiddleKeyFragment());
		room3.roomGrid[3][3].getObjects().add(sarcoy);	

		room3.roomGrid[2][5].getObjects().add(new ScrollOfStrength());

		room3.roomGrid[2][4].getObjects().add(new Skeleton(0,"Skeleton", "The remains of a glorified warrior"));
		room3.roomGrid[6][6].getObjects().add(new HealthPotion());

		//add locked door


		room3.roomGrid[4][0].getObjects().clear();
		ld2 = new LevelDoor("Locked Door to level 3", "door to level 3", room3.roomGrid[4][0], "Forged Key", LEVEL_3, null);
		room3.roomGrid[4][0].getObjects().add(ld2);


		//level2

		roomsLevel2[0] = startingRoom;
		roomsLevel2[1] = room1;
		roomsLevel2[2] = room2;
		roomsLevel2[3] = room3;

		rooms[4] = startingRoom;
		rooms[5] = room1;
		rooms[6] = room2;
		rooms[7] = room3;
	}


	/**
	 * Create Level 3
	 */
	public void createLevel3(){

		//starting room

		Room startingRoom = new Room("Starting Room for Level 3", "Starting Room for Level3", LEVEL_3);


		startingRoom.roomGrid[4][Room.COLS-1].getObjects().clear();
		ld21 = new LevelDoor("Entrance Door to level 3", "door from level 2", startingRoom.roomGrid[4][Room.COLS-1], "Forged Key",LEVEL_2, ld2);
		startingRoom.roomGrid[4][Room.COLS-1].getObjects().add(ld21);

		ld21.setConnectingDoor(ld2);
		ld2.setConnectingDoor(ld21);

		startingRoom.roomGrid[1][1].getObjects().add(new Tablet("Level 3 Tablet", LEVEL_3.getDescription()));		
		startingRoom.roomGrid[2][4].getObjects().add(new Sarcophagus(1,"Sarcophagus", "Rusted Sarcophagus"));
		startingRoom.roomGrid[4][3].getObjects().add(new Skeleton(0,"Skeleton", "The remains of a glorified warrior"));

		startingRoom.roomGrid[4][1].getObjects().add(new ScrollOfStamina());
		startingRoom.roomGrid[5][1].getObjects().add(new ScrollOfStamina());
		startingRoom.roomGrid[6][1].getObjects().add(new ScrollOfStrength());
		startingRoom.roomGrid[6][2].getObjects().add(new HealthPotion());                       

		startingRoom.roomGrid[Room.ROWS-1][4].getObjects().clear();

		Door door1 = new Door("Door to The Room of Confusion", "door to room 2",startingRoom.roomGrid[Room.ROWS-1][4], false, null);
		startingRoom.roomGrid[Room.ROWS-1][4].getObjects().add(door1);

		//Room2

		Room room2 = new Room("The Room of Confusion","The room of confusion",LEVEL_3);

		room2.roomGrid[0][4].getObjects().clear();
		Door d2 = new Door("Door to starting room of level 3", "door from starting room", room2.roomGrid[0][4], false, door1);
		room2.roomGrid[0][4].getObjects().add(d2);

		door1.setConnectingDoor(d2);
		d2.setConnectingDoor(door1);

		Bookcase bc = new Bookcase(1,"mouldy bookcase","mouldy bookcase");
		room2.roomGrid[2][1].getObjects().add(bc);

		bc.getContainer().add(new HealthPotion());
		bc.getContainer().add(new ScrollOfStrength());

		room2.roomGrid[3][3].getObjects().add(new Key("broken key", "destroyed"));
		room2.roomGrid[3][4].getObjects().add(new Key("ruined key", "broken"));
		room2.roomGrid[4][3].getObjects().add(new Key("polished key", "polished"));
		room2.roomGrid[4][4].getObjects().add(new Key("fragile key", "fragile"));

		room2.roomGrid[2][4].getObjects().add(new Sarcophagus(0,"Sarcophagus", "Rusted Sarcophagus"));
		room2.roomGrid[2][5].getObjects().add(new HealthPotion());
		room2.roomGrid[6][6].getObjects().add(new HealthPotion());


		room2.roomGrid[4][0].getObjects().clear();
		Door door2 = new Door("Door to The hall of mysteries", "door to room 3", room2.roomGrid[4][0], false, null);
		room2.roomGrid[4][0].getObjects().add(door2);

		//room3

		Room room3 = new Room("Hall of Mysteries", "Hall of Mysteries", LEVEL_3);

		room3.roomGrid[4][Room.COLS-1].getObjects().clear();
		Door d3 = new Door("Door to the room of confusion", "Door from room 2", room3.roomGrid[4][Room.COLS-1], false, door2);
		room3.roomGrid[4][Room.COLS-1].getObjects().add(d3);

		d3.setConnectingDoor(door2);
		door2.setConnectingDoor(d3);

		room3.roomGrid[1][3].getObjects().add(new Key("Pristine Key", "perfect key"));
		room3.roomGrid[2][4].getObjects().add(new Key("worn key", "useless key"));
		room3.roomGrid[3][4].getObjects().add(new Key("scratched key", "perfect key"));
		room3.roomGrid[4][3].getObjects().add(new Key("shiny key", "crazyily shiny"));     
		room3.roomGrid[4][4].getObjects().add(new Sarcophagus(1,"sarcy", "rusted sarcy"));

		room3.roomGrid[4][1].getObjects().add(new HealthPotion());
		room3.roomGrid[5][1].getObjects().add(new HealthPotion());
		room3.roomGrid[6][1].getObjects().add(new HealthPotion());

		Bookcase bc1 = new Bookcase(1,"mouldy bookcase","mouldy bookcase");
		room3.roomGrid[2][1].getObjects().add(bc1);
		bc1.getContainer().add(new ScrollOfStrength());
		bc1.getContainer().add(new ScrollOfStrength());
		bc1.getContainer().add(new ScrollOfStamina());


		room3.roomGrid[0][4].getObjects().clear();
		Door d4 = new Door("Door to The Lost Puzzles", "door to room 4",room3.roomGrid[0][4], false, null);
		room3.roomGrid[0][4].getObjects().add(d4);

		//room4 

		Room room4 = new Room("Lost Puzzles", "Lost Puzzles", LEVEL_3);

		room4.roomGrid[Room.ROWS-1][4].getObjects().clear();		
		Door door3 = new Door("Door to Hall of mysteries", "door from room 3",room4.roomGrid[Room.ROWS-1][4], false, d4);
		room4.roomGrid[Room.ROWS-1][4].getObjects().add(door3);

		door3.setConnectingDoor(d4);
		d4.setConnectingDoor(door3);

		room4.roomGrid[3][3].getObjects().add(new Key("broken key", "broken key" ));
		room4.roomGrid[3][5].getObjects().add(new Key("specaled key", "specaled key"));
		room4.roomGrid[5][3].getObjects().add(new Key("chipped key", "chipped key"));
		room4.roomGrid[5][5].getObjects().add(new Key("two ended key", "two ended key"));
		room4.roomGrid[6][6].getObjects().add(new HealthPotion());
		room4.roomGrid[1][6].getObjects().add(new HealthPotion());
		room4.roomGrid[1][1].getObjects().add(new HealthPotion());
		room4.roomGrid[6][1].getObjects().add(new HealthPotion());

		room4.roomGrid[4][4].getObjects().add(new Skeleton(0,"Skeleton", "The remains of a glorified warrior"));

		room4.roomGrid[2][0].getObjects().clear();		
		ld3 = new LevelDoor("Level door to level 4","Level door to level 4", room4.roomGrid[2][0],"chipped key",LEVEL_4,null);
		room4.roomGrid[2][0].getObjects().add(ld3);


		//level 3

		roomsLevel3[0] = startingRoom;
		roomsLevel3[1] = room2;
		roomsLevel3[2] = room3;
		roomsLevel3[3] = room4;

		rooms[8] = startingRoom;
		rooms[9] = room2;
		rooms[10] = room3;
		rooms[11] = room4;

	}

	/**
	 * Create level 4
	 */
	public void createLevel4(){

		//starting room

		Room startingRoom = new Room("Starting Room of level 4", "Starting room of level4", LEVEL_4);		

		startingRoom.roomGrid[2][Room.COLS-1].getObjects().clear();		
		ld31 = new LevelDoor("Entrance to level 4", "door from level 3", startingRoom.roomGrid[2][Room.COLS-1],"Pristine Key",LEVEL_3, ld3);
		startingRoom.roomGrid[2][Room.COLS-1].getObjects().add(ld31);

		ld31.setConnectingDoor(ld3);
		ld3.setConnectingDoor(ld31);

		//add the engineer
		Engineer eng = new Engineer(startingRoom.roomGrid[6][6]);
		startingRoom.roomGrid[6][6].getObjects().add(eng);
		NPCS.add(eng);


		Bookcase bc = new Bookcase(1,"Mouldy bookcase","Mouldy bookcase");
		startingRoom.roomGrid[2][1].getObjects().add(bc);	
		bc.getContainer().add(new ExplosiveRecipe());

		startingRoom.roomGrid[1][6].getObjects().add(new HealthPotion());
		startingRoom.roomGrid[4][4].getObjects().add(new Skeleton(0,"Skeleton", "The remains of a glorified warrior"));
		startingRoom.roomGrid[1][1].getObjects().add(new Tablet("Level 4 Tablet", LEVEL_4.getDescription()));	

		startingRoom.roomGrid[4][0].getObjects().clear();
		Door door1 = new Door("Door to the Spider cavern", "Door to room1",startingRoom.roomGrid[4][0], false, null);
		startingRoom.roomGrid[4][0].getObjects().add(door1);


		//room2

		Room room1 = new Room("Spider Cavern", "Spider Cavern", LEVEL_4);

		room1.roomGrid[4][Room.COLS-1].getObjects().clear();
		Door d2 = new Door("Door to starting room of level 4" , "door from room starting",room1.roomGrid[4][Room.COLS-1], false, door1);
		room1.roomGrid[4][Room.COLS-1].getObjects().add(d2);

		d2.setConnectingDoor(door1);
		door1.setConnectingDoor(d2);

		NPC gsv = new GeneralSuppliesVendor(room1.roomGrid[6][6]);
		room1.roomGrid[6][6].getObjects().add(gsv);
		NPCS.add(gsv);

		Bookcase bc1 = new Bookcase(1,"mouldy bookcase","mouldy bookcase");
		room1.roomGrid[2][1].getObjects().add(bc1);	
		bc1.getContainer().add(new ExplosiveRecipe());
		bc1.getContainer().add(new ScrollOfStamina());

		room1.roomGrid[6][1].getObjects().add(new ScrollOfStrength());

		room1.roomGrid[0][4].getObjects().clear();
		rb = new RobustDoor("Door to level Draco's Lair", "door to level 5", room1.roomGrid[0][4], LEVEL_5, null);
		room1.roomGrid[0][4].getObjects().add(rb);

		room1.roomGrid[4][4].getObjects().add(new Skeleton(0,"Skeleton", "The remains of a glorified warrior"));

		Sarcophagus sarcoy = new Sarcophagus(1,"Sarcophagus", "Rusted Sarcophagus");
		sarcoy.getContainer().add(new Flint());
		room1.roomGrid[2][5].getObjects().add(sarcoy);	

		room1.roomGrid[3][3].getObjects().add(new HealthPotion());

		room1.roomGrid[Room.ROWS-1][4].getObjects().clear();		
		SpiderwebDoor sd = new SpiderwebDoor("Woven covered door to the Torture Dungeon", "Woven covered door to room 3", room1.roomGrid[Room.ROWS-1][4], null);
		room1.roomGrid[Room.ROWS-1][4].getObjects().add(sd);

		//room3

		Room room2 = new Room("Torture Dungeon", "Torture Dungeon", LEVEL_4);


		room2.roomGrid[0][4].getObjects().clear();


		SpiderwebDoor sd1 = new SpiderwebDoor("Woven covered door to the Spider Cavern", "Woven covered door to room 2", room2.roomGrid[0][4], sd);
		room2.roomGrid[0][4].getObjects().add(sd1);

		sd1.setConnectingDoor(sd);
		sd.setConnectingDoor(sd1);

		room2.roomGrid[3][Room.COLS-3].getObjects().add(new Skeleton(0,"Skeleton", "The remains of a glorified warrior"));

		Sarcophagus sarcoo = new Sarcophagus(1,"Sarcophagus", "Rusted Sarcophagus");
		sarcoo.getContainer().add(new GunpowderPouch());
		room2.roomGrid[5][3].getObjects().add(sarcoo);	


		room2.roomGrid[5][1].getObjects().add(new HealthPotion());

		room2.roomGrid[3][Room.COLS-1].getObjects().clear();
		Door door2 = new Door("Door to the Dungeon of Horrors","door to room 4", room2.roomGrid[3][Room.COLS-1], false, null);
		room2.roomGrid[3][Room.COLS-1].getObjects().add(door2);

		//room 4

		Room room3 = new Room("Dungeon of Horrors","Dungeon of Horrors", LEVEL_4);

		room3.roomGrid[3][0].getObjects().clear();		
		Door d3 = new Door("Door to the Torture Dungeon","Door from room3", room3.roomGrid[3][0], false, door2);
		room3.roomGrid[3][0].getObjects().add(d3);

		d3.setConnectingDoor(door2);
		door2.setConnectingDoor(d3);

		room3.roomGrid[2][4].getObjects().add(new Sarcophagus(0,"Sarcophagus", "A rusted sarcophagus"));

		Bookcase bc3 = new Bookcase(1,"mouldy bookcase","mouldy bookcase");
		room3.roomGrid[2][1].getObjects().add(bc3);	
		bc3.getContainer().add(new HealthPotion());

		room3.roomGrid[3][3].getObjects().add(new ScrollOfStamina());

		Skeleton sk = new Skeleton(1, "Scary Skeleton",  "Scary Skeleton");
		sk.getContainer().add(new Fuse());
		room3.roomGrid[5][2].getObjects().add(sk);

		//level 4

		roomsLevel4[0] = startingRoom;
		roomsLevel4[1] = room1;
		roomsLevel4[2] = room2;
		roomsLevel4[3] = room3;

		rooms[12] = startingRoom;
		rooms[13] = room1;
		rooms[14] = room2;

	}

	/**
	 * Create level 5
	 */
	public void createLevel5(){

		Room startingRoom = new Room("Draco's Lair", "Draco's Lair", LEVEL_5);

		startingRoom.roomGrid[Room.COLS-1][4].getObjects().clear();
		rb1 = new RobustDoor("Entrance door to level 5", "door from level 4", startingRoom.roomGrid[Room.COLS-1][4],LEVEL_4,  rb);
		startingRoom.roomGrid[Room.COLS-1][4].getObjects().add(rb1);

		rb1.setConnectingDoor(rb);
		rb.setConnectingDoor(rb1);


		startingRoom.roomGrid[3][3].getObjects().add(new Dragon(startingRoom.roomGrid[3][3], 0, 0, 0, 0));	
		startingRoom.roomGrid[4][1].getObjects().add(new Orb("final mission", "touch this and you win"));



	}
	public Room[] getRooms(){
		return rooms;
	}

	public void addPlayer(Player p){
		STARTING_LOCATION.getObjects().add(p);
		playersInGame.add(p);
	}


	/**
	 * Get the name.
	 * @return String name
	 */
	public String getName(){
		return name;
	}

	/**
	 * Get the description.
	 * @return String description
	 */
	public static String getDescription(){
		return description;
	}

	public List<Player> getPlayers(){
		return playersInGame;
	}



	/**'
	 * helper method to reference rooms by their IDs
	 * @param roomId
	 * @return
	 */
	public static Room getRoom(int roomId) {
		return rooms[roomId];
	}

	public String toXML(){
		XMLFormatter x = new XMLFormatter();

		x.appendTag("gameworld.TowerOfFate");

		
		x.appendTag("playersInGame");
		x.append("\n");
	
		for(Player p : players){
			
			x.append(p.toXML());
		
		}
		x.closeTag();
		

		x.appendTag("rooms");
		x.append("\n");
		
		for(Room r : rooms){
			if (r != null)
				x.append(r.toXML());
		}
		x.closeTag();
		
		
		x.closeTag();
		return x.getXML();
	}

	public static TowerOfFate fromXML(XMLObject o){
		
		
		System.out.println(o);
		
		
		TowerOfFate newGame = new TowerOfFate();
		
		//clear previous rooms
		TowerOfFate.rooms = new Room[200];
		NPCS.clear();
		newGame.playersInGame.clear();
		players.clear();
	
		String startingTag = o.getNextTag();
		
		while(o.peek().getTag().equals("gameworld.Player")){
			
		System.out.println("TURE");
		XMLObject player = o.getNextObject();
		players.add((Player) player.getObject());
		}
		
		//clear closing tag
		o.getNextTag();
		
		//read all the rooms
		startingTag = o.getNextTag();
		System.out.println("rooom == " + startingTag);
				
		System.out.println("peek == " + o.peek().getTag());
		while(o.peek().getTag().equals("gameworld.Room")){
			
		
			XMLObject room = o.getNextObject();
			
		    Room r = (Room) room.getObject();
			rooms[r.getId()] = r;
			
			System.out.println("ITS WORKS adsfasdfasd " + r.getId());
			}
		
		return newGame;

	}

}
