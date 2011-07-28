package renderer;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import userInterface.MainFrame;
/**
 * Class to represent a Loading Screen
 * @author treachmich
 *
 */
public class LoadingScreen extends JPanel {
	
	private int totalPlayers;
	private int currentPlayers= 0;
	private BufferedImage img;
	/**
	 * Constructs a Loading Screen
	 * @param totalPlayers
	 */
	public LoadingScreen(int totalPlayers){
		setBounds(5,30,MainFrame.CANVAS_WIDTH-5,MainFrame.CANVAS_HEIGHT);
		try{img = ImageIO.read(new File("TowerOfFate"+File.separator+"rendererImages"+File.separator+"LoadingScreen.png"));}catch(Exception e){}
		this.totalPlayers = totalPlayers;
	}
	/**
	 * Sets the amount of players currently in game
	 * @param players
	 */
	public void setPlayerCount(int players){
		currentPlayers = players;
		repaint();
	}
	/*
	 * (non-Javadoc)
	 * @see javax.swing.JComponent#update(java.awt.Graphics)
	 */
	public void update(Graphics g){
		paintComponent(g);
	}
	/*
	 * (non-Javadoc)
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	public void paintComponent(Graphics g){
		BufferedImage off = new BufferedImage(getWidth(),getHeight(),BufferedImage.TYPE_INT_RGB);
		Graphics graphics = off.getGraphics();
		graphics.setColor(Color.BLACK);
		graphics.drawImage(img.getScaledInstance(MainFrame.CANVAS_WIDTH, MainFrame.CANVAS_HEIGHT, Image.SCALE_SMOOTH), 0, 0, null);
		graphics.setFont(new Font("dialog", Font.BOLD, 50));
		graphics.drawString("Waiting for players", 150, this.getHeight()/2);
		graphics.setFont(new Font("dialog", Font.BOLD,80));
		graphics.drawString(currentPlayers + "/" + totalPlayers, 320, this.getHeight()/2 + 100);
		g.drawImage(off, 0, 0, null);
	}
	

	


}
