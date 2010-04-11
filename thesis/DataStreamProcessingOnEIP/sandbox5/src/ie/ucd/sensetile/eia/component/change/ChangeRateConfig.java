package ie.ucd.sensetile.eia.component.change;

import ie.ucd.sensetile.eia.component.change.strategy.ChangeRateStrategy;
import ie.ucd.sensetile.eia.component.history.History;

public class ChangeRateConfig {
	private History history = null;
	private int interval = 0;
	private ChangeRateStrategy strategy = null;
	private String endpoint = null;
	
	public String getEndpoint() {
		return endpoint;
	}

	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}

	public ChangeRateStrategy getStrategy() {
		return strategy;
	}

	public void setStrategy(ChangeRateStrategy strategy) {
		this.strategy = strategy;
	}

	public int getInterval() {
		return interval;
	}

	public void setInterval(int interval) {
		this.interval = interval;
	}

	public History getHistory() {
		return history;
	}

	public void setHistory(History history) {
		this.history = history;
	}
	
}
