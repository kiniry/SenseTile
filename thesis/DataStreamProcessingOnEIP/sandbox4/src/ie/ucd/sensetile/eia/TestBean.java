package ie.ucd.sensetile.eia;

import ie.ucd.sensetile.eia.data.CompositeDataPacket;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class TestBean implements Processor {

	int counter = 0;
	
	String name = null;
	
	public TestBean(String id) {
		this.name = id;
	}
	
	public void process(Exchange e) {
		go2(e);
		counter++;
	}
	
	public void go1(Exchange e) {
		CompositeDataPacket p  = (CompositeDataPacket)e.getIn().getBody();
		System.out.println("TestBean." + name + ":"  + counter + " " + p.getPrimaryChannelData().length);	
	}
	
	public void go2(Exchange e) {
		if (counter % 100 == 0) {
			CompositeDataPacket p  = (CompositeDataPacket)e.getIn().getBody();
			System.out.println("TestBean." + name + ":"  + counter + " " + p.getPrimaryChannelData().length);
		}	
	}
}
