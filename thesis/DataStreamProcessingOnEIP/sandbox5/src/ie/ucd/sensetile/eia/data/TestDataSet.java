package ie.ucd.sensetile.eia.data;

import java.util.List;

import org.apache.camel.component.dataset.DataSetSupport;

public class TestDataSet extends DataSetSupport {

	DataStreamProvider dsp = new DataStreamProvider();
	
	Integer primaryChannelSize = null;
	List<Integer> secondaryChannelRates = null;
	
	short [] secondaryChannels;


	public Integer getPrimaryChannelSize() {
		return primaryChannelSize;
	}


	public void setPrimaryChannelSize(Integer primaryChannelSize) {
		this.primaryChannelSize = primaryChannelSize;
	}


	public List<Integer> getSecondaryChannelRates() {
		return secondaryChannelRates;
	}


	public void setSecondaryChannelRates(List<Integer> secondaryChannelRates) {
		this.secondaryChannelRates = secondaryChannelRates;
		secondaryChannels = new short [secondaryChannelRates.size()];
		for (int i=0; i<secondaryChannels.length; i++) {
			secondaryChannels[i] = secondaryChannelRates.get(i).shortValue();
		}
	}


	@Override
	protected Object createMessageBody(long index) {
		
		CompositeDataPacket p =  dsp.buildSamplePacket(primaryChannelSize, secondaryChannels);
		
		return p;
	}


}
