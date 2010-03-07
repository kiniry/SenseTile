package ie.ucd.sensetile.eia.component.history;

import ie.ucd.sensetile.eia.util.db.DerbyDatabase;

public class DBHistoryConfig extends HistoryConfig {
	private DerbyDatabase db = null;
	
	public DerbyDatabase getDb() {
		return db;
	}

	public void setDb(DerbyDatabase db) {
		this.db = db;
	}
}
