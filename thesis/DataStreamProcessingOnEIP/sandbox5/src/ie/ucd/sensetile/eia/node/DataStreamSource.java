package ie.ucd.sensetile.eia.node;

import ie.ucd.sensetile.eia.data.DataStreamProvider;
import ie.ucd.sensetile.eia.util.buffer.TestBean;

import java.util.List;

import org.apache.camel.builder.RouteBuilder;

public class DataStreamSource extends SensetileNode {
	
	Integer packetsToSend = null;
	Integer primaryChannelSize = null;
	List<Integer> secondaryChannelRates = null;
	
	public void setNodeDefinition(NodeDefinition config) {
		super.setNodeDefinition(config);
	}
	
	public void setupRoutes() throws Exception {
		camelContext.addRoutes(new RouteBuilder() {
			@Override
			public void configure() throws Exception {
				from(nodeConfig.getHomeURI()).process(new TestBean());
			}
		});
	}
	
	public void setPacketsToSend(Integer count) {
		this.packetsToSend = count;
	}
	
	public void setPrimaryChannelSize(Integer size) {
		this.primaryChannelSize = size;
	}
	
	public void setSecondaryChannelRates(List<Integer> list) {
		this.secondaryChannelRates = list;
	}
	
	public void start() {
		sendSampleStreamData();
	}
	
	public void sendSampleStreamData() {
		DataStreamProvider provider = new DataStreamProvider();

		short [] secondaryChannels = new short [secondaryChannelRates.size()];
		for (int i=0; i<secondaryChannels.length; i++) {
			secondaryChannels[i] = secondaryChannelRates.get(i).shortValue();
		}
		
		long j = System.currentTimeMillis();
		for (int i=0; i<packetsToSend; i++) {
			producer.sendBody("direct:sendData", provider.buildSamplePacket(primaryChannelSize, secondaryChannels));
		}
		System.out.println("Sent " + packetsToSend + " packets in " + (System.currentTimeMillis() - j) + " msec");
		System.out.println(provider.buildSamplePacket(primaryChannelSize, secondaryChannels));
	}
}
