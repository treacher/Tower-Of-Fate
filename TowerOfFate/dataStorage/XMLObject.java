package dataStorage;

import gameworld.GameObject;
import gameworld.Item;
import gameworld.Player;

import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * class that wraps around an object
 * that has various methods for parsing the object
 * 
 * @author anderssean2
 *
 */
public class XMLObject {

	private Element[] object;
	private String className;
	private boolean isList = false;
	private int index = 0;

	private final String listName = "List";

	/**
	 * Will create a new XMLObject given from an array of elements and a starting index
	 * 
	 * @param elements
	 * @param startIndex
	 */
	public XMLObject(Element[] elements, int startIndex){

		className = elements[startIndex].getTag();


		int length = -1;

		if(className.startsWith(listName)){

			isList = true;			
			className = className.substring(listName.length());
			//remove listName prefix
		}

		//calculate the length of array required
		if (!elements[startIndex].isWholeObject()){
			//loop till we find the closing tag
			for (length = startIndex ; length < elements.length ; length++){
				if (("/" + className).equals(elements[length].getTag()))
					break;					
			}

			object = new Element[length - startIndex];
			//populate the object array
			for (int j = startIndex, i = 0 ; i < object.length; j++, i++ ){
				object[i] = elements[j];
			}
		}
		//if the Object only spans one line
		else {
			object = new Element[]{ elements[startIndex] };
		}

	}

	/**
	 * returns the leading tag
	 * 
	 * @return
	 */
	public String getName(){
		return className;
	}

	/**
	 * returns the whole array of elements
	 * 
	 * @return
	 */
	public Element[] getElements(){

		return object;

	}

	public String toString(){
		String output = "";

		for(Element e : object)
			output += e.toString() + "\n";

		return output;
	}

	/**
	 * tells the user if the object is a list
	 * 
	 * @return
	 */
	public boolean isList(){
		return isList;
	}

	/**
	 * Returns the ArrayList of GameObjects held in the XML data
	 * 
	 * @return
	 */
	public ArrayList<GameObject> getNextGameObjectList(){
		if(isList){
			ArrayList<GameObject> objectsList = new ArrayList<GameObject>();

			for (int i = 0 ; i < object.length - 2; i++){
				//skip index tags
				if(object[i].getTag().startsWith("List") || object[i].getTag().startsWith("index") || object[i].getTag().startsWith("/index")){
					//ignore the line
				}

				else{;
				XMLObject o = new XMLObject(object, i);			
				objectsList.add((GameObject)o.getObject());
				}
			}	
			return objectsList;
		}
		return null;
	}

	/**
	 * Returns the ArrayList of items held in the XML data
	 * 
	 * @return
	 */
	public ArrayList<Item> getNextItemList(){
		if(isList){
			ArrayList<Item> ItemList = new ArrayList<Item>();

			for (int i = 0 ; i < object.length - 2; i++){
				//skip index tags
				if(object[i].getTag().startsWith("List") || object[i].getTag().startsWith("index") || object[i].getTag().startsWith("/index")){
					//ignore the line
				}

				else{;
				XMLObject o = new XMLObject(object, i);			
				ItemList.add((Item)o.getObject());
				}
			}	
			return ItemList;
		}
		return null;
	}
	
	
	


	/**
	 * Method that will return the object created from the XML data
	 * 
	 * @return Object
	 */
	public Object getObject() {
		if (!isList){
			Object output = null;;
			try {
				Class cls = Class.forName(className);

				Method methlist[] = cls.getDeclaredMethods();

				for (Method meth : methlist){
					//System.out.

					if (meth.toString().contains("fromXML")) {
						output = meth.invoke(null, this);
					}					
				}	
				
				
				
				return output;

			} catch (Throwable e) {
				//Something went terribly wrong
				e.printStackTrace();
			}
		}
		return null;
	}
	
	/**
	 * returns the next element without
	 * incrementing the index count
	 * 
	 * @return
	 */
	public Element peek(){
		return object[index + 1];
	}
		

	/**
	 * returns the next element
	 * 
	 * @return
	 */
	public Element getNextElement() {


		index++;
		return object[index];
	}
	/**
	 * returns the next String data
	 * 
	 * @return
	 */
	public String getNextData(){
		index++;
		return object[index].getData();	
	}
	
	/**
	 * returns the next tag
	 * 
	 * @return
	 */
	public String getNextTag(){
		index++;
		return object[index].getTag();	
	}

	/**
	 * 
	 * returns the current index
	 * @return
	 */
	public int getCurrentIndex(){
		return index;
	}
	
	/**
	 * returns the length of the object
	 * 
	 * @return
	 */
	private int getLength(){
		return object.length;
	}

	/**
	 * will parse the next XMLObject and return it
	 * 
	 * @return
	 */
	public XMLObject getNextObject(){
		index++;
		
		XMLObject out = new XMLObject(object, index);
		index += out.getLength();
		
		return out;
	}
}
