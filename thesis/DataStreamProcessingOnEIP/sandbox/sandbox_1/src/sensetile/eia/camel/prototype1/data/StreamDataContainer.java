package sensetile.eia.camel.prototype1.data;

import java.io.Serializable;

public class StreamDataContainer implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public short lowCount  = 20;
	public short highCount = 230; 
	public int [] low;
	public int [] high;
	
	public StreamDataContainer() {
		low = new int [lowCount];
		high = new int [highCount];
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("[" + lowCount + "]");
		sb.append("[" + highCount + "]");
		
		sb.append("[");
		for (int i : low) {
			sb.append(i + ",");
		}
		sb.append("]");
		
		sb.append("[");
		for (int i : high) {
			sb.append(i + ",");
		}
		sb.append("]");

		return sb.toString();
	}
}


