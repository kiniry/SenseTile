package ie.ucd.sensetile.eia.util.printer;

import ie.ucd.sensetile.eia.data.CompositeDataPacket;

public class CompositeDataPacketPrinter {

	
	public CompositeDataPacketPrinter() {
		
	}
	
	public String printPacket(CompositeDataPacket p, boolean includeHeader) {
		StringBuffer sb = new StringBuffer();
		if (includeHeader) {
			sb.append(p.toString());
		}
			
		int [] pData = p.getPrimaryChannelData();
		int sChannels = p.getSecondaryChannelCount();
		
		for (int i=0; i<pData.length; i++) {
			sb.append("[");
			sb.append(pData[i]);
			sb.append(",");
			for (int j=0; j<sChannels; j++) {
				int secIndex = p.findSecondaryIndexForPrimaryIndex(j, i);
				if (secIndex < 0) {
					sb.append(" ");
				} else {
					sb.append(p.getSecondaryChannel(j)[secIndex]);
				}
				sb.append(",");
			}
			sb.deleteCharAt(sb.length()-1);
			sb.append("]");
			sb.append("\n");
		}

		return sb.toString();
	}
}
