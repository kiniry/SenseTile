package sensetile.eia.camel.prototype1.data;

import java.io.Serializable;
import java.util.Random;

public class StreamDataContainer implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;
	
	public int lowCount  = 200;
	public int highCount = 2300; 
	public int [] low;
	public int [] high;
	
	public StreamDataContainer(int lowCount, int highCount) {
		this.lowCount = lowCount;
		this.highCount = highCount;
		low = new int [lowCount];
		high = new int [highCount];
	}
	
	public void initRandom(Random r) {
		for (int j=0; j<highCount; j++) {
			high[j] = r.nextInt();
		}
		for (int j=0; j<lowCount; j++) {
			low[j] = j;
		}
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


