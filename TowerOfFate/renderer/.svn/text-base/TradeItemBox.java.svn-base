package renderer;

import gameworld.VendorItem;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
/**
 * Class to represent the Trade Item Box
 * @author Michael Treacher
 *
 */
public class TradeItemBox extends JPanel {


	private Image itemImage;
	private JLabel price;
	private JTextArea description;
	private JLabel itemImageLabel;
	private TradeWindow parent;
	private VendorItem item;
	/**
	 * Constructs a Trade item box
	 * @param item
	 * @param parent
	 */
	public TradeItemBox(final VendorItem item,TradeWindow parent){
		this.item = item;
		this.parent = parent;
		setOpaque(false);
		try{
			itemImage = ImageIO.read(new File("TowerOfFate"+File.separator+"rendererImages"+File.separator+item.getClass().getName().substring(10)+".png"));
		}
		catch(Exception e){System.out.println(e);}
		
		
		setLayout(null);
		
		itemImageLabel = new JLabel();
		itemImageLabel.setIcon(new ImageIcon(itemImage));
		itemImageLabel.setBounds(20,20,100,100);
		add(itemImageLabel);
		
		price = new JLabel("Price: "+item.getGoldPrice()+"g");
		price.setForeground(Color.black);
		price.setFont(new Font("",Font.BOLD,30));
		price.setBounds(80,20,300,40);
		add(price);
		
		description = new JTextArea();
		description.addMouseListener(new MouseAdapter(){

			public void mouseClicked(MouseEvent arg0){
				TradeItemBox.this.getMouseListeners()[0].mouseClicked(arg0);
			}
		});
		description.setOpaque(false);
		description.setForeground(Color.BLACK);
		description.setFont(new Font("",Font.BOLD,15));
		description.setText(item.getDescription());
		description.setEditable(false);
		description.setBounds(80, 80, 300, 40);
		description.setLineWrap(true);
		description.setSize(190, 200);
		description.setFocusable(false);
		add(description);
		
		addMouseListener(new MouseAdapter(){
			/*
			 * (non-Javadoc)
			 * @see java.awt.event.MouseAdapter#mouseClicked(java.awt.event.MouseEvent)
			 */
			public void mouseClicked(MouseEvent arg0){
				TradeItemBox.this.parent.buyItem(TradeItemBox.this.item);
			}
		});
	}
	
	

}
