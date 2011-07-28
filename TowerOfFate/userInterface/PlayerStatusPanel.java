package userInterface;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 * Class to represent the PlayerStatusPanel
 * @author Michael Treacher
 *
 */
public class PlayerStatusPanel extends JPanel {
	
	private Image background;
	private SeperatorPanel seperator;
	private SeperatorPanel leftSeperator;
	private SeperatorPanel rightSeperator;
	private JLabel playerName = new JLabel("Player Name");
	private SeperatorPanel seperatorOne;
	private JLabel playerLevel = new JLabel("Level:        ");
	private JLabel playerStrength = new JLabel("Strength:  ");
	private JLabel playerGold = new JLabel("Gold:         ");
	private SeperatorPanel seperatorTwo;
	private HealthBar healthBar = new HealthBar(0,0);
	private ExperienceBar experienceBar = new ExperienceBar(0,0);
	private final int DISTANCE_BETWEEN_ITEMS = 15;
	
	/**
	 * Constructs a player status panel which contains all the information 
	 * you would need to know about a player on the graphical user interface
	 * health bars etc.
	 */
	public PlayerStatusPanel(){
		setLayout(null); // null layout so i can specify where everything goes
		setBounds(0,0,MainFrame.FRAME_WIDTH - MainFrame.CANVAS_WIDTH-5,300);
		try {
			background = ImageIO.read(new File("TowerOfFate"+File.separator+"uiImages"+File.separator+"sidepanel.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		/*
		 * Set all the positions of the objects on the panel
		 * then add them to panel
		 */
		seperator = new SeperatorPanel();
		seperator.setBounds(0, 0,getWidth() , 5);
		add(seperator);
		
		playerName.setBounds(0, 5, getWidth(), 50);
		playerName.setFont(new Font("dialog",Font.BOLD,30));
		playerName.setForeground(Color.WHITE);
		playerName.setHorizontalAlignment(JLabel.CENTER);
		add(playerName);
		
		leftSeperator = new SeperatorPanel();
		leftSeperator.setBounds(0, 0, 5, getHeight() +2);
		add(leftSeperator);
		
		rightSeperator = new SeperatorPanel();
		rightSeperator.setBounds(getWidth()-5, 0, 5, getHeight()+2);
		add(rightSeperator);
		
		seperatorOne = new SeperatorPanel();
		seperatorOne.setBounds(0, 60, getWidth() , 5);
		add(seperatorOne);
		
		playerLevel.setForeground(Color.WHITE);
		playerLevel.setBounds(58,80,getWidth(),25);
		playerLevel.setFont(new Font("dialog",Font.BOLD,15));
		add(playerLevel);
		
		playerStrength.setForeground(Color.WHITE);
		playerStrength.setBounds(58,(playerLevel.getHeight()+playerLevel.getY()) + DISTANCE_BETWEEN_ITEMS,getWidth(),25);
		playerStrength.setFont(new Font("dialog",Font.BOLD,15));
		add(playerStrength);
		
		playerGold.setForeground(Color.WHITE);
		playerGold.setBounds(58,(playerStrength.getHeight()+playerStrength.getY()) + DISTANCE_BETWEEN_ITEMS,getWidth(),25);
		playerGold.setFont(new Font("dialog",Font.BOLD,15));
		add(playerGold);
		
		healthBar.setBounds(10,(playerGold.getHeight()+playerGold.getY()) + DISTANCE_BETWEEN_ITEMS,getWidth()-20,30);
		add(healthBar);
		
		experienceBar.setBounds(10,(healthBar.getHeight() + healthBar.getY())+DISTANCE_BETWEEN_ITEMS,getWidth()-20,30);
		add(experienceBar);
		
		seperatorTwo = new SeperatorPanel();
		seperatorTwo.setBounds(0, getHeight() - 5, getWidth() , 5);
		add(seperatorTwo);

	}
	/**
	 * Sets the players name
	 * @param name
	 */
	public void setPlayerName(String name){
		if(name.length() > 12){
			playerName.setFont(new Font("dialog",Font.BOLD,18));
		}
		playerName.setText(name);
	}
	/**
	 * Sets the level
	 * @param level
	 */
	public void setLevel(int level){
		if(level == 0){playerLevel.setText("Level:           ");}
		else 
			playerLevel.setText("Level:           " + level);
	}
	/**
	 * Sets the total health
	 * @param totalHealth
	 */
	public void setTotalHealth(int totalHealth){
		healthBar.setTotalHealth(totalHealth);
	}
	/**
	 * Sets the strength
	 * @param strength
	 */
	public void setStrength(int strength){
		if(strength == 0){playerStrength.setText("Strength:      ");}
		else 
			playerStrength.setText("Strength:      " + strength);
	}
	/**
	 * Sets the health remaining
	 * @param health
	 */
	public void setHealthRemaining(int health){
		healthBar.setHealth(health);
	}
	/**
	 * Sets the amount of gold
	 * @param gold
	 */
	public void setGold(int gold){
		if(gold == -1 ){playerGold.setText("Gold:            ");}
		else 
			playerGold.setText("Gold:            " + gold);
	}
	/**
	 * Sets the experience you need
	 * @param experienceNeeded
	 */
	public void setExperienceNeeded(int experienceNeeded){
		experienceBar.setTotalExperience(experienceNeeded);
	}
	/**
	 * Sets the experience you currently have
	 * @param experience
	 */
	public void setExperience(int experience){
		experienceBar.setExperience(experience);
	}
	/*
	 * (non-Javadoc)
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		if(background!= null){
			g.drawImage(background, 0, 0, null);
		}
		
	}
	
}
