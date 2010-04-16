package ie.ucd.sensetile.eia.util.buffer;

import ie.ucd.sensetile.eia.data.CompositeDataPacket;
import ie.ucd.sensetile.eia.util.printer.CompositeDataPacketPrinter;

import org.apache.camel.CamelContext;
import org.apache.camel.CamelContextAware;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class SimpleLoggingBean implements Processor, CamelContextAware {

	int processCount = 0;
	CompositeDataPacketPrinter printer = new CompositeDataPacketPrinter();
	
	CamelContext ctx;
	
	@Override
	public void process(Exchange arg0) throws Exception {
		processCount++;
	if (processCount % 1000 == 0) {
			CompositeDataPacket cdp = (CompositeDataPacket)arg0.getIn().getBody();
			System.out.println("SimpleLoggingBean got Message body : " + cdp);
	}
		
		
		//System.out.println("TestBean:" +arg0);
	}

	@Override
	public CamelContext getCamelContext() {
		// TODO Auto-generated method stub
		return ctx ;
	}

	@Override
	public void setCamelContext(CamelContext ctx) {
		// TODO Auto-generated method stub
		this.ctx = ctx;
	}

}