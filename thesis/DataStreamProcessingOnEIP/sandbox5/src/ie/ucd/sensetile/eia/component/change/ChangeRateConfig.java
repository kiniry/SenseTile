package ie.ucd.sensetile.eia.component.change;

import ie.ucd.sensetile.eia.component.change.strategy.ChangeRateStrategy;
import ie.ucd.sensetile.eia.component.history.History;

public class ChangeRateConfig {
	private History history = null;
	private int produceInterval = 0;
	private ChangeRateStrategy strategy = null;
	
	public int getProduceInterval() {
		return produceInterval;
	}

	public void setProduceInterval(int produproduceIntervalceRate) {
		this.produceInterval = produceInterval;
	}

	public History getHistory() {
		return history;
	}

	public void setHistory(History history) {
		this.history = history;
	}
	
}
