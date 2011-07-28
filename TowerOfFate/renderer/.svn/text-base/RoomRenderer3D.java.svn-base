package renderer;

import gameworld.Door;
import gameworld.Dragon;
import gameworld.NPC;
import gameworld.GameObject;
import gameworld.GridLocation;
import gameworld.Item;
import gameworld.Player;
import gameworld.Wall;
import gameworld.Player.Direction;
import gameworld.Room;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
/**
 * Class to represent the Room renderer
 * @author Michael Treacher
 *
 */
public class RoomRenderer3D extends JPanel{
	final static int HEIGHT_ERROR = 38;
	final static int WIDTH_ERROR = 15;
	final int SIDE_WALL_WIDTH = 100;
	final int MAX_HEIGHT = 617;
	final int MAX_WIDTH = 795;
	final int SIDE_WALL_HEIGHT = 300;
	final int FLOOR_SIZE = 6;
	private BufferedImage currentImage;
	private Room currentRoom;
	private Player play;
	
	/**
	 * Constructs a 3D room renderer
	 * @param player
	 * @param room
	 */
	public RoomRenderer3D(Player player,Room room){
		setBounds(5,30,795,617);
		this.currentRoom = room;
		this.play = player;
	}
	/**
	 * Gets the player object
	 * @return
	 */
	public Player getPlayer(){
		return play;
	}
	/**
	 * Sets the players direction via the renderer
	 * @param dir
	 */
	public void setPlayerDirection(Direction dir){
		play.setDirection(dir);
		repaint();
	}
	/*
	 * (non-Javadoc)
	 * @see java.awt.Canvas#update(java.awt.Graphics)
	 */
	public void update(Graphics g){
		paint(g);
	}
	/**
	 * Replaces a grid location
	 * @param grid
	 */
	public void setGrid(GridLocation grid,boolean repaint){
		currentRoom.roomGrid[grid.getRowIndex()][grid.getColIndex()] = grid;
		if(repaint){repaint();}
	}
	/*
	 * (non-Javadoc)
	 * @see java.awt.Canvas#paint(java.awt.Graphics)
	 */
	public void paint(Graphics g){
		g.drawImage(drawRoom(), 0, 0, null);
	}

	/**
	 * Set the current player
	 * @param player
	 */
	public void setPlayer(Player player){
		this.play = player;
		repaint();
	}
	/**
	 * Sets he current room
	 * @param room
	 */
	public void setRoom(Room room){
		this.currentRoom = room;
		repaint();
	}
	/**
	 * Draws the door objects
	 * @param g
	 */
	public void drawDoors(Graphics g){
		GridLocation[][] grid = currentRoom.getRoomView(play.getDirectionFacing());
		Polygon[][] polygons = getFloorPolygons();
		BufferedImage image = null;
		
		
		for(int x = 1; x<FLOOR_SIZE;x++){
			GameObject door = grid[x][0].getObjects().get(0);
			if(door instanceof Door){
				try{
					image = ImageIO.read(new File("TowerOfFate"+File.separator+"rendererImages"+File.separator+"Level-"+play.getLevel()+"-"+door.getClass().getName().substring(10)+".png"));
				}catch(Exception e){System.out.print(e);}
				Polygon doorPolygon = polygons[x-1][0];
				Rectangle doorRectangle=doorPolygon.getBounds();
				int xPoint = doorRectangle.x;
				int yPoint = (doorRectangle.y-image.getHeight());
				g.drawImage(image.getScaledInstance(doorRectangle.width,image.getHeight() , Image.SCALE_SMOOTH),xPoint,yPoint,null);
			
			}
		}
		// left side
		for(int y=1;y<FLOOR_SIZE;y++){
			GameObject door = grid[0][y].getObjects().get(0);
			if(door instanceof Door){
				try{
					image = ImageIO.read(new File("TowerOfFate"+File.separator+"rendererImages"+File.separator+"Level-"+play.getLevel()+"-"+"LeftSide"+door.getClass().getName().substring(10)+".png"));
				}catch(Exception e){System.out.print(e);}
				Polygon doorPolygon = polygons[0][y];
				
				Rectangle doorRectangle=doorPolygon.getBounds();
				int xPoint = doorRectangle.x+20;
				int yPoint = doorRectangle.y - image.getHeight();
				g.drawImage(image,xPoint,yPoint,null);
			}
		}
		// right side
		for(int y=1;y<FLOOR_SIZE;y++){
			GameObject door = grid[7][y].getObjects().get(0);
			if(door instanceof Door){
				try{
					image = ImageIO.read(new File("TowerOfFate"+File.separator+"rendererImages"+File.separator+"Level-"+play.getLevel()+"-"+"RightSide"+door.getClass().getName().substring(10)+".png"));
				}catch(Exception e){System.out.print(e);}
				Polygon doorPolygon = polygons[5][y];
				Rectangle doorRectangle=doorPolygon.getBounds();
				int xPoint = doorRectangle.x + doorRectangle.width-50;
				int yPoint = doorRectangle.y - image.getHeight();
				g.drawImage(image,xPoint,yPoint,null);
			}
		}
	}
	
