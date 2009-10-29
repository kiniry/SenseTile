package sensetile.eia.camel.prototype1;

import java.util.Scanner;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.util.jndi.JndiContext;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import sensetile.eia.camel.prototype1.processor.PacketProcessor;
import sensetile.eia.camel.prototype1.processor.SimpleBean;
import sensetile.eia.camel.prototype1.processor.SyncerProcessor;

public class SampleSink {

	public static final String SOURCE_URL  = "mina:tcp://localhost:7777?sync=false";
	public static final String SOURCE_URL2 = "mina:tcp://localhost:7778?sync=false";

	private DefaultCamelContext ctx;
	
	public SampleSink() {
		ctx = new DefaultCamelContext();
		setupRoutes(ctx);

	}
	
	protected void setupRoutes(DefaultCamelContext ctx) {
		try {
			
			JndiContext context = new JndiContext();
			context.bind("syncer", new SyncerProcessor());
			context.bind("lowAggregator", new PacketProcessor(1, PacketProcessor.TYPE.LOW, ctx));
			context.bind("highAggregator", new PacketProcessor(1, PacketProcessor.TYPE.HIGH, ctx));
			context.bind("simpleBean", new SimpleBean());
			
			ctx.setJndiContext(context);

			ctx.addRoutes(new RouteBuilder() {
				public void configure() {
					from(SOURCE_URL).to("bean:syncer?method=timestamp").multicast().to("direct:low", "direct:high");
					from("direct:low").to("bean:lowAggregator?method=process");
					from("direct:high").to("bean:highAggregator?method=process");
					from("direct:sendLow").to("bean:simpleBean?method=process");
					from("direct:sendHigh").to("bean:simpleBean?method=process");
					
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void run() {
		try {
			ctx.start();
			
			
			
			Scanner in = new Scanner(System.in);
			in.nextLine();
			
			SyncerProcessor b = (SyncerProcessor)ctx.getRegistry().lookup("syncer");
			
			System.out.println(b.timestamp1 + ":" + b.timestamp2 + " : " +(b.timestamp2-b.timestamp1));
			ctx.stop();
		} catch (Exception e){
			e.printStackTrace();
		}		
	}
	
	public static void main (String [] args){
		//Logger.getRootLogger().setLevel(Level.FATAL);
		new SampleSink().run();
	}
}
