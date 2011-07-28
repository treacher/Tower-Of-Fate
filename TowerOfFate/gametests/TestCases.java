package gametests;

import gameworld.*;
import gameworld.Player.Direction;
import static org.junit.Assert.*;
import org.junit.Test;

public class TestCases {

	//Testing non trivial player methods
	
	@Test public void testPlayerInventoryFull(){
		
		Room r = new Room("Room","testroom", new Level("", "", 1));
		Player p = new Player(null, "damian", "LZgamer", r, 0);
		
		p.getBackpack().setOwner(p);
		for(int i = 0; i < Backpack.MAX_ITEMS; i++){
			HealthPotion hp = new HealthPotion();
			p.getBackpack().getContainer().add(hp);
		}
		
		assertTrue(p.checkFullBackpack());
	}
	
	
	@Test public void testPlayerInventoryNotFull(){
		
		Room r = new Room("Room","testroom", new Level("", "", 1));
		Player p = new Player(null, "damian", "LZgamer", r, 0);
		
		p.getBackpack().setOwner(p);
		for(int i = 0; i < Backpack.MAX_ITEMS-1; i++){ //1 less item
			HealthPotion hp = new HealthPotion();
			p.getBackpack().getContainer().add(hp);
		}
		
		assertFalse(p.checkFullBackpack());
	}
	
	@Test public void testValidPlayerMove1(){ //testing north
		
		Room r = new Room("Room","testroom", new Level("", "", 1));
		Player p = new Player(null, "damian", "LZgamer", r, 0);
		
		r.roomGrid[3][3].getGameObjects().add(p);
		p.setGridLocation(r.roomGrid[3][3]);
		p.move();		
		assertEquals(p.getGridLocation().getColIndex(), 2); //moved up one square
		
	}
	
	@Test public void testValidPlayerMove2(){ //testing west
		
		Room r = new Room("Room","testroom", new Level("", "", 1));
		Player p = new Player(null, "damian", "LZgamer", r, 0);
		
		r.roomGrid[3][3].getGameObjects().add(p);
		p.setGridLocation(r.roomGrid[3][3]);
		p.setDirection(Direction.WEST);
		p.move();		
		assertEquals(p.getGridLocation().getRowIndex(), 2); //moved one to the left
		
	}
	
	
	@Test public void testValidPlayerMove3(){ //testing south
		
		Room r = new Room("Room","testroom", new Level("", "", 1));
		Player p = new Player(null, "damian", "LZgamer", r, 0);
		
		r.roomGrid[3][3].getGameObjects().add(p);
		p.setGridLocation(r.roomGrid[3][3]);
		p.setDirection(Direction.SOUTH);
		p.move();		
		assertEquals(p.getGridLocation().getColIndex(), 4); 
		
	}
	
	
	@Test public void testValidPlayerMove4(){ //testing east
		
		Room r = new Room("Room","testroom", new Level("", "", 1));
		Player p = new Player(null, "damian", "LZgamer", r, 0);
		
		r.roomGrid[3][3].getGameObjects().add(p);
		p.setGridLocation(r.roomGrid[3][3]);
		p.setDirection(Direction.EAST);
		p.move();		
		assertEquals(p.getGridLocation().getRowIndex(), 4); 
		
	}
	
	@Test public void testValidPlayerMove5(){
		
		Room r = new Room("Room","testroom", new Level("", "", 1));
		Player p = new Player(null, "damian", "LZgamer", r, 0);
		
		r.roomGrid[3][3].getGameObjects().add(p);
		p.setGridLocation(r.roomGrid[3][3]);
		p.setDirection(Direction.EAST);
		p.move();
		p.move();//move twice
		assertEquals(p.getGridLocation().getRowIndex(), 5); 
		
	}
	
	
	@Test public void testInValidPlayerMove(){   //THIS IS INVALID CANNOT MOVE 2 SQUARES AT ONCE
		
		Room r = new Room("Room","testroom", new Level("", "", 1));
		Player p = new Player(null, "damian", "LZgamer", r, 0);
		
		r.roomGrid[3][3].getGameObjects().add(p);
		p.setGridLocation(r.roomGrid[3][3]);
		p.setDirection(Direction.EAST);
		p.move();		
		
		assertFalse(p.getGridLocation().getRowIndex() == 5); 
		
	}
	

