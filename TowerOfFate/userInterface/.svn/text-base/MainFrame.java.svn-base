package userInterface;

import gameworld.Backpack;
import gameworld.GridLocation;
import gameworld.Keyring;
import gameworld.Monster;
import gameworld.Player;
import gameworld.Room;
import gameworld.TowerOfFate;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import network.ClientConnection;
import network.GameServer;

import renderer.BattleScene;
import renderer.LoadingScreen;
import renderer.RoomRenderer3D;
import renderer.SplashCanvas;
import renderer.TradeWindow;
import server.Slave;

/**
 * Class to represent the MainFrame the main frame displays all the components in the game
 * @author Michael Treacher
 *
 */
public class MainFrame extends JFrame{
	public static final int FRAME_WIDTH = 1026;
	public static final int FRAME_HEIGHT = 843;
	public static final int CANVAS_WIDTH = 800;
	public static final int CANVAS_HEIGHT = 645;
	public static final int WIDTH_ERROR = 3;// used for netbsd machines
	public static final int HEIGHT_ERROR = 5; // used for netbbeginIndexsd machines
	private String userName;
	private String IPAddress;
	private int amountOfPlayers;
	private SidePanel sidePanel;
	private LogPanel logPanel;
	private JPanel renderer;
	private PositionPanel positionPanel;
	private ClientConnection connection;
	private boolean isHost = false;
	
