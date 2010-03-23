package ie.ucd.sensetile.eia.util.buffer;

public interface Buffer {

	public abstract int writeData(int sample);

	public abstract int getSize();

	public abstract int[] getData();

	public abstract void reset();

	public abstract Integer getSampleIndex();

}