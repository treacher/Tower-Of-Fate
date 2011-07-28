package userInterface;

import gameworld.Item;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.LayoutManager;
import java.io.File;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 * Class to represent the Item Panel
 * @author Michael Treacher
 *
 */
public class ItemPanel extends JPanel{
	// item labels in the grid
	private ItemLabel[] items;
	private MainFrame root;
	/**
	 * Constructs the Item Panel
	 * @param root 
	 */
	public ItemPanel(int rows,int cols, MainFrame root){
		this.root = root;
		// rows by cols 
		GridLayout layout = new GridLayout(rows,cols);
		items = new ItemLabel[rows*cols];
		layout.setHgap(20); 
		layout.setVgap(20);
		setLayout(layout);
		
		// initialise items
		for(int i = 0;i<items.length;i++){
			items[i] = new ItemLabel(i,this);
			add(items[i]);
		}
		
	}
	/**
	 * Resets the items in the panel
	 */
	public void reset(){
		setItems(Arrays.asList(new Item[16]));
	}
	/**
	 * Gets the root of the object
	 * @return
	 */
	public MainFrame getRoot(){
		return root;
	}
	/*
	 * (non-Javadoc)
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	public void paintComponent(Graphics g){/*do nothing then it does not paint ugly white background*/}
	
	/**
	 * Sets the items in the inventory panel.
	 * @param inventory
	 */
	public void setItems(List<Item> inventory){
			for(int i = 0;i < items.length; i++){
				
				if(i<inventory.size()){
					items[i].setItem(inventory.get(i));
				}
				else{
					items[i].setItem(null);
				}
			}
	}

}

