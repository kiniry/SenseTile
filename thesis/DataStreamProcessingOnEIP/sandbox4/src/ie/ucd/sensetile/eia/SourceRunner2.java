package ie.ucd.sensetile.eia;

import ie.ucd.sensetile.eia.component.demultiplexer2.Demultiplexer2;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.util.jndi.JndiContext;

public class SourceRunner2 extends SourceRunner {
	
	public void runTest() throws Exception{
			
		
			int [] secChan = new int[] {0};
			int [] secSizes = new int[] {8000};
			Demultiplexer2 demux = new Demultiplexer2(8000, 8000, secChan, secSizes);
			
			JndiContext context = new JndiContext();
			context.bind("demux",demux);
			ctx.setJndiContext(context);		
			
			ctx.addRoutes(new RouteBuilder() {
			    public void configure() {
			    	from("direct: sendData").processRef("demux");
			    	
		    	}
			});
			
			start();
			long l1 = System.currentTimeMillis();
			sendSampleStreamData(1000, 8000, new short[] {2});
			System.out.println("End: "  + (System.currentTimeMillis() - l1));
			try {
				while(1 == 1) {
					Thread.sleep(500);
				}
			}catch (Exception e){}
//			end();
	}
}