	/**
	 * Constructs the Main Frame
	 */
	public MainFrame(){
		super("The Tower Of Fate"); // set the frame title
		
		setLayout(null);

		this.setSize(new Dimension(FRAME_WIDTH+WIDTH_ERROR,FRAME_HEIGHT+HEIGHT_ERROR));
		
		renderer =  new SplashCanvas(this);
		
		add(renderer);
		
		logPanel = new LogPanel(this); 
		add(logPanel);
		
		sidePanel = new SidePanel(this); 
		add(sidePanel);
		
		SeperatorPanel panelOne = new SeperatorPanel();
		panelOne.setBounds(0, 0, 5 , CANVAS_HEIGHT +5);
		add(panelOne);
		
		positionPanel = new PositionPanel();
		add(positionPanel);
		
		this.setResizable(false); // just want it to be a set size
		
		setupMenu();
		
		this.setVisible(true);
		renderer.requestFocusInWindow();
		
		addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent arg0){
				if(renderer!=null){
					sendRendererKeyEvent(arg0);
				}
			}
		});
		renderer.repaint();
	}
	/**
	 * Displays the splash screen
	 */
	public void showSplashScreen(){
		renderer.setVisible(false);
		remove(renderer);
		this.renderer = new SplashCanvas(this);
		add(renderer);
		renderer.repaint();
		renderer.setVisible(true);
	}
	public void setRenderer(RoomRenderer3D renderer){
		this.renderer.setVisible(false);
		remove(this.renderer);
		add(renderer);
		this.renderer = renderer;
		this.renderer.requestFocusInWindow();
	}
	public void setLoadingScreen(LoadingScreen screen){
		this.renderer = screen;
	}
	/**
	 * Gets the log panel
	 * @return
	 */
	public LogPanel getLogPanel(){
		return logPanel;
	}
	
	public void setPlayersGold(int gold){
		sidePanel.getPlayerStatusPanel().setGold(gold);
	}
	/**
	 * Setsthe current room in the rendrer
	 * @param room
	 */
	public void setRenderRoom(Room room){
		if(renderer instanceof RoomRenderer3D)
			((RoomRenderer3D)renderer).setRoom(room);
	}
	/**
	 * Brings up the player trade screen
	 */
	public void playerTrade(){
		remove(this.renderer);
		this.renderer = new TradeWindow(this);
		add(renderer);
		renderer.repaint();
		renderer.requestFocusInWindow();
	}
	/**
	 * Brings up the battle scene
	 * @param monster
	 * @param play
	 */
	public void monsterAttacks(Monster monster,Player play){
		remove(this.renderer);
		this.renderer = new BattleScene(monster,play,connection,this);
		add(renderer);
		renderer.repaint();
		renderer.requestFocusInWindow();
	}
	/**
	 * Updates the player while hes in battle 
	 * @param p
	 */
	public void updatePlayerInBattle(Player p ){
		BattleScene bs = (BattleScene) renderer;
		bs.setPlayer(p);
	}
	/**
	 * Get the side Panel
	 * @return
	 */
	public SidePanel getSidePanel(){
		return sidePanel;
	}
	/**
	 * Set the room cell
	 * @param grid
	 */
	public void setCell(GridLocation grid, boolean repaint){
		((RoomRenderer3D)renderer).setGrid(grid, repaint);
	}
	/**
	 * Gets the current connection
	 * @return
	 */
	public ClientConnection getClientConnection(){
		return connection;
	}
	/**
	 * Sets the renderer as the current focus
	 */
	public void sendRendererKeyEvent(KeyEvent arg0){
			if(renderer instanceof BattleScene || renderer instanceof RoomRenderer3D){
				renderer.getKeyListeners()[0].keyPressed(arg0);
				renderer.requestFocusInWindow();
			}
	}
	
	/**
	 * Sets up the menu
	 */
	private void setupMenu(){
		JMenuBar menubar = new JMenuBar();
		JMenu gameMenu = new JMenu("Game");
		gameMenu.getPopupMenu().setLightWeightPopupEnabled(false);
		// set the short cut key 
		gameMenu.setMnemonic('g');
		
		// host server option
		JMenuItem hostServer = new JMenuItem("Host Server");
		// attach action listener to the JMenuItem
		hostServer.addActionListener( 
				new ActionListener(){
					public void actionPerformed(ActionEvent arg0) {
						
					}
		});
		// set the short cut key
		hostServer.setMnemonic('h');
		// add the menu bar to the file menu
		gameMenu.add(hostServer);
		
		JMenuItem saveGame = new JMenuItem("Save Game");
		saveGame.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent arg0) {
				if(isHost){
					saveGame();
				}
				else {
					showDialog("You can not do this!");
				}
				
			}
			
		});
		saveGame.setMnemonic('s');
		gameMenu.add(saveGame);
		//Join server option
		JMenuItem joinServer = new JMenuItem("Join Server");
		// attach action listener to the JMenuItem
		joinServer.addActionListener( 
				new ActionListener(){
					public void actionPerformed(ActionEvent arg0) {
						joinServer();
					}
		});
		// set the short cut key
		joinServer.setMnemonic('j');
		// add the menu bar to the file menu
		gameMenu.add(joinServer);
		
		//Exit Game option
		JMenuItem exitGame = new JMenuItem("Exit Game");
		exitGame.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						exit();
					}
				}
		);
		// set the short cut key to x
		exitGame.setMnemonic('x');
		gameMenu.add(exitGame);
		
		JMenu helpMenu = new JMenu("Help");
		helpMenu.getPopupMenu().setLightWeightPopupEnabled(false);
		helpMenu.setMnemonic('H');
		
		JMenuItem controls = new JMenuItem("Controls");
		controls.addActionListener(
				new ActionListener(){

					public void actionPerformed(ActionEvent e) {
						showDialog(
								"Room Controls: \n\n" +
								"Up Arrow - Moves player forward.\n" +
								"Left and Right Arrow - Changes players direction.\n" +
								"Space Bar - Interacts with any object. (you must be one sqquare away to interact)\n" +
								"P key - Picks up the items on the ground. (Must be on top of the item to pickup)\n"  +
								"I key - Inspects objects\n" +
								"\nBattle Screen Controls : \n\n" +
								"Space Bar  - Attacks the target. (Attacks are turn based)\n" +
								"\nTrade Screen:\n\n" +
								"To buy items just click on the item you wish to buy in the trade screen.\n" +
								"\nUser Interface:\n\n" +
								"Right clicking items in your bag slot will bring up options for your items.\n" +
								"Left clicking items in your bag slot will perform the items use effect.\n");
					}
					
				}
		);
		helpMenu.add(controls);
		JMenuItem about = new JMenuItem("About");
		about.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				showDialog("Tower Of Fate\n\n" +
						"Render & UI coded by Michael Treacher\n" +
						"Game World coded by Damian Kaye\n" +
						"Network coded by Calvin Kaye\n" +
						"Storage coded by Chris Nelson && Sean Anderson\n\n" +
						"\u00a9 Leet Mikey Corp");
				
			}
		});
		helpMenu.add(about);
		
		// add the menu bar to the file menu
		menubar.add(gameMenu);
		menubar.add(helpMenu);
		setJMenuBar(menubar);	
	}
	/**
	 * Exits the game
	 */
	public void exit(){
		int option = JOptionPane.showConfirmDialog(MainFrame.this,"Are you sure you want to exit?","Exit Game?",JOptionPane.YES_NO_OPTION); 
		// Possibly a save option here.
		if(option == JOptionPane.YES_OPTION)System.exit(0);
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param iPAddress the iPAddress to set
	 */
	public void setIPAddress(String iPAddress) {
		IPAddress = iPAddress;
	}
	/**
	 * Sets the render room direction
	 * @param player
	 */
	public void setRenderRoomDirection(Player player){
		if(renderer instanceof RoomRenderer3D)
			((RoomRenderer3D)renderer).setPlayerDirection(player.getDirectionFacing());
	}

	/**
	 * @return the iPAddress
	 */
	public String getIPAddress() {
		return IPAddress;
	}
	/**
	 * Prompts the user for server information then connects to server.
	 */
	public boolean joinServer(){
		 JoinServerPanel joinServerPanel = new JoinServerPanel();
		// initialise it to cancel option so if the user cancels out of window it wont cause havoc will just treat it like a cancel option.
		int opt = JOptionPane.CANCEL_OPTION;
		// add panel to confirm dialog.
		opt = JOptionPane.showConfirmDialog(this, joinServerPanel, "Join Server:", JOptionPane.OK_CANCEL_OPTION);
		if(opt == JOptionPane.OK_OPTION){
			String userName = joinServerPanel.getUserName();
			String IPAddress = joinServerPanel.getIpAddress();
			if(userName != null && !userName.equals("") && userName.length() > 0 && userName.length() <= 20 && IPAddress != null && !IPAddress.equals("")){
				renderer.setVisible(false);
				remove(renderer);
				connection = new ClientConnection(this,IPAddress, userName);
				connection.start();
			}
			else{
			 JOptionPane.showMessageDialog(this,"Please enter a valid user name and IP address","Input Error",JOptionPane.ERROR_MESSAGE);
			 joinServer();
			}
		}
		return true; // they cancelled so technically not a failure 
	}
	/**
	 * Method to set the player up
	 * @param player
	 */
	public void updatePlayer(Player player){
		sidePanel.getPlayerStatusPanel().setPlayerName(player.getName());
		sidePanel.getPlayerStatusPanel().setGold(player.getGoldAmount());
		sidePanel.getPlayerStatusPanel().setHealthRemaining(player.getCurrentHealth());
		sidePanel.getPlayerStatusPanel().setTotalHealth(player.getMaxHealth());
		sidePanel.getPlayerStatusPanel().setLevel(player.getPlayerLevel());
		sidePanel.getPlayerStatusPanel().setStrength(player.getStrength());
		sidePanel.getPlayerStatusPanel().setExperience(player.getCurrentExperience());
		sidePanel.getPlayerStatusPanel().setExperienceNeeded(player.getExperienceCap());
		positionPanel.setLevel(player.getLevel()); // will change when implemented properly
		positionPanel.setCurrentdirection(player.getDirectionFacing());
		if(renderer instanceof RoomRenderer3D){
			((RoomRenderer3D)renderer).getPlayer().setLevel(player.getLevel());
		}
	}
	/**
	 * Resets the player status panel
	 * @param player
	 */
	public void resetPlayer(){
		sidePanel.getPlayerStatusPanel().setPlayerName("Player Name");
		sidePanel.getPlayerStatusPanel().setGold(-1);
		sidePanel.getPlayerStatusPanel().setHealthRemaining(0);
		sidePanel.getPlayerStatusPanel().setTotalHealth(0);
		sidePanel.getPlayerStatusPanel().setLevel(0);
		sidePanel.getPlayerStatusPanel().setStrength(0);
		sidePanel.getPlayerStatusPanel().setExperience(0);
		sidePanel.getPlayerStatusPanel().setExperienceNeeded(0);
		positionPanel.setLevel(0); // will change when implemented properly
		positionPanel.setCurrentdirection(null);
		positionPanel.setRoom("");
		sidePanel.getPlayerInventoryPanel().getItemPanel().reset();
		sidePanel.getKeyRingPanel().getKeyPanel().reset();
	}
	/**
	 * Constructs a dialog message
	 * @param message
	 */
	public void showDialog(String message){
		JOptionPane.showMessageDialog(this, message, "Tower Of Fate", JOptionPane.QUESTION_MESSAGE, new ImageIcon("TowerOfFate"+File.separator+"rendererImages"+File.separator+"CrystalBall.png"));
		
	}
	
	/**
	 * Starts to try and host server
	 */
	public void hostServer() {
		//String name = JOptionPane.showInputDialog(this, "Please enter your user name :");
		 HostServerPanel hostServerPanel = new HostServerPanel();
		// initialise it to cancel option so if the user cancels out of window it wont cause havoc will just treat it like a cancel option.
		int opt = JOptionPane.CANCEL_OPTION;
		// add panel to confirm dialog.
		opt = JOptionPane.showConfirmDialog(this, hostServerPanel, "Host Server:", JOptionPane.OK_CANCEL_OPTION);
		if(opt == JOptionPane.OK_OPTION){
			userName = hostServerPanel.getUserName();
			amountOfPlayers = hostServerPanel.getAmountOfPlayers();
			loadGame();
			if(userName!=null && userName.length() > 0 && userName.length() <= 20 && amountOfPlayers >0 &&amountOfPlayers <=5){
				GameServer server = new GameServer(amountOfPlayers);
				server.start();
				// sleep to make sure the thread gets enough time to connect to server before connecting you as a client
				try{Thread.sleep(500);}catch(Exception e){}
				renderer.setVisible(false);
				remove(renderer);
				connection = new ClientConnection(this,"127.0.0.1", userName);
				connection.start();
			}
			isHost = true;
		}
		else {
			JOptionPane.showMessageDialog(this, "Please enter a valid user name & the amount of players is between 1 and 5");
		}
	}
	/**
	 * Updates the current room position
	 * @param currentRoom
	 */
	public void updatePositionRoomField(Room currentRoom) {
		positionPanel.setRoom(currentRoom.getName());
		
	}
	/**
	 * Loads a game
	 */
	public void loadGame(){
		
	}
	/**
	 * Saves a game 
	 */
	public void saveGame(){
		
	}
}

