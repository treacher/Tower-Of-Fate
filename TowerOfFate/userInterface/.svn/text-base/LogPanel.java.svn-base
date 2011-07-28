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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
/**
 * Class to represent the  log panel
 * @author  Michael Treacher 300160013
 *
 */
public class LogPanel extends JPanel {
	
	private Image background;
	private SeperatorPanel seperator = new SeperatorPanel();
	private JLabel combatLogLabel = new JLabel("Log:");
	private SeperatorPanel seperatorOne = new SeperatorPanel();
	private SeperatorPanel seperatorTwo = new SeperatorPanel();
	private SeperatorPanel leftSeperator;
	private LogTextArea logTextArea;
	private JScrollPane combatScrollPane;
	private ChatTextBox chatTextBox;
	/**
	 * constructs a log panel which contains all the current things that happen in the game like combat etc.
	 */
	public LogPanel(MainFrame root){
		setLayout(null);
		setBounds(0,MainFrame.CANVAS_HEIGHT,MainFrame.CANVAS_WIDTH,MainFrame.FRAME_HEIGHT-MainFrame.CANVAS_HEIGHT);
		try {
			background = ImageIO.read(new File("TowerOfFate"+File.separator+"uiImages"+File.separator+"sidepanel.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		seperator.setBounds(0,0,getWidth()+5,5);
		add(seperator);
	
		logTextArea = new LogTextArea(root,getWidth() -5,450);
		// set up the scroll pane
		combatScrollPane = new JScrollPane(logTextArea);
		combatScrollPane.setOpaque(false);
		combatScrollPane.setBounds(5, 35, getWidth() -5, 88);
		combatScrollPane.setBorder(null);
		combatScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		combatScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		add(combatScrollPane);
		// set up the combat log label
		combatLogLabel.setFont(new Font("dialog",Font.BOLD,19));
		combatLogLabel.setForeground(Color.WHITE);
		combatLogLabel.setBounds(10, 4, getWidth(), 25);
		add(combatLogLabel);
		
		//set up chat text box
		chatTextBox = new ChatTextBox(root);
		chatTextBox.setBounds(5,123,getWidth()-5,20);
		add(chatTextBox);
		
		// sets up the seperators
		leftSeperator = new SeperatorPanel();
		leftSeperator.setBounds(0, 0, 5, getHeight() +2);
		add(leftSeperator);
		
		seperatorOne.setBounds(0,29,getWidth()+5,5);
		add(seperatorOne);
		
		seperatorTwo.setBounds(0,143,getWidth()+5,5);
		add(seperatorTwo);
	
	}
	/**
	 * Gets the log text area
	 * @return
	 */
	public LogTextArea getLogTextArea(){
		return logTextArea;
	}
	/**
	 * Enables the chat box
	 */
	public void enableChatBox(){
		this.chatTextBox.setEnabled(true);
	}
	/**
	 * Disables the chat box
	 */
	public void disableChatBox(){
		this.chatTextBox.setEnabled(false);
	}
	/**
	 * Sets the focus to the cht text box
	 */
	public void setChatBoxFocus(){
		this.chatTextBox.requestFocusInWindow();
	}
	/*
	 * (non-Javadoc)
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(background.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH), 0, 0, null);
	}
	
	
}
