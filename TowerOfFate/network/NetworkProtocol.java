package network;

/**
 * A network protocol that governs the communication between the client and the server.
 * @author kayecalv id: 300155433
 *
 */
public class NetworkProtocol {
	
	public static final int MOVE_UP = 0;
	public static final int TURN_LEFT = 1;
	public static final int TURN_RIGHT = 2;
	public static final int PLAYER_OBJECT = 3;
	public static final int ROOM_OBJECT = 4;
	public static final int GRIDLOCATION_UPDATE = 5;
	public static final int DIRECTION_CHANGE = 6;
	public static final int PICKUP = 7;
	public static final int PICKUPITEM = 8;
	public static final int PICKUPKEY = 9;
	public static final int INTERACT = 10;
	public static final int INSPECT = 11;
	public static final int INTERACTIVE_PICKUP = 12;
	public static final int TEXT_PACKET = 13;
	public static final int KEY_RING = 14;
	public static final int BACKPACK = 15;
	public static final int GRIDLOCATION_NO_UPDATE = 16;
	public static final int DROP = 17;
	public static final int DESTROY = 18;
	public static final int USE = 19;
	public static final int CONTAINER_INSPECT = 20;
	public static final int DESTROY_ITEM = 21;
	public static final int DESTROY_KEY = 22;
	public static final int USE_ITEM = 23;
	public static final int GRIDLOCATION_ROOM_UPDATE = 24;
	public static final int NPC_GRIDLOCATION_UPDATE = 25;
	public static final int NPC_GRIDLOCATION_NO_UPDATE = 26;
	public static final int GAME_PORT = 27;
	public static final int STARTING_COUNTDOWN = 28;
	public static final int DISPLAY_DIALOG = 29;
	public static final int PLAYER_LEVEL_UPDATE = 30;
	public static final int PLAYERS_JOINED = 31;
	public static final int NUMBER_OF_PLAYERS = 32;
	public static final int START_GAME = 33;
	public static final int MONSTER = 34;
	public static final int PLAYER_ATTACKED = 35;
	public static final int PLAYER_DIED = 36;
	public static final int MONSTER_DIED = 37;
	public static final int BATTLE_OVER = 38;
	public static final int DIED_IN_BATTLE = 39;
	public static final int PLAYER_REVIVED = 40;
	public static final int GENERAL_TRADE = 41;
	public static final int TRADE_OVER = 42;
	public static final int BUY_ITEM = 43;
	public static final int GAME_OVER = 44;
	
	
}