	@Test public void testTurnRight(){
		Room r = new Room("Room","testroom", new Level("", "", 1));
		Player p = new Player(null, "damian", "LZgamer", r, 0);
		p.turnRight();
		
		assertEquals(p.getDirectionFacing(), Direction.EAST);
		
	}
	
	@Test public void testTurnLeft(){
		Room r = new Room("Room","testroom", new Level("", "", 1));
		Player p = new Player(null, "damian", "LZgamer", r, 0);
		p.turnLeft();
		
		assertEquals(p.getDirectionFacing(), Direction.WEST);
		
	}
	
	@Test public void testDrop1(){
		
		Room r = new Room("Room","testroom", new Level("", "", 1));
		Player p = new Player(null, "damian", "LZgamer", r, 0);
		
		r.roomGrid[3][3].getGameObjects().add(p);
		p.setGridLocation(r.roomGrid[3][3]);
		
		p.getBackpack().setOwner(p);
		HealthPotion hp = new HealthPotion();
		p.getBackpack().getContainer().add(hp);
		
		p.drop(0);
		
		assertTrue(r.roomGrid[3][3].getGameObjects().contains(hp)); //checks to see if the gridlocation has the healthpotion now that the player has dropped it
		
		
	}
	
	@Test public void testDrop2(){
		
		Room r = new Room("Room","testroom", new Level("", "", 1));
		Player p = new Player(null, "damian", "LZgamer", r, 0);
		
		r.roomGrid[3][3].getGameObjects().add(p);
		p.setGridLocation(r.roomGrid[3][3]);
		
		p.getBackpack().setOwner(p);
		HealthPotion hp = new HealthPotion();
		p.getBackpack().getContainer().add(hp); //add the potion
		
		p.drop(0);
		
		assertTrue(p.getBackpack().getContainer().isEmpty()); //the players backpack is empty because he dropped the health potion
		
		
	}
	
	
	@Test public void testDestroy(){ 
	
		Room r = new Room("Room","testroom", new Level("", "", 1));
		Player p = new Player(null, "damian", "LZgamer", r, 0);
	
		r.roomGrid[3][3].getGameObjects().add(p);
		p.setGridLocation(r.roomGrid[3][3]);
	
		p.getBackpack().setOwner(p);
		HealthPotion hp = new HealthPotion();
		p.getBackpack().getContainer().add(hp); //add the potion
		
		p.destroy(0, true);
		
		assertTrue(p.getBackpack().getContainer().isEmpty());
	}
	
	@Test public void testLoot(){
		Room r = new Room("Room","testroom", new Level("", "", 1));
		Player p = new Player(null, "damian", "LZgamer", r, 0);
		
		p.loot(100,200); //loot received from killing a monster
		
		assertTrue(p.getCurrentExperience() == 100);
		assertTrue(p.getGoldAmount() == 300);
		
		
	}
	
	
	@Test public void testLevelUp(){
		
		Room r = new Room("Room","testroom", new Level("", "", 1));
		Player p = new Player(null, "damian", "LZgamer", r, 0);
		
		p.loot(300, 100);
		
		assertTrue(p.getPlayerLevel() == 2);
		
		
	}
	
	
	@Test public void testBuyItem1(){
		
		Room r = new Room("Room","testroom", new Level("", "", 1));
		Player p = new Player(null, "damian", "LZgamer", r, 0);
		
		HealthPotion hp = new HealthPotion();
		assertTrue(p.canPay(hp.getGoldPrice()));
			
	}
	
