package ie.ucd.sensetile.eia.component;

public interface DataStreamAggregator {
	public void setPacketSize(int size);
	public void addEndpoint(String endpoint);
	public int handleData(int [] data);
	public void addListener(AggregatorListener listener);
}