	/**
	 * Draws the rooms objects
	 * @param g
	 */
	public void drawRoomObjects(Graphics g){
	
		GridLocation[][] grid = currentRoom.getRoomView(play.getDirectionFacing());
		Polygon[][] polygons = getFloorPolygons();
		
		for(int y = 1; y<=FLOOR_SIZE;y++){
			
			for(int x = 1; x<=FLOOR_SIZE;x++){
				
				if(grid[y][x].getObjects().isEmpty()) continue; // if there is no objects no need to draw anything on the polygon
				// has objects in side the grid
				for(GameObject objects : grid[y][x].getObjects()){
					boolean insidePolygon = false; // boolean to test if the item can be drawn with in the polygon
					boolean outsidePolygon = false;
					BufferedImage objectImage = null; // object to store the image
				
					try{
							//load the image
							if(objects.equals(play)){
								objectImage = ImageIO.read(new File("TowerOfFate"+File.separator+"rendererImages"+File.separator+play.UID+"-"+"NORTH"+"-"+objects.getClass().getName().substring(10)+".png"));
							}
							else if(objects instanceof Player){
								int rotateDegrees = Player.rotateDegrees(play.getDirectionFacing());
								Direction otherPlayersDirection = Player.rotateClockWise(rotateDegrees, ((Player)objects).getDirectionFacing());
								objectImage = ImageIO.read(new File("TowerOfFate"+File.separator+"rendererImages"+File.separator+((Player)objects).UID+"-"+otherPlayersDirection+"-"+objects.getClass().getName().substring(10)+".png"));
							
							}
							else if(objects instanceof NPC){
								int rotateDegrees = Player.rotateDegrees(play.getDirectionFacing());
								Direction otherPlayersDirection = Player.rotateClockWise(rotateDegrees, ((NPC)objects).getDirectionFacing());
								objectImage = ImageIO.read(new File("TowerOfFate"+File.separator+"rendererImages"+File.separator+otherPlayersDirection+"-"+objects.getClass().getName().substring(10)+".png"));
							}
							else if(objects instanceof Item){
								objectImage = ImageIO.read(new File("TowerOfFate"+File.separator+"rendererImages"+File.separator+objects.getClass().getName().substring(10)+".png"));
							}
							else{
								objectImage = ImageIO.read(new File("TowerOfFate"+File.separator+"rendererImages"+File.separator+play.getDirectionFacing()+"-"+objects.getClass().getName().substring(10)+".png"));
							}
						}catch(Exception e){System.out.println(e);}	
						
						// gets the polygon that this cell is refering to
						Polygon polygon = polygons[y-1][x-1];
						// get bounding box
						Rectangle polyBox = polygon.getBounds();
						// set the boolean to test if its inside the polygon
						insidePolygon = objectImage.getWidth() < polyBox.getWidth() && objectImage.getHeight() < polyBox.getHeight();
						outsidePolygon = objectImage.getWidth() > polyBox.getWidth() && objectImage.getHeight() > polyBox.getHeight();
						int drawPointX =  polyBox.x + polyBox.width;
						int drawPointY =  polyBox.y + polyBox.height;
						if(insidePolygon){
							// xGap and yGap to draw it nicely inside the polygon
							int yGap = (polyBox.height - objectImage.getHeight())/2;
							int xGap = (polyBox.width - objectImage.getWidth())/2;
							drawPointX -= objectImage.getWidth() + xGap;
							drawPointY -= objectImage.getHeight() + yGap;
							g.drawImage(objectImage, drawPointX, drawPointY, null);
						}
						else if(objects.getClass().getName().substring(10).equals("Bookcase") && ((play.getDirectionFacing() == Direction.EAST)||(play.getDirectionFacing() == Direction.WEST))){
							int xGap = (polyBox.width - objectImage.getWidth())/2;
							int yOverHang = (objectImage.getHeight() - polyBox.height)/2;
							int imgHeight = (int)(objectImage.getHeight()*(polyBox.getHeight()+(yOverHang))*0.0085);
							int imgWidth = (int)(objectImage.getWidth()*((polyBox.getWidth()-(xGap))*0.0085));
							xGap = (polyBox.width - imgWidth)/2;
							yOverHang = (imgHeight - polyBox.height)/2;
							drawPointX -= (polyBox.width - xGap);
							drawPointY -= (polyBox.height + yOverHang + (yOverHang-10));
							g.drawImage(objectImage.getScaledInstance(imgWidth, imgHeight, Image.SCALE_SMOOTH), drawPointX, drawPointY, null);
						}
						// for objects like bookshelfs and sarcophagus
						else if(outsidePolygon){
							int xOverHang = (objectImage.getWidth()-polyBox.width)/2;
							int yGap = (polyBox.height)/2;
							drawPointX -= (objectImage.getWidth() - xOverHang);
							drawPointY -= (objectImage.getHeight() + yGap);
							g.drawImage(objectImage, drawPointX, drawPointY, null);
						}
						else if( objects.getClass().getName().substring(10).equals("Tablet") ||
								objects.getClass().getName().substring(10).equals("GeneralSuppliesVendor") ||
								objects.getClass().getName().substring(10).equals("Engineer") ||objects.getClass().getName().substring(10).equals("KeySmith") ||
								objects.getClass().getName().substring(10).equals("Orb")
								){
							int yGap = (polyBox.height/2);
							int xGap = (polyBox.width - objectImage.getWidth())/2;
							int imgWidth = (int)(objectImage.getWidth()*((polyBox.getWidth()-(xGap))*0.0085));
							xGap = ((polyBox.width - imgWidth)/2);
							drawPointY -= objectImage.getHeight()+yGap;
							drawPointX -= imgWidth+xGap;
							g.drawImage(objectImage.getScaledInstance(imgWidth,objectImage.getHeight(),Image.SCALE_SMOOTH), drawPointX, drawPointY, null);
						}
						// for drawing a player and a book case
						else if(objects.getClass().getName().substring(10).equals("Player") ||
								objects.getClass().getName().substring(10).equals("Engineer") ||
								objects.getClass().getName().substring(10).equals("GeneralSuppliesVendor")){
							int yGap = (polyBox.height/2);
							int xGap = (polyBox.width - objectImage.getWidth())/2;
							int imgHeight = (int)(objectImage.getHeight()*(polyBox.getHeight()+(yGap))*0.0085);
							int imgWidth = (int)(objectImage.getWidth()*((polyBox.getWidth()-(xGap))*0.0085));
							xGap = ((polyBox.width - imgWidth)/2);
							drawPointY -= imgHeight+yGap;
							drawPointX -= imgWidth+xGap;
							g.drawImage(objectImage.getScaledInstance(imgWidth,imgHeight,Image.SCALE_SMOOTH), drawPointX, drawPointY, null);
						}
						// case where the image needs to be drawn across
						else if(objectImage.getWidth() > objectImage.getHeight()){
								// y gap to make it draw nicely
								int yGap = (polyBox.height - objectImage.getHeight())/2;
								int xOverHang = (objectImage.getWidth() - polyBox.width)/2;
								drawPointY -= (polyBox.height + yGap);
								drawPointX -= (polyBox.width + xOverHang);
								g.drawImage(objectImage, drawPointX, drawPointY, null);
							//}
						}
						else if(objectImage.getWidth() < objectImage.getHeight()){
							int xGap = (polyBox.width - objectImage.getWidth())/2;
							int yOverHang = (objectImage.getHeight() - polyBox.height)/2;
							int imgHeight = (int)(objectImage.getHeight()*(polyBox.getHeight()+(yOverHang))*0.0085);
							int imgWidth = (int)(objectImage.getWidth()*((polyBox.getWidth()-(xGap))*0.0085));
							xGap = (polyBox.width - imgWidth)/2;
							yOverHang = (imgHeight - polyBox.height)/2;
							drawPointX -= (polyBox.width - xGap);
							drawPointY -= (polyBox.height + yOverHang);
							g.drawImage(objectImage.getScaledInstance(imgWidth, imgHeight, Image.SCALE_SMOOTH), drawPointX, drawPointY, null);
						}
						
					}
				}
			}
		}
	
	
	/**
	 * Draws the room 
	 * @param g - Graphics component containing the room
	 * @return
	 */
	public BufferedImage drawRoom(){
		currentImage = new BufferedImage(MAX_WIDTH,MAX_HEIGHT,BufferedImage.TYPE_INT_RGB);
		
		Graphics graphics = currentImage.getGraphics();
		
		drawFloor(currentImage);
		
		drawSideWalls(currentImage);
		
		drawWall(currentImage);
		
		drawRoomWallOutline(graphics);
		
		drawDoors(graphics);
		
		drawRoomObjects(graphics);
		
		return currentImage;
	}
	/**
	 * Draws the floor outline
	 * @param g
	 * @return
	 */
	public Graphics drawFloorOutline(Graphics g){
		Polygon[][] polygons = getFloorPolygons();
		g.setColor(Color.BLACK);
		for(int i = 0;i<FLOOR_SIZE;i++){
			for(int j = 0; j<FLOOR_SIZE;j++){
				g.drawPolygon(polygons[j][i]);
			}
		}
		return g;
	}
	/**
	 * Draws the side walls
	 * @param currentImage
	 * @return
	 */
	public BufferedImage drawSideWalls(BufferedImage currentImage){
		BufferedImage sideWall = null;
	
		try{
			sideWall = ImageIO.read(new File("TowerOfFate"+File.separator+"rendererImages"+File.separator+"Level-"+play.getLevel()+"-SideWall.jpg"));
		}catch(Exception e){}
		Polygon leftSideWallPolygon = new Polygon(new int[]{0,SIDE_WALL_WIDTH,SIDE_WALL_WIDTH,0}, 
				  							  new int[]{0,0,SIDE_WALL_HEIGHT,MAX_HEIGHT}, 4);
		for(int y = 0; y < MAX_HEIGHT;y++){
			for(int x = 0; x < SIDE_WALL_WIDTH;x++){
				if(leftSideWallPolygon.contains(new Point(x,y))){
					currentImage.setRGB(x, y, sideWall.getRGB(x, y));
				}
			}
		}
		
		Polygon rightSideWallPolygon = new Polygon(new int[]{MAX_WIDTH,MAX_WIDTH-(SIDE_WALL_WIDTH),MAX_WIDTH-(SIDE_WALL_WIDTH),MAX_WIDTH}, 
												   new int[]{0,0,SIDE_WALL_HEIGHT,MAX_HEIGHT}, 4);
		
		for(int y = 0; y < MAX_HEIGHT;y++){
			for(int  x = MAX_WIDTH - SIDE_WALL_WIDTH, imageX = 0; x < MAX_WIDTH;x++, imageX++){
				if(rightSideWallPolygon.contains(new Point(x,y))){
						currentImage.setRGB(x, y, sideWall.getRGB(imageX, y));
				}
			}
		}
		
		
		return currentImage;
	}
	/**
	 * Draws the walls
	 * @param g - Graphics component containing the walls
	 * @return
	 */
	public BufferedImage drawWall(BufferedImage currentImage){
		BufferedImage wallImage = null;
		try{
				wallImage = ImageIO.read(new File("TowerOfFate"+File.separator+"rendererImages"+File.separator+"Level-"+play.getLevel()+"-Wall.jpg"));
		}
		catch(Exception e){
			System.out.println(e);
		}
		for(int y = 0; y < SIDE_WALL_HEIGHT;y++){
			for(int x = SIDE_WALL_WIDTH ,imageX = 0;x < MAX_WIDTH-SIDE_WALL_WIDTH;x++,imageX++){

					currentImage.setRGB(x, y, wallImage.getRGB(imageX, y));
			}
		}
		return currentImage;
	}
	/**
	 * Draws the floor
	 * @param g - Graphics component containing the floor
	 * @return
	 */
	public BufferedImage drawFloor(BufferedImage currentImage){
		BufferedImage floorTile = null;
		try{
			floorTile = ImageIO.read(new File("TowerOfFate"+File.separator+"rendererImages"+File.separator+"floor.jpg"));
		}catch(Exception e){System.out.println(e);}
		
		Polygon[][] polygons = getFloorPolygons();

		for(int i = 0;i<FLOOR_SIZE;i++){
			for(int j = 0;j<FLOOR_SIZE;j++){
			
				Polygon polygon = polygons[i][j];
				Rectangle polyBox = polygon.getBounds();
			
				for(int y = polyBox.y,imageY = 0; y < polyBox.y + polyBox.height;y++,imageY++){
					if(y < 0 || y >= MAX_HEIGHT)continue; // if its out of bounds dont draw it
					for(int x = polyBox.x ,imageX = 0; x < polyBox.x + polyBox.width; x++,imageX++ ){
						if(x < 0 || x >= MAX_WIDTH)continue; // if its out of bonds dont draw it
					
						if(polygon.contains(new Point(x,y))){
						currentImage.setRGB(x, y,floorTile.getRGB(imageX,imageY));
						}
					}
				}
			}
		}
		return currentImage;
		
	}
	/**
	 * Calculates the floors polygons in relation to the floor size
	 * @return - 2D array of polygons
	 */
	public Polygon[][] getFloorPolygons(){
		Point[][] points = calculateFloorPoints();
		Polygon[][] polygons = new Polygon[FLOOR_SIZE][FLOOR_SIZE];
		for(int x = 0; x < FLOOR_SIZE;x++){
			for(int y = 0; y < FLOOR_SIZE;y++){
				Point topLeft = points[x][y];
				Point topRight = points[x][y+1];
				Point bottomRight = points[x+1][y+1];
				Point bottomLeft = points[x+1][y];
				Polygon poly = new Polygon(new int[]{topLeft.x,topRight.x,bottomRight.x,bottomLeft.x},new int[]{topLeft.y,topRight.y,bottomRight.y,bottomLeft.y},4);
				polygons[y][x] = poly;
			}
		}
		return polygons;
	}
	/**
	 * Calculates all the points on the floor in relation to the floor size
	 * this method is useful for producing the polygons to represent the floor
	 * @return - 2D array of points
	 */
	public Point[][] calculateFloorPoints(){
		// draw the floor outline
		
		double wallLine = Math.hypot(SIDE_WALL_WIDTH,SIDE_WALL_HEIGHT);
		Point[][] points = new Point[FLOOR_SIZE+1][FLOOR_SIZE+1];
		
			for(int j = 0;j<FLOOR_SIZE+1;j++){
				int startXValue = SIDE_WALL_WIDTH - ((SIDE_WALL_WIDTH/FLOOR_SIZE+3)*j);
				int yValue = (int) (SIDE_WALL_HEIGHT +((wallLine/FLOOR_SIZE)*j));
				int endXValue = (MAX_WIDTH)-(SIDE_WALL_WIDTH) + ((SIDE_WALL_WIDTH/FLOOR_SIZE+3)*j);

				for(int i = 0; i < FLOOR_SIZE+ 1; i++){
					// going accross
					int tileWidth = ((endXValue-startXValue)/FLOOR_SIZE);
					int xValue = startXValue + (tileWidth * i);
					points[j][i] = new Point(xValue,yValue);
				}
			}
		
			return points;
		}
	
	
	/**
	 * Draws the rooms wall outline
	 * @param g - Graphics Component containing wall outline
	 * @return
	 */
	public Graphics drawRoomWallOutline(Graphics g){
		
		// draw the front wall outline
		g.setColor(Color.BLACK);
		g.drawRect(SIDE_WALL_WIDTH, 0, MAX_WIDTH-((SIDE_WALL_WIDTH)*2), SIDE_WALL_HEIGHT);
		//outline of left side wall
		g.drawPolygon(new int[]{0,SIDE_WALL_WIDTH,SIDE_WALL_WIDTH,0}, 
				  new int[]{0,0,SIDE_WALL_HEIGHT,MAX_HEIGHT}, 4);
		// Outline of right side wall
		g.drawPolygon(new int[]{MAX_WIDTH,MAX_WIDTH-(SIDE_WALL_WIDTH),MAX_WIDTH-(SIDE_WALL_WIDTH),MAX_WIDTH}, 
				  new int[]{0,0,SIDE_WALL_HEIGHT,MAX_HEIGHT}, 4);
		return g;
	}
	
}




