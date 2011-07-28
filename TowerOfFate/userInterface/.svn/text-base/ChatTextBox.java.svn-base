package userInterface;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.BorderFactory;
import javax.swing.JTextField;
/**
 * Class used to represent a chat text box
 * @author treachmich
 *
 */
public class ChatTextBox extends JTextField {
	/**
	 * Constructs a new chat text box
	 * @param root - Components root object
	 */
	public ChatTextBox(final MainFrame root){
		this.setOpaque(false); // makes it so it inherits the background of the others
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		addKeyListener(new KeyAdapter(){
			/*
			 * (non-Javadoc)
			 * @see java.awt.event.KeyAdapter#keyPressed(java.awt.event.KeyEvent)
			 */
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode() == KeyEvent.VK_ENTER && ChatTextBox.this.isEnabled()){
					String textToSend = ChatTextBox.this.getText();
					ChatTextBox.this.setText("");
					root.getClientConnection().sendChatMessage(textToSend);
					root.sendRendererKeyEvent(arg0);
				}
				else if(arg0.getKeyCode() == KeyEvent.VK_UP || arg0.getKeyCode() == KeyEvent.VK_LEFT || arg0.getKeyCode() == KeyEvent.VK_RIGHT && ChatTextBox.this.isEnabled()) {
					root.sendRendererKeyEvent(arg0);// passes focus & key presses to the renderer
				}
			}
		});
		this.setEnabled(false); // you can not chat unless a game has been started
	}

}
