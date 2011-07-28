package gameworld;


import java.util.ArrayList;
import java.util.List;

import dataStorage.XMLFormatter;
import dataStorage.XMLObject;

/**
 * This class represents a Player. A Player represents a player in the game and all of it's state.
 * @author Calvin Kaye
 *
 */
public class Player extends CombatObject{

	private int goldAmount = 100; // Current amount of gold on the player.
	private int playerLevel = 1; 
	private int experienceCap = 250; // How much experience required to get a new level.
	private int currentExperience = 0;
	private transient Backpack backpack = new Backpack(getName()+ "'s Backpack");
	private transient Keyring keyring  = new Keyring(getName()+ "'s keyring");
	private int stamina = 0;
	private static final int STARTING_HEALTH = 100; // All player's start with 100 health.
	private transient Room currentRoom; // THe current room the player is in.
	public enum Direction { NORTH , EAST , SOUTH , WEST };
	private Direction directionFacing = Direction.NORTH; // All player's start off facing north.
	public int UID;
	private int level;
	private transient boolean fightingMonster = false;
	private transient boolean isTrading = false;
	private boolean killedDraco = false;
	
	//xml related
	public int roomID;
	public int rowID;
	public int colID;


	/**
	 * Constructs a new Player object.
	 * @param gl The grid location the player will start on.
	 * @param name the name of the player.
	 */
	public Player(GridLocation gridLocation, String name, String description, Room currentRoom, int uid) {

		//100 starting health
		//20 strength
		//0.15 dodge (15% dodge)
		super(gridLocation, name, description, STARTING_HEALTH , 20 , 0.15);
		backpack.setOwner(this);
		keyring.setOwner(this);
		this.currentRoom = currentRoom;
		UID = uid;
		
		if(currentRoom != null)
			this.level = currentRoom.getLevel().getLevelNumber();

	}

	public void killDraco(){
		killedDraco = true;
	}

	public boolean hasKilledDraco(){
		return killedDraco;
	}

	public void setBackpack(Backpack b){
		this.backpack = b;
	}


	public GridLocation getGridLocation(){
		return super.getGridLocation();
	}

	public int getLevel(){
		return level;
	}

	public void setLevel(int levelUpdate){
		level = levelUpdate;
	}

	public static Direction rotateC90(Direction dir){

		switch(dir){

		case WEST:
			return Direction.NORTH;

		case EAST:
			return Direction.SOUTH;

		case NORTH:
			return Direction.EAST;

		case SOUTH:
			return Direction.WEST;
		}

		return null;

	}

	public static Direction rotateClockWise(int degrees , Direction dir){

		int degreeCheck = 0;

		while(degreeCheck != degrees){
			dir = rotateC90(dir);
			degreeCheck += 90;
		}

		return dir;
	}

	public static int rotateDegrees(Direction dir){

		switch (dir){
		case NORTH:
			return 0;
		case SOUTH:
			return 180;
		case WEST:
			return 90;
		case EAST:
			return 270;
		default: // doesn't reach here.
			return 0;

		}
	}

	/**
	 * Turn the player left.
	 */
	public void turnLeft (){


		switch(directionFacing){
		case NORTH :
			directionFacing = Direction.WEST;
			break;
		case WEST :
			directionFacing = Direction.SOUTH;
			break;
		case SOUTH :
			directionFacing = Direction.EAST;
			break;
		case EAST :
			directionFacing = Direction.NORTH;
			break;
		}

		//super.getGridLocation().broadCast();
	}

	/**
	 * Turn the player right.
	 */
	public void turnRight(){

		switch(directionFacing){
		case NORTH :
			directionFacing = Direction.EAST;
			break;
		case EAST :
			directionFacing = Direction.SOUTH;
			break;
		case SOUTH :
			directionFacing = Direction.WEST;
			break;
		case WEST :
			directionFacing = Direction.NORTH;
			break;
		}

		//broadcast the turn


	}

	/**
	 * Get the direction the player is currently facing.
	 * @return Direction
	 */
	public Direction getDirectionFacing(){
		return directionFacing;
	}