	@Test public void testBuyItem2(){
		
		Room r = new Room("Room","testroom", new Level("", "", 1));
		Player p = new Player(null, "damian", "LZgamer", r, 0);
		
		Machete mach = new Machete();
		assertFalse(p.canPay(mach.getGoldPrice()));
		
		
	}
	
	
	@Test public void testRotate(){
		
		Room r = new Room("Room","testroom", new Level("", "", 1));
		Player p = new Player(null, "damian", "LZgamer", r, 0);
		
		assertEquals(p.rotateDegrees(Direction.WEST), 90);
		
		
	}
	
	
	@Test public void testCanPickUp1(){
		
		Room r = new Room("Room","testroom", new Level("", "", 1));
		Player p = new Player(null, "damian", "LZgamer", r, 0);
		
		r.roomGrid[3][3].getGameObjects().add(p);
		p.setGridLocation(r.roomGrid[3][3]);
		
		p.getBackpack().setOwner(p);
		HealthPotion hp = new HealthPotion();
		
		r.roomGrid[3][3].getGameObjects().add(hp);
		
		assertEquals(p.canPickUp() , hp);
		
		
	}
	
	@Test public void testCanPickUp2(){
		
		Room r = new Room("Room","testroom", new Level("", "", 1));
		Player p = new Player(null, "damian", "LZgamer", r, 0);
		
		r.roomGrid[3][3].getGameObjects().add(p);
		p.setGridLocation(r.roomGrid[3][3]);
		
		
		
		
		assertEquals(p.canPickUp() , null); //nothing to pickup
		
		
	}
	
	
	@Test public void testPickUp1(){
		
		Room r = new Room("Room","testroom", new Level("", "", 1));
		Player p = new Player(null, "damian", "LZgamer", r, 0);
		
		r.roomGrid[3][3].getGameObjects().add(p);
		p.setGridLocation(r.roomGrid[3][3]);
		
		p.getBackpack().setOwner(p);
		HealthPotion hp = new HealthPotion();
		
		r.roomGrid[3][3].getGameObjects().add(hp);
		
		p.pickUp(hp);
		
		assertTrue(p.getBackpack().getContainer().contains(hp));
		
	}
	
	@Test public void testPickUp2(){
		
		Room r = new Room("Room","testroom", new Level("", "", 1));
		Player p = new Player(null, "damian", "LZgamer", r, 0);
		
		r.roomGrid[3][3].getGameObjects().add(p);
		p.setGridLocation(r.roomGrid[3][3]);
		
		p.getBackpack().setOwner(p);
		HealthPotion hp = new HealthPotion();
		
		r.roomGrid[3][3].getGameObjects().add(hp);
		
		p.pickUp(hp);
		
		assertFalse(r.roomGrid[3][3].getGameObjects().contains(hp));
		
	}
	

	@Test public void testPickUp3(){ //with key 
		
		Room r = new Room("Room","testroom", new Level("", "", 1));
		Player p = new Player(null, "damian", "LZgamer", r, 0);
		
		r.roomGrid[3][3].getGameObjects().add(p);
		p.setGridLocation(r.roomGrid[3][3]);
		
		p.getBackpack().setOwner(p);
		Key key = new Key("rotten key", "worn down");
		
		r.roomGrid[3][3].getGameObjects().add(key);
		
		p.pickUp(key);
		
		assertTrue(r.roomGrid[3][3].getGameObjects().contains(key)); //key does not get picked up due to game play with other players
		
	}
	
	
	@Test public void testPickUp4(){ //with key again
		
		Room r = new Room("Room","testroom", new Level("", "", 1));
		Player p = new Player(null, "damian", "LZgamer", r, 0);
		
		r.roomGrid[3][3].getGameObjects().add(p);
		p.setGridLocation(r.roomGrid[3][3]);
		
		p.getKeyring().setOwner(p);
		Key key = new Key("rotten key", "worn down");
		
		r.roomGrid[3][3].getGameObjects().add(key);
		
		p.pickUp(key);
		
		assertTrue(p.getKeyring().getContainer().contains(key)); //key does not get picked up due to game play with other players
		
	}
	
	@Test public void testPickUp5(){ //with key 
		
		Room r = new Room("Room","testroom", new Level("", "", 1));
		Player p = new Player(null, "damian", "LZgamer", r, 0);
		
		r.roomGrid[3][3].getGameObjects().add(p);
		p.setGridLocation(r.roomGrid[3][3]);
		
		p.getBackpack().setOwner(p);
		Key key = new Key("rotten key", "worn down");
		
		r.roomGrid[3][3].getGameObjects().add(key);
		
		p.pickUp(key);
		p.pickUp(key);
		
		assertEquals("You have too many rotten key 's", "You have too many " + key.getName()  + " 's"); 
		//cannot pickup more than one key so this string should be returned when trying to pick up the same key twice
	}
	
	
	
