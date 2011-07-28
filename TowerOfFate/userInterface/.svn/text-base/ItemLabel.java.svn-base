package userInterface;


import gameworld.Backpack;
import gameworld.Keyring;
import gameworld.UsableItem;
import gameworld.Item;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.border.BevelBorder;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
/**
 * Class to represent Item Label
 * @author Michael Treacher
 *
 */
public class ItemLabel extends JLabel {
	
	private BufferedImage emptyCell;
	private Item item;
	private BufferedImage cell;
	private int index;
	private ItemPanel parent;
	/**
	 * Constructs an Item label
	 */
	public ItemLabel(final int index,final ItemPanel parent){
		this.index = index;
		this.parent = parent;
		try{
			// loads empty cell image
			emptyCell = ImageIO.read(new File("TowerOfFate" + File.separator + "uiImages"+File.separator+"bagslot.jpg"));
		}catch(Exception e){System.out.println(e);}
		// Will need to implement mouse listener here
		addMouseListener(new MouseListener(){
			/*
			 * (non-Javadoc)
			 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
			 */
			public void mouseClicked(MouseEvent e) {
				// no item no need to return pop up
				if(item == null){return;}
				// check if its right click
				if(e.isMetaDown()){
					JPopupMenu optionMenu = createPopupMenu();
					optionMenu.show(e.getComponent(), e.getX(), e.getY());
				}
				else {
					if(item instanceof UsableItem){
						parent.getRoot().getClientConnection().sendOption("Use",index,false);
					}
				}
			}
			/*
			 * (non-Javadoc)
			 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
			 */
			public void mouseEntered(MouseEvent e) {}
			/*
			 * (non-Javadoc)
			 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
			 */
			public void mouseExited(MouseEvent e) {}
			/*
			 * (non-Javadoc)
			 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
			 */
			public void mousePressed(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}
			
		});
		
	}
	/**
	 * Creates a popup menu for an item
	 * @return
	 */
	public JPopupMenu createPopupMenu(){
		String[] options = null;
		if(item.getContainer() instanceof Backpack){
			options = ((Backpack)item.getContainer()).itemOptions(index);
		}
		else {
			options = ((Keyring)item.getContainer()).keyOptions(index);
		}
		JPopupMenu optionMenu = new JPopupMenu();
		for(String option : options){
			JMenuItem optionMenuItem = new JMenuItem(option);
			optionMenuItem.addActionListener(new ActionListener(){

				public void actionPerformed(ActionEvent arg0) {
					String[] splitString = arg0.getActionCommand().split(" ");
					String command = splitString[0];
					boolean isKey = item.getClass().getName().substring(10).equals("Key");
					parent.getRoot().getClientConnection().sendOption(command,index,isKey);
				}
				
			});
			optionMenu.add(optionMenuItem);
			optionMenu.setBorder(new BevelBorder(BevelBorder.RAISED));
			optionMenu.addPopupMenuListener(new PopupMenuListener(){

				/*
				 * (non-Javadoc)
				 * @see javax.swing.event.PopupMenuListener#popupMenuCanceled(javax.swing.event.PopupMenuEvent)
				 */
				public void popupMenuCanceled(PopupMenuEvent arg0) {
					ItemLabel.this.parent.getParent().repaint();
				}

				/*
				 * (non-Javadoc)
				 * @see javax.swing.event.PopupMenuListener#popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent)
				 */
				public void popupMenuWillBecomeInvisible(PopupMenuEvent arg0) {
					ItemLabel.this.parent.getParent().repaint();
				}

				/*
				 * (non-Javadoc)
				 * @see javax.swing.event.PopupMenuListener#popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent)
				 */
				public void popupMenuWillBecomeVisible(PopupMenuEvent arg0) {}
				
			});

		}
		return optionMenu;
	}
	
	/*
	 * (non-Javadoc)
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	public void paintComponent(Graphics g){
		if(item != null){
			try{
				
				cell = ImageIO.read(new File("TowerOfFate" + File.separator + "rendererImages"+File.separator+item.getClass().getName().substring(10)+".png"));
			}catch(Exception e){System.out.println("File error");}
			if(item.getClass().getName().substring(10).equals("Key")){
				g.drawImage(emptyCell.getScaledInstance(getWidth(),getHeight(),Image.SCALE_SMOOTH),0,0 ,null);
				g.drawImage(cell.getScaledInstance(getWidth(),getHeight()/2,Image.SCALE_SMOOTH), 0, getHeight()/4, null);
			}
			else {
				g.drawImage(emptyCell.getScaledInstance(getWidth(),getHeight(),Image.SCALE_SMOOTH),0,0 ,null);
				g.drawImage(cell.getScaledInstance(getWidth(),getHeight(),Image.SCALE_SMOOTH), 0, 0, null);
			}
		}
		else {
			g.drawImage(emptyCell.getScaledInstance(getWidth(),getHeight(),Image.SCALE_SMOOTH),0,0 ,null);
		}
	}
	
	
	/**
	 * Sets the item in the label
	 * @param item
	 */
	public void setItem(Item item){
		this.item = item;
		repaint();
	}
	/**
	 * Gets the item object
	 * @return
	 */
	public Item getItem(){
		return item;
	}

}
