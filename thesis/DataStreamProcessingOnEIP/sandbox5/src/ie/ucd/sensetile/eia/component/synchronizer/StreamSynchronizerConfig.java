package ie.ucd.sensetile.eia.component.synchronizer;

import ie.ucd.sensetile.eia.component.history.History;

public class StreamSynchronizerConfig {

	private History stream1;
	private History stream2;
	
	public History getStream1() {
		return stream1;
	}
	public void setStream1(History stream1) {
		this.stream1 = stream1;
	}
	public History getStream2() {
		return stream2;
	}
	public void setStream2(History stream2) {
		this.stream2 = stream2;
	}
	
	
	
}
