package renderer;

import gameworld.HealthPotion;
import gameworld.Machete;
import gameworld.ScrollOfStamina;
import gameworld.ScrollOfStrength;
import gameworld.VendorItem;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import userInterface.MainFrame;
/**
 * Class to represent the trade window.
 * @author treachmich
 *
 */
public class TradeWindow extends JPanel {
	
	private Image background;
	private MainFrame root;

	
	/**
	 * Constructs a trade window
	 * @param root
	 */
	public TradeWindow(MainFrame root){
		this.root = root;
		setBounds(5,30,795,617);
		try{ 
			background = ImageIO.read(new File("TowerOfFate"+File.separator+"rendererImages"+File.separator+"TradeWindow"+".jpg"));  
		}catch(Exception e){System.out.println(e);}
		
		setLayout(null);
		
		TradeItemBox potion = new TradeItemBox(new HealthPotion(),this);
		potion.setBounds(50,150,300,200);
		
		TradeItemBox scrollOfStrength = new TradeItemBox(new ScrollOfStrength(),this);
		scrollOfStrength.setBounds(450,150,300,200);
		
		TradeItemBox scrollOfStamina = new TradeItemBox(new ScrollOfStamina(),this);
		scrollOfStamina.setBounds(50,400,300,200);
		
		TradeItemBox machete = new TradeItemBox(new Machete(),this);
		machete.setBounds(450,400,300,200);
		
		add(potion);
		add(scrollOfStrength);
		add(scrollOfStamina);
		add(machete);

		
		addMouseListener(new MouseAdapter(){
			/*
			 * (non-Javadoc)
			 * @see java.awt.event.MouseAdapter#mouseClicked(java.awt.event.MouseEvent)
			 */
			public void mouseClicked(MouseEvent arg0){
				if(arg0.getX()>(TradeWindow.this.getWidth()-50) && (arg0.getY() <=50)){
					endTrade();
				}
			}
		});
		
	}
	/*
	 * (non-Javadoc)
	 * @see javax.swing.JComponent#paint(java.awt.Graphics)
	 */
	public void paint(Graphics g){
		g.drawImage(background,0,0,null);
		g.setColor(Color.black);
		g.setFont(new Font("",Font.BOLD,50));
		g.drawString("General Supplies Vendor",80,80);
		super.paint(g);
	}
	
	/*
	 * (non-Javadoc)
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	public void paintComponent(Graphics g){}
	/**
	 * Sends the end trade window signal
	 */
	public void endTrade(){
		root.getClientConnection().tradeOver();
	}
	/**
	 * Sends the buy item signal
	 * @param item
	 */
	public void buyItem(VendorItem item) {
		root.getClientConnection().buyVendorItem(item.getName());
	}
	
	

}
