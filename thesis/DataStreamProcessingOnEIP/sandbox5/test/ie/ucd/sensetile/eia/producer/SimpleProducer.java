package ie.ucd.sensetile.eia.producer;

import ie.ucd.sensetile.eia.data.CompositeDataPacket;
import ie.ucd.sensetile.eia.data.DataStreamProvider;
import junit.framework.TestCase;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class SimpleProducer extends TestCase {

	private DefaultCamelContext ctx;
	private DataStreamProvider dsp;
	
	
	public void testSinglePacket() {
		
		String endpoint = "direct:sendData";
		CompositeDataPacket packet = dsp.buildSamplePacket(4000, new short[] {10});
		String header = "streamID";
		

		
		ctx.createProducerTemplate().sendBodyAndHeader(endpoint, packet, header, "1" );
		
		ctx.createProducerTemplate().sendBodyAndHeader(endpoint, packet, header, "2" );
	}
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		ctx = new DefaultCamelContext();
		
		try {
			ctx.addRoutes(new RouteBuilder() {
			    public void configure() {
			    	//from("direct: sendData").throttle(1000).timePeriodMillis(1000).to(SINK_URL);
			    	from("direct:sendData").to("mina:tcp://localhost:7104?sync=false&transferExchange=true");
		    	}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		ctx.start();
		
		dsp = new DataStreamProvider();
	}
	
	@Override
	protected void tearDown() throws Exception {
		ctx.stop();
	}
	
}
