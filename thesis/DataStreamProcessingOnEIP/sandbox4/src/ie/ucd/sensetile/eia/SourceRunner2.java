package ie.ucd.sensetile.eia;

import ie.ucd.sensetile.eia.component.BufferedAggregator;
import ie.ucd.sensetile.eia.component.demultiplexer2.Demultiplexer2;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.util.jndi.JndiContext;

public class SourceRunner2 extends SourceRunner {
	
	public void runTest() throws Exception{
			Demultiplexer2 demux = new Demultiplexer2();
			
			BufferedAggregator primary = new BufferedAggregator(ctx);
			primary.setPacketSize(8000);
			primary.addEndpoint("direct: mainEndpoint");
			demux.setPrimaryOutput(primary);
			
			BufferedAggregator secondary0 = new BufferedAggregator(ctx);
			secondary0.setPacketSize(8000);
			secondary0.addEndpoint("direct: secondaryEndpoint0");
			demux.setSecondaryOutput(0, secondary0);
			
			JndiContext context = new JndiContext();
			context.bind("demux",demux);
			context.bind("testBean1", new TestBean("TB1"));
			context.bind("testBean2", new TestBean("TB2"));
			ctx.setJndiContext(context);		
			
			ctx.addRoutes(new RouteBuilder() {
			    public void configure() {
			    	from("direct: sendData").to(MINA_ENDPOINT_1);
			    	from(MINA_ENDPOINT_1).processRef("demux");
			    	from("direct: mainEndpoint").processRef("testBean1");
			    	from("direct: secondaryEndpoint0").to(MINA_ENDPOINT_2);
		    	}
			});
			
			start();
			
			sendSampleStreamData(1000, 4000, new short[] {7,328,328});
			
			end();
	}
}
