package ie.ucd.sensetile.eia;

import ie.ucd.sensetile.eia.component.BufferedAggregator;
import ie.ucd.sensetile.eia.component.demultiplexer.Demultiplexer;
import ie.ucd.sensetile.eia.data.DataStreamProvider;

import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.util.jndi.JndiContext;


public class SourceRunner {

	public static final String MINA_ENDPOINT_1  = "mina:tcp://localhost:7777?sync=false";
	public static final String MINA_ENDPOINT_2 = "mina:tcp://localhost:7778?sync=false";
	
	private DefaultCamelContext ctx;
	private ProducerTemplate pt = null;	

	private String endMesg = null;
	
	public static void main (String [] args) {
	
		new SourceRunner().go();
		
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
		
		for (int i=0; i<10000; i++) {
			pt.sendBody("direct: sendData", provider.buildSamplePacket(4000, new short[] {7,328,328}));
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
			    	from(MINA_ENDPOINT_1).to("bean:demux?method=process");
			    	from("direct: mainEndpoint").stop();
			    	from("direct: secondaryEndpoint0").processRef("bufferedEndpoint1");
			    	from("direct: secondaryEndpoint1").stop();
			    	from("direct: secondaryEndpoint2").stop();
			    	from("direct: bufferedEnpoint1Sink").beanRef("testBean","process");
		    	}
			});
			
			pt = ctx.createProducerTemplate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void configure() throws Exception {
		
		Demultiplexer demux = new Demultiplexer(ctx);
		
		demux.addMainEndpoint("direct: mainEndpoint");
		demux.addSecondaryEndpoint(0, "direct: secondaryEndpoint0");
		demux.addSecondaryEndpoint(1, "direct: secondaryEndpoint1");
		demux.addSecondaryEndpoint(2, "direct: secondaryEndpoint2");
		
		BufferedAggregator ba = new BufferedAggregator(ctx);
		ba.setPacketSize(570);
		ba.addEndpoint("direct: bufferedEnpoint1Sink");
		
		
		JndiContext context = new JndiContext();
		context.bind("demux",demux);
		context.bind("bufferedEndpoint1", ba);
		context.bind("testBean", new TestBean());
		
		ctx.setJndiContext(context);		
	}
	
	public void end() {
		try {
			
			Thread.sleep(2000);
			ctx.stop();
			System.out.println("Ending...");
			System.out.println("End mesg: " + endMesg);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
