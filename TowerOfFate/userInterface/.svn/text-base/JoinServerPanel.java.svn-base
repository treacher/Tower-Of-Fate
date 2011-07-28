package userInterface;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.LayoutManager;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import javax.swing.InputVerifier;
import javax.swing.JComponent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
/**
 * Class to represent the Join Server Panel
 * @author Michael Treacher
 *
 */
public class JoinServerPanel extends JPanel{
	private JLabel nameLabel;
	private JTextField nameField;
	private JLabel ipAddressLabel;
	private JTextField ipAddressField;
	private LayoutManager layout;
	private String userName;
	private String ipAddress;
	/**
	 * Constructs the Join Server Panel
	 */
	public JoinServerPanel(){
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
	 * Gets the ip address
	 * @return ipAddress
	 */
	public String getIpAddress(){
		return ipAddress;
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

			/*
			 * (non-Javadoc)
			 * @see javax.swing.event.CaretListener#caretUpdate(javax.swing.event.CaretEvent)
			 */
			public void caretUpdate(CaretEvent arg0) {
				userName = nameField.getText();
				
			}
			
		});
		ipAddressLabel = new JLabel("Server Address:");
		
		ipAddressField = new JTextField(15);
		// update the ip address field in synch with the text Field 
		ipAddressField.addCaretListener(new CaretListener(){
			public void caretUpdate(CaretEvent arg0) {
				ipAddress = ipAddressField.getText();
			}
			
		});
		
		add(nameLabel);
		add(nameField);
		add(ipAddressLabel);
		add(ipAddressField);
		
		
	}
	
}
