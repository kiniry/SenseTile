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
	
	private DefaultCamelContext ctx;
	
	public SampleSource() {
		ctx = new DefaultCamelContext();
		setupRoutes(ctx);
	}
	
	protected void setupRoutes(DefaultCamelContext ctx) {
		try {
			ctx.addRoutes(new RouteBuilder() {
			    public void configure() {
			    	//from("direct: sendData").throttle(10000).timePeriodMillis(1000).to(SINK_URL);
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
			
			List<StreamDataContainer> data = getSampleStreamData();
			
			ProducerTemplate pt = ctx.createProducerTemplate();
			long t1 = System.currentTimeMillis();
			for (StreamDataContainer packet : data) {
				pt.requestBody("direct: sendData", packet);	
			}
			System.out.println(System.currentTimeMillis() - t1);
			
			ctx.stop();
		} catch (Exception e){
			e.printStackTrace();
		}	
	}
	
	public String buildMessage(int bytes) {
		StringBuffer sb = new StringBuffer();
		
		int size = bytes;
		for (int i=0; i<size; i++){
			sb.append("A");
		}
		
		return sb.toString();
	}
	
	public List<StreamDataContainer> getSampleStreamData() {
		
		Random r = new Random(System.currentTimeMillis());
		
		List<StreamDataContainer> data = new ArrayList<StreamDataContainer>();
		for (int i=0; i<10000; i++) {
			StreamDataContainer sample = new StreamDataContainer();
			for (int j=0; j<sample.highCount; j++) {
				sample.high[j] = r.nextInt();
			}
			for (int j=0; j<sample.lowCount; j++) {
				sample.low[j] = j;
			}
			data.add(sample);
		}
		return data;	
	}
	
	public static void main (String [] args){
		new SampleSource().run(); 
	}
}
