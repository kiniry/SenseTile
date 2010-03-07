package ie.ucd.sensetile.eia.component.history;

public class OracleHistoryConfig extends HistoryConfig {
	private String dbDirectory = null;
	private String tableName = null;

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getDbDirectory() {
		return dbDirectory;
	}

	public void setDbDirectory(String dbDirectory) {
		this.dbDirectory = dbDirectory;
	}
	
}
