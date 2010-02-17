package ie.ucd.sensetile.eia;

import ie.ucd.sensetile.eia.component.BufferedAggregator;
import ie.ucd.sensetile.eia.component.demultiplexer2.Demultiplexer2;
import ie.ucd.sensetile.eia.data.DataStreamProvider;

import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.util.jndi.JndiContext;

public class SourceRunner2 {

	public static final String MINA_ENDPOINT_1  = "mina:tcp://localhost:7777?sync=false";
	public static final String MINA_ENDPOINT_2 = "mina:tcp://localhost:7778?sync=false";
	
	private DefaultCamelContext ctx;
	private ProducerTemplate pt = null;	

	private String endMesg = null;
	
	public static void main (String [] args) {
	
		new SourceRunner2().go();
		
	}
	
	public void go() {
		init();
		start();
		sendSampleStreamData();
		end();
	}

	protected void sendSampleStreamData() {
		DataStreamProvider provider = new DataStreamProvider();

		long j = System.currentTimeMillis();
		System.out.println(provider.buildSamplePacket(8000, new short[] {7,328,328}));
		for (int i=0; i<1000; i++) {
			pt.sendBody("direct: sendData", provider.buildSamplePacket(8000, new short[] {7,328,328}));
		}
	
		endMesg = "" + (System.currentTimeMillis()-j);
	}
	
	public void start() {
		try {
			ctx.start();
			while(!ctx.isStarted()) {
				Thread.sleep(50);
			}
			System.out.println("Started");
		} catch (Exception e) {}
	}
	
	public void init() {
		try {
			ctx = new DefaultCamelContext();
			//ctx.setTracing(true);
			configure();
			
			ctx.addRoutes(new RouteBuilder() {
			    public void configure() {
			    	from("direct: sendData").to(MINA_ENDPOINT_1);
			    	from(MINA_ENDPOINT_1).processRef("demux");
			    	from("direct: mainEndpoint").processRef("testBean1");
			    	from("direct: secondaryEndpoint0").to(MINA_ENDPOINT_2);
		    	}
			});
			
			pt = ctx.createProducerTemplate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void configure() throws Exception {
		
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
	}
	
	public void end() {
		try {
			
			
			Thread.sleep(2000);
//			while(true) {
//				Thread.sleep(2000);
//			}
			ctx.stop();
			System.out.println("Ending...");
			System.out.println("End mesg: " + endMesg);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
