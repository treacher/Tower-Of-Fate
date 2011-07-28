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
 * Class to represent the experience bar
 * @author Michael Treacher
 *
 */
public class ExperienceBar extends JLabel {
	
		private Image experienceImage;
		private int experience = 0;
		private int totalExperience = 0;
		/**
		 * Constructs an experience bar
		 * @param experience experience you have
		 * @param totalExperience the total experience for this level
		 */
		public ExperienceBar(int experience, int totalExperience){
			setTotalExperience(totalExperience); // use set so that it updates when its constructed
			setExperience(experience);
			this.experience = experience;
			this.totalExperience = totalExperience;
			this.setForeground(Color.WHITE); // set the text to white
			this.setHorizontalAlignment(JLabel.CENTER); // horizontal allignment
			this.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // black border to make it look cleaner
			try {
				// load in the image
				experienceImage = ImageIO.read(new File("TowerOfFate" + File.separatorChar + "uiImages"+File.separatorChar+"experience.JPG"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		/*
		 * (non-Javadoc)
		 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
		 */
		public void paintComponent(Graphics g){
			if(totalExperience == 0  || totalExperience ==1){
				g.drawImage(experienceImage.getScaledInstance(getWidth(),getHeight(), Image.SCALE_SMOOTH), 0, 0, null);
			}
			else if(experience != 1 && experience != 0){
				g.drawImage(experienceImage.getScaledInstance((int) (((float)experience/(float)totalExperience) * (float)getWidth()),getHeight(), Image.SCALE_SMOOTH), 0, 0, null);
			}
			super.paintComponent(g);
		}
		/**
		 * Set the experience
		 * @param experience
		 */
		public void setExperience(int experience){
			this.experience = experience;
			setText("Experience: " + experience + "/" + totalExperience);
		}
		/**
		 * Set the total experience
		 * @param totalExperience
		 */
		public void setTotalExperience(int totalExperience){
			this.totalExperience = totalExperience;
			setText("Experience: " + experience + "/" + totalExperience);
		}
		
	

}
