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
 * Class to represent a key ring panel
 * @author Michael Treacher
 *
 */
public class KeyRingPanel extends JPanel {
	private Image background;
	private JLabel keyChainLabel = new JLabel("Key Ring: ");
	private SeperatorPanel seperator = new SeperatorPanel();
	private ItemPanel keyPanel;
	private SeperatorPanel endSeperator = new SeperatorPanel();
	private SeperatorPanel leftSeperator;
	private SeperatorPanel rightSeperator;
	private MainFrame root;
	/**
	 * Constructs a key ring panel
	 */
	public KeyRingPanel(MainFrame root){
		this.root = root;
		keyPanel = new ItemPanel(1,4,root);
		setLayout(null); // null layout so i can specify where everything goes
		setBounds(0,MainFrame.CANVAS_HEIGHT+5,MainFrame.FRAME_WIDTH - MainFrame.CANVAS_WIDTH-5,MainFrame.FRAME_HEIGHT - MainFrame.CANVAS_HEIGHT+5);
		try {
			background = ImageIO.read(new File("TowerOfFate"+File.separator+"uiImages"+File.separator+"sidepanel.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		keyChainLabel.setBounds(0, 0, getWidth(), 25);
		keyChainLabel.setFont(new Font("dialog",Font.BOLD,19));
		keyChainLabel.setForeground(Color.WHITE);
		keyChainLabel.setHorizontalAlignment(JLabel.CENTER);
		add(keyChainLabel);
		
		leftSeperator = new SeperatorPanel();
		leftSeperator.setBounds(0, 0, 5, getHeight() +1);
		add(leftSeperator);
		
		rightSeperator = new SeperatorPanel();
		rightSeperator.setBounds(getWidth()-5, 0, 5, getHeight() +1);
		add(rightSeperator);
		
		seperator.setBounds(0,24,getWidth(),5);
		add(seperator);
		
		keyPanel.setBounds(10,60,getWidth()-20,45);
		add(keyPanel);
		endSeperator.setBounds(0,138,getWidth(),5);
		add(endSeperator);
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
	 * Gets the root component
	 * @return
	 */
	public MainFrame getRoot(){
		return root;
	}
	/**
	 * Gets the item panel
	 * @return
	 */
	public ItemPanel getKeyPanel(){
		return keyPanel;
	}

}
