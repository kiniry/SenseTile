package sensetile.eia.camel.prototype1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.impl.SerializationDataFormat;
import org.apache.camel.spi.DataFormat;

import sensetile.eia.camel.prototype1.data.StreamDataContainer;

public class SampleSource {

	public static final String SINK_URL = "mina:tcp://localhost:7777?sync=false";
	public static final String SINK_URL2 = "mina:tcp://localhost:7778?sync=false";
	
	Random r = new Random(System.currentTimeMillis());
	
	private DefaultCamelContext ctx;
	
	public SampleSource() {
		ctx = new DefaultCamelContext();
		setupRoutes(ctx);
	}
	
	protected void setupRoutes(DefaultCamelContext ctx) {
		try {
			ctx.addRoutes(new RouteBuilder() {
			    public void configure() {
			    	//from("direct: sendData").throttle(1000).timePeriodMillis(1000).to(SINK_URL);
			    	from("direct: sendData").to(SINK_URL);
		    	}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void run () {
		try {
			ctx.start();
			
			ProducerTemplate pt = ctx.createProducerTemplate();
			long t1 = System.currentTimeMillis();
			for (int i=0; i<100000; i++) {
				pt.requestBody("direct: sendData", getSampleData());	
			}
			System.out.println(System.currentTimeMillis() - t1);
			
			ctx.stop();
		} catch (Exception e){
			e.printStackTrace();
		}	
	}

	public List<StreamDataContainer> getSampleStreamData() {
		List<StreamDataContainer> data = new ArrayList<StreamDataContainer>();
		for (int i=0; i<20000; i++) {
			data.add(getSampleData());
		}
		return data;
	}
	
	public StreamDataContainer getSampleData() {
		StreamDataContainer sample = new StreamDataContainer(250,2250);
		sample.initRandom(r);
		return sample;
	}
	
	public static void main (String [] args){
		new SampleSource().run(); 
	}
}
