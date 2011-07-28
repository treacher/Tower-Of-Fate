package userInterface;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

public class HostServerPanel extends JPanel{
	private JLabel nameLabel;
	private JTextField nameField;
	private JLabel playerAmountLabel;
	private JComboBox amountOfPlayers;
	private LayoutManager layout;
	private String userName;
	private int playerAmount = 2;
	/**
	 * Constructs the Host Server Panel
	 */
	public HostServerPanel(){
		setSize(new Dimension(200,180));
		setupPanel();
		this.setLayout(layout);
		setVisible(true);
	}
	/**
	 * Gets the user name 
	 * @return userName
	 */
	public String getUserName(){
		return userName;
	}
	/**
	 * Gets the amount of players
	 * @return ipAddress
	 */
	public int getAmountOfPlayers(){
		return playerAmount;
	}
	/**
	 * Sets up the Host Server Frame
	 */
	private void setupPanel(){
		layout = new FlowLayout();
		
		nameLabel = new JLabel("Player Name:");
		
		nameField = new JTextField(12);
		// update the user name field in synch with the text Field 
		nameField.addCaretListener(new CaretListener(){

			
			public void caretUpdate(CaretEvent arg0) {
				userName = nameField.getText();
				
			}
			
		});
		playerAmountLabel = new JLabel("No. of Players");
		amountOfPlayers = new JComboBox(new Integer[]{2,3,4,5,});
		amountOfPlayers.addItemListener(new ItemListener(){

			public void itemStateChanged(ItemEvent e) {
				playerAmount = (Integer)e.getItem();
				
			}
			
		});
		
		add(nameLabel);
		add(nameField);
		add(playerAmountLabel);
		add(amountOfPlayers);
		
		
	}
	
}

