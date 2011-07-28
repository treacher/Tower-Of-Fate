package userInterface;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
/**
 * Class to represent Health Bar
 * @author Michael Treacher
 *
 */
public class HealthBar extends JLabel {
	
	private Image healthImage;
	private int health = 0;
	private int totalHealth = 0;
	/**
	 * Constructs a health bar
	 * @param health
	 * @param totalHealth
	 */
	public HealthBar(int health, int totalHealth){
		setTotalHealth(totalHealth);
		setHealth(health);
		this.health = health;
		this.totalHealth = totalHealth;
		this.setForeground(Color.WHITE); // set font to white
		this.setHorizontalAlignment(JLabel.CENTER);
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		try {
			// load the image
			healthImage = ImageIO.read(new File("TowerOfFate"+File.separator+"uiImages"+File.separator+"health.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/*
	 * (non-Javadoc)
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	public void paintComponent(Graphics g){
		if(totalHealth == 0 ){
			g.drawImage(healthImage.getScaledInstance(getWidth(),getHeight(), Image.SCALE_SMOOTH), 0, 0, null);
		}
		else if(health != 1 && health != 0){
			g.drawImage(healthImage.getScaledInstance((int) (((float)health/(float)totalHealth) * (float)getWidth()),getHeight(), Image.SCALE_SMOOTH), 0, 0, null);
		}
		
		super.paintComponent(g);
	}
	/**
	 * Set the health
	 * @param health
	 */
	public void setHealth(int health){
		this.health = health;
		setText("Health: " + health + "/" + totalHealth);
	}
	/**
	 * Set the total health
	 * @param totalHealth
	 */
	public void setTotalHealth(int totalHealth){
		this.totalHealth = totalHealth;
		setText("Health: " + health + "/" + totalHealth);
	}
	
}
