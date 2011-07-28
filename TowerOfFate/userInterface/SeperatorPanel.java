package userInterface;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
/**
 * Class to represent the seperator Panel
 * @author Michael Treacher
 *
 */
public class SeperatorPanel extends JPanel {
	
	private Image background;
	
	/**
	 * Constructs the seperator panel
	 */
	public SeperatorPanel(){
	
		try {
			// loads image
			background = ImageIO.read(new File("TowerOfFate"+File.separator+"uiImages"+File.separator+"seperator.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/*
	 * (non-Javadoc)
	 * @see javax.swing.JComponent#paint(java.awt.Graphics)
	 */
	public void paint(Graphics g){
		g.drawImage(background.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH), 0, 0, null);
	}

}