	/**
	 * Move the player from its current grid location to one grid location in front. 
	 * Will not move the player if the grid location in front has a boundary object in it.
	 * @return the monster generated. Returns null if no monster generated.
	 * 
	 */
	public synchronized Monster move(){

		GridLocation nextGridLocation = currentRoom.moveNextGridLocation(this);

		if(getGridLocation().equals(nextGridLocation)){ // The player has not moved.
			return null;
		}
		else{ //The player has moved.
			getGridLocation().removeGameObject(this);
			nextGridLocation.addGameObject(this);
			setGridLocation(nextGridLocation);
			return nextGridLocation.generateMonster();
		}
	}

	/**
	 * Move the player through the specified door.
	 * @param door the door to move through.
	 */
	public void moveThrough(Door door){

		if(door.getClass() == Door.class){

			currentRoom = door.getConnectingDoor().getGridLocation().getRoom();
			door.setOpen();
			getGridLocation().removeGameObject(this);
			setGridLocation(door.getConnectingDoor().getGridLocation());
			getGridLocation().addGameObject(this);
			move();

		}
		else { // it's a locked door.

			currentRoom = door.getConnectingDoor().getGridLocation().getRoom();
			getGridLocation().removeGameObject(this);
			setGridLocation(door.getConnectingDoor().getGridLocation());
			getGridLocation().addGameObject(this);
			move();
			if(door instanceof LevelDoor){
				LevelDoor ld = (LevelDoor) door;
				level = ld.getLevelTo().getLevelNumber();
			}
		}

	}

	/**
	 * Checks to see if the player can interact with an object
	 * (checks the grid location one square in front of the player for an interactive object).
	 * @return An interactive object that can be interacted with.
	 * Returns null if there is no object to interact with.
	 */
	public InteractiveObject canInteract(){

		GridLocation nextGridLocation = currentRoom.examineGridLocation(this);

		synchronized(nextGridLocation.getObjects()){
			for(GameObject gO : nextGridLocation.getObjects()){
				if(gO instanceof InteractiveObject){
					InteractiveObject iO = (InteractiveObject) gO;
					return iO;
				}
			}
		}

		return null;

	}

	/**
	 * Checks to see if the player can inspect a game object.
	 * (checks the grid location one square in front of the player for a game object to inspect).
	 * @return A game object that can be inspected.
	 * Returns null if there is no game object to inspect.
	 */
	public GameObject canInspect(){

		GridLocation nextGridLocation = currentRoom.examineGridLocation(this);

		synchronized(nextGridLocation.getObjects()){
			for(GameObject gO : nextGridLocation.getObjects()){

				if(gO instanceof NPC){
					return gO;
				}

				if(gO instanceof InanimateObject){

					if(gO.getClass() == Wall.class || gO.getClass() == Player.class){						
					}
					else{
						return gO;
					}

				}
			}
		}

		return null;

	}

	/**
	 * Checks to see if the player can pick up an item
	 * (checks the grid location the player is on for an item to pick up).
	 * @return An item that can be picked up.
	 * Returns null if there is no item to pick up.
	 */
	public Item canPickUp(){

		synchronized(getGridLocation().getObjects()){
			for(GameObject gO : getGridLocation().getObjects()){
				if(gO instanceof Item){
					return (Item) gO;
				}
			}
		}

		return null;

	}

	/**
	 * The rewards of killing a monster.
	 * @param monster the monster killed.
	 */
	public void loot(int xp , int goldLoot){

		changeCurrentExperience(xp);
		changeGoldAmount(goldLoot);

	}

	/**
	 * Get the current room of the player.
	 * @return the current room the player is in.
	 */
	public Room getCurrentRoom(){
		return currentRoom;
	}

	/**
	 * Set the current room of the player.
	 * @param r the room the player will now be in.
	 */
	public void setCurrentRoom(Room r){
		currentRoom = r;
		this.level = currentRoom.getLevel().getLevelNumber();
	}

	/**
	 * Get this player's experience level.
	 * @return this player's experience level.
	 */
	public int getPlayerLevel(){
		return playerLevel;
	}


	/**
	 * Change the max health of the player based on a set amount of stamina.
	 * @param staminaAmount the stamina change.
	 */
	public void changeMaxHealth(int staminaAmount){
		stamina += staminaAmount;
		setMaxHealth(getMaxHealth() + (stamina * 10));
		stamina = 0;
	}

	/**
	 * Get the amount of gold this player owns.
	 * @return gold amount.
	 */
	public int getGoldAmount(){
		return goldAmount;
	}

	/**
	 * Change this player's gold by the set amount.
	 * @param amount the amount of gold.
	 */
	public void changeGoldAmount(int amount){
		goldAmount += amount;
	}

