package renderer;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import userInterface.MainFrame;

/**
 * Canvas class to represent the splash canvas
 * @author Micharl Treacher 300160013
 *
 */
public class SplashCanvas extends JPanel {
	
	private BufferedImage splash;
	private BufferedImage splashLogo;
	private BufferedImage hostGame;
	private BufferedImage joinGame;
	private BufferedImage exitGame;
	private BufferedImage selectedHostGame;
	private BufferedImage selectedJoinGame;
	private BufferedImage selectedExitGame;
	private BufferedImage hostGameImage;
	private BufferedImage exitGameImage;
	private BufferedImage joinGameImage;
	/**
	 * Splash canvas to show the title screen and offer options 
	 */
	public SplashCanvas(final MainFrame parent){
		setBounds(5,30,MainFrame.CANVAS_WIDTH-5,MainFrame.CANVAS_HEIGHT);	
		try{
			splash = ImageIO.read(new File("TowerOfFate"+File.separator+"rendererImages"+File.separator+"Tower of fate.jpg"));
			splashLogo = ImageIO.read(new File("TowerOfFate"+File.separator+"rendererImages"+File.separator+"splashLogo.png"));
			selectedHostGame = ImageIO.read(new File("TowerOfFate"+File.separator+"rendererImages"+File.separator+"selectedHostGame.png"));
			selectedJoinGame = ImageIO.read(new File("TowerOfFate"+File.separator+"rendererImages"+File.separator+"selectedJoinGame.png"));
			selectedExitGame = ImageIO.read(new File("TowerOfFate"+File.separator+"rendererImages"+File.separator+"selectedExitGame.png"));
			hostGame = ImageIO.read(new File("TowerOfFate"+File.separator+"rendererImages"+File.separator+"hostGame.png"));
			joinGame = ImageIO.read(new File("TowerOfFate"+File.separator+"rendererImages"+File.separator+"joinGame.png"));
			exitGame = ImageIO.read(new File("TowerOfFate"+File.separator+"rendererImages"+File.separator+"exitGame.png"));
			
		}catch(Exception e){System.out.println(e);}
		
		hostGameImage = hostGame;
		joinGameImage = joinGame;
		exitGameImage = exitGame;
		
		addMouseListener(
				new MouseAdapter(){
			/*
			 * (non-Javadoc)
			 * @see java.awt.event.MouseAdapter#mouseClicked(java.awt.event.MouseEvent)
			 */
			public void mouseClicked(MouseEvent arg0){
				int xValue = arg0.getX();
				int yValue = arg0.getY();
				if((xValue > ((getWidth()/2)-(hostGame.getWidth()/2)) 
						&& xValue < ((getWidth()/2) + (hostGame.getWidth()/2)))
							&&(yValue >(splash.getHeight()-150)-(hostGame.getHeight())+hostGame.getHeight()-20) 
								&& (yValue <((splash.getHeight()-150)-(hostGame.getHeight())+hostGame.getHeight())+hostGame.getHeight()-20)){
					parent.hostServer();
				}
				else if((xValue > ((getWidth()/2)-(joinGame.getWidth()/2)) 
						&& xValue < ((getWidth()/2) + (joinGame.getWidth()/2)))
							&&(yValue >(splash.getHeight()-150)-(joinGame.getHeight())+joinGame.getHeight()) 
								&& (yValue <((splash.getHeight()-150)-(joinGame.getHeight())+joinGame.getHeight())+joinGame.getHeight()+hostGame.getHeight()-20)){
					parent.joinServer();
				}
				else if((xValue > ((getWidth()/2)-(exitGame.getWidth()/2)) 
						&& xValue < ((getWidth()/2) + (exitGame.getWidth()/2)))
							&&(yValue >(splash.getHeight()-150)-(exitGame.getHeight())+exitGame.getHeight()) 
								&& (yValue <((splash.getHeight()-150)-(exitGame.getHeight())+exitGame.getHeight())
										+exitGame.getHeight()+hostGame.getHeight()+ joinGame.getHeight()-20)){
					parent.exit();
				}
			}
		});		
	
	addMouseMotionListener(new MouseMotionListener(){
		boolean selectState = false;
		/*
		 * (non-Javadoc)
		 * @see java.awt.event.MouseAdapter#mouseMoved(java.awt.event.MouseEvent)
		 */
		public void mouseMoved(MouseEvent arg0) {
				int xValue = arg0.getX();
				int yValue = arg0.getY();
				
				if((xValue > ((getWidth()/2)-(hostGame.getWidth()/2)) 
						&& xValue < ((getWidth()/2) + (hostGame.getWidth()/2)))
							&&(yValue >(splash.getHeight()-150)-(hostGame.getHeight())+hostGame.getHeight()) 
								&& (yValue <((splash.getHeight()-150)-(hostGame.getHeight())+hostGame.getHeight())+hostGame.getHeight()-20)){
					selectState = true;
					// change the picture and repaint
					hostGameImage = selectedHostGame;
					joinGameImage = joinGame;
					exitGameImage = exitGame;
					repaint();
				}
				else if((xValue > ((getWidth()/2)-(joinGame.getWidth()/2)) 
						&& xValue < ((getWidth()/2) + (joinGame.getWidth()/2)))
							&&(yValue >(splash.getHeight()-150)-(joinGame.getHeight())+joinGame.getHeight()) 
								&& (yValue <((splash.getHeight()-150)-(joinGame.getHeight())+joinGame.getHeight())+joinGame.getHeight()+hostGame.getHeight()-20)){
					selectState = true;
					// change the picture and repaint
					hostGameImage = hostGame;
					joinGameImage = selectedJoinGame;
					exitGameImage = exitGame;
					repaint();
					
				}
				else if((xValue > ((getWidth()/2)-(exitGame.getWidth()/2)) 
						&& xValue < ((getWidth()/2) + (exitGame.getWidth()/2)))
							&&(yValue >(splash.getHeight()-150)-(exitGame.getHeight())+exitGame.getHeight()) 
								&& (yValue <((splash.getHeight()-150)-(exitGame.getHeight())+exitGame.getHeight())+exitGame.getHeight()+hostGame.getHeight()+ joinGame.getHeight()-20)){
					
					selectState = true;
					// change the picture and repaint
					hostGameImage = hostGame;
					joinGameImage = joinGame;
					exitGameImage = selectedExitGame;
					repaint();
				}
				/* checks if you have something selected if you do then 
				 * you must have just left that area of selection so repaint  
				 * the images to the un selected form
				 */
				else if(selectState){
					hostGameImage = hostGame;
					joinGameImage = joinGame;
					exitGameImage = exitGame;
					selectState = false;
					repaint();
				}
			
			}
		/*
		 * (non-Javadoc)
		 * @see java.awt.event.MouseMotionListener#mouseDragged(java.awt.event.MouseEvent)
		 */
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		});
	
	
	}
	/*
	 * (non-Javadoc)
	 * @see java.awt.Canvas#update(java.awt.Graphics)
	 */
	public void update(Graphics g){
		paint(g);
	}
	/*
	 * (non-Javadoc)
	 * @see java.awt.Canvas#paint(java.awt.Graphics)
	 */
	public void paint(Graphics g){
		BufferedImage offScreen = new BufferedImage(getWidth(),getHeight(),BufferedImage.TYPE_INT_RGB);
		Graphics graphics = offScreen.getGraphics();
		graphics.drawImage(splash.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH), 0, 0, null);
		graphics.drawImage(splashLogo,(getWidth()/2)-(splashLogo.getWidth()/2),(splash.getHeight()/4)-(splashLogo.getHeight()/1),null);
		graphics.drawImage(hostGameImage,(getWidth()/2)-(hostGameImage.getWidth()/2),(splash.getHeight()-150)-(hostGameImage.getHeight())+hostGameImage.getHeight(),null);
		graphics.drawImage(joinGameImage,(getWidth()/2)-(joinGameImage.getWidth()/2),(splash.getHeight()-150)-(joinGameImage.getHeight())+joinGameImage.getHeight() + hostGameImage.getHeight(),null);
		graphics.drawImage(exitGameImage,(getWidth()/2)-(exitGameImage.getWidth()/2),(splash.getHeight()-150)-(exitGameImage.getHeight())+exitGameImage.getHeight() + joinGameImage.getHeight()+hostGameImage.getHeight(),null);
		g.drawImage(offScreen, 0, 0, null);
	}

}
	


