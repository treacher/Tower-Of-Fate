package network;

import java.io.Serializable;

/**
 * This class represents a packet which is a unit of data that is sent across the network.
 * @author kayecalv id: 300155433
 *
 */
public class Packet implements Serializable {

	public final int PROTOCOL; // A unique identifier of what the packet represents.
	public final Object object; // the data.
	
	/**
	 * Construct a new packet object. A packet contains a unique identifier of what the packet represents and some data.
	 * @param proto
	 * @param object
	 */
	public Packet(int proto , Object object){
		
		PROTOCOL = proto;
		this.object = object;
		
	}
	
}