	/**
	 * Checks to see if this player can pay for a certain amount.
	 * @param amount the amount the player must pay.
	 * @return boolean : can pay or not.
	 */
	public boolean canPay(int amount){

		if(goldAmount - amount < 0){
			return false;
		}
		else{
			return true;
		}
	}

	/**
	 * Change the experience cap.
	 */
	public void changeExpCap(){
		experienceCap += 250;
	}

	/**
	 * Get the experience cap for this player (based on his/her current experience level).
	 * @return
	 */
	public int getExperienceCap(){
		return experienceCap;
	}

	/**
	 * Get the current experience amount this player has.
	 * @return current experience.
	 */
	public int getCurrentExperience(){
		return currentExperience;
	}

	/**
	 * Change the current experience this player has by the set amount.
	 * @param experience amount.
	 */
	public void changeCurrentExperience(int amount){

		currentExperience += amount;
		int overflow = currentExperience - experienceCap;
		checkLevelUp(overflow);

	}


	/**
	 * Checks to see if the player has levelled up. If the player has levelled up then it makes the appropriate changes.
	 * @param int the amount of experience that has gone over the experience cap to be added into the next levels experience total.
	 */
	private void checkLevelUp(int overflow){

		if(overflow >= 0){		
			playerLevel++;
			changeExpCap();
			this.currentExperience = overflow;
			changeMaxHealth(10);
			setCurrentHealth(getMaxHealth());
			setStrength(getStrength() + 10);		
		}

	}

	/**
	 * Requests a trade to an NPC.
	 * @param npc to trade with.
	 * @return boolean : Do you have the requirements to trade with this NPC.
	 */
	public boolean requestTrade(NPC npc){

		if(!npc.checkRequirements(this)){
			return false;
		}

		return true;	
	}

	/**
	 * Get the key ring this player owns.
	 * @return Keyring.
	 */
	public Keyring getKeyring(){
		return keyring;
	}

	/**
	 * Get the backpack this player owns.
	 * @return Backpack.
	 */
	public Backpack getBackpack(){
		return backpack;
	}

	/**
	 * Change the player's strength by the set amount.
	 * @param strength amount.
	 */
	public void changeStrength(int amount){

		setStrength(getStrength() + amount); 
	}




	/**
	 * This player attacks the specified monster.
	 * @param mon the monster to attack.
	 * @return the String result of the attack.
	 */
	public String attack(Monster mon){


		if(!dodge()){
			if(criticalStrike()){			
				mon.setCurrentHealth(mon.getCurrentHealth() - getStrength()*2);
				return  getName() + " critically hit " + mon.getName() + " for " + getStrength() * 2;
			}
			else{			
				mon.setCurrentHealth(mon.getCurrentHealth() - getStrength());
				return getName() + " hit " + mon.getName() + " for " + getStrength();
			}
		}
		else{
			return  mon.getName() + " dodges your attack";
		}
	}

	/**
	 * Interact with an interactive object.
	 * @param intObj the interactive object to interact with.
	 * @return the String result of interacting with the interactive object.
	 */
	public String interact(InteractiveObject intObj){
		String s = intObj.interactEffect(this);
		return s;
	}

	/**
	 * Destroy a specified item in this player's backpack or keyring.
	 * @param slot the index of the item in the container to destroy.
	 * @param inBackpack is the item in the backpack or the keyring.
	 * @return String : the result of destroying the item or key.
	 */
	public String destroy(int slot, boolean inBackpack){

		if(inBackpack){
			List<Item> bp = backpack.getContainer();
			Item item =	bp.remove(slot);
			return "You destroy " + item.getName();
		}
		else{
			List<Item> kr = keyring.getContainer();
			Item item = kr.remove(slot);
			return "You destroy " + item.getName();
		}

	}

	/**
	 * Checks to see if this player's backpack is full.
	 * @return is the backpack full or not.
	 */
	public boolean checkFullBackpack(){

		if(backpack.isFull()){
			return true;
		}
		else{
			return false;
		}
	}


