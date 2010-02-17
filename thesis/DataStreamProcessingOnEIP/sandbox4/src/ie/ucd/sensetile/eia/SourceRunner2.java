package ie.ucd.sensetile.eia;

import ie.ucd.sensetile.eia.component.BufferedAggregator;
import ie.ucd.sensetile.eia.component.demultiplexer2.Demultiplexer2;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.util.jndi.JndiContext;

public class SourceRunner2 extends SourceRunner {
	
	public void runTest() throws Exception{
			
			
			BufferedAggregator primary = new BufferedAggregator(ctx);
			primary.setPacketSize(4000);
			primary.addEndpoint("direct: mainEndpoint");
			
			BufferedAggregator secondary0 = new BufferedAggregator(ctx);
			secondary0.setPacketSize(1000);
			secondary0.addEndpoint("direct: secondaryEndpoint0");
			
			Demultiplexer2 demux = new Demultiplexer2(primary, secondary0);
			
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
			    	from("direct: secondaryEndpoint0").processRef("testBean2");
		    	}
			});
			
			start();
			
			sendSampleStreamData(11, 1000, new short[] {10});
			
			try {
				while(1 == 1) {
					Thread.sleep(500);
				}
			}catch (Exception e){}
//			end();
	}
}
