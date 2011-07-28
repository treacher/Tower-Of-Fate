package userInterface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JTextArea;

import renderer.BattleScene;
/**
 * Class to represent the combat text area
 * @author Michael Treacher 300160013
 *
 */
public class LogTextArea extends JTextArea {

	private Image background;
	private int width,height;
	/**
	 * Constructs a log text area which is close to the same as a JTextArea but uses customized images as a back drop.
	 */
	public LogTextArea(final MainFrame root,int width,int height){
		this.width = width;
		this.height = height;
		setFont(new Font("dialog",Font.BOLD,13));
		setForeground(Color.WHITE);
		setOpaque(false);
		this.setMaximumSize(new Dimension(width, height));
		this.setEditable(false);
		this.setLineWrap(true);
		try {
			background = ImageIO.read(new File("TowerOfFate"+File.separator+"uiImages"+File.separator+"sidepanel.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		// this is so when i press a key it gives focus back to the renderer
		addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent arg0){
			root.sendRendererKeyEvent(arg0);

			}
		});
		
	}
	/**
	 * Appends the text to the log
	 * @param text
	 */
	public void appendLogText(String text){
		
		this.setCaretPosition(this.getDocument().getLength());
		if(this.getCaretPosition() > 180){
			this.setText("");
		}
		append(text + "\n");
	}
	/*
	 * (non-Javadoc)
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	public void paintComponent(Graphics g){
		g.drawImage(background.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0, 0, null);
		super.paintComponent(g);
	}
	
}
