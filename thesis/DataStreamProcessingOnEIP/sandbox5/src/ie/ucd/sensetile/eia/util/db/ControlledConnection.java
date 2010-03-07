package ie.ucd.sensetile.eia.util.db;

import java.sql.Connection;

public class ControlledConnection {
	Connection connection;
	Object lock = null;

	public Connection getConnection() {
		return connection;
	}

	public synchronized void lock(Object o) {
		if (o == null) {
			this.lock = o;
		}
	}

	public Object getLock() {
		return lock;
	}
	
	public boolean isLocked() {
		return (lock != null);
	}
}