	public String interactivePickup(Item i){

		List<Item> bp = backpack.getContainer();
		List<Item> kr = keyring.getContainer();

		if(i instanceof VendorItem){
			if(bp.size() < Backpack.MAX_ITEMS){
				bp.add(i);
				i.setContainer(backpack);
				return "You receive loot " + i.getName();
			}
			else{
				return "You are over burdened";
			}
		}


		else if(i.getClass() != Key.class){
			if(!bp.contains(i)){
				if(bp.size() < Backpack.MAX_ITEMS){
					bp.add(i);
					i.setContainer(backpack);
					return "You receive loot " + i.getName();
				}
				else{
					return "You are over burdened";
				}
			}
			else{
				return "You have too many " + i.getName()  + " 's";
			}

		}

		else{
			if(!kr.contains(i)){
				if(kr.size() < Keyring.MAX_KEYS){
					Key k = (Key) i;
					kr.add(k);
					k.setContainer(keyring);
					return "You receive loot " + k.getName();
				}
				else{
					return "You are over burdened";
				}

			}
			else{
				return "You have too many " + i.getName()  + " 's";
			}
		}

	}


	/**
	 * Pick up an item.
	 * @return the String result of picking up the item.
	 */
	public String pickUp(Item i){

		List<Item> bp = backpack.getContainer();
		List<Item> kr = keyring.getContainer();

		if(i.getClass() != Key.class){

			if(!bp.contains(i)){
				if(bp.size() < Backpack.MAX_ITEMS){
					bp.add(i);
					i.setContainer(backpack);
					getGridLocation().removeGameObject(i); // remove the item from the grid location.
					return "You receive loot " + i.getName();
				}
				else{
					return "You are over burdened";
				}
			}
			if(i instanceof VendorItem){
				if(bp.size() < Backpack.MAX_ITEMS){
					bp.add(i);
					i.setContainer(backpack);
					getGridLocation().removeGameObject(i); // remove the item from the grid location.
					return "You receive loot " + i.getName();
				}
				else{
					return "You are over burdened";
				}

			}
			else{
				return "You have too many of this " + i.getName();
			}
		}
		else{
			if(!kr.contains(i)){
				if(kr.size() < Keyring.MAX_KEYS){
					Key k = (Key) i;
					kr.add(k);
					k.setContainer(keyring);
					if(!k.isUnique()){
						getGridLocation().removeGameObject(k);
					}
					return "You receive loot " + i.getName();
				}
				else{
					return "You have too many keys";
				}
			}
			else{
				return "You have too many " + i.getName()  + " 's";
			}
		}

	}

	/**
	 * Drop a vendor item currently in the players backpack.
	 * @param backpackSlot the index of the item in the backpack to drop.
	 * @return the String result of dropping the item.
	 */
	public String drop(int backpackSlot){

		VendorItem vi = (VendorItem) backpack.getContainer().remove(backpackSlot);
		vi.setContainer(null);
		getGridLocation().addGameObject(vi);
		return "You drop " + vi.getName();

	}

	/**
	 * Inspect a game object out on the floor.
	 * @param gO the game object to inspect.
	 * @return the description of the game object.
	 */
	public String inspect(GameObject gO){

		return gO.getDescription();

	}

	/**
	 * Inspect an Item in your backpack or key ring.
	 * @param itemIndex the item index in the backpack or key ring
	 * @param inBackpack is the item in your backpack or key ring. 
	 * @return
	 */
	public String inspect(int itemIndex , boolean inBackpack){

		if(inBackpack){
			return backpack.getContainer().get(itemIndex).getDescription();
		}
		else{
			return keyring.getContainer().get(itemIndex).getDescription();

		}
	}


	//Equality for players is defined by having the same name.
	public boolean equals(Object other){

		if(other == null){
			return false;
		}
		if(other.getClass() != Player.class){
			return false;
		}

		Player p = (Player) other;

		return UID == p.UID;
	}


	public boolean isDead() {

		//may have to change
		if(getCurrentHealth() <= 0){
			return true;
		}
		else{
			return false;
		}

	}

	public void setDirection(Direction dir) {
		directionFacing = dir;

	}

	public boolean inBattle(){
		return fightingMonster;
	}

	public void fightingBattle(){
		fightingMonster = true;
	}

	public void battleOver(){
		fightingMonster = false;
	}

	public boolean inTrade(){
		return isTrading;
	}

	public void trading(){
		isTrading = true;
	}

	public void tradeOver(){
		isTrading = false;
	}