	@Test public void testMoveIntoBoundaryObject(){   
		
		Room r = new Room("Room","testroom", new Level("", "", 1));
		Player p = new Player(null, "damian", "LZgamer", r, 0);
		
		r.roomGrid[3][3].getGameObjects().add(p);
		p.setGridLocation(r.roomGrid[3][3]);
		r.roomGrid[4][3].getGameObjects().add(new Sarcophagus(0, "sarcy", "sarcy"));
		p.setDirection(Direction.EAST);
		p.move();		
		
		//PLAYER CANNOT MOVE THROUGH A BOUNDARY OBJECT SO STAYS AT HIS CURRENT POSITION
		assertTrue(p.getGridLocation().getRowIndex() == 3); 
		
	}
	
	@Test public void testMoveOverItem(){   
		
		Room r = new Room("Room","testroom", new Level("", "", 1));
		Player p = new Player(null, "damian", "LZgamer", r, 0);
		
		r.roomGrid[3][3].getGameObjects().add(p);
		p.setGridLocation(r.roomGrid[3][3]);
		r.roomGrid[4][3].getGameObjects().add(new HealthPotion());
		p.setDirection(Direction.EAST);
		p.move();		//moving over a item is valid
		
		
		assertTrue(p.getGridLocation().getRowIndex() == 4); 
		
	}
	

	
	@Test public void testUseScrollOfStrength(){
		
		Room r = new Room("Room","testroom", new Level("", "", 1));
		Player p = new Player(null, "damian", "LZgamer", r, 0);
		
		ScrollOfStrength ss = new ScrollOfStrength();
		p.getBackpack().setOwner(p);		
		ss.setContainer(p.getBackpack());
		p.getBackpack().getContainer().add(ss);

		ss.useEffect(0);
		
		assertTrue(p.getStrength() == 22);
		
		
	}
	
	@Test public void testUseVendorItem(){
		
		Room r = new Room("Room","testroom", new Level("", "", 1));
		Player p = new Player(null, "damian", "LZgamer", r, 0);
		
		ScrollOfStrength ss = new ScrollOfStrength();
		p.getBackpack().setOwner(p);		
		ss.setContainer(p.getBackpack());
		p.getBackpack().getContainer().add(ss);

		ss.useEffect(0);
		
		assertTrue(p.getBackpack().getContainer().isEmpty());
			
	}
	
	@Test public void testUseScrollOfStamina1(){
		
		Room r = new Room("Room","testroom", new Level("", "", 1));
		Player p = new Player(null, "damian", "LZgamer", r, 0);
		
		ScrollOfStamina ss = new ScrollOfStamina();
		p.getBackpack().setOwner(p);		
		ss.setContainer(p.getBackpack());
		p.getBackpack().getContainer().add(ss);

		ss.useEffect(0);
		
		assertTrue(p.getMaxHealth() == 120);
		
	}
	
	@Test public void testUseScrollOfStamina2(){
		
		Room r = new Room("Room","testroom", new Level("", "", 1));
		Player p = new Player(null, "damian", "LZgamer", r, 0);
		
		ScrollOfStamina ss = new ScrollOfStamina();
		ScrollOfStamina sss = new ScrollOfStamina();
		p.getBackpack().setOwner(p);		
		ss.setContainer(p.getBackpack());
		p.getBackpack().getContainer().add(ss);
					
		sss.setContainer(p.getBackpack());
		p.getBackpack().getContainer().add(sss);

		ss.useEffect(0);
		sss.useEffect(0);
		
		
		assertTrue(p.getMaxHealth() == 140); //max health
		assertTrue(p.getCurrentHealth() == 100); //current health
		
	}
	
	@Test public void testTradeWithGeneralSuppliesVendor(){
		
		Room r = new Room("Room","testroom", new Level("", "", 1));
		Player p = new Player(null, "damian", "LZgamer", r, 0);
		
		assertTrue(p.requestTrade(new GeneralSuppliesVendor(r.roomGrid[6][6])));
			
	}
	
