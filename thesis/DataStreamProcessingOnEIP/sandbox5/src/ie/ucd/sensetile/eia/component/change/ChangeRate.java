package ie.ucd.sensetile.eia.component.change;

import ie.ucd.sensetile.eia.data.CompositeDataPacket;

import java.util.List;

import org.apache.camel.CamelContext;
import org.apache.camel.CamelContextAware;
import org.apache.camel.ProducerTemplate;

public class ChangeRate implements CamelContextAware {

	private CamelContext ctx = null;
	ChangeRateConfig config = null;
	
	ProducerTemplate producer = null;
	
	public ChangeRate(ChangeRateConfig cfg) {
		this.config = cfg;
	}

	public void processRateChange() {
		List<CompositeDataPacket> history = config.getHistory().getHistory(config.getInterval());
		int result = config.getStrategy().getValue(history, 0);
		
		CompositeDataPacket cdp = new CompositeDataPacket();
		int [] primaryData = new int[] {result};
		cdp.setPrimaryStream(primaryData);
		getProducer().sendBody(config.getEndpoint(), cdp);
	}
	
	@Override
	public void setCamelContext(CamelContext ctx) {
		this.ctx = ctx;
	}	
	
	@Override
	public CamelContext getCamelContext() {
		return this.ctx;
	}
	
	protected ProducerTemplate getProducer() {
		if (producer == null) {
			this.producer = ctx.createProducerTemplate();
		}
		return this.producer;
	}	
}
