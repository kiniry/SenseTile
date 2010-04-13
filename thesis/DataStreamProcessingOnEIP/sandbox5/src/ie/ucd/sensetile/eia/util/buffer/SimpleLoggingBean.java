package ie.ucd.sensetile.eia.util.buffer;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class SimpleLoggingBean implements Processor {

	int processCount = 0;
	
	@Override
	public void process(Exchange arg0) throws Exception {
		System.out.println("SimpleLoggingBean got Message body : " + arg0.getIn().getBody());
		
		//System.out.println("TestBean:" +arg0);
	}

}
