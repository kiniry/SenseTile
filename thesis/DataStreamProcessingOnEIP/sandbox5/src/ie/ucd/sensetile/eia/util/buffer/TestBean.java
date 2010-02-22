package ie.ucd.sensetile.eia.util.buffer;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class TestBean implements Processor {

	int processCount = 0;
	
	@Override
	public void process(Exchange arg0) throws Exception {
		processCount++;
		
		if (processCount % 1000 == 0) {
			System.out.println("TestBean:" +processCount);
		}
		
		//System.out.println("TestBean:" +arg0);
	}
	
}
