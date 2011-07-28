package userInterface;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 * Class to represent the Player inventory panel
 * @author Michael Treacher
 *
 */
public class PlayerInventoryPanel extends JPanel {

	private Image background;
	private JLabel inventoryLabel = new JLabel("Inventory:");
	private SeperatorPanel leftSeperator;
	private SeperatorPanel rightSeperator;
	private SeperatorPanel seperator = new SeperatorPanel();
	private ItemPanel itemPanel;
	private SeperatorPanel lastSeperator = new SeperatorPanel();
	private MainFrame root;
	/**
	 * Constructs a player inventory panel
	 */
	public PlayerInventoryPanel(MainFrame root){
		this.root = root;
		itemPanel = new ItemPanel(4,4, root);
		setLayout(null);
		setBounds(0,300,MainFrame.FRAME_WIDTH - MainFrame.CANVAS_WIDTH-5,MainFrame.CANVAS_HEIGHT-295);
		try {
			background = ImageIO.read(new File("TowerOfFate"+File.separator+"uiImages"+File.separator+"sidepanel.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		inventoryLabel.setBounds(0, 0, getWidth(), 25);
		inventoryLabel.setFont(new Font("dialog",Font.BOLD,19));
	
		inventoryLabel.setForeground(Color.WHITE);
		inventoryLabel.setHorizontalAlignment(JLabel.CENTER);
		add(inventoryLabel);
		
		leftSeperator = new SeperatorPanel();
		leftSeperator.setBounds(0, 0, 5, getHeight()+2);
		add(leftSeperator);
		
		rightSeperator = new SeperatorPanel();
		rightSeperator.setBounds(getWidth()-5, 0, 5, getHeight() +2);
		add(rightSeperator);
		
		seperator.setBounds(0, 24, getWidth(), 5);
		add(seperator);
		
		itemPanel.setBounds(10,55,getWidth()-20,getHeight()-90);
		add(itemPanel);
		
		lastSeperator.setBounds(0, getHeight()-5, getWidth(), 5);
		add(lastSeperator);
	
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
	/**
	 * Gets the root of this component
	 * @return
	 */
	public MainFrame getRoot(){
		return root;
	}
	/**
	 * Gets the item panel
	 * @return - the item panel
	 */
	public ItemPanel getItemPanel(){
		return itemPanel;
	
	}
	
}
