package dataStorage;

public class Element {

	private String[] data;
	private String element;
	
	
	/**
	 * Class that represents one line in a XML document
	 * 
	 * @param element
	 */
	public Element(String element){
		data = element.split("<|>");
		this.element = element;
	
	}
	
	/**
	 * returns starting tag
	 * @return
	 */
	public String getTag(){
		return data[1];		
	}
	
	/**
	 * returns data wrapped from tags
	 * 
	 * @return
	 */
	public String getData(){
		if (data.length < 2){
			return getTag();
		}
		
		return data[2];
	}
	
	/**
	 * tells the user if this element is a whol object
	 * or part of one
	 * 
	 * @return
	 */
	public boolean isWholeObject(){
		return data.length == 3;
	}
	

	public String toString(){
		
		String output = "";
		
		for (String s : data)
			output += s + " : ";
		
			return output;
	}
	
	
}
