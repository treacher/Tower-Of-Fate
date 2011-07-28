package dataStorage;

import java.util.Stack;
/**
 * Formatter class that can create XML tags and close them
 * 
 * while maintaining an XML format
 * and will return the produced XML as a String
 * @author Sean Anderson
 *
 */
public class XMLFormatter {

	private StringBuilder s;
	private Stack<String> tags; 
	private boolean secondTag = false;

	
	/**
	 * Formatter class that can create XML tags and close them
	 * while maintaining an XML format
	 * and will return the produced XML as a String
	 * 
	 */
	public XMLFormatter(){

		s = new StringBuilder();
		tags = new Stack<String>();
	}

	/**
	 * Will append a tag with the string provided
	 * 
	 * @param tag
	 */
	public void appendTag(String tag){
		if (secondTag){
			s.append("\n<" + tag + ">");
			secondTag = false;
		}
		else {
			s.append("<" + tag + ">");
			secondTag = true;
		}
		
		tags.push(tag);		
	}

	/**
	 * will close the most recent tag
	 * 
	 */
	public void closeTag(){

		s.append("</" + tags.pop() + ">\n");
		secondTag = false;
	}

	/**
	 * appends the data provided
	 * 
	 * @param data
	 */
	public void append(String data){
		s.append(data);
	}
	
	/**
	 * will return a String of the XML produced
	 * 
	 * @return
	 */
	public String getXML(){
		return s.toString();
	}
}
