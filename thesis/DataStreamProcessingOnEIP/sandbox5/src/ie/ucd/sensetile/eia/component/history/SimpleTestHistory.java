package ie.ucd.sensetile.eia.component.history;

public class SimpleTestHistory {

	private History history;

	private int from;
	private int to;
	private int window;
	
	
	public int getWindow() {
		return window;
	}

	public void setWindow(int window) {
		this.window = window;
	}

	public int getFrom() {
		return from;
	}

	public void setFrom(int from) {
		this.from = from;
	}

	public int getTo() {
		return to;
	}

	public void setTo(int to) {
		this.to = to;
	}

	public History getHistory() {
		return history;
	}

	public void setHistory(History history) {
		this.history = history;
	}

	public void test() {
		if (history != null) {
			if (window > 0) {
				System.out.println("HISTORY: " + history.getHistory(window).size());
			} else {
				System.out.println("HISTORY: " + history.getHistory(from, to).size());
			}
			System.gc();
		}
	}
	
}