	public String toXML(){

		/**
		 * private int goldAmount = 100; // Current amount of gold on the player.
	private int playerLevel = 1; 
	private int experienceCap = 250; // How much experience required to get a new level.
	private int currentExperience = 0;
	private transient Backpack backpack = new Backpack(getName()+ "'s Backpack");
	private transient Keyring keyring  = new Keyring(getName()+ "'s keyring");
	private int stamina = 0;
	private static final int STARTING_HEALTH = 100; // All player's start with 100 health.
	private transient Room currentRoom; // THe current room the player is in.
	public enum Direction { NORTH , EAST , SOUTH , WEST };
	private Direction directionFacing = Direction.NORTH; // All player's start off facing north.
	public int UID;
	private int level;
	private transient boolean fightingMonster = false;
	private transient boolean isTrading = false;
	private boolean killedDraco = false;
		 * 
		 * 
		 */


		XMLFormatter x = new XMLFormatter();

		x.appendTag("gameworld.Player");

		x.appendTag("name");
		x.append(super.getName());
		x.closeTag();

		x.appendTag("description");
		x.append(super.getDescription());
		x.closeTag();

		x.appendTag("UID");
		x.append("" + UID);
		x.closeTag();

		x.appendTag("currentExperience");
		x.append("" + currentExperience);
		x.closeTag();

		x.appendTag("stamina");
		x.append("" + stamina);
		x.closeTag();

		x.appendTag("directionFacing");
		x.append("" + directionFacing);
		x.closeTag();
		

		x.appendTag("currentRoom");
		x.append("" + currentRoom.getId());
		x.closeTag();


		x.appendTag("goldAmount");
		x.append("" + goldAmount);
		x.closeTag();

		x.appendTag("playerLevel");
		x.append("" + playerLevel);
		x.closeTag();

		x.appendTag("killDraco");
		x.append("" + killedDraco);
		x.closeTag();


		if (!backpack.getContainer().isEmpty()){
			//Backpackitems
			x.appendTag("backpack");

			x.appendTag("Listgameworld.Item");
			int count = 0;
			for (Item i : backpack.getContainer()){
				x.appendTag("index" + count);

				//this  new line is very important!
				x.append("\n");

				x.append(i.toXML());
				x.closeTag();

			}
			x.closeTag();
			x.closeTag();
		}
		if (!keyring.getContainer().isEmpty()){
			//Keyring Items
			x.appendTag("keyring");
			x.appendTag("Listgameworld.Item");
			int count = 0;
			for (Item i : keyring.getContainer()){
				x.appendTag("index" + count);

				//this  new line is very important!
				x.append("\n");

				x.append(i.toXML());
				x.closeTag();

			}
			x.closeTag();
			x.closeTag();
		}

		x.closeTag();

		return x.getXML();
	}
	/**
	 * Method for recreating a Player from an XMLObject
	 * 
	 * @param o
	 * @return
	 */
	public static Player fromXML(XMLObject o){

		String name = o.getNextData();
		String description = o.getNextData();
		int uid = new Integer(o.getNextData());
		int currentExperience = new Integer(o.getNextData());
		int stamina = new Integer(o.getNextData());
		String directionFacing = o.getNextData();

		int currentRoom = new Integer(o.getNextData());
		int goldAmount = new Integer(o.getNextData());
		int playerLevel = new Integer(o.getNextData());
		boolean killedDraco = new Boolean(o.getNextData());


		/**
		XMLObject backPack = o.getNextObject();

		System.out.println("$$$$$" + backPack.getName());

		Backpack b = (Backpack) o.getObject();

		XMLObject keyring = o.getNextObject();		
		Keyring k = (Keyring) o.getObject();

		 **/
		
		ArrayList backpackList = null;
		ArrayList keyringList = null;

		String nextTag = o.getNextTag();
		//check if there is a bag next
		if(nextTag.equals("backpack")) {
			backpackList = o.getNextItemList();
			
			nextTag = o.getNextTag();
		}
		//check if there is a keyring next
		if (o.getNextData().equals("keyring")){
			keyringList = o.getNextItemList();
		}
				

		Player p = new Player(null, name, description, null, uid);

		p.currentExperience = currentExperience;
		p.stamina = stamina;
		p.goldAmount = goldAmount;
		p.playerLevel = playerLevel;
		p.killedDraco = killedDraco;

		//set player containers
		if (backpackList != null)
			p.getBackpack().setContainer(backpackList);
		if (keyringList != null)
			p.getKeyring().setContainer(keyringList);

		return p;
	}
}
