package userInterface;

import gameworld.Player.Direction;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 * Class to represent the position panel
 * @author Michael Treacher 300160013
 *
 */
public class PositionPanel extends JPanel {
	
	private SeperatorPanel panel = new SeperatorPanel();		
	private SeperatorPanel panelOne = new SeperatorPanel();
	private Image background;
	private JLabel roomLabel;
	private JLabel levelLabel;
	private JLabel directionLabel;
	
	
	/**
	 * Constructs a position panel which contains the current level a player is on as well as the room and the current direction the player is facing. 
	 */
	public PositionPanel(){
		setLayout(null);
		setBounds(0,0,MainFrame.CANVAS_WIDTH+5,30);
		try {
			background = ImageIO.read(new File("TowerOfFate"+File.separator+"uiImages"+File.separator+"sidepanel.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		panel.setBounds(0, 0, MainFrame.CANVAS_WIDTH+5 , 5);
		add(panel);
		panelOne.setBounds(0, 25, MainFrame.CANVAS_WIDTH+5 , 5);
		add(panelOne);
		
		roomLabel = new JLabel("Room: ");
		roomLabel.setForeground(Color.WHITE);
		roomLabel.setFont(new Font("dialog",Font.BOLD,13));
		roomLabel.setBounds(10,7,MainFrame.CANVAS_WIDTH/3,15);
		add(roomLabel);
		
		levelLabel = new JLabel("Level: ");
		levelLabel.setForeground(Color.WHITE);
		levelLabel.setFont(new Font("dialog",Font.BOLD,13));
		levelLabel.setBounds(MainFrame.CANVAS_WIDTH/3,7,MainFrame.CANVAS_WIDTH/3,15);
		add(levelLabel);
		
		directionLabel = new JLabel("Current Direction: ");
		directionLabel.setForeground(Color.WHITE);
		directionLabel.setFont(new Font("dialog",Font.BOLD,13));
		directionLabel.setBounds((MainFrame.CANVAS_WIDTH/3)*2,7,MainFrame.CANVAS_WIDTH/3,15);
		add(directionLabel);
		
		
	}
	/**
	 * Sets the level label
	 * @param level
	 */
	public void setLevel(int level){
		if (level == 0){levelLabel.setText("Level: ");}
		else 
			levelLabel.setText("Level: "+level);
	}
	/**
	 * Sets the current direction label
	 * @param dir
	 */
	public void setCurrentdirection(Direction dir){
		if (dir == null){directionLabel.setText("Current Direction: ");}
		else 
			directionLabel.setText("Current Direction: " +dir);
	}
	/**
	 * Sets the room label
	 * @param name
	 */
	public void setRoom(String name){
		roomLabel.setText("Room: "+name);
	}
	/*
	 * (non-Javadoc)
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		if(background!= null){
			g.drawImage(background.getScaledInstance(getWidth(), 450, Image.SCALE_SMOOTH), 0, 0, null);
		}
		
	}

}
