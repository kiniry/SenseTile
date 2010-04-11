package ie.ucd.sensetile.eia.component.change;

import ie.ucd.sensetile.eia.component.history.History;

import org.apache.camel.CamelContext;
import org.apache.camel.CamelContextAware;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class ChangeRate implements Processor, CamelContextAware {

	private CamelContext ctx = null;
	private History history  = null;
	
	public ChangeRate(ChangeRateConfig cfg) {
		this.history = cfg.getHistory();
		
	}
	
	@Override
	public void process(Exchange arg0) throws Exception {
		
	}

	@Override
	public void setCamelContext(CamelContext ctx) {
		this.ctx = ctx;
	}	
	
	@Override
	public CamelContext getCamelContext() {
		return this.ctx;
	}
	
}
