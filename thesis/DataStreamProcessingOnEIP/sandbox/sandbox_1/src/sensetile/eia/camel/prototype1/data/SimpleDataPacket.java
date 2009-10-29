package sensetile.eia.camel.prototype1.data;

import java.io.Serializable;

public class SimpleDataPacket implements Serializable{

	private static final long serialVersionUID = 1L;

	int syncCode = -1;
	int [][] payload;
	
	public void setPayload(int [][] payload) {
		this.payload = payload;
	}
	
	public void setSyncCode (Integer syncCode) {
		this.syncCode = syncCode;
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		
		sb.append("[");
		if (payload != null);
		for (int i=0; i<payload.length; i++) {
			if (payload[i] != null) {
				sb.append("P:" + i + "[");
				for (int j=0; j<payload[i].length; j++) {
					sb.append(payload[i][j] + ",");
				}
				sb.append("\n");
			}
		}
		sb.append("]");

		return sb.toString();
	}
}
