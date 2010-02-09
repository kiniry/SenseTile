package ie.ucd.sensetile.eia;

import ie.ucd.sensetile.eia.data.CompositeDataPacket;

import org.apache.camel.Exchange;

public class TestBean {

	int counter = 0;
	
	public void process(Exchange e) {
		if (counter % 100 == 0) {
			CompositeDataPacket p  = (CompositeDataPacket)e.getIn().getBody();
			System.out.println("TestBean.process():" + counter + " " + p.getPrimaryChannelData().length);
		}
		counter++;
	}
	
}
