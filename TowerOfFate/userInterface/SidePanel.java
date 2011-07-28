package userInterface;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Class to represent the games side panel
 * @author Michael Treacher
 *
 */
public class SidePanel extends JPanel {
	
	private JFrame parent;
	private PlayerStatusPanel playerStatus;
	private PlayerInventoryPanel inventory;
	private KeyRingPanel keyRing;
	private Image background;
	/**
	 * Constructs the Side Panel
	 * @param parent
	 */
	public SidePanel(MainFrame parent){
		this.parent = parent;
		setLayout(null);
		setBounds(MainFrame.CANVAS_WIDTH,0,MainFrame.FRAME_WIDTH,MainFrame.FRAME_HEIGHT);
		playerStatus = new PlayerStatusPanel();
		add(playerStatus);
		inventory = new PlayerInventoryPanel(parent);
		add(inventory);
		keyRing = new KeyRingPanel(parent);
		add(keyRing);
		setVisible(true);
	}
	/**
	 * Gets the key ring planel
	 * @return
	 */
	public KeyRingPanel getKeyRingPanel(){
		return keyRing;
	}
	/**
	 * Gets the player status panel
	 * @return
	 */
	public PlayerStatusPanel getPlayerStatusPanel(){
		return playerStatus;
	}
	/**
	 * Gets the player inventory panel;
	 * @return
	 */
	public PlayerInventoryPanel getPlayerInventoryPanel(){
		return inventory;
		
	}
	
}