	@Test public void testInvalidTradeWithEngineer(){
		
		Room r = new Room("Room","testroom", new Level("", "", 1));
		Player p = new Player(null, "damian", "LZgamer", r, 0);
		
		assertFalse(p.requestTrade(new Engineer(r.roomGrid[6][6]))); //invalid, don't have requirements
			
	}
	
	@Test public void testValidTradeWithEngineer(){
		
		Room r = new Room("Room","testroom", new Level("", "", 1));
		Player p = new Player(null, "damian", "LZgamer", r, 0);
		
		Fuse f = new Fuse();
		GunpowderPouch gp = new GunpowderPouch();
		
		p.getBackpack().setOwner(p);		
		f.setContainer(p.getBackpack());
		p.getBackpack().getContainer().add(f);
		gp.setContainer(p.getBackpack());
		p.getBackpack().getContainer().add(gp);
		
		assertTrue(p.requestTrade(new Engineer(r.roomGrid[6][6]))); //got requirements
			
	}
	
	
	@Test public void testInvalidTradeWithKeySmith(){
		
		Room r = new Room("Room","testroom", new Level("", "", 1));
		Player p = new Player(null, "damian", "LZgamer", r, 0);
		
		assertFalse(p.requestTrade(new KeySmith(r.roomGrid[6][6]))); //invalid, don't have requirements
			
	}
	
	@Test public void testValidTradeWithKeySmith(){
		
		Room r = new Room("Room","testroom", new Level("", "", 1));
		Player p = new Player(null, "damian", "LZgamer", r, 0);
		
		MiddleKeyFragment mk = new MiddleKeyFragment();
		BottomKeyFragment bk = new BottomKeyFragment();
		TopKeyFragment tk = new TopKeyFragment();
		
		p.getBackpack().setOwner(p);		
		mk.setContainer(p.getBackpack());
		p.getBackpack().getContainer().add(mk);
		bk.setContainer(p.getBackpack());
		p.getBackpack().getContainer().add(bk);
		tk.setContainer(p.getBackpack());
		p.getBackpack().getContainer().add(tk);
		
		assertTrue(p.requestTrade(new KeySmith(r.roomGrid[6][6]))); //got requirements
		
	}
	
	
	
	//Testing room non trivial methods
	
	
	@Test public void testRoomExamine1(){
		
		Room r = new Room("Room","testroom", new Level("", "", 1));
		Player p = new Player(null, "damian", "LZgamer", r, 0);
		//player facing north by default
		r.roomGrid[3][3].getGameObjects().add(p);
		p.setGridLocation(r.roomGrid[3][3]);
		
		assertTrue(r.examineGridLocation(p).getColIndex() == 2);
	}
	
	@Test public void testRoomExamine2(){
		
		Room r = new Room("Room","testroom", new Level("", "", 1));
		Player p = new Player(null, "damian", "LZgamer", r, 0);
		
		r.roomGrid[3][3].getGameObjects().add(p);
		p.setGridLocation(r.roomGrid[3][3]);
		
		p.setDirection(Direction.EAST);
		
		assertTrue(r.examineGridLocation(p).getRowIndex() == 4);
		
		
	}
	
	@Test public void testRoomExamine3(){
		
		Room r = new Room("Room","testroom", new Level("", "", 1));
		Player p = new Player(null, "damian", "LZgamer", r, 0);
		
		r.roomGrid[3][3].getGameObjects().add(p);
		p.setGridLocation(r.roomGrid[3][3]);
		
		p.setDirection(Direction.SOUTH);
		
		assertTrue(r.examineGridLocation(p).getColIndex() == 4);
		
		
	}
	
	
	@Test public void testRoomExamine4(){
		
		Room r = new Room("Room","testroom", new Level("", "", 1));
		Player p = new Player(null, "damian", "LZgamer", r, 0);
		
		r.roomGrid[3][3].getGameObjects().add(p);
		p.setGridLocation(r.roomGrid[3][3]);
		
		p.setDirection(Direction.WEST);
		
		assertTrue(r.examineGridLocation(p).getRowIndex() == 2);
				
	}
	
	
}
