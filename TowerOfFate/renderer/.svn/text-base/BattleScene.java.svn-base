package renderer;

import gameworld.Dragon;
import gameworld.Monster;
import gameworld.Player;

import java.awt.AWTEvent;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import userInterface.MainFrame;

import network.ClientConnection;
/**
 * Class to represent the battle scene
 * @author treachmich
 *
 */
public class BattleScene extends JPanel {

	private BufferedImage playerImage;
	private BufferedImage monsterImage;
	private BufferedImage backDrop;
	private int playersPosition = 200;
	private int monstersPosition = 260;
	private boolean playersTurn = true;
	private Monster monster;
	private Player play;
	private MainFrame root;
	private ClientConnection connection;

	
	/**
	 * Constructs a battle scene
	 * @param monster
	 * @param play
	 * @param connection
	 * @param root
	 */
	public BattleScene(Monster monster,Player play, ClientConnection connection,MainFrame root){
		this.connection = connection;
		this.monster = monster;
		this.play = play;
		this.root = root;
		setBounds(5,30,795,617);
		int fightScene =(int) (Math.random() * 3);
		
		try{
			if(fightScene == 3){fightScene --;}
			playerImage = ImageIO.read(new File("TowerOfFate"+File.separator+"rendererImages"+File.separator+play.UID+"-"+"EAST"+"-"+"Player"+".png"));
			monsterImage = ImageIO.read(new File("TowerOfFate"+File.separator+"rendererImages"+File.separator+monster.getClass().getName().substring(10)+".png"));
			backDrop = ImageIO.read(new File("TowerOfFate"+File.separator+"rendererImages"+File.separator+fightScene+"-backdrop"+".jpg"));
		}catch(Exception e){System.out.println(e);}
		this.setBackground(Color.RED);
		addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent e){
				
				if(e.getKeyCode() == KeyEvent.VK_SPACE && playersTurn){
					new BattleThread().start();
				}
				
			}
		});
		this.requestFocusInWindow();
	}
	/**
	 * Emulates attacking a player.
	 */
	public void playerAttack(){
		for(int i = 0 ; i < 70;i++){
			playersPosition+=5;
			try{Thread.sleep(10);}catch(Exception e){}
			repaint();
		}
		root.getLogPanel().getLogTextArea().appendLogText(play.attack(monster));
		for(int i = 0 ; i < 70;i++){
			playersPosition -=5;
			try{Thread.sleep(10);}catch(Exception e){}
			repaint();
		}
	}
	/**
	 * Emulates a battle round
	 */
	public void battleRound(){
		if (playersTurn && !play.isDead() && !monster.isDead()){
				playersTurn = false;
				playerAttack();
			try{Thread.sleep(500);}catch(Exception e){}
			if(!monster.isDead()){
				monsterAttack();
				playersTurn = true;
			}
		}
		if(play.isDead()){
			connection.die();
		}
		else if (monster.isDead()){
			connection.killMonster(monster.getExperience(),monster.getGoldLoot(),(monster instanceof Dragon));
		}
	}
	/**
	 * Emulates a monster attacks
	 */
	public void monsterAttack(){
		for(int i = 0 ; i < 70;i++){
			monstersPosition+=5;
			try{Thread.sleep(10);}catch(Exception e){}
			repaint();
		}
		root.getLogPanel().getLogTextArea().appendLogText(monster.attack(play));
		connection.attacked(play.getCurrentHealth());
		for(int i = 0 ; i < 70;i++){
			monstersPosition -=5;
			try{Thread.sleep(10);}catch(Exception e){}
			repaint();
		}
		playersTurn = true;
	}
	
	/**
	 * Set player.
	 * @param p
	 */
	public void setPlayer(Player p){
		play = p;
	}
	/*
	 * (non-Javadoc)
	 * @see java.awt.Canvas#update(java.awt.Graphics)
	 */
	public void update(Graphics g){
		paintComponent(g);
	}
	/*
	 * (non-Javadoc)
	 * @see java.awt.Canvas#paint(java.awt.Graphics)
	 */
	public void paintComponent(Graphics g){
		Image off = createImage(getWidth(),getHeight());
		Graphics graphics = off.getGraphics();
		graphics.drawImage(backDrop.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH),0,0,null);
		graphics.drawImage(playerImage,playersPosition,(getHeight() - playerImage.getHeight()),null);
		graphics.drawImage(monsterImage,getWidth()-monstersPosition,(getHeight() - playerImage.getHeight()),null);
		graphics.setColor(Color.red);
		graphics.fillRect((getWidth()-monstersPosition) + ((monsterImage.getWidth() -(monster.getMaxHealth()/monster.getMaxHealth())*200)/2) , getHeight() - monsterImage.getHeight() - 40, (monster.getMaxHealth()/monster.getMaxHealth())*200, 30);
		graphics.setColor(Color.green);
		graphics.fillRect((getWidth()-monstersPosition) + ((monsterImage.getWidth() -(monster.getMaxHealth()/monster.getMaxHealth())*200)/2), getHeight() - monsterImage.getHeight() - 40, (int) (((double)monster.getCurrentHealth()/(double)monster.getMaxHealth())*200), 30);
		graphics.setColor(Color.black);
		graphics.drawRect((getWidth()-monstersPosition) + ((monsterImage.getWidth() -(monster.getMaxHealth()/monster.getMaxHealth())*200)/2), getHeight() - monsterImage.getHeight() - 40, (monster.getMaxHealth()/monster.getMaxHealth())*200, 30);
		g.drawImage(off, 0, 0, null);
	}
	/**
	 * Class to represent a battle thread
	 * @author treachmich
	 *
	 */
	private class BattleThread extends Thread{
		/*
		 * (non-Javadoc)
		 * @see java.lang.Thread#run()
		 */
		public void run(){
			// runs a battle round 
			battleRound();
			//update player
		}
	}


}
